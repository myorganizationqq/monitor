package com.bluedon.monitor.system.model.system;

/**
 * @author  : Aaron
 * @date    : 2015年3月11日
 * @module  : 系统模块 
 * @comment : 菜单管理参数传输类
 */
public class MenuModel {
	private Integer id;//菜单ID
	
	private Integer parentid;//父菜单ID
	
	private String menuname;//菜单名称
	
	private Integer menuorder;//菜单顺序
	
	private String menupath;//菜单路径
	
	private String shortname;//菜单短标题
	
	private String description;//菜单描述
	
	private String enable;//是否启用
	
	private Integer systemtype;//菜单所属系统（0：媒资，1：播控，2：三维展示）
	
	private Integer MENULEVEL;//菜单登记 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Integer getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}

	public String getMenupath() {
		return menupath;
	}

	public void setMenupath(String menupath) {
		this.menupath = menupath;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Integer getSystemtype() {
		return systemtype;
	}

	public void setSystemtype(Integer systemtype) {
		this.systemtype = systemtype;
	}

	public Integer getMENULEVEL() {
		return MENULEVEL;
	}

	public void setMENULEVEL(Integer mENULEVEL) {
		MENULEVEL = mENULEVEL;
	}
}
