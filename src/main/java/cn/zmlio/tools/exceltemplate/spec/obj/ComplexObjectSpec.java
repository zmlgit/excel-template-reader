package cn.zmlio.tools.exceltemplate.spec.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComplexObjectSpec extends ObjectSpec {

    private List<ObjectSpec> objects;

    private Class<?> type;
}
