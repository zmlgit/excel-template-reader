package cn.zmlio.tools.exceltemplate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SingleSheetValueReader implements SheetValueReader<SingleSourceSpec, Object> {
    @Override
    public Object readValue(SingleSourceSpec sourceSpec, Sheet sheet) {
        if (sourceSpec == null || sheet == null) {
            throw new IllegalArgumentException("sourceSpec and sheet must not be null");
        }
        if (sourceSpec.getRow() == null || sourceSpec.getColumn() == null) {
            throw new IllegalArgumentException("row and column must not be null");
        }

        Row row = sheet.getRow(sourceSpec.getRow());
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(sourceSpec.getColumn());

        if (cell == null) {
            return null;
        }
        return SheetValueReader.getObject(cell, sourceSpec.getValueType());
    }
}
