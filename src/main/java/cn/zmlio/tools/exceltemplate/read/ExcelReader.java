package cn.zmlio.tools.exceltemplate.read;

import cn.zmlio.tools.exceltemplate.spec.Template;
import org.apache.poi.ss.usermodel.Sheet;

public interface ExcelReader {
    Object fromExcel(Sheet sheet, Template template);
}
