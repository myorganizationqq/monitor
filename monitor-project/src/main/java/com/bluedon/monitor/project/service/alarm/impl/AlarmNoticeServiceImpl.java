package com.bluedon.monitor.project.service.alarm.impl;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.system.service.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhouming
 * @date 2017年11月21日
 * @module
 * @comment 告警通知service实现
 */
@Service("alarmNoticeServiceImpl")
public class AlarmNoticeServiceImpl extends BaseServiceImpl implements IAlarmNoticeManagerService {

    //日志记录对象
    private static final Logger log = Logger.getLogger(AlarmNoticeServiceImpl.class);

    @Autowired
    @Qualifier("hibernateDao")
    private IBaseDao <AlarmNotice> hibernateDao;


    //列表查询
    @Override
    public PageUtil getPageList(AlarmNotice param, PageUtil pageUtil) {

        //查询参数构造
        List <Criterion> paramList = new ArrayList <Criterion>();
        if (!StringUtil.isEmpty(param.getNoticeName())) {
            paramList.add(Restrictions.like("noticeName", param.getNoticeName(), MatchMode.ANYWHERE));
        }

        if (!StringUtil.isEmpty(param.getNoticeStatus())) {
            paramList.add(Restrictions.eq("noticeStatus", param.getNoticeStatus()));
        }

        if (!StringUtil.isEmpty(param.getCreateDateStr())) {
            paramList.add(Restrictions.gt("createDate", DateUtil.strToDate(param.getCreateDateStr(), DateUtil.DATE_STYLE_DATE_1)));
        }

        if (!StringUtil.isEmpty(param.getUpdateDateStr())) {
            paramList.add(Restrictions.lt("createDate", DateUtil.strToDate(param.getUpdateDateStr(), DateUtil.DATE_STYLE_DATE_1)));
        }


        List <Order> order = new ArrayList <Order>();
        order.add(Order.desc("createDate"));

        //获取总记录数
        int count = this.hibernateDao.getCount(AlarmNotice.class, paramList, null);

        pageUtil.setTotalRecordNumber(count);

        if (pageUtil.fetchPaging()) {
            //开始获取分页数据
            List <AlarmNotice> resultList = hibernateDao.findByPage(AlarmNotice.class, paramList, (pageUtil.getPage() - 1) * pageUtil.getRows(), pageUtil.getRows(), order, null);
            for (AlarmNotice alarm : resultList) {
                if (alarm.getCreateDate() != null) {
                    alarm.setCreateDateStr(DateUtil.dateToString(alarm.getCreateDate(), "yyyy-MM-dd HH:hh:ss"));
                    if (alarm.getNoticeName().equals(Alarm.ALARM_TYPE_JYZXT)) {
                        alarm.setNoticeName("交易子系统");
                    } else if (alarm.getNoticeName().equals(Alarm.ALARM_TYPE_JYWJHSJ)) {
                        alarm.setNoticeName("交易文件和数据");
                    } else if (alarm.getNoticeName().equals(Alarm.ALARM_TYPE_TXYWXTXXSF)) {
                        alarm.setNoticeName("通信业务系统消息收发");
                    } else {
                        alarm.setNoticeName("通信业务系统FTP文件");
                    }

                    if (alarm.getNoticeStatus().equals("0")) {
                        alarm.setNoticeStatus("待处理");
                    } else {
                        alarm.setNoticeStatus("已处理");
                    }

                    long d = (new Date().getTime() - alarm.getCreateDate().getTime()) / (1000);
                    if (d < 3600) {
                        alarm.setNoticeDuration(String.valueOf(d / 60) + "分钟");
                    } else {
                        alarm.setNoticeDuration(String.valueOf(d / 3600) + "小时");
                    }

                }
                if (alarm.getUpdateDate() != null) {
                    alarm.setUpdateDateStr(DateUtil.dateToString(alarm.getUpdateDate(), "yyyy-MM-dd HH:hh:ss"));
                }


            }
            pageUtil.setResultList(resultList);

        }

        return pageUtil;
    }

}
