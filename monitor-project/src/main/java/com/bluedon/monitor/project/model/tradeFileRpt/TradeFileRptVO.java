package com.bluedon.monitor.project.model.tradeFileRpt;

import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author JiangFeng
 * @date 2017/11/14
 */
@Table(name="trade_file_rpt")
@Entity
@DynamicInsert
@DynamicUpdate
public class TradeFileRptVO extends TradeFileRpt {


    private String beginDate;
    private String endDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}
