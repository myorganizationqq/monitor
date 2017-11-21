package com.bluedon.monitor.project.entity.stSysFlowCurrentDt;

import com.bluedon.monitor.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author JIANGFENG
 * @DATE  2017/11/17
 */
@Entity
@Table(name = "ST_SYS_FLOW_CURRENT_DT")
public class StSysFlowCurrentDt extends BaseEntity{
    @Id
    @Column(name = "balance_water_no")
    private String balanceWaterNo;
    @Id
    @Column(name = "step")
    private String step;
    @Id
    @Column(name = "step_pre")
    private String stepPre;
    @Id
    @Column(name = "finish_flag")
    private String finishFlag;
    @Id
    @Column(name = "error_code")
    private String errorCode;
    @Id
    @Column(name = "error_message")
    private String errorMessage;
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_time")
    private Date beginTime;
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;
    @Id
    @Column(name = "operator_id")
    private String operatorId;
    @Id
    @Column(name = "remark")
    private String remark;

   // @Column(name = "balance_water_no")
    public String getBalanceWaterNo() {
        return balanceWaterNo;
    }

    public void setBalanceWaterNo(String balanceWaterNo) {
        this.balanceWaterNo = balanceWaterNo == null ? null : balanceWaterNo.trim();
    }
    //@Column(name = "step")
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

   // @Column(name = "STEP_PRE")
    public String getStepPre() {
        return stepPre;
    }

    public void setStepPre(String stepPre) {
        this.stepPre = stepPre == null ? null : stepPre.trim();
    }
    //@Column(name = "finish_flag")
    public String getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag == null ? null : finishFlag.trim();
    }
    //@Column(name = "error_code")
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }
    //@Column(name = "error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }
   // @Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "begin_Time")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    //@Temporal(TemporalType.TIMESTAMP)
   // @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
   // @Column(name = "operator_id")
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }
    //@Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}