package cn.zmlio.tools.exceltemplate.read;

import cn.zmlio.tools.exceltemplate.HandleContext;
import cn.zmlio.tools.exceltemplate.spec.Template;
import cn.zmlio.tools.exceltemplate.spec.element.ElementSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.BasicObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ComplexObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ListObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ObjectSpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class DefaultExcelReader implements ExcelReader {
    @Override
    public Object fromExcel(Sheet sheet, Template template) {
        ObjectSpec rootObject = template.getObject();
        HandleContext context = new HandleContext();
        Object object = null;
        if (rootObject instanceof BasicObjectSpec) {
            object = readBasicObject(sheet, context, (BasicObjectSpec) rootObject);
        } else if (rootObject instanceof ComplexObjectSpec) {
            object = readComplexObject(sheet, context, (ComplexObjectSpec) rootObject);
        } else if (rootObject instanceof ListObjectSpec) {
            object = readListObject(sheet, context, (ListObjectSpec) rootObject);
        }
        return object;
    }

    private Object readBasicObject(Sheet sheet, HandleContext context, BasicObjectSpec basicObjectSpec) {
        ElementSpec spec = basicObjectSpec.getSpec();
        log.debug("readObject:{},context:{}", spec, context);
        Integer col = spec.getCol();
        Integer row = spec.getRow();
        // 如果没有指定行列，那么就使用当前行列
        if (col == null) {
            throw new IllegalArgumentException("col is null");
        }
        if (row == null) {
            return readCellValue(sheet.getRow(context.getCurrentRow()).getCell(col));
        } else {
            return readCellValue(sheet.getRow(row).getCell(col));
        }
    }

    private Object readComplexObject(Sheet sheet, HandleContext context, ComplexObjectSpec spec) {
        log.debug("readObject:{},context:{}", spec, context);
        Class<?> javaType = spec.getJavaType();

        try {
            Object instance = javaType.newInstance();
            spec.getFields().forEach(objectSpec -> {
                try {
                    log.debug("reading cell:{}", objectSpec);
                    Object value = null;
                    if (objectSpec instanceof BasicObjectSpec) {
                        value = readBasicObject(sheet, context, (BasicObjectSpec) objectSpec);
                    } else if (objectSpec instanceof ComplexObjectSpec) {
                        value = readComplexObject(sheet, context, (ComplexObjectSpec) objectSpec);
                    } else if (objectSpec instanceof ListObjectSpec) {
                        value = readListObject(sheet, context, (ListObjectSpec) objectSpec);
                    }
                    log.debug("read cell value:{}", objectSpec.getAttribute());
                    Field field = javaType.getDeclaredField(objectSpec.getAttribute());
                    field.setAccessible(true);
                    field.set(instance, value);
                } catch (Exception e) {
                    log.error("readObject error", e);
                }
            });
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object readListObject(Sheet sheet, HandleContext context, ListObjectSpec objectSpec) {
        log.debug("readObject:{},context:{}", objectSpec, context);
        List<Object> list = new LinkedList<>();
        int startRow = objectSpec.getStartRow();
        context.setCurrentRow(startRow);
        context.setStartRow(startRow);
        for (int row1 = startRow; row1 < sheet.getLastRowNum(); row1++) {
            context.setCurrentRow(row1);
            Row row = sheet.getRow(context.getCurrentRow());
            if (row == null) {
                break;
            }
            if (isBlankRow(row)) {
                break;
            }
            ObjectSpec spec = objectSpec.getObject();
            if (spec instanceof BasicObjectSpec) {
                BasicObjectSpec basicObjectSpec = (BasicObjectSpec) spec;
                list.add(readBasicObject(sheet, context, basicObjectSpec));
            } else if (spec instanceof ComplexObjectSpec) {
                ComplexObjectSpec complexObjectSpec = (ComplexObjectSpec) spec;
                list.add(readComplexObject(sheet, context, complexObjectSpec));
            } else if (spec instanceof ListObjectSpec) {
                ListObjectSpec listObjectSpec = (ListObjectSpec) spec;
                list.add(readListObject(sheet, context, listObjectSpec));
            }
        }
        context.setCurrentRow(null);
        return list;
    }

    private Object readCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case ERROR:
                return cell.getErrorCellValue();
            default:
                return null;
        }
    }

    private boolean isBlankRow(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i) != null && row.getCell(i).getStringCellValue() != null && !row.getCell(i).getStringCellValue().isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
