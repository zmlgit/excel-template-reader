package cn.zmlio.tools.exceltemplate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class ListSheetValueReader implements SheetValueReader<ListSourceSpec, List> {
    @Override
    public List readValue(ListSourceSpec sourceSpec, Sheet sheet) {
        if (sourceSpec == null || sheet == null) {
            throw new IllegalArgumentException("sourceSpec and sheet must not be null");
        }
        if (sourceSpec.getColumn() == null) {
            throw new IllegalArgumentException("column must not be null");
        }

        Integer startRow = sourceSpec.getStartRow();

        if (startRow == null) {
            startRow = sheet.getFirstRowNum();
        }

        Integer endRow = sourceSpec.getEndRow();

        if (endRow == null) {
            endRow = sheet.getLastRowNum();
        }
        List result = new ArrayList(endRow - startRow + 1);
        for (int i = startRow; i <= endRow; i++) {
            Object value = null;
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(sourceSpec.getColumn());
                if (cell != null) {
                    value = SheetValueReader.getObject(cell, sourceSpec.getValueType());
                }
            }
            result.add(value);
        }
        return result;
    }

}
