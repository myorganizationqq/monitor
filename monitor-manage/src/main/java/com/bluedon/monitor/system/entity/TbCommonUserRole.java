package com.bluedon.monitor.system.entity;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bluedon.monitor.common.entity.BaseEntity;

/** 
 * @author :yangdandan 
 * @date：2015年4月20日
 * @module:系统模块 
 * @comment:用户角色管理Entity
 */
@Entity
@Table(name="TB_COMMON_USER_ROLE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbCommonUserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long roleId;
	private Long userId;

	public TbCommonUserRole() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="ROLE_ID")
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	@Column(name="USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	



}