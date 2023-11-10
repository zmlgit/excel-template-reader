package cn.zmlio.tools.exceltemplate;

import org.apache.poi.ss.usermodel.CellType;

public abstract class SourceSpec {
    protected TitleSpec title;

    protected CellType valueType;
    protected String id;

    public TitleSpec getTitle() {
        return title;
    }

    public void setTitle(TitleSpec title) {
        this.title = title;
    }

    public CellType getValueType() {
        return this.valueType;
    }

    public void setValueType(CellType valueType) {
        this.valueType = valueType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
