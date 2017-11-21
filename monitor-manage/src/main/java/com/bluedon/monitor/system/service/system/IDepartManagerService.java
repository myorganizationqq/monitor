package com.bluedon.monitor.system.service.system;

import java.util.List;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.system.entity.TbCommonDepart;
import com.bluedon.monitor.system.model.system.DepartManagerModel;
import com.bluedon.monitor.system.model.util.ComboTree;
import com.bluedon.monitor.system.service.IBaseService;

/**
 * @author  : liangliwen
 * @date    : 2015年4月27日
 * @module  : 系统模块 
 * @comment : 部门管理业务实现接口
 */
public interface IDepartManagerService extends IBaseService {
	//查询组织机构列表
	PageUtil getPageList(DepartManagerModel modelParam, PageUtil pageUtil);

	//保存组织机构信息
	boolean saveDepart(TbCommonDepart model);

	//修改组织机构信息
	boolean updateDepart(TbCommonDepart model);
	
	//删除组织机构信息
	boolean deleteDepart(Long id);
	
	//根据id获取机构信息
	TbCommonDepart getDepart(Long id);
	
	//获取所有机构的集合
	public List<TbCommonDepart> getDepartList();
	
	//根据关联ID获取机构的集合
	public List<TbCommonDepart> getDepartByParams(DepartManagerModel modelParams);
		
	//获取指定节点的子节点集合
	List<TbCommonDepart> getDepartListByParentId(Long parentId);
	
	//获取根节点的机构集合
	public List<TbCommonDepart> getDepartListForRoot();
	
	//将机构集合转为comboTree的list
	public List<ComboTree> comTree(List<TbCommonDepart> orgList);
}
