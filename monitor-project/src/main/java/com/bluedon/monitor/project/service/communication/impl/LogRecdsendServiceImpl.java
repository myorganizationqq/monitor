package com.bluedon.monitor.project.service.communication.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.project.service.communication.LogRecdsendService;
import com.bluedon.monitor.system.entity.TbCommonDepart;

@Service
public class LogRecdsendServiceImpl implements LogRecdsendService {
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonDepart> hibernateDao;
	
	@SuppressWarnings("unchecked")
	public String getLatestRecdDatetime() {
		List<Map<String, Object>> list = hibernateDao.selectBySql("SELECT MAX(RECD_DATETIME) RECD_DATETIME FROM cm_log_recv_send_dt LIMIT 1;");
		if(list != null && list.size() > 0) {
			return list.get(0).get("RECD_DATETIME").toString();
		}
		return null;
	}
	
}
