package cn.zmlio.tools.exceltemplate.spec.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ListObjectSpec extends ObjectSpec {
    @XmlElement
    private ObjectSpec object;
    @XmlAttribute
    private Integer startRow;
    @XmlAttribute
    private String endingCondition;
}
