package cn.zmlio.tools.exceltemplate.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TitleSpec {
    public String content;
    private int row;
    private int column;
}
