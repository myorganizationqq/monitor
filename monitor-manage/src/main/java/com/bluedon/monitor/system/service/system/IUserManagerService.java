package com.bluedon.monitor.system.service.system;

import java.util.List;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.system.entity.TbCommonUser;
import com.bluedon.monitor.system.model.system.LoginUserModel;
import com.bluedon.monitor.system.model.system.UserManagerModel;
import com.bluedon.monitor.system.service.IBaseService;


/**
 * @author  : jason
 * @date    : 2015年3月10日
 * @module  : 系统模块 
 * @comment : 用户管理逻辑处理类接口
 */
public interface IUserManagerService extends IBaseService{
	
	/**
	 *  获取分页数据
	 * @param param
	 * 	分页查询参数
	 * @param pageUtil
	 *  分页信息
	 * @return
	 * 	分页后的信息
	 */
	public PageUtil getPageList(UserManagerModel param,PageUtil pageUtil);
	
	//保存用户信息
	boolean saveUser(TbCommonUser model);

	//修改用户信息
	boolean updateUser(TbCommonUser model);
	
	//修改用户是否可用状态
	boolean updateUserValid(String idArr, String status);
	
	//删除用户信息
	boolean deleteUser(Long id);
	
	//根据id获取用户信息
	TbCommonUser getUser(Long id);
	
	//根据登录名获取用户信息
	TbCommonUser getUserByLoginName(String loginname);
		
	
	/**
	 * 根据参数获取用户集合
	 * @param param
	 * @return
	 */
	public List<TbCommonUser> getUserListByParam(LoginUserModel param);
	
	
	/**
	 * 根据参数获取用户集合
	 * @param param
	 * @return
	 */
	public List<TbCommonUser> getUserListByParam(UserManagerModel param);
	
	/**
	 * 根据参数获取指定角色的用户集合
	 * @param param
	 * @param roleId
	 * @return
	 */
	public List<TbCommonUser> getSelectUserListByParam(UserManagerModel param,String roleId);
	
	
}
