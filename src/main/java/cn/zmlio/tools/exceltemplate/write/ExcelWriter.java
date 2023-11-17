package cn.zmlio.tools.exceltemplate.write;

import cn.zmlio.tools.exceltemplate.spec.Template;
import org.apache.poi.ss.usermodel.Sheet;

public interface ExcelWriter {

    void write(Object data, Template template, Sheet sheet);
}
