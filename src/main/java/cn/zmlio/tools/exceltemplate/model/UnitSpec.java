package cn.zmlio.tools.exceltemplate.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UnitSpec {
    TitleSpec title;
    ValueSpec value;
}
