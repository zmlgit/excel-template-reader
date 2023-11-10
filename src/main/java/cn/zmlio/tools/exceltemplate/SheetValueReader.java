package cn.zmlio.tools.exceltemplate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;

public interface SheetValueReader<S extends SourceSpec, R> {
    static Object getObject(Cell cell, CellType valueType) {
        Object value=null;
        switch (valueType) {
            case _NONE:
                break;
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case ERROR:
                value = cell.getErrorCellValue();
                break;
        }
        return value;
    }

    R readValue(S sourceSpec, Sheet sheet);
}
