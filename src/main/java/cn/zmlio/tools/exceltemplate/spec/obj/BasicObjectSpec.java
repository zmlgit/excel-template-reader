package cn.zmlio.tools.exceltemplate.spec.obj;

import cn.zmlio.tools.exceltemplate.spec.element.ElementSpec;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicObjectSpec extends ObjectSpec {
    private ElementSpec spec;


}
