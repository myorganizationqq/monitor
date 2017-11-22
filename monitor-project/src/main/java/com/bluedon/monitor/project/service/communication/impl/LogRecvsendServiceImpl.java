package com.bluedon.monitor.project.service.communication.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.communication.CmLogRecvsendDT;
import com.bluedon.monitor.project.service.communication.LogRecvsendService;

@Service
public class LogRecvsendServiceImpl implements LogRecvsendService {
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<CmLogRecvsendDT> hibernateDao;
	
	@SuppressWarnings("unchecked")
	public String getLatestRecdDatetime() {
		List<Map<String, Object>> list = hibernateDao.selectBySql("SELECT MAX(RECD_DATETIME) RECD_DATETIME FROM cm_log_recv_send_dt LIMIT 1;");
		if(list != null && list.size() > 0) {
			Object recdDatetime = list.get(0).get("RECD_DATETIME");
			return recdDatetime == null ? null : recdDatetime.toString().substring(0, 19);
		}
		return null;
	}

	@Override
	public PageUtil getPageList(CmLogRecvsendDT param, PageUtil pageUtil) {
		// 查询参数构造
		List<Criterion> paramList = new ArrayList<Criterion>();
		if (!StringUtil.isEmpty(param.getLinkIp())) {
			paramList.add(Restrictions.like("linkIp", param.getLinkIp(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isEmpty(param.getServerCode())) {
			paramList.add(Restrictions.like("serverCode", param.getServerCode(), MatchMode.ANYWHERE));
		}

		List<Order> order = new ArrayList<Order>();
		// order.add(Order.asc("createTime"));

		// 获取总记录数
		int count = hibernateDao.getCount(CmLogRecvsendDT.class, paramList, null);

		pageUtil.setTotalRecordNumber(count);

		if (pageUtil.fetchPaging()) {
			// 开始获取分页数据
			List<CmLogRecvsendDT> resultList = hibernateDao.findByPage(CmLogRecvsendDT.class, paramList,
					(pageUtil.getPage() - 1) * pageUtil.getRows(), pageUtil.getRows(), order, null);
			for (CmLogRecvsendDT model : resultList) {
				// if (alarm.getCreateDate() != null) {
				// alarm.setCreateDateStr(DateUtil.dateToString(alarm.getCreateDate(),
				// "yyyy-MM-dd HH:hh:ss"));
				// }
				// if (alarm.getUpdateDate() != null) {
				// alarm.setUpdateDateStr(DateUtil.dateToString(alarm.getUpdateDate(),
				// "yyyy-MM-dd HH:hh:ss"));
				// }
			}
			pageUtil.setResultList(resultList);
		}
		return pageUtil;
	}
	
}
