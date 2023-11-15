package cn.zmlio.tools.exceltemplate.spec.element;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementSpec {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer row;
    @XmlAttribute
    private Integer col;
    @XmlAttribute
    private String valueType;
    @XmlAttribute
    private Integer startRow;
    @XmlAttribute
    private String endingCondition;

}
