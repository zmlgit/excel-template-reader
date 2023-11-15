package cn.zmlio.tools.exceltemplate.spec;

import cn.zmlio.tools.exceltemplate.spec.element.ElementSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.BasicObjectSpec;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

class TemplateTest {
    Marshaller marshaller;
    Unmarshaller unmarshaller;

    @BeforeEach
    void setUp() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);
        marshaller = jaxbContext.createMarshaller();
        // 格式化结果，便于查看
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf8");
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void createTemplate() throws JAXBException {

        Template template = new Template();
        BasicObjectSpec obj = new BasicObjectSpec();
        obj.setAttribute("test");
        ElementSpec spec = new ElementSpec();
        spec.setRow(1);
        spec.setCol(1);
        obj.setSpec(spec);
        template.setObject(obj);

        StringWriter writer = new StringWriter();
        marshaller.marshal(template, writer);

        System.out.println(writer.toString());
    }

    @org.junit.jupiter.api.Test
    void readBasicTemplate() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"utf8\" standalone=\"yes\"?>\n" + "<template>\n" + "    <object xsi:type=\"basicObjectSpec\" attribute=\"test\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + "        <spec row=\"1\" col=\"1\"/>\n" + "    </object>\n" + "</template>";
        Source source = new StreamSource(new StringReader(xml));
        JAXBElement<Template> jaxbElement = unmarshaller.unmarshal(source, Template.class);
        Template template = jaxbElement.getValue();
        System.out.println(template);
    }

    @org.junit.jupiter.api.Test
    void createComplexTemplate() {

    }
}