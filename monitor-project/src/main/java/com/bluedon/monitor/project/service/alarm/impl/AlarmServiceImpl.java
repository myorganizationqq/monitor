package com.bluedon.monitor.project.service.alarm.impl;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.service.alarm.IAlarmManagerService;
import com.bluedon.monitor.system.service.BaseServiceImpl;
import com.bluedon.monitor.system.service.common.impl.LoginLogServiceImpl;
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
 * @comment 告警设置service实现
 */
@Service("alarmServiceImpl")
public class AlarmServiceImpl extends BaseServiceImpl implements IAlarmManagerService {

	//日志记录对象
	private static final Logger log = Logger.getLogger(LoginLogServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<Alarm> hibernateDao;


	//列表查询
	@Override
	public PageUtil getPageList(Alarm param, PageUtil pageUtil) {

		//查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if(!StringUtil.isEmpty(param.getAlarmName())){
			paramList.add(Restrictions.like("alarmName", param.getAlarmName(), MatchMode.ANYWHERE));
		}

		if(!StringUtil.isEmpty(param.getAlarmStatus())){
			paramList.add(Restrictions.eq("alarmStatus", param.getAlarmStatus()));
		}


		List<Order> order=new ArrayList<Order>();

		//获取总记录数
		int count = this.hibernateDao.getCount(Alarm.class, paramList,null);

		pageUtil.setTotalRecordNumber(count);

		if(pageUtil.fetchPaging()){
			//开始获取分页数据
			List<Alarm> resultList = hibernateDao.findByPage(Alarm.class,paramList, (pageUtil.getPage()-1)*pageUtil.getRows(), pageUtil.getRows(),order,null);
			for(Alarm alarm : resultList){
				if (alarm.getCreateDate() != null) {
					alarm.setCreateDateStr(DateUtil.dateToString(alarm.getCreateDate(), "yyyy-MM-dd HH:hh:ss"));
				}
				if (alarm.getUpdateDate() != null) {
					alarm.setUpdateDateStr(DateUtil.dateToString(alarm.getUpdateDate(), "yyyy-MM-dd HH:hh:ss"));
				}

					alarm.setAlarmCronTrigger(alarm.getAlarmCronTriggerHour()+"点"+alarm.getAlarmCronTriggerStart()+"分");

			}
			pageUtil.setResultList(resultList);

		}

		return pageUtil;
	}

}
