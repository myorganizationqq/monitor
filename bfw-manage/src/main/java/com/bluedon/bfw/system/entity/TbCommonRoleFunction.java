package com.bluedon.bfw.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TB_COMMON_ROLE_FUNCTION database table.
 * 
 */
@Entity
@Table(name="TB_COMMON_ROLE_FUNCTION")
@NamedQuery(name="TbCommonRoleFunction.findAll", query="SELECT t FROM TbCommonRoleFunction t")
public class TbCommonRoleFunction implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Long functionId;
	private String operationId;
	private Long roleId;

	public TbCommonRoleFunction() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="FUNCTION_ID")
	public Long getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}


	@Column(name="OPERATION_ID")
	public String getOperationId() {
		return this.operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}


	@Column(name="ROLE_ID")
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}