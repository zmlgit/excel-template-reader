package cn.zmlio.tools.exceltemplate.write;

import cn.zmlio.tools.exceltemplate.HandleContext;
import cn.zmlio.tools.exceltemplate.spec.Template;
import cn.zmlio.tools.exceltemplate.spec.element.ElementSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.BasicObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ComplexObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ListObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ObjectSpec;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.Collection;

public class DefaultExcelWriter implements ExcelWriter {
    @Override
    public void write(Object data, Template template, Sheet sheet) {
        ObjectSpec root = template.getObject();
        if (root instanceof BasicObjectSpec) {
            writeBasicObject(data, (BasicObjectSpec) root, sheet, new HandleContext());
        }
        if (root instanceof ComplexObjectSpec) {
            writeComplexObject(data, (ComplexObjectSpec) root, sheet, new HandleContext());
        }
        if (root instanceof ListObjectSpec) {
            writeListObject(data, (ListObjectSpec) root, sheet, new HandleContext());
        }
    }

    private void writeComplexObject(Object data, ComplexObjectSpec root, Sheet sheet, HandleContext handleContext) {
        Class<?> javaType = root.getJavaType();
        boolean instance = javaType.isInstance(data);
        if (!instance) {
            throw new IllegalArgumentException("data is not instance of " + javaType);
        }
        root.getFields().forEach(objectSpec -> {
            try {
                Field field = javaType.getDeclaredField(objectSpec.getAttribute());
                field.setAccessible(true);
                Object o = field.get(data);
                if (objectSpec instanceof BasicObjectSpec) {
                    writeBasicObject(o, (BasicObjectSpec) objectSpec, sheet, handleContext);
                } else if (objectSpec instanceof ComplexObjectSpec) {
                    writeComplexObject(o, (ComplexObjectSpec) objectSpec, sheet, handleContext);
                } else if (objectSpec instanceof ListObjectSpec) {
                    writeListObject(o, (ListObjectSpec) objectSpec, sheet, handleContext);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void writeListObject(Object data, ListObjectSpec objectSpec, Sheet sheet, HandleContext handleContext) {
        handleContext.setStartRow(objectSpec.getStartRow());
        handleContext.setCurrentRow(objectSpec.getStartRow());
        Collection list = (Collection) data;

        ObjectSpec spec = objectSpec.getObject();
        if (spec instanceof BasicObjectSpec) {
            list.forEach(item -> {
                sheet.createRow(handleContext.getCurrentRow());
                writeBasicObject(item, (BasicObjectSpec) spec, sheet, handleContext);
                handleContext.setCurrentRow(handleContext.getCurrentRow() + 1);
            });
        }
        if (spec instanceof ComplexObjectSpec) {
            list.forEach(item -> {
                sheet.createRow(handleContext.getCurrentRow());
                writeComplexObject(item, (ComplexObjectSpec) spec, sheet, handleContext);
                handleContext.setCurrentRow(handleContext.getCurrentRow() + 1);
            });
        }
    }


    void writeBasicObject(Object data, BasicObjectSpec spec, Sheet sheet, HandleContext context) {
        ElementSpec elementSpec = spec.getSpec();
        String name = elementSpec.getName();
        Integer row = elementSpec.getRow() == null ? context.getCurrentRow() : elementSpec.getRow();
        Integer col = elementSpec.getCol();
        if (col == null) {
            throw new IllegalArgumentException("col is null");
        }
        if(sheet.getRow(row)==null){
            sheet.createRow(row);
        }
        if(sheet.getRow(row).getCell(col)==null){
            sheet.getRow(row).createCell(col);
        }

        writeCellValue(data, sheet.getRow(row).getCell(col));
    }

    void writeCellValue(Object data, Cell cell) {
        if (data == null) {
            return;
        }
        if (data instanceof Number) {
            cell.setCellValue(Double.parseDouble(data.toString()));
            return;
        }
        if (data instanceof Boolean) {
            cell.setCellValue(Boolean.parseBoolean(data.toString()));
            return;
        }
        if (data instanceof String) {
            cell.setCellValue(data.toString());
            return;
        }
        if (data instanceof Character) {
            cell.setCellValue(data.toString());
            return;
        }
        cell.setCellValue(data.toString());

    }
}
