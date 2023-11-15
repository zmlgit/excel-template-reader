package cn.zmlio.tools.exceltemplate.spec.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexObjectSpec extends ObjectSpec {

    @XmlElement(name = "field")
    private List<ObjectSpec> fields;

    @XmlAttribute
    private Class<?> javaType;
}
