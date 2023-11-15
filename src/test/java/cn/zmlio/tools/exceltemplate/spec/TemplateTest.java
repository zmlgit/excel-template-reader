package cn.zmlio.tools.exceltemplate.spec;

import cn.zmlio.tools.exceltemplate.ZhiRenGuangDa;
import cn.zmlio.tools.exceltemplate.spec.element.ElementSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.BasicObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ComplexObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ListObjectSpec;
import cn.zmlio.tools.exceltemplate.spec.obj.ObjectSpec;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TemplateTest {
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    JAXBContext jaxbContext;

    private static ComplexObjectSpec getComplexObjectSpec() {
        ComplexObjectSpec complexObjectSpec = new ComplexObjectSpec();
        complexObjectSpec.setJavaType(ZhiRenGuangDa.class);
        List<ObjectSpec> objects = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            BasicObjectSpec obj = new BasicObjectSpec();
            obj.setAttribute("test" + i);
            ElementSpec spec = new ElementSpec();
            spec.setRow(1);
            spec.setCol(i);
            obj.setSpec(spec);
            objects.add(obj);
        }
        complexObjectSpec.setFields(objects);

        return complexObjectSpec;
    }

    @BeforeEach
    void setUp() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Template.class);
        marshaller = jaxbContext.createMarshaller();
        // 格式化结果，便于查看
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf8");
//        marshaller.setAdapter(ObjectSpec.class,new ObjectSpecAdapter());
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
        System.out.println(writer);
    }

    @org.junit.jupiter.api.Test
    void readBasicTemplate() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"utf8\" standalone=\"yes\"?>\n" +
                "<template>\n" +
                "    <object attribute=\"test\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "        <spec row=\"1\" col=\"1\"/>\n" +
                "    </object>\n" +
                "</template>\n";
//        String xml = "<?xml version=\"1.0\" encoding=\"utf8\" standalone=\"yes\"?>\n" + "<template xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + "    <object xsi:type=\"basicObjectSpec\" attribute=\"test\" >\n" + "        <spec row=\"1\" col=\"1\"/>\n" + "    </object>\n" + "</template>";
        Source source = new StreamSource(new StringReader(xml));
        JAXBElement<Template> jaxbElement = unmarshaller.unmarshal(source, Template.class);
        Template template = jaxbElement.getValue();
        System.out.println(template);
    }

    @org.junit.jupiter.api.Test
    void createComplexTemplate() throws JAXBException {
        Template template = new Template();

        ComplexObjectSpec complexObjectSpec = getComplexObjectSpec();
        template.setObject(complexObjectSpec);
        StringWriter writer = new StringWriter();
        marshaller.marshal(template, writer);
        System.out.println(writer);
    }

    @org.junit.jupiter.api.Test
    void readComplexTemplate() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"utf8\" standalone=\"yes\"?>\n" +
                "<template>\n" +
                "    <complex javaType=\"cn.zmlio.tools.exceltemplate.ZhiRenGuangDa\">\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test0\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"0\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test1\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"1\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test2\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"2\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test3\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"3\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test4\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"4\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test5\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"5\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test6\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"6\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test7\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"7\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test8\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"8\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"test9\" type=\"basic\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "            <spec row=\"1\" col=\"9\"/>\n" +
                "        </field>\n" +
                "    </complex>\n" +
                "</template>";

        Source source = new StreamSource(new StringReader(xml));
        JAXBElement<Template> jaxbElement = unmarshaller.unmarshal(source, Template.class);
        Template template = jaxbElement.getValue();
        System.out.println(template);
    }

    @org.junit.jupiter.api.Test
    void createListObject() throws JAXBException {
        Template template = new Template();
        ListObjectSpec listObjectSpec = new ListObjectSpec();
        listObjectSpec.setAttribute("test");
        listObjectSpec.setStartRow(1);
        listObjectSpec.setEndingCondition("test");
        ComplexObjectSpec complexObjectSpec = getComplexObjectSpec();
        listObjectSpec.setObject(complexObjectSpec);
        template.setObject(listObjectSpec);
        StringWriter writer = new StringWriter();
        marshaller.marshal(template, writer);
        System.out.println(writer);
    }
@Test
    void readExcelTemplate() throws IOException, JAXBException {
        Source source = new StreamSource(this.getClass().getResourceAsStream("/智人光大.xml"));
        JAXBElement<Template> jaxbElement = unmarshaller.unmarshal(source, Template.class);
        Template template = jaxbElement.getValue();
        System.out.println(template);
    }

    @Test
    void createSchema() throws IOException {
        jaxbContext.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
                File file = new File(suggestedFileName);
                StreamResult result = new StreamResult(file);
                result.setSystemId(file.toURI().toURL().toString());
                return result;
            }
        });
    }
}