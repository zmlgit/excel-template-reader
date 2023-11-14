package cn.zmlio.tools.exceltemplate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ListSpec extends ElementSpec {
    private Integer startRow;
    private Integer endRow;
    private Integer leftColumn;
    private Integer rightColumn;
}
