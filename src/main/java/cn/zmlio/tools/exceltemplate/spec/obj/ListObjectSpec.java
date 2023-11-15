package cn.zmlio.tools.exceltemplate.spec.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListObjectSpec extends ObjectSpec {
    private ObjectSpec object;
}
