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
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<template xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <object xsi:type=\"complexObjectSpec\" attribute=\"test\" javaType=\"cn.zmlio.tools.exceltemplate.ZhiRenGuangDa\">\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"查询日期\">\n" +
                "            <spec xsi:type=\"elementSpec\" name=\"查询日期\" row=\"2\" col=\"1\" valueType=\"string\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"查询开始日期\">\n" +
                "            <spec xsi:type=\"elementSpec\" name=\"查询开始日期\" row=\"3\" col=\"1\" valueType=\"string\"/>\n" +
                "        </field>\n" +
                "        <field xsi:type=\"basicObjectSpec\" attribute=\"查询结束日期\">\n" +
                "            <spec xsi:type=\"elementSpec\" name=\"查询结束日期\" row=\"3\" col=\"1\" valueType=\"string\"/>\n" +
                "        </field>\n" +
                "\n" +
                "        <field xsi:type=\"listObjectSpec\" startRow=\"16\" endingCondition=\"查询结束日期\">\n" +
                "            <object xsi:type=\"complexObjectSpec\" javaType=\"cn.zmlio.tools.exceltemplate.ZhiRenGuangDa.流水\">\n" +
                "                <field xsi:type=\"basicObjectSpec\" attribute=\"交易日期\">\n" +
                "                    <spec xsi:type=\"elementSpec\" name=\"交易日期\" col=\"0\" valueType=\"string\"/>\n" +
                "                </field>\n" +
                "                <field xsi:type=\"basicObjectSpec\" attribute=\"交易时间\">\n" +
                "                    <spec xsi:type=\"elementSpec\" name=\"交易时间\" col=\"1\" valueType=\"string\"/>\n" +
                "                </field>\n" +
                "                <field xsi:type=\"basicObjectSpec\" attribute=\"借方发生额\">\n" +
                "                    <spec xsi:type=\"elementSpec\" name=\"借方发生额\" col=\"2\" valueType=\"string\"/>\n" +
                "                </field>\n" +
                "                <field xsi:type=\"basicObjectSpec\" attribute=\"账户余额\">\n" +
                "                    <spec xsi:type=\"elementSpec\" name=\"账户余额\" col=\"3\" valueType=\"string\"/>\n" +
                "                </field>\n" +
                "                <field xsi:type=\"basicObjectSpec\" attribute=\"对方名称\">\n" +
                "                    <spec xsi:type=\"elementSpec\" name=\"对方名称\" col=\"4\" valueType=\"string\"/>\n" +
                "                </field>\n" +
                "                <field xsi:type=\"basicObjectSpec\" attribute=\"对方账号\">\n" +
                "                    <spec xsi:type=\"elementSpec\" name=\"对方账号\" col=\"5\" valueType=\"string\"/>\n" +
                "                </field>\n" +
                "            </object>\n" +
                "        </field>\n" +
                "    </object>\n" +
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
        listObjectSpec.setEndingCondition(ListObjectSpec.EndingCondition.EMPTY_ROW);
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