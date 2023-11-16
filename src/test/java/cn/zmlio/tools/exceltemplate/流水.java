package cn.zmlio.tools.exceltemplate;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class 流水 {
    private String 交易日期;

    private String 交易时间;

    private Double 借方发生额;

    private Double 贷方发生额;

    private String 账户余额;

    private String 对方名称;

    private String 对方账号;

    private String 摘要;

    private String 对方银行;

    private String 流水号;
}
