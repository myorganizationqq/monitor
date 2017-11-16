package com.bluedon.monitor.system.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bluedon.monitor.common.entity.BaseEntity;

/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:组织机构管理Entity
 */
@Entity
@Table(name="TB_COMMON_ORG")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbCommonOrg  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String orgCode;//机构代码
	
	private String orgName;//机构名称
	
	private String fullName;//机构全名
	
	private String description;//描述
	
	private Long parentId;//父ID
	
	private int disabled;//是否启用 0:禁用1:启用

	public TbCommonOrg() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getDisabled() {
		return this.disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}


	@Column(name="FULL_NAME")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	@Column(name="ORG_CODE")
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	@Column(name="ORG_NAME")
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	@Column(name="PARENT_ID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	//子节点集合
	private List<TbCommonOrg> children;
	@Transient
	public List<TbCommonOrg> getChildren() {
		return children;
	}

	public void setChildren(List<TbCommonOrg> children) {
		this.children = children;
	}
	
	//子节点是否展开
	private String state;
	@Transient
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}