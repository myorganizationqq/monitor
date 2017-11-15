package com.bluedon.bfw.system.service.system;

import java.util.List;

import com.bluedon.bfw.common.util.PageUtil;
import com.bluedon.bfw.system.entity.TbCommonRole;
import com.bluedon.bfw.system.model.system.RoleManagerModel;

/**
 * @author : yangdandan
 * @date: 2015年4月13日
 * @module: 系统模块 
 * @comment: 角色管理逻辑处理实现接口
 */
public interface IRoleManagerService {

	//查询用户角色列表
	PageUtil getPageList(RoleManagerModel modelParam, PageUtil pageUtil);

	//保存角色信息
	boolean saveRole(TbCommonRole model);

	//修改角色信息
	boolean updateRole(TbCommonRole model);
	
	//删除角色信息
	boolean deleteRole(Long id);
	
	//根据id获取角色信息
	TbCommonRole getRole(Long id);
		
	//获取指定用户的角色列表
	List<TbCommonRole> getRoleListByUserId(Long userId);
	
	//获取指定用户和机构的角色列表
	List<TbCommonRole> getRoleListByUserIdAndOrgId(Long userId,Long orgId);

	//删除角色时判断是否存在关联关系
	boolean existRel(long roleId);
}
