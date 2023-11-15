package cn.zmlio.tools.exceltemplate.model;

import java.util.List;

public class RowSpec<T> {
    /**
     * units of this row
     */
    private List<Unit> units;
    /**
     * row number
     */
    private Integer row;
    /**
     * row binding type
     */
    private Class<T> type;

    public T read() {
        return null;
    }
}
