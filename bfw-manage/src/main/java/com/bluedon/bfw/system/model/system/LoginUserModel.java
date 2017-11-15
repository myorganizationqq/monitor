package com.bluedon.bfw.system.model.system;


/**
 * @author  : jason
 * @date    : 2015年3月17日
 * @module  : 后台管理模块 
 * @comment : 系统登录用户实体类
 */
public class LoginUserModel {
	
	private String loginName;//用户名
	private String password;//密码
	private Long orgId;	//机构id
	private Integer userType;//用户类别
	private Integer isValid;//是否有效	
	private int checkType;//检查类型
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getCheckType() {
		return checkType;
	}
	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}
	
	
	
}
