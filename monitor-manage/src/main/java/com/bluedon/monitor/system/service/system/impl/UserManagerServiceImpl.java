package com.bluedon.monitor.system.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.bluedon.monitor.system.entity.UserAndOrgAnddepart;
import com.bluedon.monitor.system.model.system.LoginUserModel;
import com.bluedon.monitor.system.service.BaseServiceImpl;
import com.bluedon.monitor.system.service.system.IUserManagerService;
import com.bluedon.monitor.system.util.ConstantUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.system.entity.TbCommonUser;
import com.bluedon.monitor.system.model.system.UserManagerModel;


/**
 * @author  : yangdandan
 * @date    : 2015年4月17日
 * @module  : 系统模块 
 * @comment : 用户管理逻辑处理实现类
 */
@Service("userManagerServiceImpl")
public class UserManagerServiceImpl extends BaseServiceImpl implements IUserManagerService {
	
	//日志记录对象
	private static final Logger log = Logger.getLogger(UserManagerServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonUser> hibernateDao;
	
	/**
	 *  获取分页数据
	 * @param param
	 * 	分页查询参数
	 * @param pageUtil
	 *  分页信息
	 * @return
	 * 	分页后的信息
	 */
	public PageUtil getPageList(UserManagerModel param,PageUtil pageUtil){
		
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if(!StringUtil.isEmpty(param.getRealName())){
			paramList.add(Restrictions.like("realName", param.getRealName(),MatchMode.ANYWHERE));
		}
		
		if(!StringUtil.isEmpty(param.getLoginName())){
			paramList.add(Restrictions.like("loginName", param.getLoginName(),MatchMode.ANYWHERE));
		}
		
		if(param.getOrgId()!=null){
			paramList.add(Restrictions.eq("orgId", param.getOrgId()));
		}
		
		if(param.getDepartId()!=null){
			paramList.add(Restrictions.eq("departId", param.getDepartId()));
		}
		
		if(param.getUserType()!=null){
			paramList.add(Restrictions.eq("userType", param.getUserType()));
		}
		
		if(param.getIsValid()!=null){
			paramList.add(Restrictions.eq("isValid", param.getIsValid()));
		}
		
		
		//获取总记录数
		int count = this.hibernateDao.getCount(TbCommonUser.class, paramList,null);
		
		log.debug("获取记录数:"+count);
		
		pageUtil.setTotalRecordNumber(count);
		//计算分页数据
		if(pageUtil.fetchPaging()){
			String hql = "select new UserAndOrgAnddepart(u.id,u.loginName,u.password,u.realName,u.departId,u.orgId,u.userType,u.isValid"
					+ ",(select d.departName from TbCommonDepart d where u.departId=d.id) as departName"
					+ ",(select o.orgName from TbCommonOrg o where u.orgId = o.id) as orgName)"
					+ " from TbCommonUser u where 1=1 ";
			System.out.println("++++++++++++++++++++++++" + paramList.toString());
			for (Object p : paramList.toArray()) {
				if (!StringUtil.isEmpty(p.toString())) {
					hql += " and u." + p;
				}
			}
			//开始获取分页数据
			List<UserAndOrgAnddepart> resultList = this.hibernateDao.findByPage(hql , new Object[]{}, (pageUtil.getPage() - 1) * pageUtil.getRows(), pageUtil.getRows());
			String idArr = "";
			for (UserAndOrgAnddepart userAndOrgAnddepart : resultList) {
				idArr += userAndOrgAnddepart.getId() + ",";
			}
			idArr = idArr.contains(",") ? idArr.substring(0, idArr.length()-1) : idArr;
			if (!StringUtil.isEmpty(idArr)) {
				List<UserAndOrgAnddepart> roleNameList = getListRoleName(idArr);
				
				for (UserAndOrgAnddepart userAndOrgAnddepart : resultList) {
					String roleName = "";
					for (UserAndOrgAnddepart uod : roleNameList) {
						if (uod.getId() == userAndOrgAnddepart.getId()) {
							roleName += uod.getRoleName() + ",";
						}
					}
					if (roleName.contains(",")) {
						roleName = roleName.substring(0, roleName.length()-1);
					}
					userAndOrgAnddepart.setRoleName(roleName);
					//用户类型
					if(userAndOrgAnddepart.getUserType()!=null){
						userAndOrgAnddepart.setUserTypeStr(ConstantUtil.USER_TYPE.get(userAndOrgAnddepart.getUserType()));
					}else{
						userAndOrgAnddepart.setUserTypeStr(ConstantUtil.USER_TYPE.get(5));//其他
					}
					
					//是否有效
					if(userAndOrgAnddepart.getUserType()!=null){
						userAndOrgAnddepart.setIsValidStr(ConstantUtil.IS_VAILD.get(userAndOrgAnddepart.getIsValid()));
					}else{
						userAndOrgAnddepart.setIsValidStr(ConstantUtil.IS_VAILD.get(0));//无效
					}
				}
			}
			
			if(resultList!=null&&resultList.size()>0){				
				pageUtil.setResultList(resultList);
			}
		}
		
		log.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));
				
