package cn.zmlio.tools.exceltemplate.spec;

import cn.zmlio.tools.exceltemplate.spec.obj.ObjectSpec;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "template")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Data
public class Template implements Serializable {
    ObjectSpec object;
}
