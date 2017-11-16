package com.bluedon.monitor.system.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.bluedon.monitor.system.model.util.ComboTree;
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
import com.bluedon.monitor.system.entity.TbCommonOrg;
import com.bluedon.monitor.system.model.system.OrgManagerModel;
import com.bluedon.monitor.system.service.system.IOrgManagerService;

/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:组织机构管理逻辑处理实现类
 */
@Service("orgManagerServiceImpl")
public class OrgManagerServiceImpl  implements IOrgManagerService{

	//日志记录对象
	private static final Logger log = Logger.getLogger(OrgManagerServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonOrg> hibernateDao;
		
	//列表查询
	@Override
	public PageUtil getPageList(OrgManagerModel param, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if(!StringUtil.isEmpty(param.getOrgName()) || !StringUtil.isEmpty(param.getFullName())){
			if(!StringUtil.isEmpty(param.getOrgName())){
				paramList.add(Restrictions.like("orgName", param.getOrgName(),MatchMode.ANYWHERE));
			}else{
				paramList.add(Restrictions.like("fullName", param.getFullName(),MatchMode.ANYWHERE));
			}
		}else{//父节点为空 
			paramList.add(Restrictions.isNull("parentId"));
		}
		
		//获取总记录数
		int count = this.hibernateDao.getCount(TbCommonOrg.class, paramList,null);
		
		log.debug("获取记录数:"+count);
		
		pageUtil.setTotalRecordNumber(count);
		//计算分页数据
		if(pageUtil.fetchPaging()){
			//开始获取分页数据
			List<TbCommonOrg> resultList = this.hibernateDao.findByPage(TbCommonOrg.class,paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(),null,null);
			if(resultList!=null&&resultList.size()>0){
				List<TbCommonOrg> orgList=new ArrayList<TbCommonOrg>();
				for(TbCommonOrg root:resultList){	
					root=getOrgChildren(root, true);
					orgList.add(root);
				}
				pageUtil.setResultList(orgList);
			}
		}
	
		log.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));
				
