package com.bluedon.monitor.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Long id;
	private String waterNo;
	private String ftpIp;
	private Date ftpDatetime;
	private String operCode;
	private String fileName;
	private int fileSize;
	private Date startDatetime;
	private int spendDatetime;
	private String result;

	public CmLogFtpDT() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FTP_DATETIME")
	public Date getFtpDatetime() {
		return ftpDatetime;
	}
	public void setFtpDatetime(Date ftpDatetime) {
		this.ftpDatetime = ftpDatetime;
	}

	@Column(name="OPER_CODE")
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	@Column(name="FILE_NAME")
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATETIME")
	public Date getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Date startDatetime) {
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

}