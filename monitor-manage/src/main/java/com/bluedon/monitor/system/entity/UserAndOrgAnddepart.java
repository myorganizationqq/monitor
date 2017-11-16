package com.bluedon.monitor.system.entity;


public class UserAndOrgAnddepart {
	private Long id;	//id
	private String loginName;//登录名
	private String password;//密码
	private String realName;//真实姓名
	private Long departId;//部门id
	private Long orgId;	//机构id
	private Integer userType;//用户类别
	private Integer isValid;//是否有效
	private String departName;//部门名称
	private String orgName;//机构名称
	private String roleName;//角色名
	private String userTypeStr;//用户类型
	private String isValidStr;//是否可用
	
	public UserAndOrgAnddepart(Long id,String loginName,String password,String realName,Long departId,
			Long orgId,Integer userType,Integer isValid,String departName,String orgName){
			this.id = id;
			this.loginName = loginName;
			this.password =password;
			this.realName = realName;
			this.departId =departId;
			this.orgId =orgId;
			this.userType =userType;
			this.isValid =isValid;
			this.departName =departName;
			this.orgName = orgName;
	}
	public UserAndOrgAnddepart(Long id,String roleName){
		this.id = id;
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public String getRealName() {
		return realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
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
	
	public String getDepartName() {
		return departName;
	}
	
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getIsValidStr() {
		return isValidStr;
	}

	public void setIsValidStr(String isValidStr) {
		this.isValidStr = isValidStr;
	}

	public String getUserTypeStr() {
		return userTypeStr;
	}

	public void setUserTypeStr(String userTypeStr) {
		this.userTypeStr = userTypeStr;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}