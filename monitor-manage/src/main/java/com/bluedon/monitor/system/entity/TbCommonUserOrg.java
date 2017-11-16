package com.bluedon.monitor.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the TB_COMMON_USER_ORG database table.
 * 
 */
@Entity
@Table(name="TB_COMMON_USER_ORG")
@NamedQuery(name="TbCommonUserOrg.findAll", query="SELECT t FROM TbCommonUserOrg t")
public class TbCommonUserOrg implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long departId;
	private Long orgId;
	private Long userId;

	public TbCommonUserOrg() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="DEPART_ID")
	public Long getDepartId() {
		return this.departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}


	@Column(name="ORG_ID")
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}


	@Column(name="USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}