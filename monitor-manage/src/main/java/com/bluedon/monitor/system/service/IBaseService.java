package com.bluedon.monitor.system.service;


/**
 * @author  : jason
 * @date    : 2015年3月10日
 * @module  : 系统模块 
 * @comment : 逻辑处理基础接口
 */
public interface IBaseService {
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	public boolean add(Object obj);
	
	/**
	 * 删除对象
	 * @param obj
	 * @return
	 */
	public boolean del(Object obj);
	
	/**
	 * 修改对象
	 * @param obj
	 * @return
	 */
	public boolean update(Object obj);
	
}
