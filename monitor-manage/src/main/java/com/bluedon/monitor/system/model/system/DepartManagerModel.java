package com.bluedon.monitor.system.model.system;

import java.util.Date;

/** 
 * @author :liangliwen 
 * @date：2015年4月27日
 * @module:系统模块 
 * @comment:部门管理参数传输类
 */
public class DepartManagerModel {
	
	private Long id;
	
	private String departCode;//部门编号
	
	private String departName;//部门名称
	
	private String description;//描述
	
	private Long orgId;//组织机构ID
	
	private Long parentId;//上级部门ID
	
	private String phone;//联系电话
	
	private Date createTime;//创建时间
	
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
