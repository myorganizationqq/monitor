package com.bluedon.monitor.system.model.system;

import java.util.Date;

/**
 * @author  : yangdandan
 * @date    : 2015年4月13日
 * @module  : 系统模块 
 * @comment : 角色管理参数传输类
 */
public class RoleManagerModel {
	private Long id;//角色ID
	
	private String rolecode;//角色名称
	
	private String rolename;//角色名称
	
	private Integer roleType;//角色类别
	
	private String description;//角色描述
	
	private Date createTime;//添加时间
	
	private Date updateTime;//编辑时间	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	
}
