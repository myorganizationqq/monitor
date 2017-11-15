package com.bluedon.bfw.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;



/**
 * @author : jason
 * @date : 2015年1月16日
 * @module : 基础模块
 * @comment : 数据库访问基础接口
 */
public interface IBaseDao<T> {
	
	
	/**
	 * 通过ID查询对象信息
	 * @param clazz
	 * 	待查询class对象
	 * @param id
	 * 	查询ID
	 * @return
	 */
	public <T> T queryById(Class<T> clazz, Serializable id);
	
	/**
	 * 通过load方式加载实体信息
	 */
	public <T> T loadById(Class<T> clazz, Serializable serial);

	/**
	 * 通过HQL获取记录总数
	 * @param hql
	 * 	待查询HQL语句
	 * @param params[可选]
	 * 	查询条件
	 * @return
	 */
	public Integer getCount(String hql,Object [] params);

	/**
	 * 通过QBC方式查询记录总数
	 * @param clazz
	 * 	待查询class对象
	 * @param criterions[可选]
	 * 	查询条件
	 * @param projectionp[可选]
	 * 	预测条件
	 * @return
	 */
	public Integer getCount(Class<T> clazz, List<Criterion> criterions,Projection projection);
	
	/**
	 * 通过HQL语句查询集合信息
	 * @param hql
	 * 	待查询语句
	 * @param params[可选]
	 * 	查询条件
	 * @param maxResults[可选]
	 * 	返回最大记录数
	 * @return
	 */
	public List queryForList(String hql, Object[] params,Integer maxResults);
	
	/**
	 * 通过HQL语句查询集合信息
	 * @param hql
	 * 	待查询语句
	 * @param params[可选]
	 * 	查询条件
	 * @param maxResults[可选]
	 * 	返回最大记录数
	 * @return
	 */
	public List queryForList(String hql, Map<String,Object> params,Integer maxResults);

	/**
	 * 通过QBC方式查询集合,此方式支持属性进行排序和高级预测功能
	 * @param clazz
	 * 	待查询class对象
	 * @param list[可选]
	 * 	条件集合
	 * @param maxResults[可选]
	 * 	返回结果记录数
	 * @param orders[可选]
	 * 	排序条件
	 * @param projection[可选]
	 * 	预测功能条件
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> clazz, List<Criterion> list,
			 Integer maxResults,List<Order> orders,Projection projection);

	/**
	 *  通过QBC方式查询单条记录
	 * @param clazz
	 *  待查询的class对象
	 * @param list
	 * 	查询条件集合
	 * @return
	 */
	public <T> T queryForObject(Class<T> clazz, List<Criterion> list);

	/**
	 * 通过HQL语句查询单个值信息
	 * @param hql
	 * 	待查询的HQL语句
	 * @param params
	 * 	参数列表[没有可传null]
	 * @return
	 */
	public T queryForUnique(String hql, Object[] params);

	/**
	 * 通过HQL查询分页
	 * @param hql
	 * 	待查询HQL语句
	 * @param params[可选]
	 * 	参数object数组
	 * @param firstResult[可选]
	 * 	首记录开始位置
	 * @param maxResult[可选]
	 * 	最大查询记录数
	 * @return
	 */
	public List findByPage(String hql, Object[] params, Integer firstResult,
			Integer maxResult);

	/**
	 * 通过QBC进行分页查询记录，包含字段排序功能
	 * @param clazz
	 * 	需要查询的class对象
	 * @param criterions[可选]
	 * 	查询条件
	 * @param firstRecord[可选]
	 * 	首记录位置
	 * @param maxResult	[可选]
	 * 	最大查询记录数
	 * @param orders[可选]
	 * 	排序条件
	 * @param projection[可选]
	 * 	预测条件
	 * @return
	 */
	public List<T> findByPage(Class<T> clazz, List<Criterion> criterions,
			Integer firstRecord, Integer maxResult, List<Order> orders,Projection projection);


    /**
	 * 保存持久化对象信息
	 * @param t
	 */
	public <T> void save(T t);
	
	/**
	 * 更新持久化对象信息
	 * @param t
	 */
	public <T> void update(T t);
	
	/**
	 * 通过HQL语句更新对象信息
	 * @param hql
	 * @param obj
	 * @return
	 */
	public boolean update(String hql, Object obj);
	
	/**
	 * 保存或更新持久化对象信息
	 * @param t
	 */
	public <T> void saveOrUpdate(T t);
	
	/**
	 * 删除持久化对象信息
	 * @param t
	 */
	public <T> void delete(T t);
	
	/**
	 * 通过ID删除对象信息
	 * @param entityClass
	 * @param id
	 */
	public <T> void delete(Class<T> entityClass, Integer id);
	
	/**
	 * 通过SQL语句进行查询操作
	 * @param sql
	 * @return
	 */
	public List selectBySql(String sql);
	
	/**
	 * 根据HQL执行增删改操作
	 * @param hql
	 * 	要执行的HQL语句
	 * @param params
	 * 	要参数化的数组[可选]
	 */
	public void executeByHql(String hql,Object [] params);
	
	
	/**
	 * 保存或更新持久化对象信息
	 * @param t
	 */
	public <T> void merge(T t);
	
	/**
	 * 批量删除
	 * @param ids       id集合
	 * @param entityName   删除实体名
	 * @param propertyName 删除条件（属性名）
	 */
	public void batchDelete(List<Long> ids, String entityName, String propertyName);
}
