package com.bluedon.monitor.system.service.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluedon.monitor.system.model.util.ComboTree;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.system.entity.TbCommonFunction;
import com.bluedon.monitor.system.entity.TbCommonOperation;
import com.bluedon.monitor.system.entity.TbCommonRoleFunction;
import com.bluedon.monitor.system.service.system.IFunctionManagerService;
import com.bluedon.monitor.system.util.NumberComparator;

/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:菜单管理逻辑处理实现类
 */
@Service("functionManagerServiceImpl")
public class FunctionManagerServiceImpl  implements IFunctionManagerService{

	//日志记录对象
	private static final Logger log = Logger.getLogger(FunctionManagerServiceImpl.class);

	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonFunction> hibernateDao;

	//列表查询
	@Override
	public PageUtil getPageList(PageUtil pageUtil) {
		// TODO Auto-generated method stub
		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		//父节点为空
		paramList.add(Restrictions.isNull("parentFunctionId"));	

		//获取总记录数
		int count = this.hibernateDao.getCount(TbCommonFunction.class, paramList,null);
		log.debug("获取记录数:"+count);

		pageUtil.setTotalRecordNumber(count);
		//计算分页数据
		if(pageUtil.fetchPaging()){
			//开始获取分页数据
			List<TbCommonFunction> resultList = this.hibernateDao.findByPage(TbCommonFunction.class,paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(),null,null);
			if(resultList!=null&&resultList.size()>0){
				List<TbCommonFunction> funcList=new ArrayList<TbCommonFunction>();
				for(TbCommonFunction func:resultList){
					funcList.add(getFuncChildren(func,true));
				}
				pageUtil.setResultList(funcList);
			}
		}

		log.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));

		return pageUtil;
	}

	//保存菜单信息
	@Override
	public boolean saveFunc(TbCommonFunction model) {
		// TODO Auto-generated method stub
		log.debug("添加"+TbCommonFunction.class+"开始");
		if(model!=null){
			hibernateDao.save(model);
			log.debug("添加"+TbCommonFunction.class+"结束");
			return true;
		}else{
			log.debug("添加"+TbCommonFunction.class+"失败");
			return false;
		}
	}

	//修改菜单信息
	@Override
	public boolean updateFunc(TbCommonFunction model) {
		// TODO Auto-generated method stub
		log.debug("修改"+TbCommonFunction.class+"开始");
		if(model!=null){
			hibernateDao.update(model);
			log.debug("修改"+TbCommonFunction.class+"结束");
			return true;
		}else{
			log.debug("修改"+TbCommonFunction.class+"失败");
			return false;
		}
	}

	//删除菜单信息
	@Override
	public boolean deleteFunc(Long id) {
		// TODO Auto-generated method stub
		TbCommonFunction model=this.getFunc(id);
		if(model!=null){
			//查询所有子菜单
			List<TbCommonFunction> childFunc = getFuncChildByFuncId(id);

			//删除角色菜单关系
			List<Long> functionIds = new ArrayList<Long>();
			functionIds.add(id);
			for(TbCommonFunction f : childFunc){
				functionIds.add(f.getId());
			}
			List<TbCommonRoleFunction> roleFuncRel = getRoleFuncRelByFuncIds(functionIds);
			List<Long> roleFuncIds = new ArrayList<Long>();
			for(TbCommonRoleFunction rf : roleFuncRel){
				roleFuncIds.add(rf.getId());
			}
			hibernateDao.batchDeleteByProperty(roleFuncIds, "TbCommonRoleFunction", "id");

			//删除按钮
			hibernateDao.batchDeleteByProperty(functionIds, "TbCommonOperation", "functionId");
			
			//删除菜单
			hibernateDao.batchDeleteByProperty(functionIds, "TbCommonFunction", "id");
			
			return true;
		}else{
			return false;
		}
	}
	
	//通过functionId获取子菜单
	public List<TbCommonFunction> getFuncChildByFuncId(Long functionId) {
		String hql="select r from TbCommonFunction r where r.parentFunctionId = ?";
		List<TbCommonFunction> roleList=(List<TbCommonFunction>)this.hibernateDao.queryForList(hql, new Object[]{functionId}, null);
		return roleList;
	}

	//通过functionIds获取角色菜单关系
	public List<TbCommonRoleFunction> getRoleFuncRelByFuncIds(List<Long> functionIds) {
		String hql="select r from TbCommonRoleFunction r where r.functionId in (:functionIds)";
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("functionIds", functionIds);
		List<TbCommonRoleFunction> roleList=(List<TbCommonRoleFunction>)this.hibernateDao.queryForList(hql,map, null);
		return roleList;
	}

	//根据id获取菜单信息
	@Override
	public TbCommonFunction getFunc(Long id) {
		// TODO Auto-generated method stub
		TbCommonFunction func=this.hibernateDao.loadById(TbCommonFunction.class, id);
		return func;
	}


	//获取所有菜单的集合
	@Override
	public List<TbCommonFunction> getFuncList() {
		// TODO Auto-generated method stub
		List<TbCommonFunction> list=this.hibernateDao.queryForList("select t from TbCommonFunction t", new Object[]{}, null);
		return list;
	}

	//获取指定节点的子节点集合
	@Override
	public List<TbCommonFunction> getFuncListByParentId(Long parentId) {
		// TODO Auto-generated method stub
		List<TbCommonFunction> list=this.hibernateDao.queryForList("select t from TbCommonFunction t where t.parentFunctionId=?", new Object[]{parentId}, null);
		return list;
	}

	//获取根节点的菜单集合
	@Override
	public List<TbCommonFunction> getFuncListForRoot() {
		// TODO Auto-generated method stub
		List<TbCommonFunction> list=this.hibernateDao.queryForList("select t from TbCommonFunction t where t.parentFunctionId is null", new Object[]{}, null);
		return list;
	}


	//将菜单集合转为comboTree的list
	@Override
	public List<ComboTree> comTree(List<TbCommonFunction> funcList) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (TbCommonFunction func : funcList) {
			trees.add(tree(func, true));
		}
		return trees;
	}

	//递归方法：逐级加入菜单对象，树形结构
	public ComboTree tree(TbCommonFunction func, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(String.valueOf(func.getId()));
		tree.setText(func.getFunctionName());
		//获取指定节点的所有子节点集合
		List<TbCommonFunction> funcList=this.getFuncListByParentId(func.getId());
		if (funcList != null && funcList.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			if (recursive) {
				//递归查询子节点
				List<TbCommonFunction> oList = new ArrayList<TbCommonFunction>(funcList);
				List<ComboTree> children = new ArrayList<ComboTree>();
				for (TbCommonFunction d : oList) {
					ComboTree t = tree(d, true);
					children.add(t);
				}
				tree.setChildren(children);
			}
		}
		return tree;
	}


	//递归方法：逐级设置其子节点集合
	public TbCommonFunction getFuncChildren(TbCommonFunction func, boolean recursive) {
		//获取指定节点的所有子节点集合
		List<TbCommonFunction> funcList=this.getFuncListByParentId(func.getId());
		if (funcList != null && funcList.size() > 0) {
			func.setState("closed");
			if (recursive) {
				//递归查询子节点
				List<TbCommonFunction> children = new ArrayList<TbCommonFunction>();
				for (TbCommonFunction d : funcList) {
					TbCommonFunction t = getFuncChildren(d, true);
					children.add(t);
				}
				func.setChildren(children);
			}
		}
		return func;
	}

	/**
	 * 获取指定角色的所有一级菜单
	 * @param roleIds
	 * @return
	 */
	@Override
	public List<TbCommonFunction> getFuncRootListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		String hql="select t from TbCommonFunction t where t.parentFunctionId is null"
				+ " and t.id in (select rf.functionId from TbCommonRoleFunction rf"
				+ " where roleId = :roleId group by functionId)";
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("roleId", roleId);
		List<TbCommonFunction> list=this.hibernateDao.queryForList(hql, map, null);
		return list;
	}

	/**
	 * 获取指定父节点和角色的下级菜单
	 * @param parentId
	 * @param roleIds
	 * @return
	 */
	public List<TbCommonFunction> getFuncListByParentIdAndRole(Long parentId,Long roleId) {
		// TODO Auto-generated method stub
		String hql="select t from TbCommonFunction t where t.parentFunctionId=:parentId"
				+ " and t.id in (select rf.functionId from TbCommonRoleFunction rf"
				+ " where roleId = :roleId group by functionId)";
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("parentId", parentId);
		map.put("roleId", roleId);		
		List<TbCommonFunction> list=this.hibernateDao.queryForList(hql, map, null);
		return list;
	}

	/**
	 * 设置指定一级菜单的所有下级菜单
	 * @param func
	 * @param roleIds
	 * @param recursive
	 * @return
	 */
	@Override
	public TbCommonFunction getFuncChildrenByRoleId(TbCommonFunction func,Long roleId, boolean recursive) {
		//获取指定节点的所有子节点集合
		List<TbCommonFunction> funcList=this.getFuncListByParentIdAndRole(func.getId(),roleId);
		if (funcList != null && funcList.size() > 0) {
			func.setState("closed");
			if (recursive) {
				//递归查询子节点
				List<TbCommonFunction> children = new ArrayList<TbCommonFunction>();
				for (TbCommonFunction d : funcList) {
					TbCommonFunction t = getFuncChildrenByRoleId(d, roleId, true);
					children.add(t);
				}
				//子菜单排序
				Collections.sort(children, new NumberComparator());
				func.setChildren(children);
			}
		}
		return func;
	}

	@Override
	public List<TbCommonFunction> getSelectedFuncList(Long roleId) {
		String sql = "select a from TbCommonFunction a where a.id in "
				+ "( select b.functionId from TbCommonRoleFunction b where b.roleId  = ? )";

		List<TbCommonFunction> list=this.hibernateDao.queryForList(sql, new Object[]{roleId}, null);
		return list;
	}

	/**
	 * 更新角色菜单按钮关系
	 * @param parseLong
	 * @param selectedFunctions
	 * @param newFunctions
	 */
	@Override
	public void updateRoleFunctionRel(Long roleId,List<Long> oldFunctions, List<Long> newFunctions,Long selFunctionId, String operationId) {
		if(oldFunctions!=null && oldFunctions.size()>0){
			//查询当前角色的角色菜单关系
			String hql="select t from TbCommonRoleFunction t where t.roleId = :roleId and t.functionId in (:oldFunctions) ";
			Map<String,Object> map = new HashMap<String,Object>(); 
			map.put("roleId", roleId);
			map.put("oldFunctions", oldFunctions);		
			List<TbCommonRoleFunction> list=this.hibernateDao.queryForList(hql, map, null);
			List<Long> listIds = new ArrayList<Long>();

			//准备要更新的关系
			List<TbCommonRoleFunction> prepareList = new ArrayList<TbCommonRoleFunction>();
			TbCommonRoleFunction t = null;
			for(Long f : newFunctions){
				t = new TbCommonRoleFunction();
				t.setFunctionId(f);
				t.setRoleId(roleId);
				prepareList.add(t);
			}

			//如果更新前后菜单有相同的，则把旧的按钮设置到新的
			for (int i=0; i<list.size(); i++){
				listIds.add(list.get(i).getId());
				for (int j=0; j<prepareList.size(); j++){
					if (prepareList.get(j).getFunctionId().longValue()==list.get(i).getFunctionId().longValue() 
							&& prepareList.get(j).getRoleId().longValue()==list.get(i).getRoleId().longValue()){
						prepareList.get(j).setOperationId(list.get(i).getOperationId());
					}
				}
			}

			//保存新的角色菜单关系
			for (TbCommonRoleFunction tb : prepareList){
				//当前菜单变更的按钮
				if (!StringUtil.isEmpty(String.valueOf(selFunctionId)) && tb.getFunctionId().longValue() == selFunctionId.longValue()){
					tb.setOperationId(operationId);
				}
				hibernateDao.save(tb);
			}

			//删除旧的角色菜单关系
			hibernateDao.batchDeleteByProperty(listIds, "TbCommonRoleFunction", "id");
			
		} else {
			//准备要更新的关系
			List<TbCommonRoleFunction> prepareList = new ArrayList<TbCommonRoleFunction>();
			TbCommonRoleFunction t = null;
			for(Long f : newFunctions){
				t = new TbCommonRoleFunction();
				t.setFunctionId(f);
				t.setRoleId(roleId);
				//当前菜单变更的按钮
				if (!StringUtil.isEmpty(String.valueOf(selFunctionId)) && f.longValue() == selFunctionId.longValue()){
					t.setOperationId(operationId);
				}
				hibernateDao.save(t);
			}
		}
	}

	/**
	 * 通过菜单id获取按钮
	 * @param functionId
	 * @return
	 */
	@Override
	public List<TbCommonOperation> getOperationByFunId(long functionId) {
		String sql = "select a from TbCommonOperation a where a.functionId = ?";
		List<TbCommonOperation> list=this.hibernateDao.queryForList(sql, new Object[]{functionId}, null);
		return list;
	}

	/**
	 * 通过id获取按钮
	 * @param operationId
	 * @return
	 */
	@Override
	public TbCommonOperation getOperationById(long operationId) {
		String sql = "select a from TbCommonOperation a where a.id = ?";
		List<TbCommonOperation> list=this.hibernateDao.queryForList(sql, new Object[]{operationId}, null);
		if (list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存按钮
	 * @param oper
	 * @retrun 
	 */
	@Override
	public boolean saveOperation(TbCommonOperation oper) {
		if(oper!=null){
			hibernateDao.saveOrUpdate(oper);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除按钮
	 * @param operationId
	 * @retrun 
	 */
	@Override
	public boolean deleteOperationById(long operationId) {
		TbCommonOperation oper = hibernateDao.loadById(TbCommonOperation.class, operationId);
		hibernateDao.delete(oper);
		return true;
	}

	/**
	 * 通过角色id菜单id获取按钮
	 * @param roleId
	 * @param functionId
	 * @retrun 
	 */
	@Override
	public List<TbCommonRoleFunction> getSelectedOperation(long roleId,
			long functionId) {
		String hql="select t from TbCommonRoleFunction t where t.roleId = :roleId and t.functionId = :functionId ";
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("roleId", roleId);
		map.put("functionId", functionId);		
		List<TbCommonRoleFunction> list=this.hibernateDao.queryForList(hql, map, null);
		return list;
	}

	//删除菜单时判断是否存在关联关系
	@Override
	public boolean existRel(long functionId) {
		List<TbCommonFunction> childFunc = getFuncChildByFuncId(functionId);
		if (childFunc!=null && childFunc.size()>0){
			return true;
		}
		return false;
	}
}
