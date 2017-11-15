package com.bluedon.bfw.system.entity;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bluedon.bfw.common.entity.BaseEntity;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TB_COMMON_DEPART database table.
 * 
 */
@Entity
@Table(name="TB_COMMON_DEPART")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbCommonDepart extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date createTime;
	private String departCode;
	private String departName;
	private String description;
	private Long orgId;
	private Long parentId;
	private String phone;
	private Date updateTime;

	public TbCommonDepart() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Column(name="DEPART_CODE")
	public String getDepartCode() {
		return this.departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}


	@Column(name="DEPART_NAME")
	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="ORG_ID")
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}


	@Column(name="PARENT_ID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	//子节点集合
	private List<TbCommonDepart> children;
	@Transient
	public List<TbCommonDepart> getChildren() {
		return children;
	}

	public void setChildren(List<TbCommonDepart> children) {
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