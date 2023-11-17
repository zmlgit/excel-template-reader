package cn.zmlio.tools.exceltemplate.write;

import cn.zmlio.tools.exceltemplate.ZhiRenGuangDa;
import cn.zmlio.tools.exceltemplate.spec.Template;
import com.alibaba.fastjson2.JSON;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class DefaultExcelWriterTest {
    String data = "{\"查询开始日期\":\"2023-06-21\",\"查询日期\":\"2023/08/07 15:57:10\",\"查询结束日期\":\"2023-06-21\",\"流水\":[{\"交易日期\":\"2023-06-21\",\"交易时间\":\"17:04:55\",\"对方名称\":\"中智智人信息技术有限公司\",\"对方账号\":\"110925846710702\",\"对方银行\":\"招商银行股份有限公司北京分行营业部\",\"摘要\":\"公司内部账户转账\",\"账户余额\":\"1000.00\",\"贷方发生额\":1000.0},{\"交易日期\":\"2023-07-03\",\"交易时间\":\"13:59:14\",\"借方发生额\":4.0,\"对方名称\":\"网上银行手续费收入\",\"对方账号\":\"35310123580000001\",\"对方银行\":\"北京京广桥支行\",\"摘要\":\"网银电子汇划费\",\"账户余额\":\"996.00\"},{\"交易日期\":\"2023-07-03\",\"交易时间\":\"13:59:14\",\"借方发生额\":900.0,\"对方名称\":\"中智智人信息技术有限公司\",\"对方账号\":\"110925846710702\",\"对方银行\":\"招商银行股份有限公司北京分行营业部\",\"摘要\":\"退款－备注：公司内部账户转账\",\"账户余额\":\"96.00\"}]}";

    ZhiRenGuangDa excelData;
    Template template;

    @BeforeEach
    void before() throws JAXBException {
        excelData = JSON.parseObject(data, ZhiRenGuangDa.class);
        JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream xmlStream = this.getClass().getResourceAsStream("/智人光大.xml");
        template = unmarshaller.unmarshal(new StreamSource(xmlStream), Template.class).getValue();
    }

    @Test
    void write() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        new DefaultExcelWriter().write(excelData, template, sheet);
        workbook.write(new File("out.xlsx"));
        workbook.close();
    }

    @Test
    void writeBasicObject() {
    }

    @Test
    void writeCellValue() {
    }
}