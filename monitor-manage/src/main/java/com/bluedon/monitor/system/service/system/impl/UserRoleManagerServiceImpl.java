package com.bluedon.monitor.system.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.bluedon.monitor.system.service.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.system.entity.TbCommonUserRole;
import com.bluedon.monitor.system.service.system.IUserRoleManagerService;

/**
 * @author : yangdandan
 * @date: 2015年4月21日
 * @module: 系统模块 
 * @comment: 用户角色管理逻辑处理实现类
 */
@Service("userRoleManagerServiceImpl")
public class UserRoleManagerServiceImpl  extends BaseServiceImpl implements IUserRoleManagerService{
	
	//日志记录对象
	private static final Logger log = Logger.getLogger(UserRoleManagerServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonUserRole> hibernateDao;
	
	
	/**
	 * 新增的用户角色关系
	 * @param roleId
	 * @param userIds
	 */
	@Override
	public boolean saveUserRoles(Long roleId,List<String> userIds){
		for(String userId:userIds){
			TbCommonUserRole ur=this.getUserRole(roleId, Long.parseLong(userId));
			if(ur!=null){
				log.debug("已经存在roleId="+roleId+",userId="+userId+"的用户角色关系，不能重复添加。");
				continue;
			}else{
				TbCommonUserRole userRole=new TbCommonUserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(Long.parseLong(userId));
				this.hibernateDao.save(userRole);
			}
			
		}		
		return true;
	}
	
	/**
	 * 删除的用户角色关系
	 * @param roleId
	 * @param userIds
	 */
	@Override
	public boolean deleteUserRoles(Long roleId,List<String> userIds){
		boolean isOk=true;
		for(String userId:userIds){
			TbCommonUserRole ur=this.getUserRole(roleId, Long.parseLong(userId));
			if(ur!=null){
				this.hibernateDao.delete(ur);
			}else{
				isOk=false;
				log.debug("删除"+TbCommonUserRole.class+"失败");
			}
		}		
		return isOk;
	}
	
	
	public TbCommonUserRole getUserRole(Long roleId,Long userId){
		List<Criterion> param=new ArrayList<Criterion>();
		param.add(Restrictions.eq("roleId", roleId));//角色id
		param.add(Restrictions.eq("userId", userId));//用户id
			
		TbCommonUserRole ur=this.hibernateDao.queryForObject(TbCommonUserRole.class, param);		
		return ur;		
	}
}
