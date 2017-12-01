package com.bluedon.monitor.project.service.stSysFlowCurrentDt.impl;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;
import com.bluedon.monitor.project.service.stSysFlowCurrentDt.StSysFlowCurrentDtService;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/20
 * @Description
 */
@Service("stSysFlowCurrentDtService")
public class StSysFlowCurrentDtServiceImpl implements StSysFlowCurrentDtService {
    @Autowired
    @Qualifier("hibernateDao")
    private IBaseDao <StSysFlowCurrentDt> hibernateDao;

    private static final Logger log = Logger.getLogger(StSysFlowCurrentDtServiceImpl.class);

    @Override
    public List <StSysFlowCurrentDt> getPageList() {
        String hql = "select a from StSysFlowCurrentDt a";

        return null;
    }

    @Override
    public PageUtil getPageList(StSysFlowCurrentDt param, PageUtil pageUtil) {
        //查询参数构造
        List <Criterion> paramList = new ArrayList <Criterion>();
        if (!StringUtil.isEmpty(param.getFinishFlag())) {
            paramList.add(Restrictions.eq("finishFlag", param.getFinishFlag()));
        }
        List <Order> orders = new ArrayList <>();
        orders.add(Order.desc("balanceWaterNo"));
        //获取总记录数
        int count = this.hibernateDao.getCount(StSysFlowCurrentDt.class, paramList, null);
        log.debug("获取记录数:" + count);
        pageUtil.setTotalRecordNumber(count);
        //计算分页数据
        if (pageUtil.fetchPaging()) {
            //开始获取分页数据
            List <StSysFlowCurrentDt> resultList = this.hibernateDao.findByPage(StSysFlowCurrentDt.class, paramList, (pageUtil.getPage() - 1) * pageUtil.getRows(), pageUtil.getRows(), orders, null);
            if (resultList != null && resultList.size() > 0) {
                pageUtil.setResultList(resultList);
            }
        }
        log.debug("获取结果总数:" + ((pageUtil.getResultList() != null && pageUtil.getResultList().size() > 0) ? pageUtil.getResultList().size() : 0));
        return pageUtil;
    }

    @Override
    public Map <String, Object> getTime(Map <String, Object> param) {
        Map <String, Object> timeMap = new HashMap <>();
        String sql = "select a.STEP step,a.REMARK remark,IFNULL(SUM(UNIX_TIMESTAMP(a.END_TIME)-UNIX_TIMESTAMP(a.BEGIN_TIME)),0) timeDiff FROM st_sys_flow_current_dt a GROUP BY a.STEP ORDER BY timeDiff DESC";
        List <Map <String, Object>> list = hibernateDao.selectBySql(sql);
        int temp = 0;
        for (Map <String, Object> map : list) {
            //默认显示前五名
            if (temp < 5) {
                timeMap.put(String.valueOf(map.get("remark")), map.get("timeDiff"));
                temp += 1;
                continue;
            }
            break;
        }
        return timeMap;
    }

    /**
     * 获取当天0点和前天0点之间的数据
     * @return
     */
    @Override
    public List <StSysFlowCurrentDt> alarmCount() {
        PageUtil pageUtil = new PageUtil();
        pageUtil.setRows(Integer.MAX_VALUE);

        List <Criterion> paramList = new ArrayList <Criterion>();

        Map <String, String> t = CommonUtil.getCurrentAndPreTime();
        String T1 = t.get("currentDay");
        String T2 = t.get("preDay");

        paramList.add(Restrictions.between("beginTime", T1, T2));

        List <StSysFlowCurrentDt> resultList = hibernateDao.findByPage(StSysFlowCurrentDt.class, paramList, (pageUtil.getPage() - 1) * pageUtil.getRows(), pageUtil.getRows(), null, null);
        return resultList;
    }
}
