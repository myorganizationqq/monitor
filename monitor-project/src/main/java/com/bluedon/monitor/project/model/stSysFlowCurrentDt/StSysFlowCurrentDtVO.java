package com.bluedon.monitor.project.model.stSysFlowCurrentDt;

import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;

/**
 * @author JiangFeng
 * @date 2017/12/1
 * @Description
 */
public class StSysFlowCurrentDtVO extends StSysFlowCurrentDt{
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
