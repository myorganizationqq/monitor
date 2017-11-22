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
 * The persistent class for the cm_log_ftp_dt database table.
 * 
 */
@Entity
@Table(name="cm_log_ftp_dt")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CmLogFtpDT extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String waterNo;
	private String ftpIp;
	private String ftpDatetime;
	private String operCode;
	private String fileName;
	private int fileSize;
	private String startDatetime;
	private int spendDatetime;
	private String result;
	
	private int successCount;
	private int failureCount;
	private String dateTime1;
	private String dateTime2;

	public CmLogFtpDT() {
	}

	@Id
	@Column(name="WATER_NO")
	public String getWaterNo() {
		return waterNo;
	}
	public void setWaterNo(String waterNo) {
		this.waterNo = waterNo;
	}

	@Column(name="FTP_IP")
	public String getFtpIp() {
		return ftpIp;
	}
	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	@Column(name="FTP_DATETIME")
	public String getFtpDatetime() {
		return ftpDatetime;
	}
	public void setFtpDatetime(String ftpDatetime) {
		this.ftpDatetime = ftpDatetime;
	}

	@Column(name="OPER_CODE")
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	@Column(name="FILENAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name="FILE_SIZE")
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name="START_DATETIME")
	public String getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}

	@Column(name="SPEND_DATETIME")
	public int getSpendDatetime() {
		return spendDatetime;
	}
	public void setSpendDatetime(int spendDatetime) {
		this.spendDatetime = spendDatetime;
	}

	@Column(name="RESULT")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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