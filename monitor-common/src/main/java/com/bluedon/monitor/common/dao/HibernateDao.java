package com.bluedon.monitor.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bluedon.monitor.common.util.StringUtil;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author : jason
 * @date : 2015年1月16日
 * @module : 基础模块
 * @comment : 数据访问操作类
 */
@Repository("hibernateDao")
public class HibernateDao<T> implements IBaseDao<T>{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	private Session openSession() {
		return this.sessionFactory.openSession();
	}

	/**
	 * 参数填充方法
	 * @param query
	 * 	待填充查询对象
	 * @param params
	 * 	待填充参数列表
	 */
	private void setQueryParams(Query query, Object[] params) {
		if (null == params||params.length<1) {
			return;
		}
		for (Integer i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}

	/**
	 * 参数填充方法
	 * @param query
	 * 	待填充查询对象
	 * @param map
	 * 	待填充参数列表
	 */
	private void setQueryParams(Query query, Map<String,Object> map) {
		if (map != null) {   
			Set<String> keySet = map.keySet();   
			for (String string : keySet) {   
				Object obj = map.get(string);   
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同   
				if(obj instanceof Collection<?>){   
					query.setParameterList(string, (Collection<?>)obj);   
				}else if(obj instanceof Object[]){   
					query.setParameterList(string, (Object[])obj);   
				}else{   
					query.setParameter(string, obj);   
				}   
			}   
		}   
	}

	/**
	 * 通过ID查询对象信息
	 * @param clazz
	 * 	待查询class对象
	 * @param id
	 * 	查询ID
	 * @return
	 */
	public <T> T queryById(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	/**
	 * 通过load方式加载实体信息
	 */
	public <T> T loadById(Class<T> clazz, Serializable serial) {
		return (T) getSession().load(clazz, serial);
	}

	/**
	 * 通过HQL获取记录总数
	 * @param hql
	 * 	待查询HQL语句
	 * @param params [可选]
	 * 	查询条件
	 * @return
	 */
	public Integer getCount(String hql,Object [] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		Object obj = query.uniqueResult();
		if(obj!=null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}

	/**
	 * 通过QBC方式查询记录总数
	 * @param clazz
	 * 	待查询class对象
	 * @param criterions [可选]
	 * 	查询条件
	 * @param projection [可选]
	 * 	预测条件
	 * @return
	 */
	public Integer getCount(Class<T> clazz, List<Criterion> criterions,Projection projection) {
		Criteria cria = getSession().createCriteria(clazz);
		if (criterions != null&&criterions.size()>0) {
			for (Criterion tmpCri : criterions) {
				cria.add(tmpCri);
			}
		}
		if(projection==null){
			cria.setProjection(Projections.rowCount());
		}else{
			cria.setProjection(Projections.rowCount()).setProjection(projection);
		}

		Object obj = cria.uniqueResult();
		if (obj != null) {
			return Integer.valueOf(obj.toString());
		}
		return 0;
	}

	/**
	 * 通过HQL语句查询集合信息
	 * @param hql
	 * 	待查询语句
	 * @param params [可选]
	 * 	查询条件
	 * @param maxResults [可选]
	 * 	返回最大记录数
	 * @return
	 */
	public List queryForList(String hql, Object[] params,Integer maxResults) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		if(maxResults!=null){
			query.setMaxResults(maxResults);
		}
		return (List<T>) query.list();

	}

	/**
	 * 通过HQL语句查询集合信息
	 * @param hql
	 * 	待查询语句
	 * @param params [可选]
	 * 	查询条件
	 * @param maxResults [可选]
	 * 	返回最大记录数
	 * @return
	 */
	public List queryForList(String hql, Map<String,Object> params,Integer maxResults) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		if(maxResults!=null){
			query.setMaxResults(maxResults);
		}
		return (List<T>) query.list();
	}

	/**
	 * 通过QBC方式查询集合,此方式支持属性进行排序和高级预测功能
	 * @param clazz
	 * 	待查询class对象
	 * @param list [可选]
	 * 	条件集合
	 * @param maxResults [可选]
	 * 	返回结果记录数
	 * @param orders [可选]
	 * 	排序条件
	 * @param projection [可选]
	 * 	预测功能条件
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> clazz, List<Criterion> list,
			Integer maxResults,List<Order> orders,Projection projection) {
		Criteria cria = getSession().createCriteria(clazz);
		if (orders != null&&orders.size()>0) {// 排序
			for (Order order : orders) {
				cria.addOrder(order);
			}
		}
		if (list != null&&list.size()>0) {//条件
			for (Criterion tmpCri : list) {
				cria.add(tmpCri);
			}
		}
		if(projection!=null){//预测参数[如:匹配等]
			cria.setProjection(projection);
		}
		if(maxResults!=null){//返回结果数
			cria.setMaxResults(maxResults);
		}
		return (List<T>) cria.list();
	}

	/**
	 *  通过QBC方式查询单条记录
	 * @param clazz
	 *  待查询的class对象
	 * @param list
	 * 	查询条件集合
	 * @return
	 */
	public <T> T queryForObject(Class<T> clazz, List<Criterion> list) {
		Criteria cria = getSession().createCriteria(clazz);
		if (list != null&&list.size()>0) {
			for (Criterion tmpCri : list) {
				cria.add(tmpCri);
			}
		}
		List<T> result = cria.list();
		if (result != null && result.size() > 0) {
			return (T) result.get(0);
		}
		return (T) null;
	}

	/**
	 * 通过HQL语句查询单个值信息
	 * @param hql
	 * 	待查询的HQL语句
	 * @param params
	 * 	参数列表[没有可传null]
	 * @return
	 */
	public T queryForUnique(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.uniqueResult();

	}

	/**
	 * 通过HQL查询分页
	 * @param hql
	 * 	待查询HQL语句
	 * @param params [可选]
	 * 	参数object数组
	 * @param firstResult [可选]
	 * 	首记录开始位置
	 * @param maxResult [可选]
	 * 	最大查询记录数
	 * @return
	 */
	public List findByPage(String hql, Object[] params, Integer firstResult,
			Integer maxResult) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		if(firstResult!=null){
			query.setFirstResult(firstResult);
		}
		if(maxResult!=null){
			query.setMaxResults(maxResult);
		}
		return (List<T>) query.list();

	}

