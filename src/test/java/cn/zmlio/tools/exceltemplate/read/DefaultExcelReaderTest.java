package cn.zmlio.tools.exceltemplate.read;

import cn.zmlio.tools.exceltemplate.spec.Template;
import com.alibaba.fastjson2.JSON;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

class DefaultExcelReaderTest {

    @Test
    void fromExcel() throws IOException, JAXBException {
        //获取工作簿
//        HSSF book = new XSSFWorkbook(Objects.requireNonNull(this.getClass().getResourceAsStream("/智人光大.xls")));
        HSSFWorkbook book = new HSSFWorkbook(Objects.requireNonNull(this.getClass().getResourceAsStream("/智人光大.xls")));
        JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream xmlStream = this.getClass().getResourceAsStream("/智人光大.xml");
        Template template = unmarshaller.unmarshal(new StreamSource(xmlStream),Template.class).getValue();
        Object result = new DefaultExcelReader().fromExcel(book.getSheetAt(0), template);
        System.out.println(JSON.toJSONString(result));
        //释放资源
        book.close();
    }

}