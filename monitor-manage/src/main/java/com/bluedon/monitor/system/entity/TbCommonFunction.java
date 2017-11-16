package com.bluedon.monitor.system.entity;

import java.io.Serializable;
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


/** 
 * @author :yangdandan 
 * @date：2015年4月21日
 * @module:系统模块 
 * @comment:菜单管理Entity
 */
@Entity
@Table(name="TB_COMMON_FUNCTION")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbCommonFunction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String functionName;//菜单名
	
	private String functionUrl;//菜单url
	
	private Integer functionLevel;//菜单级别
	
	private Integer functionOrder;//菜单顺序
	
	private Long parentFunctionId;//菜单父ID

	public TbCommonFunction() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="FUNCTION_LEVEL")
	public Integer getFunctionLevel() {
		return this.functionLevel;
	}

	public void setFunctionLevel(Integer functionLevel) {
		this.functionLevel = functionLevel;
	}

	@Column(name="FUNCTION_NAME")
	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	@Column(name="FUNCTION_ORDER")
	public Integer getFunctionOrder() {
		return this.functionOrder;
	}

	public void setFunctionOrder(Integer functionOrder) {
		this.functionOrder = functionOrder;
	}
	
	@Column(name="FUNCTION_URL")
	public String getFunctionUrl() {
		return this.functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	@Column(name="PARENT_FUNCTION_ID")
	public Long getParentFunctionId() {
		return this.parentFunctionId;
	}

	public void setParentFunctionId(Long parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	//节点状态
	private String state;
	@Transient
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	//子节点
	private List<TbCommonFunction> children;
	@Transient
	public List<TbCommonFunction> getChildren() {
		return children;
	}

	public void setChildren(List<TbCommonFunction> children) {
		this.children = children;
	}
	
	
	
}