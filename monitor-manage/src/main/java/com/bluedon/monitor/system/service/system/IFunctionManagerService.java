package com.bluedon.monitor.system.service.system;

import java.util.List;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.system.entity.TbCommonFunction;
import com.bluedon.monitor.system.entity.TbCommonOperation;
import com.bluedon.monitor.system.entity.TbCommonRoleFunction;
import com.bluedon.monitor.system.model.util.ComboTree;


/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:菜单管理逻辑处理实现接口
 */
public interface IFunctionManagerService {

	//查询菜单列表
	PageUtil getPageList(PageUtil pageUtil);

	//保存菜单信息
	boolean saveFunc(TbCommonFunction model);

	//修改菜单信息
	boolean updateFunc(TbCommonFunction model);
	
	//删除菜单信息
	boolean deleteFunc(Long id);
	
	//根据id获取菜单信息
	TbCommonFunction getFunc(Long id);
	
	//获取所有菜单的集合
	public List<TbCommonFunction> getFuncList();
		
	//获取指定节点的子节点集合
	List<TbCommonFunction> getFuncListByParentId(Long parentId);
	
	//获取根节点的菜单集合
	public List<TbCommonFunction> getFuncListForRoot();
	
	//将菜单集合转为comboTree的list
	public List<ComboTree> comTree(List<TbCommonFunction> orgList);

	//获取已选菜单
	List<TbCommonFunction> getSelectedFuncList(Long roleId);
	
	/**
	 * 获取指定角色的所有一级菜单
	 * @param roleIds
	 * @return
	 */
	public List<TbCommonFunction> getFuncRootListByRoleId(Long roleId);
	
	/**
	 * 设置指定一级菜单的所有下级菜单
	 * @param func
	 * @param roleIds
	 * @param recursive
	 * @return
	 */
	public TbCommonFunction getFuncChildrenByRoleId(TbCommonFunction func,Long roleId, boolean recursive);

	/**
	 * 更新角色菜单按钮关系
	 * @param roleId
	 * @param oldFunctions
	 * @param newFunctions
	 * @param selFunctionId
	 * @param operationId
	 */

	void updateRoleFunctionRel(Long roleId, List<Long> oldFunctions,
			List<Long> newFunctions, Long selFunctionId, String operationId);

	/**
	 * 通过菜单id获取按钮
	 * @param functionId
	 * @return
	 */
	List<TbCommonOperation> getOperationByFunId(long functionId);

	/**
	 * 通过id获取按钮
	 * @param operationId
	 * @return
	 */
	TbCommonOperation getOperationById(long operationId);

	/**
	 * 保存按钮
	 * @param oper
	 * @retrun 
	 */
	boolean saveOperation(TbCommonOperation oper);

	/**
	 * 删除按钮
	 * @param operationId
	 * @retrun 
	 */
	boolean deleteOperationById(long operationId);

	/**
	 * 通过角色id菜单id获取按钮
	 * @param roleId
	 * @param functionId
	 * @retrun 
	 */
	List<TbCommonRoleFunction> getSelectedOperation(long roleId,
			long functionId);

	//删除菜单时判断是否存在关联关系
	boolean existRel(long functionId);
	
}
