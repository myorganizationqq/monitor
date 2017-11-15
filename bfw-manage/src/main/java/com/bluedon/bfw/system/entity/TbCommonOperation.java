package com.bluedon.bfw.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TB_COMMON_OPERATION database table.
 * 
 */
@Entity
@Table(name="TB_COMMON_OPERATION")
@NamedQuery(name="TbCommonOperation.findAll", query="SELECT t FROM TbCommonOperation t")
public class TbCommonOperation implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Long functionId;
	private String operateCode;
	private String operateName;

	public TbCommonOperation() {
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


	@Column(name="OPERATE_CODE")
	public String getOperateCode() {
		return this.operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}


	@Column(name="OPERATE_NAME")
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

}