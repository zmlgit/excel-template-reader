package cn.zmlio.tools.exceltemplate.spec.obj;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Abstract class for all object spec.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({BasicObjectSpec.class, ListObjectSpec.class, ComplexObjectSpec.class})
public abstract class ObjectSpec {
    /**
     * The name of the object.
     */
    @XmlAttribute
    protected String attribute;
    @XmlAttribute
    protected String id;
}
