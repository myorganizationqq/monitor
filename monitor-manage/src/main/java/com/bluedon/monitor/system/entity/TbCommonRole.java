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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bluedon.monitor.common.entity.BaseEntity;

/** 
* @author :yangdandan 
* @date：2015年4月13日
* @module:系统模块 
* @comment:角色管理Entity
*/ 
@Entity
@Table(name = "TB_COMMON_ROLE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbCommonRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;//角色ID
	
	private String rolecode;//角色名称
	
	private String rolename;//角色名称
	
	private Integer roleType;//角色类别
	
	private String description;//角色描述
	
	private Date createTime;//添加时间	
	
	private Date updateTime;//编辑时间
	
	private String createTimeStr;//界面显示：添加时间的字符串形式

	/**
	 * 
	 */
	public TbCommonRole() {
		super();
	}

	/**
	 * @param id
	 * @param rolecode
	 * @param rolename
	 * @param description
	 * @param createTime
	 * @param updateTime
	 */
	public TbCommonRole(Long id, String rolecode, String rolename,
			String description, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.rolecode = rolecode;
		this.rolename = rolename;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable=false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="rolecode",nullable=true)
	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	@Column(name="rolename",nullable=true)
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name="role_type")
	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	@Column(name="description",nullable=true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Transient
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	//角色类别 中文显示
	private String roleTypeStr;
	@Transient
	public String getRoleTypeStr() {
		return roleTypeStr;
	}

	public void setRoleTypeStr(String roleTypeStr) {
		this.roleTypeStr = roleTypeStr;
	}
	
	

}
