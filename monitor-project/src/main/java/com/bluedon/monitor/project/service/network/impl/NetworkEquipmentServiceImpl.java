package com.bluedon.monitor.project.service.network.impl;

import com.bluedon.monitor.common.dao.HibernateDao;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.network.NetworkEquipment;
import com.bluedon.monitor.project.service.network.NetworkEquipmentService;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author JiangFeng
 * @date 2017/12/13
 * @Description 网络设备业务service类
 */
@Service("networkEquipmentService")
public class NetworkEquipmentServiceImpl implements NetworkEquipmentService{
    @Autowired
    private HibernateDao<NetworkEquipment> hibernateDao;
    private static Logger log =Logger.getLogger(NetworkEquipmentServiceImpl.class);
    @Override
    public void insert(NetworkEquipment vo) {
        vo.setCreateDate(new Date());
        hibernateDao.save(vo);
    }

    @Override
    public void insertBatch(List<NetworkEquipment> vos) {
         hibernateDao.batchInsert(vos,10000);
    }

    @Override
    public void delete(long id) {
        hibernateDao.delete(NetworkEquipment.class,id);
    }

    @Override
    public void update(NetworkEquipment vo) {
        NetworkEquipment networkEquipment = hibernateDao.queryById(NetworkEquipment.class, vo.getId());
        vo.setCreateDate(networkEquipment.getCreateDate());
        vo.setCreateBy(networkEquipment.getCreateBy());
        //把查出来的obj从一级缓存中删掉
        hibernateDao.getSession().evict(networkEquipment);
        vo.setUpdateDate(new Date());
        hibernateDao.update(vo);
    }

    @Override
    public NetworkEquipment get(long id) {
        return hibernateDao.queryById(NetworkEquipment.class,id);
    }

    @Override
    public PageUtil getPageList(NetworkEquipment param, PageUtil pageUtil) {
        //查询参数构造
        List<Criterion> paramList = new ArrayList<Criterion>();
        if(!StringUtil.isEmpty(param.getType())){
            paramList.add(Restrictions.eq("type", param.getType()));
        }
        if(!StringUtil.isEmpty(param.getEquipmentName())){
            paramList.add(Restrictions.like("equipmentName", param.getEquipmentName(), MatchMode.ANYWHERE));
        }
        if(!StringUtil.isEmpty(param.getEquipmentVersion())){
            paramList.add(Restrictions.like("equipmentVersion", param.getEquipmentVersion(), MatchMode.START));
        }
        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("createDate"));
        //获取总记录数
        int count = this.hibernateDao.getCount(NetworkEquipment.class, paramList, null);
        log.debug("获取记录数:"+count);
        pageUtil.setTotalRecordNumber(count);
        //计算分页数据
        if(pageUtil.fetchPaging()){
            //开始获取分页数据
            List<NetworkEquipment> resultList = this.hibernateDao.findByPage(NetworkEquipment.class, paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(), orders, null);
            if(resultList != null && resultList.size() > 0) {
                pageUtil.setResultList(resultList);
            }
        }
        log.debug("获取结果总数:"+((pageUtil.getResultList()!=null&&pageUtil.getResultList().size()>0)?pageUtil.getResultList().size():0));
        return pageUtil;
    }

    @Override
    public void deleteBatch(long[] ids) {
        List<NetworkEquipment> equipments = new ArrayList<>();
        for (long id : ids) {
            NetworkEquipment networkEquipment = hibernateDao.queryById(NetworkEquipment.class, id);
            equipments.add(networkEquipment);
        }
        hibernateDao.batchDelete(equipments,1000);
    }

    @Override
    public Map<Long, String> getServerInfoIds() {
        Map<Long, String> map = new HashMap<>();
        String sql="select id id,equipment_name equipmentName from network_equipment order by type asc";
        List<Map<String,Object>> list = hibernateDao.selectBySql(sql);
        for (Map<String, Object> strObjMap : list) {
            map.put(Long.valueOf(strObjMap.get("id").toString()),strObjMap.get("equipmentName").toString());
        }
        return map;
    }

    @Override
    public List<String> getIpById(long id) {
        List<String> ips = null;
        String sql="select ip from network_equipment where id="+id ;
        List<Map<String,String>> list = hibernateDao.selectBySql(sql);
        for (Map<String, String> stringStringMap : list) {
            String ip = stringStringMap.get("ip");
            String[] split = ip.split(",");
            ips = Arrays.asList(split);
        }
        if (ips ==null){
            ips=new ArrayList<>();
        }
        return ips;
    }
}