		return pageUtil;
	}
	
	public List<UserAndOrgAnddepart> getListRoleName(String idArr) {
		String hql = "select new UserAndOrgAnddepart(u.id,r.rolename) from TbCommonUser u,TbCommonRole r"
				+ " where r.id in (select ur.roleId from TbCommonUserRole ur where ur.userId=u.id and u.id in (" + idArr + "))";
		List<UserAndOrgAnddepart> roleNameList = this.hibernateDao.queryForList(hql.toString(), new Object[]{}, null);
		return roleNameList;
	}
	
	//保存用户信息
	@Override
	public boolean saveUser(TbCommonUser model) {
		// TODO Auto-generated method stub
		log.debug("添加"+TbCommonUser.class+"开始");
		if(model!=null){
			hibernateDao.save(model);
			log.debug("添加"+TbCommonUser.class+"结束");
			return true;
		}else{
			log.debug("添加"+TbCommonUser.class+"失败");
			return false;
		}
	}

	//修改用户信息
	@Override
	public boolean updateUser(TbCommonUser model) {
		// TODO Auto-generated method stub
		log.debug("修改"+TbCommonUser.class+"开始");
		if(model!=null){
			TbCommonUser user=this.getUser(model.getId());
			model.setPassword(user.getPassword());//密码不变
			hibernateDao.merge(model);
			log.debug("修改"+TbCommonUser.class+"结束");
			return true;
		}else{
			log.debug("修改"+TbCommonUser.class+"失败");
			return false;
		}
	}
	//修改用户是否可用状态
	@Override
	public boolean updateUserValid(String idArr, String status) {
		log.debug("修改"+TbCommonUser.class+"开始");
		if(!StringUtil.isEmpty(idArr)){
			String hql = "update TbCommonUser u set u.isValid=" + status + " where u.id in (" + idArr + ")";
			this.hibernateDao.executeByHql(hql, null);
			log.debug("修改"+TbCommonUser.class+"结束");
			return true;
		}else{
			log.debug("修改"+TbCommonUser.class+"失败");
			return false;
		}
	}

	//删除用户信息 账号
	@Override
	public boolean deleteUser(Long id) {
		// TODO Auto-generated method stub
		log.debug("删除"+TbCommonUser.class+"开始");
		TbCommonUser model=this.getUser(id);
		if(model!=null){
			hibernateDao.delete(model);
			log.debug("删除"+TbCommonUser.class+"结束");
			return true;
		}else{
			log.debug("删除"+TbCommonUser.class+"失败");
			return false;
		}
	}

	//根据id获取用户信息
	@Override
	public TbCommonUser getUser(Long id) {
		// TODO Auto-generated method stub
		TbCommonUser user = this.hibernateDao.loadById(TbCommonUser.class, id);
		return user;
	}	
	
	/**
	 * 根据参数获取用户集合
	 * @param param
	 * @return
	 */
	@Override
	public List<TbCommonUser> getUserListByParam(LoginUserModel param){
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		
		if(param!=null){
			int checkType=param.getCheckType();
			//登录名校验		
			if(!StringUtil.isEmpty(param.getLoginName())){
				paramList.add(Restrictions.eq("loginName", param.getLoginName()));
			}
			
	
			if(checkType==ConstantUtil.CHECK_LOGINPWS){//密码是否正确
				if(!StringUtil.isEmpty(param.getPassword())){
					String pwd=param.getPassword();//加密
					paramList.add(Restrictions.eq("password", pwd));
				}
			}				

			paramList.add(Restrictions.eq("isValid", param.getIsValid()));	
		}
	
		List<TbCommonUser> list=this.hibernateDao.queryForList(TbCommonUser.class, paramList, null, null, null);
		return list;
	}
	
	
	/**
	 * 根据参数获取用户集合
	 * @param param
	 * @return
	 */
	@Override
	public List<TbCommonUser> getUserListByParam(UserManagerModel param){
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		
		if(param!=null){		
			if(!StringUtil.isEmpty(param.getLoginName())){
				paramList.add(Restrictions.like("loginName", param.getLoginName(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtil.isEmpty(param.getRealName())){
				paramList.add(Restrictions.like("realName", param.getRealName(), MatchMode.ANYWHERE));
			}

			if(param.getOrgId()!=null){
				paramList.add(Restrictions.eq("orgId", param.getOrgId()));
			}
			
			if(param.getDepartId()!=null){
				paramList.add(Restrictions.eq("departId", param.getDepartId()));
			}

			if(param.getUserType()!=null){
				paramList.add(Restrictions.eq("userType", param.getUserType()));
			}
			
			if(param.getIsValid()!=null){
				paramList.add(Restrictions.eq("isValid", param.getIsValid()));
			}
			
		}
		
		List<TbCommonUser> list=this.hibernateDao.queryForList(TbCommonUser.class, paramList, null, null, null);
		return list;
	}

	/**
	 * 根据参数获取指定角色的用户集合
	 * @param param
	 * @param roleId
	 * @return
	 */
	@Override
	public List<TbCommonUser> getSelectUserListByParam(UserManagerModel param,String roleId){
		String hql="select u from TbCommonUser u"
				+ " where u.id in (select userId from TbCommonUserRole where roleId = ?)";
		
		List<Object> paramList = new ArrayList<Object>();
		//角色
		paramList.add(Long.parseLong(roleId));		
		
		if(param!=null){		
			if(!StringUtil.isEmpty(param.getLoginName())){
				hql +=" and u.loginName like ?";
				paramList.add("%"+param.getLoginName()+"%");
			}
			
			if(!StringUtil.isEmpty(param.getRealName())){
				hql +=" and u.realName like ?";
				paramList.add("%"+param.getRealName()+"%");
			}

			if(param.getOrgId()!=null){
				hql +=" and u.orgId = ?";
				paramList.add(param.getOrgId());
			}
			
			if(param.getDepartId()!=null){
				hql +=" and u.departId = ?";
				paramList.add(param.getDepartId());
			}

			if(param.getUserType()!=null){
				hql +=" and u.userType = ?";
				paramList.add(param.getUserType());
			}
			
			if(param.getIsValid()!=null){
				hql += " and u.isValid = ?";
				paramList.add(param.getIsValid());
			}
		}
				
		List<TbCommonUser> list=(List<TbCommonUser>)this.hibernateDao.findByPage(hql,paramList.toArray(), null, null);
		return list;
	}


	@Override
	public TbCommonUser getUserByLoginName(String loginname) {
		String hql="select u from TbCommonUser u where u.loginName=?";
		List<TbCommonUser> list = this.hibernateDao.queryForList(hql, new Object[]{loginname}, null);
		return list != null ? list.get(0) : null;
	}

	
}