	/**
	 * 通过QBC进行分页查询记录，包含字段排序功能
	 * @param clazz
	 * 	需要查询的class对象
	 * @param criterions [可选]
	 * 	查询条件
	 * @param firstRecord [可选]
	 * 	首记录位置
	 * @param maxResult	[可选]
	 * 	最大查询记录数
	 * @param orders [可选]
	 * 	排序条件
	 * @param projection [可选]
	 * 	预测条件
	 * @return
	 */
	public List<T> findByPage(Class<T> clazz, List<Criterion> criterions,
			Integer firstRecord, Integer maxResult, List<Order> orders,Projection projection) {
		Criteria cria = getSession().createCriteria(clazz);
		if (orders != null&&orders.size()>0) {
			for(Order order:orders){
				cria.addOrder(order);
			}
		}
		if (criterions != null&&criterions.size()>0) {
			for (Criterion tmpCri : criterions) {
				cria.add(tmpCri);
			}
		}
		if(firstRecord!=null){
			cria.setFirstResult(firstRecord);
		}
		if(maxResult!=null){
			cria.setMaxResults(maxResult);
		}
		if(projection!=null){
			cria.setProjection(projection);
		}
		return (List<T>) cria.list();
	}

	/**
	 * 保存持久化对象信息
	 * @param t
	 */
	public <T> void save(T t) {
		getSession().save(t);

	}

	/**
	 * 更新持久化对象信息
	 * @param t
	 */
	public <T> void update(T t) {
		getSession().update(t);
	}


	/**
	 * 保存或更新持久化对象信息
	 * @param t
	 */
	public <T> void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	/**
	 * 通过HQL语句更新对象信息
	 * @param hql
	 * @param obj
	 * @return
	 */
	public boolean update(String hql, Object obj) {
		getSession().update(hql, obj);
		return true;
	}

	/**
	 * 删除持久化对象信息
	 * @param t
	 */
	public <T> void delete(T t) {
		getSession().delete(t);

	}

