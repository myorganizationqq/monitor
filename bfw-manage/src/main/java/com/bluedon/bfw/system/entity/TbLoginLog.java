package com.bluedon.bfw.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_LOGIN_LOG database table.
 * 
 */
public class TbLoginLog implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String ip;
	private Date loginTime;
	private Date logoutTime;
	private String operateLoginname;

	public TbLoginLog() {
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}


	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}


	public String getOperateLoginname() {
		return this.operateLoginname;
	}

	public void setOperateLoginname(String operateLoginname) {
		this.operateLoginname = operateLoginname;
	}

}