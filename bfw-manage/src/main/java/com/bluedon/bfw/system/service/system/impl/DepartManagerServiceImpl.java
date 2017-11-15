package com.bluedon.bfw.system.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.bfw.common.dao.IBaseDao;
import com.bluedon.bfw.common.util.PageUtil;
import com.bluedon.bfw.common.util.StringUtil;
import com.bluedon.bfw.system.entity.TbCommonDepart;
import com.bluedon.bfw.system.model.system.DepartManagerModel;
import com.bluedon.bfw.system.model.util.ComboTree;
import com.bluedon.bfw.system.service.system.IDepartManagerService;

/**
 * @author  : liangliwen
 * @date    : 2015年4月27日
 * @module  : 系统模块 
 * @comment : 部门管理业务实现类
 */
@Service("departManagerServiceImpl")
public class DepartManagerServiceImpl implements IDepartManagerService {

	//日志记录对象
	private static final Logger log = Logger.getLogger(DepartManagerServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonDepart> hibernateDao;
	
	//列表查询
	@Override
	public PageUtil getPageList(DepartManagerModel param, PageUtil pageUtil) {
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if(!StringUtil.isEmpty(param.getDepartName())){
			paramList.add(Restrictions.like("departName", param.getDepartName(),MatchMode.ANYWHERE));
		}else{//父节点为空 
			paramList.add(Restrictions.isNull("parentId"));
		}
		if(param.getOrgId() != null){
			paramList.add(Restrictions.eq("orgId", param.getOrgId()));
		}
		//获取总记录数
		int count = this.hibernateDao.getCount(TbCommonDepart.class, paramList, null);
		log.debug("获取记录数:"+count);
		pageUtil.setTotalRecordNumber(count);
		//计算分页数据
		if(pageUtil.fetchPaging()){
			//开始获取分页数据
			List<TbCommonDepart> resultList = this.hibernateDao.findByPage(TbCommonDepart.class, paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(), null, null);
			if(resultList != null && resultList.size() > 0){
				List<TbCommonDepart> departList = new ArrayList<TbCommonDepart>();
				for (TbCommonDepart root : resultList) {
					root=getDepartChildren(root, true);
					departList.add(root);
				}
				pageUtil.setResultList(departList);
			}
		}
		log.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));
		return pageUtil;
	}

	//保存部门信息
	@Override
	public boolean saveDepart(TbCommonDepart model) {
		log.debug("添加" + TbCommonDepart.class + "开始");
		if (model != null) {
			model.setCreateTime(new Date());
			this.hibernateDao.save(model);
			log.debug("添加" + TbCommonDepart.class + "结束");
			return true;
		} else {
			log.debug("添加" + TbCommonDepart.class + "失败");
			return false;
		}
	}

	//修改部门信息
	@Override
	public boolean updateDepart(TbCommonDepart model) {
		log.debug("修改" + TbCommonDepart.class + "开始");
		if (model != null) {
			this.hibernateDao.merge(model);
			log.debug("修改" + TbCommonDepart.class + "结束");
			return true;
		} else {
			log.debug("修改" + TbCommonDepart.class + "失败");
			return false;
		}
	}

	//删除部门信息
	@Override
	public boolean deleteDepart(Long id) {
		log.debug("删除" + TbCommonDepart.class + "开始");
		TbCommonDepart model = this.getDepart(id);
		if (model != null) {
			this.hibernateDao.delete(model);
			log.debug("删除" + TbCommonDepart.class + "结束");
			return true;
		} else {
			log.debug("删除" + TbCommonDepart.class + "失败");
			return false;
		}
	}

	//根据ID获取部门信息
	@Override
	public TbCommonDepart getDepart(Long id) {
		TbCommonDepart model = this.hibernateDao.queryById(TbCommonDepart.class, id);
		return model;
	}

	//获取所有部门信息的集合
	@Override
	public List<TbCommonDepart> getDepartList() {
		List<TbCommonDepart> list = this.hibernateDao.queryForList("select t from TbCommonDepart t", new Object[]{}, null);
		return list;
	}

	//根据关联ID获取部门信息
	@Override
	public List<TbCommonDepart> getDepartByParams(DepartManagerModel params) {
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if(params.getOrgId() != null){
			paramList.add(Restrictions.eq("orgId", params.getOrgId()));
		}
		if(params.getDepartCode() != null){
			paramList.add(Restrictions.eq("departCode", params.getDepartCode()));
		}
		List<TbCommonDepart> list = this.hibernateDao.queryForList(TbCommonDepart.class, paramList, null, null, null);
		return list;
	}

	//获取指定节点的子节点的集合
	@Override
	public List<TbCommonDepart> getDepartListByParentId(Long parentId) {
		List<TbCommonDepart> list = this.hibernateDao.queryForList("select t from TbCommonDepart t where t.parentId=?", new Object[]{parentId}, null);
		return list;
	}

	//获取所有根节点的集合
	@Override
	public List<TbCommonDepart> getDepartListForRoot() {
		List<TbCommonDepart> list = this.hibernateDao.queryForList("select t from TbCommonDepart t where t.parentId is null", new Object[]{}, null);
		return list;
	}

	//将部门集合转为comboTree的list
	@Override
	public List<ComboTree> comTree(List<TbCommonDepart> departList) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (TbCommonDepart depart : departList) {
			trees.add(tree(depart, true));
		}
		return trees;
	}
	
	//递归方法：逐级加入机构对象，树形结构
	public ComboTree tree(TbCommonDepart depart, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(String.valueOf(depart.getId()));
		tree.setText(depart.getDepartName());
		//获取指定节点的所有子节点集合
		List<TbCommonDepart> orgList=this.getDepartListByParentId(depart.getId());
		if (orgList != null && orgList.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			if (recursive) {
				//递归查询子节点
				List<TbCommonDepart> departList = new ArrayList<TbCommonDepart>(orgList);
				List<ComboTree> children = new ArrayList<ComboTree>();
				for (TbCommonDepart dep : departList) {
					ComboTree d = tree(dep, true);
					children.add(d);
				}
				tree.setChildren(children);
			}
		}
		return tree;
	}
	
	//递归方法：逐级设置其子节点集合
	public TbCommonDepart getDepartChildren(TbCommonDepart depart, boolean recursive) {
		List<TbCommonDepart> departList = this.getDepartListByParentId(depart.getId());
		if (departList != null && departList.size() > 0) {
			depart.setState("closed");
			if (recursive) {
				//递归查询子节点
				List<TbCommonDepart> children = new ArrayList<TbCommonDepart>();
				for (TbCommonDepart dep : departList) {
					TbCommonDepart d = getDepartChildren(dep, true);
					children.add(d);
				}
				depart.setChildren(children);
			}
		}
		return depart;
	}
}
