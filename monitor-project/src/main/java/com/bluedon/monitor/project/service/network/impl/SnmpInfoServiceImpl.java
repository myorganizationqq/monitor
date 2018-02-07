package com.bluedon.monitor.project.service.network.impl;

import com.bluedon.monitor.common.dao.HibernateDao;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.network.SnmpInfo;
import com.bluedon.monitor.project.service.network.SnmpInfoService;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2018/1/29
 * @Description
 */
@Service("snmpInfoService")
public class SnmpInfoServiceImpl implements SnmpInfoService{
    @Autowired
    @Qualifier("hibernateDao")
    private HibernateDao<SnmpInfo> hibernateDao;
    private static Logger logger = Logger.getLogger(SnmpInfoServiceImpl.class);
    @Override
    public void insert(SnmpInfo vo) { vo.setCreateDate(new Date());
       hibernateDao.save(vo);
    }

    @Override
    public void insertBatch(List<SnmpInfo> vos) {
          hibernateDao.batchInsert(vos,10000);
    }

    @Override
    public void delete(long id) {
       hibernateDao.delete(SnmpInfo.class,id);
    }

    @Override
    public void update(SnmpInfo vo) {
        SnmpInfo snmpInfo = hibernateDao.queryById(SnmpInfo.class, vo.getId());
        vo.setCreateDate(snmpInfo.getCreateDate());
        vo.setCreateBy(snmpInfo.getCreateBy());
        //把查出来的snmp从一级缓存中删除
        hibernateDao.getSession().evict(snmpInfo);
        vo.setUpdateDate(new Date());
        hibernateDao.update(vo);
    }

    @Override
    public SnmpInfo get(long id) {
        return hibernateDao.queryById(SnmpInfo.class,id);
    }

    @Override
    public PageUtil getPageList(SnmpInfo param, PageUtil pageUtil) {
        //查询参数构造
        List<Criterion> paramList = new ArrayList<Criterion>();
        if(param.getState() >-1){
            paramList.add(Restrictions.eq("state", param.getState()));
        }

        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("createDate"));
        //获取总记录数
        int count = this.hibernateDao.getCount(SnmpInfo.class, paramList, null);
        logger.debug("获取记录数:"+count);
        pageUtil.setTotalRecordNumber(count);
        //计算分页数据
        if(pageUtil.fetchPaging()){
            //开始获取分页数据
            List<SnmpInfo> resultList = this.hibernateDao.findByPage(SnmpInfo.class, paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(), orders, null);
            if(resultList != null && resultList.size() > 0) {
                pageUtil.setResultList(resultList);
            }
        }
        logger.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));
        return pageUtil;
    }

    @Override
    public void deleteBatch(long[] ids) {
       List<SnmpInfo> snmpInfos = new ArrayList<>();
        for (long id : ids) {
            SnmpInfo snmpInfo = new SnmpInfo();
            snmpInfo.setId(id);
            snmpInfos.add(snmpInfo);
        }
        hibernateDao.batchDelete(snmpInfos,10000);
    }

    @Override
    public List<SnmpInfo> getList(Map params) {
        String hql="select  new SnmpInfo(a.id,a.serverInfoId, a.serverIp, a.port, a.version, a.user, a.passwd,a.state)  from SnmpInfo a";
        if (params !=null && params.size()>0){
            hql+= " where 1=1 ";
            if(params.get("state") != null){
                hql+=" and a.state = :state ";
            }
            if(params.get("version") != null){
                hql+=" and a.version = :version ";
            }
        }
        hql+=" order by a.id";
        List<SnmpInfo> list = null;
        try {
            list = hibernateDao.queryForList(hql, params, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
