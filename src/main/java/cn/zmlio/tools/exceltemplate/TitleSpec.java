package cn.zmlio.tools.exceltemplate;

public class TitleSpec {
    int row;
    int column;
    String title;

    public TitleSpec(int row, int column, String title) {
        this.row = row;
        this.column = column;
        this.title = title;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