	/**
	 * 通过ID删除对象信息
	 * @param entityClass
	 * @param id
	 */
	public <T> void delete(Class<T> entityClass, Integer id) {
		getSession().delete(loadById(entityClass, id));

	}

	/**
	 * 通过SQL语句进行查询操作
	 * @param sql
	 * @return
	 */
	public List selectBySql(String sql) {
		//return getSession().createSQLQuery(sql).list();
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	/**
	 * 根据HQL执行增删改操作
	 * @param hql
	 * 	要执行的HQL语句
	 * @param params
	 * 	要参数化的数组[可选]
	 */
	public void executeByHql(String hql,Object [] params) {
		Query q=getSession().createQuery(hql);
		if(params!=null&&params.length>0){
			for(int i = 0;i<params.length;i++){
				q.setParameter(i, params[i]);
			}
		}
		q.executeUpdate();
	}


	/**
	 * 保存或更新持久化对象信息
	 * @param t
	 */
	public <T> void merge(T t) {
		getSession().merge(t);
	}

	/**
	 * 批量删除
	 * @param values       id集合
	 * @param entityName   删除实体名
	 * @param propertyName 删除条件（属性名）
	 */
	@Override
	public void batchDeleteByProperty(List<Long> ids, String entityName,
									  String propertyName) {
		if (!StringUtil.isEmpty(entityName) && !StringUtil.isEmpty(propertyName) && ids!=null && ids.size()>0){
			StringBuffer hql = new StringBuffer();
			for(int i=0;i<ids.size();i++) {
				if(i==0) {
					hql.append(propertyName + "=" + ids.get(i));
				} else {
					hql =hql.append(" or " + propertyName + "=" +ids.get(i));
				}
			}   
			String sql = "delete from " + entityName + " where "+ hql.toString();
			Session session= this.getSession();
			Query q= session.createQuery(sql);
			q.executeUpdate();
		}
	}

	/**
	 * 批量插入
	 *
	 * @param list
	 * @param commitSize 每次提交数
	 */
	@Override
	public void batchInsert(List <T> list, int commitSize) {

		// 获取Session
		Session session = this.openSession();

		try {
			// 开启事务
			Transaction transaction = session.beginTransaction();

			T t = null;
			for (int i = 0; i < list.size(); i++) {
				t = list.get(i);
				// 保存对象
				session.save(t);
				// 批插入的对象立即写入数据库并释放内存
				if (i % commitSize == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch (Exception e) {
			// 打印错误信息
			e.printStackTrace();
			// 出错将回滚事物
			session.getTransaction().rollback();
		} finally {
			// 关闭Session
			session.close();
		}
	}

	/**
	 * 批量删除
	 *
	 * @param list
	 */
	@Override
	public void batchDelete(List <T> list, int commitSize) {
		// 获取Session
		Session session = this.openSession();
		try {
			// 开启事务
			Transaction transaction = session.beginTransaction();

			T t = null;
			for (int i = 0; i < list.size(); i++) {
				t = list.get(i);
				// 删除对象
				session.delete(t);
				// 批插入的对象立即写入数据库并释放内存
				if (i % commitSize == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch (Exception e) {
			// 打印错误信息
			e.printStackTrace();
			// 出错将回滚事物
			session.getTransaction().rollback();
		} finally {
			// 关闭Session
			session.close();
		}
	}

	/**
	 * 批量更新
	 *
	 * @param list
	 */
	@Override
	public void batchUpdate(List <T> list, int commitSize) {
		// 获取Session
		Session session = this.openSession();
		try {
			// 开启事务
			Transaction transaction = session.beginTransaction();

			T t = null;
			for (int i = 0; i < list.size(); i++) {
				t = list.get(i);
				// 更新
				session.update(t);
				// 批插入的对象立即写入数据库并释放内存
				if (i % commitSize == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch (Exception e) {
			// 打印错误信息
			e.printStackTrace();
			// 出错将回滚事物
			session.getTransaction().rollback();
		} finally {
			// 关闭Session
			session.close();
		}
	}
}
