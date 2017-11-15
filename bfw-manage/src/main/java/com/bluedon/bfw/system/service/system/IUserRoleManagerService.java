/**
* @author :yangdandan 
* @date：2015年4月21日
* @module:
* @comment:
*/
package com.bluedon.bfw.system.service.system;

import java.util.List;

/** 
 * @author :yangdandan 
 * @date：2015年4月21日
 * @module: 系统模块 
 * @comment: 用户角色管理逻辑处理接口
 */
public interface IUserRoleManagerService {

	/**
	 * 新增的用户角色关系
	 * @param roleId
	 * @param userIds
	 */
	boolean saveUserRoles(Long roleId,List<String> userIds);
	
	/**
	 * 删除的用户角色关系
	 * @param roleId
	 * @param userIds
	 */
	boolean deleteUserRoles(Long roleId,List<String> userIds);
}
