package cn.zmlio.tools.exceltemplate;

import lombok.Data;

import java.util.List;
@Data
public class ZhiRenGuangDa {
    private String 查询日期;

    private String 查询开始日期;

    private String 查询结束日期;

    private String 账号;

    private String 账户名称;

    private String 借贷方向;

    private String 借方笔数;

    private String 借方发生额;

    private String 贷方笔数;

    private String 贷方发生额;

    private List<流水> 流水;


    static class 流水 {
        private String 交易日期;

        private String 交易时间;

        private String 借方发生额;

        private String 贷方发生额;

        private String 账户余额;

        private String 对方名称;

        private String 对方账号;

        private String 摘要;

        private String 对方银行;

        private String 流水号;
    }
}
