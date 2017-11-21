package com.bluedon.monitor.project.service.transferTable.impl;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.transferTable.TransferTableService;
import com.bluedon.monitor.system.entity.TbLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by JiangFeng
 * on 2017/11/10.
 */
@Service("transferTableService")
public class TransferTableServiceImpl implements TransferTableService{
    @Autowired
    @Qualifier("hibernateDao")
    private IBaseDao<TbLoginLog> hibernateDao;
    @Override
    public List<TransferTable> getList(Map params) {
        String hql="select  new TransferTable(a.impTableName, a.expTableName, a.tableColumn, a.extraSql, a.stat, a.tableType)  from TransferTable a";
        if (params !=null && params.size()>0){
            hql+= " where 1=1 ";
            if(params.get("stat") != null){
                hql+=" and a.stat = :stat ";
            }
            if(params.get("tableType") != null){
                hql+=" and a.tableType = :tableType ";
            }
        }
        hql+=" order by a.impTableName";
        List<TransferTable> list = null;
        try {
            list = hibernateDao.queryForList(hql, params, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