		return pageUtil;
	}

	//保存组织机构信息
	@Override
	public boolean saveOrg(TbCommonOrg model) {
		// TODO Auto-generated method stub
		log.debug("添加"+TbCommonOrg.class+"开始");
		if(model!=null){
			hibernateDao.save(model);
			log.debug("添加"+TbCommonOrg.class+"结束");
			return true;
		}else{
			log.debug("添加"+TbCommonOrg.class+"失败");
			return false;
		}
	}

	//修改组织机构信息
	@Override
	public boolean updateOrg(TbCommonOrg model) {
		// TODO Auto-generated method stub
		log.debug("修改"+TbCommonOrg.class+"开始");
		if(model!=null){
			hibernateDao.update(model);
			log.debug("修改"+TbCommonOrg.class+"结束");
			return true;
		}else{
			log.debug("修改"+TbCommonOrg.class+"失败");
			return false;
		}
	}

	//删除组织机构信息
	@Override
	public boolean deleteOrg(Long id) {
		// TODO Auto-generated method stub
		log.debug("删除"+TbCommonOrg.class+"开始");
		TbCommonOrg model=this.getOrg(id);
		if(model!=null){
			hibernateDao.delete(model);
			log.debug("删除"+TbCommonOrg.class+"结束");
			return true;
		}else{
			log.debug("删除"+TbCommonOrg.class+"失败");
			return false;
		}
	}

	//根据id获取机构信息
	@Override
	public TbCommonOrg getOrg(Long id) {
		// TODO Auto-generated method stub
		TbCommonOrg org=this.hibernateDao.loadById(TbCommonOrg.class, id);
		return org;
	}
	
	
	//获取有效机构的集合
	@Override
	public List<TbCommonOrg> getEnabledOrgList() {
		// TODO Auto-generated method stub
		List<TbCommonOrg> list=this.hibernateDao.queryForList("select t from TbCommonOrg t where t.disabled=1", new Object[]{}, null);
		return list;
	}

	//获取指定节点的子节点集合
	@Override
	public List<TbCommonOrg> getOrgListByParentId(Long parentId) {
		// TODO Auto-generated method stub
		List<TbCommonOrg> list=this.hibernateDao.queryForList("select t from TbCommonOrg t where t.parentId=?", new Object[]{parentId}, null);
		return list;
	}

	//获取根节点的机构集合
	@Override
	public List<TbCommonOrg> getOrgListForRoot() {
		// TODO Auto-generated method stub
		List<TbCommonOrg> list=this.hibernateDao.queryForList("select t from TbCommonOrg t where t.parentId is null", new Object[]{}, null);
		return list;
	}
	
	
	//将机构集合转为comboTree的list
	@Override
	public List<ComboTree> comTree(List<TbCommonOrg> orgList) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (TbCommonOrg org : orgList) {
			trees.add(tree(org, true));
		}
		return trees;
	}
	
	//递归方法：逐级加入机构对象，树形结构
	public ComboTree tree(TbCommonOrg org, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(String.valueOf(org.getId()));
		tree.setText(org.getOrgName());
		//获取指定节点的所有子节点集合
		List<TbCommonOrg> orgList=this.getOrgListByParentId(org.getId());
		if (orgList != null && orgList.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			if (recursive) {
				//递归查询子节点
				List<TbCommonOrg> oList = new ArrayList<TbCommonOrg>(orgList);
				List<ComboTree> children = new ArrayList<ComboTree>();
				for (TbCommonOrg d : oList) {
					ComboTree t = tree(d, true);
					children.add(t);
				}
				tree.setChildren(children);
			}
		}
		return tree;
	}
	
	
	//递归方法：逐级设置其子节点集合
	public TbCommonOrg getOrgChildren(TbCommonOrg org, boolean recursive) {
		//获取指定节点的所有子节点集合
		List<TbCommonOrg> orgList=this.getOrgListByParentId(org.getId());
		if (orgList != null && orgList.size() > 0) {
			org.setState("closed");
			if (recursive) {
				//递归查询子节点
				List<TbCommonOrg> children = new ArrayList<TbCommonOrg>();
				for (TbCommonOrg d : orgList) {
					TbCommonOrg t = getOrgChildren(d, true);
					children.add(t);
				}
				org.setChildren(children);
			}
		}
		return org;
	}

	//获取指定节点的子节点集合
	@Override
	public List<TbCommonOrg> getOrgListByParentIdArr(String parentIdArr) {
		String hql = "select t from TbCommonOrg t where t.parentId in (" + parentIdArr + ") ";
		List<TbCommonOrg> list=this.hibernateDao.queryForList(hql, new Object[]{}, null);
		if (list != null && list.size() > 0) {
			String idArr = "";
			for (TbCommonOrg tbCommonOrg : list) {
				idArr += tbCommonOrg.getId() + ",";
			}
			idArr = idArr.substring(0, idArr.length() - 1);
		}
		return list;
	}

	//修改机构是否启用状态
	@Override
	public boolean updateOrgEnableStatus(String idArr, String status, boolean haveChildren) {
		log.debug("修改"+TbCommonOrg.class+"开始");
		if(!StringUtil.isEmpty(idArr)){
			String hql = "update TbCommonOrg o set o.disabled=" + status + " where o.parentId in (" + idArr + ") or o.id in (" + idArr + ")";
			this.hibernateDao.executeByHql(hql, null);
			if (haveChildren) {
				String queryHql = "select t from TbCommonOrg t where t.parentId in (" + idArr + ") ";
				List<TbCommonOrg> list = this.hibernateDao.queryForList(queryHql, new Object[]{}, null);
				if (list != null && list.size() > 0) {
					String queryIdArr = "";
					for (TbCommonOrg tbCommonOrg : list) {
						queryIdArr += tbCommonOrg.getId() + ",";
					}
					queryIdArr = queryIdArr.substring(0, queryIdArr.length() - 1);
					updateOrgEnableStatus(queryIdArr, status, true);
				}
			}
			log.debug("修改"+TbCommonOrg.class+"结束");
			return true;
		}else{
			log.debug("修改"+TbCommonOrg.class+"失败");
			return false;
		}
	}

	//根据参数类查询机构信息
	@Override
	public List<TbCommonOrg> getOrgListByParams(OrgManagerModel modelParams) {
		List<Criterion> paramsList = new ArrayList<Criterion>();
		if (!StringUtil.isEmpty(modelParams.getOrgCode())) {
			paramsList.add(Restrictions.eq("orgCode", modelParams.getOrgCode()));
		}
		if (!StringUtil.isEmpty(modelParams.getOrgName())) {
			paramsList.add(Restrictions.eq("orgName", modelParams.getOrgName()));
		}
		if (!StringUtil.isEmpty(modelParams.getFullName())) {
			paramsList.add(Restrictions.eq("fullName", modelParams.getFullName()));
		}
		List<TbCommonOrg> orgList = this.hibernateDao.queryForList(TbCommonOrg.class, paramsList, null, null, null);
		return orgList;
	}
	
	
	//获取指定用户的机构列表
	@Override
	public List<TbCommonOrg> getOrgListByUserId(Long userId) {
		// TODO Auto-generated method stub
		String hql="select r from TbCommonOrg r where r.id in (select orgId from TbCommonUserOrg where userId="+userId+")";

		List<TbCommonOrg> orgList=(List<TbCommonOrg>)this.hibernateDao.queryForList(hql, new Object[]{}, null);

		return orgList;
	}
}
