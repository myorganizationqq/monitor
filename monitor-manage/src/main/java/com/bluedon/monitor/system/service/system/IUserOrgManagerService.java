package com.bluedon.monitor.system.service.system;

import java.util.Map;

import com.bluedon.monitor.system.entity.TbCommonUserOrg;


/** 
 * @author :yangdandan 
 * @date：2015年5月12日
 * @module:系统模块 
 * @comment:用户组织机构关系管理逻辑处理实现接口
 */
public interface IUserOrgManagerService {

	//获取指定用户和组织的用户组织关系
	TbCommonUserOrg getUserOrg(Map<String,Object> params);
}
