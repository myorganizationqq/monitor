package com.bluedon.bfw.system.model.system;


/**
 * @author  : jason
 * @date    : 2015年3月10日
 * @module  : 系统模块 
 * @comment : 用户管理参数传输类
 */
public class UserManagerModel {
	
	private String loginName;//用户名
	private String password;//密码
	private String realName;//真实姓名
	private Long departId;//部门id
	private Long orgId;	//机构id
	private Integer userType;//用户类别
	private Integer isValid;//是否有效
	

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
}
