package cn.zmlio.tools.exceltemplate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CellSpec extends ElementSpec {
    private Integer row;
    private Integer column;
    private String value;
    private String formula;
    private String format;
    private String type;
    private String style;
    private String comment;
    private String commentAuthor;
    private String hyperlink;
    private String hidden;
    private String locked;
    private String mergeAcross;
    private String mergeDown;
    private String quotePrefix;
    private String richText;
    private String rowSpan;
    private String colSpan;
    private String wrapText;
    private String verticalAlignment;
    private String horizontalAlignment;
    private String rotation;
    private String indention;
    private String borderLeft;
    private String borderRight;
    private String borderTop;
    private String borderBottom;
    private String borderDiagonal;
    private String borderLeftColor;
    private String borderRightColor;
    private String borderTopColor;
    private String borderBottomColor;
    private String borderDiagonalColor;
    private String borderLeftColorIndex;
    private String borderRightColorIndex;
    private String borderTopColorIndex;
    private String borderBottomColorIndex;
    private String borderDiagonalColorIndex;
    private String borderLeftLineStyle;
    private String borderRightLineStyle;
    private String borderTopLineStyle;
    private String borderBottomLineStyle;
    private String borderDiagonalLineStyle;
    private String borderLeftWeight;
    private String borderRightWeight;
    private String borderTopWeight;
    private String borderBottomWeight;
    private String borderDiagonalWeight;
    private String borderLeftLineCap;
    private String borderRightLineCap;
    private String borderTopLineCap;
    private String borderBottomLineCap;
    private String borderDiagonalLineCap;
    private String borderLeftDashStyle;
    private String borderRightDashStyle;
    private String borderTopDashStyle;
    private String borderBottomDashStyle;
    private String borderDiagonalDashStyle;
    private String borderLeftFill;
    private String borderRightFill;
    private String borderTopFill;
    private String borderBottomFill;
    private String borderDiagonalFill;
    private String borderLeftTheme;
    private String borderRightTheme;
    private String borderTopTheme;

}
