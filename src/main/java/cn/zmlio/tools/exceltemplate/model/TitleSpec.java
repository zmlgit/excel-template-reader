package cn.zmlio.tools.exceltemplate.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TitleSpec {
    public String title;
    private int row;
    private int column;

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
