package cn.zmlio.tools.exceltemplate;

import cn.zmlio.tools.exceltemplate.spec.obj.ObjectSpec;
import lombok.Data;
import org.apache.poi.ss.usermodel.Sheet;
@Data
public class HandleContext {
    private Sheet sheet;
    private ObjectSpec parentObjectSpec;
    private Integer startRow;
    private int startColumn;

    private int currentColumn;
    private int endRow;
    private int endColumn;
    private Integer currentRow;



    @Override
    public String toString() {
        return "ReadingContext{" + "startRow=" + startRow + ", startColumn=" + startColumn + ", currentColumn=" + currentColumn + ", endRow=" + endRow + ", endColumn=" + endColumn + ", currentRow=" + currentRow + '}';
    }
}
