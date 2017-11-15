package com.bluedon.bfw.system.service.system;

import java.util.List;

import com.bluedon.bfw.common.util.PageUtil;
import com.bluedon.bfw.system.entity.TbCommonOrg;
import com.bluedon.bfw.system.model.system.OrgManagerModel;
import com.bluedon.bfw.system.model.util.ComboTree;


/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:组织机构管理逻辑处理实现接口
 */
public interface IOrgManagerService {

	//查询组织机构列表
	PageUtil getPageList(OrgManagerModel modelParam, PageUtil pageUtil);

	//保存组织机构信息
	boolean saveOrg(TbCommonOrg model);

	//修改组织机构信息
	boolean updateOrg(TbCommonOrg model);
	
	//修改组织机构是否启用状态
	boolean updateOrgEnableStatus(String idArr, String status, boolean haveChildren);
	
	//删除组织机构信息
	boolean deleteOrg(Long id);
	
	//根据id获取机构信息
	TbCommonOrg getOrg(Long id);
	
	//获取有效机构的集合
	public List<TbCommonOrg> getEnabledOrgList();
		
	//获取指定节点的子节点集合
	List<TbCommonOrg> getOrgListByParentId(Long parentId);
	
	//根据多个parentId获取指定节点的子节点集合
	List<TbCommonOrg> getOrgListByParentIdArr(String parentIdArr);
	
	//获取根节点的机构集合
	public List<TbCommonOrg> getOrgListForRoot();
	
	//将机构集合转为comboTree的list
	public List<ComboTree> comTree(List<TbCommonOrg> orgList);
	
	//根据多个parentId获取指定节点的子节点集合
	List<TbCommonOrg> getOrgListByParams(OrgManagerModel modelParams);
	
	//获取指定用户的机构列表
	public List<TbCommonOrg> getOrgListByUserId(Long userId);
}
