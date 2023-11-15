package cn.zmlio.tools.exceltemplate.model;

import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.concurrent.CompletableFuture;

/**
 * 单元格, 用于描述单元格的位置, 以及单元格的值
 */
@Data
public class Unit {
    private Row rowObj = null;
    private String field;
    private int column;

    private CellType cellType;
    private final CompletableFuture<Object> value = CompletableFuture.supplyAsync(() -> {
        try {
            Cell cell = this.rowObj.getCell(this.column);
            this.cellType = cell.getCellType();
            return getObject(cell, cell.getCellType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    });
    private int row;

    public Unit(String field, int column, int row, Row rowObj) {
        this.field = field;
        this.column = column;
        this.row = row;
        this.rowObj = rowObj;
    }

    static Object getObject(Cell cell, CellType valueType) {
        Object value = null;
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

    public Object getValue() {
        try {

            return value.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
