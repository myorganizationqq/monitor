package com.bluedon.monitor.project.util.snmp;

import java.util.List;

/**
 * @author JiangFeng
 * @date 2018/1/24
 * @Description snmp返回结果类
 */
public class SnmpResult {
    private boolean isSuccess;
    private String errorMsg;
    private List<SnmpDetailResult> detailResults;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<SnmpDetailResult> getDetailResults() {
        return detailResults;
    }

    public void setDetailResults(List<SnmpDetailResult> detailResults) {
        this.detailResults = detailResults;
    }
}
