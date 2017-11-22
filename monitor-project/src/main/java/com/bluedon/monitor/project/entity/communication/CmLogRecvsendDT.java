package com.bluedon.monitor.project.entity.communication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bluedon.monitor.common.entity.BaseEntity;

/**
 * The persistent class for the cm_log_recv_send_dt database table.
 * 
 */
@Entity
@Table(name="cm_log_recv_send_dt")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CmLogRecvsendDT extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String waterNo;
	private String recdDatetime;
	private String linkIp;
	private String queueName;
	private String serverCode;
	private String logType;
	private String msgType;
	private String msgSequ;
	private String message;
	private String result;
	private String msgTransType;
	private int msgLength;
	
	private int totalCount;
	private int successCount;
	private int failureCount;
	private String dateTime1;
	private String dateTime2;

	public CmLogRecvsendDT() {
	}

	@Id
	@Column(name="WATER_NO")
	public String getWaterNo() {
		return waterNo;
	}
	public void setWaterNo(String waterNo) {
		this.waterNo = waterNo;
	}

	@Column(name="RECD_DATETIME")
	public String getRecdDatetime() {
		return recdDatetime;
	}
	public void setRecdDatetime(String recdDatetime) {
		this.recdDatetime = recdDatetime;
	}

	@Column(name="LINK_IP")
	public String getLinkIp() {
		return linkIp;
	}
	public void setLinkIp(String linkIp) {
		this.linkIp = linkIp;
	}

	@Column(name="QUEUE_NAME")
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Column(name="SERVER_CODE")
	public String getServerCode() {
		return serverCode;
	}
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	@Column(name="LOG_TYPE")
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Column(name="MSG_TYPE")
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Column(name="MSG_SEQU")
	public String getMsgSequ() {
		return msgSequ;
	}
	public void setMsgSequ(String msgSequ) {
		this.msgSequ = msgSequ;
	}

	@Column(name="MESSAGE")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name="RESULT")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	@Column(name="MSG_TRANS_TYPE")
	public String getMsgTransType() {
		return msgTransType;
	}
	public void setMsgTransType(String msgTransType) {
		this.msgTransType = msgTransType;
	}

	@Column(name="MSG_LENGTH")
	public int getMsgLength() {
		return msgLength;
	}
	public void setMsgLength(int msgLength) {
		this.msgLength = msgLength;
	}

	@Transient
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Transient
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	@Transient
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}

	@Transient
	public String getDateTime1() {
		return dateTime1;
	}
	public void setDateTime1(String dateTime1) {
		this.dateTime1 = dateTime1;
	}

	@Transient
	public String getDateTime2() {
		return dateTime2;
	}
	public void setDateTime2(String dateTime2) {
		this.dateTime2 = dateTime2;
	}
	
	

}