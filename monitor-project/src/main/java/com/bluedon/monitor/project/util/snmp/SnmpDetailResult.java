package com.bluedon.monitor.project.util.snmp;

/**
 * @author JiangFeng
 * @date 2018/1/24
 * @Description snmp
 */
public class SnmpDetailResult {
    private String requestOid;
    private String responseOid;
    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getRequestOid() {
        return requestOid;
    }

    public void setRequestOid(String requestOid) {
        this.requestOid = requestOid;
    }

    public String getResponseOid() {
        return responseOid;
    }

    public void setResponseOid(String responseOid) {
        this.responseOid = responseOid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
