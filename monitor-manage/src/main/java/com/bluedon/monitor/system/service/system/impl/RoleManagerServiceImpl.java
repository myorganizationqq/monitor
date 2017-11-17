package com.bluedon.monitor.system.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.bluedon.monitor.system.entity.TbCommonRole;
import com.bluedon.monitor.system.entity.TbCommonUser;
import com.bluedon.monitor.system.entity.TbCommonUserRole;
import com.bluedon.monitor.system.model.system.RoleManagerModel;
import com.bluedon.monitor.system.service.system.IRoleManagerService;
import com.bluedon.monitor.system.util.ConstantUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.system.entity.TbCommonRoleFunction;

/**
 * @author : yangdandan
 * @date: 2015年4月13日
 * @module: 系统模块 
 * @comment: 角色管理逻辑处理实现类
 */
@Service("roleManagerServiceImpl")
public class RoleManagerServiceImpl implements IRoleManagerService {

	//日志记录对象
	private static final Logger log = Logger.getLogger(RoleManagerServiceImpl.class);

	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonRole> hibernateDao;

	//列表查询
	@Override
	public PageUtil getPageList(RoleManagerModel param, PageUtil pageUtil) {

		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if(!StringUtil.isEmpty(param.getRolename())){
			paramList.add(Restrictions.like("rolename", param.getRolename(),MatchMode.ANYWHERE));
		}

		if(param.getRoleType()!=null){
			paramList.add(Restrictions.eq("roleType", param.getRoleType()));
		}

		List<Order> order=new ArrayList<Order>();
		order.add(Order.asc("createTime"));

		//获取总记录数
		int count = this.hibernateDao.getCount(TbCommonRole.class, paramList,null);

		log.debug("获取记录数:"+count);

		pageUtil.setTotalRecordNumber(count);
		//计算分页数据
		if(pageUtil.fetchPaging()){
			//开始获取分页数据
			List<TbCommonRole> resultList = this.hibernateDao.findByPage(TbCommonRole.class,paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(),order,null);
			if(resultList!=null&&resultList.size()>0){
				for(TbCommonRole role:resultList){
					role.setCreateTimeStr(DateUtil.dateToString(role.getCreateTime(), DateUtil.DATE_STYLE_TIME_1));
					if(role.getRoleType()!=null){
						role.setRoleTypeStr(ConstantUtil.ROLE_TYPE.get(role.getRoleType()));
					}
				}
				pageUtil.setResultList(resultList);
			}
		}

		log.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));

		return pageUtil;
	}

	//保存角色信息
	@Override
	public boolean saveRole(TbCommonRole model) {
		log.debug("添加"+TbCommonRole.class+"开始");
		if(model!=null){
			hibernateDao.save(model);
			log.debug("添加"+TbCommonRole.class+"结束");
			return true;
		}else{
			log.debug("添加"+TbCommonRole.class+"失败");
			return false;
		}
	}

	//修改角色信息
	@Override
	public boolean updateRole(TbCommonRole model) {
		// TODO Auto-generated method stub
		log.debug("修改"+TbCommonRole.class+"开始");
		if(model!=null && model.getId()!=null){
			hibernateDao.merge(model);
			log.debug("修改"+TbCommonRole.class+"结束");
			return true;
		}else{
			log.debug("修改"+TbCommonRole.class+"失败");
			return false;
		}
	}

	//删除角色信息
	@Override
	public boolean deleteRole(Long id) {
		// TODO Auto-generated method stub
		TbCommonRole model=this.getRole(id);
		if(model!=null){
			//删除角色菜单关系
			for (TbCommonRoleFunction rf : getRoleFuncRelByRoleId(id)){
				hibernateDao.delete(rf);
			}
			//删除角色用户关系
			for (TbCommonUserRole rf : getRoleUserRelByRoleId(id)){
				hibernateDao.delete(rf);
			}
			//删除角色
			hibernateDao.delete(model);
			return true;
		}else{
			return false;
		}
	}
	
	//删除角色时判断是否存在关联关系
	@Override
	public boolean existRel(long roleId) {
		List<TbCommonRoleFunction> rf = getRoleFuncRelByRoleId(roleId);
		List<TbCommonUserRole> ur = getRoleUserRelByRoleId(roleId);
		if ((rf!=null && rf.size()>0) || (ur!=null && ur.size()>0)){
			return true;
		}
		return false;
	}

	//根据id获取角色信息
	@Override
	public TbCommonRole getRole(Long id) {
		// TODO Auto-generated method stub
		TbCommonRole role=this.hibernateDao.loadById(TbCommonRole.class, id);
		return role;
	}


	//获取指定用户的角色列表
	@Override
	public List<TbCommonRole> getRoleListByUserId(Long userId) {
		// TODO Auto-generated method stub
		String hql="select r from TbCommonRole r where r.id in (select roleId from TbCommonUserRole where userId="+userId+")";

		List<TbCommonRole> roleList=(List<TbCommonRole>)this.hibernateDao.queryForList(hql, new Object[]{}, null);

		return roleList;
	}
	
	//获取指定用户和机构的角色列表
	@Override
	public List<TbCommonRole> getRoleListByUserIdAndOrgId(Long userId,Long orgId) {
		// TODO Auto-generated method stub
		String hql="select r from TbCommonRole r where r.id in"
				+ " (select roleId from TbCommonUserRole where userId="+userId+" )";

		List<TbCommonRole> roleList=(List<TbCommonRole>)this.hibernateDao.queryForList(hql, new Object[]{}, null);

		return roleList;
	}

	//通过roleId获取角色菜单关系
	public List<TbCommonRoleFunction> getRoleFuncRelByRoleId(Long roleId) {
		String hql="select r from TbCommonRoleFunction r where r.roleId = ?";

		List<TbCommonRoleFunction> roleList=(List<TbCommonRoleFunction>)this.hibernateDao.queryForList(hql, new Object[]{roleId}, null);

		return roleList;
	}

	//通过roleId获取角色用户关系
	public List<TbCommonUserRole> getRoleUserRelByRoleId(Long roleId) {
		String hql="select r from TbCommonUserRole r where r.roleId = ?";

		List<TbCommonUserRole> roleList=(List<TbCommonUserRole>)this.hibernateDao.queryForList(hql, new Object[]{roleId}, null);

		return roleList;
	}

}
