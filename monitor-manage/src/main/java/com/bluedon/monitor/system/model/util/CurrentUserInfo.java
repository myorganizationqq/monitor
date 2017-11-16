
package com.bluedon.monitor.system.model.util;

import java.util.List;

import com.bluedon.monitor.system.entity.TbCommonFunction;
import com.bluedon.monitor.system.entity.TbCommonOrg;
import com.bluedon.monitor.system.entity.TbCommonRole;
import com.bluedon.monitor.system.entity.TbLoginLog;

/** 
 * @author :yangdandan 
 * @date：2015年4月17日
 * @module:
 * @comment:
 */
public class CurrentUserInfo {
	
	//用户信息
	private Long userId;	//id
	private String loginName;//登录名
	private String realName;//真实姓名	
	private Integer userType;//用户类别	
	
	//用户当前登录的组织机构信息
	private Long orgId;//机构id
	private String orgCode;//机构编码
	private String orgName;//机构名称
	private String orgFullName;//机构全名

	//用户当前登录的机构下部门信息
	private Long departId;//部门id
	private String departCode;//部门编码
	private String departName;//部门名称
	
	//用户当前登录的角色信息
	private Long roleId;//角色id
	private String roleCode;//角色编码
	private String roleName;//角色名称
	private Integer roleType;//角色类别
	
	private List<TbCommonOrg> orgs;//用户的机构集合
	
	private List<TbCommonRole> roles;//用户的角色集合
	
	private List<TbCommonRole> orgRoles;//登录机构的角色集合
	
	private List<TbCommonFunction> menus;//用户的菜单集合
	
	private TbLoginLog loginLog;//登录日志
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgFullName() {
		return orgFullName;
	}

	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public String getDepartCode() {
		return departCode;
	}

	public List<TbCommonOrg> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<TbCommonOrg> orgs) {
		this.orgs = orgs;
	}

	public List<TbCommonRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TbCommonRole> roles) {
		this.roles = roles;
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

	/*public List<Long> getRoles() {
		return roles;
	}

	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}*/

	public List<TbCommonFunction> getMenus() {
		return menus;
	}

	public void setMenus(List<TbCommonFunction> menus) {
		this.menus = menus;
	}

	public TbLoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(TbLoginLog loginLog) {
		this.loginLog = loginLog;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public List<TbCommonRole> getOrgRoles() {
		return orgRoles;
	}

	public void setOrgRoles(List<TbCommonRole> orgRoles) {
		this.orgRoles = orgRoles;
	}	
	
	
	
}
