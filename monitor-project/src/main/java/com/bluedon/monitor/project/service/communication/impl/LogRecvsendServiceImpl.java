package com.bluedon.monitor.project.service.communication.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.PageUtil;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLogRecvsendPageList(CmLogRecvsendDT param, PageUtil pageUtil) {
		String sql = "SELECT a.LINK_IP,MAX(a.MSG_LENGTH) MSG_LENGTH,"
						+ "MAX(CASE a.RESULT WHEN 0 THEN a.num ELSE 0 END) SUCCESS,"
						+ "MAX(CASE a.RESULT WHEN 1 THEN a.num ELSE 0 END) FAILURE "
					+ "FROM "
					+ "("
						+ "SELECT w.LINK_IP,w.RESULT,COUNT(0)num,w.MSG_LENGTH,w.SERVER_CODE,w.RECD_DATETIME "
						+ "FROM cm_log_recv_send_dt w WHERE w.RESULT=0 OR w.RESULT=1 GROUP BY w.LINK_IP,w.RESULT "
					+ ")a "
					+ "WHERE a.LINK_IP IS NOT NULL AND a.LINK_IP LIKE '%linkIp%' AND a.SERVER_CODE LIKE '%serverCode%'"
					+ (param.getDateTime1()!="" && param.getDateTime2()!="" ? " AND (a.RECD_DATETIME BETWEEN 'T1' AND 'T2') " : " ")
					+ "GROUP BY a.LINK_IP LIMIT start, length;";

		Integer start = (pageUtil.getPage() - 1) * pageUtil.getRows();

		sql = sql.replace("linkIp", param.getLinkIp() == null ? "" : param.getLinkIp())
				.replace("serverCode", param.getServerCode() == null ? "" : param.getServerCode())
				.replace("T1", param.getDateTime1()).replace("T2", param.getDateTime2())
				.replace("start", start.toString()).replace("length", pageUtil.getRows() + "");

		return hibernateDao.selectBySql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public int getAllCount(CmLogRecvsendDT param, PageUtil pageUtil) {
		String sql = "SELECT a.LINK_IP,MAX(a.MSG_LENGTH) MSG_LENGTH,"
						+ "MAX(CASE a.RESULT WHEN 0 THEN a.num ELSE 0 END) SUCCESS,"
						+ "MAX(CASE a.RESULT WHEN 1 THEN a.num ELSE 0 END) FAILURE "
					+ "FROM "
					+ "("
						+ "SELECT w.LINK_IP,w.RESULT,COUNT(0)num,w.MSG_LENGTH,w.SERVER_CODE,w.RECD_DATETIME "
						+ "FROM cm_log_recv_send_dt w WHERE w.RESULT=0 OR w.RESULT=1 GROUP BY w.LINK_IP,w.RESULT "
					+ ")a "
					+ "WHERE a.LINK_IP IS NOT NULL AND a.LINK_IP LIKE '%linkIp%' AND a.SERVER_CODE LIKE '%serverCode%'"
					+ (param.getDateTime1()!="" && param.getDateTime2()!="" ? " AND (a.RECD_DATETIME BETWEEN 'T1' AND 'T2') " : " ")
					+ "GROUP BY a.LINK_IP";

		sql = sql.replace("linkIp", param.getLinkIp() == null ? "" : param.getLinkIp())
				.replace("serverCode", param.getServerCode() == null ? "" : param.getServerCode())
				.replace("T1", param.getDateTime1()).replace("T2", param.getDateTime2());

		List<Map<String, Object>> list = hibernateDao.selectBySql(sql);
		
		return list!=null ? list.size() : 0;
	}
	
	@Override
	public PageUtil getPageList(CmLogRecvsendDT param, PageUtil pageUtil) {
		List<Map<String, Object>> list = getLogRecvsendPageList(param, pageUtil);
				
		// 获取总记录数
		int count = getAllCount(param, pageUtil);

		pageUtil.setTotalRecordNumber(count);

		if (pageUtil.fetchPaging()) {
			for(Map<String, Object> map : list) {
				String succ = map.get("SUCCESS").toString();
				String fail = map.get("FAILURE").toString();
				map.put("totalCount",  Integer.parseInt(succ) + Integer.parseInt(fail));
			}
			pageUtil.setResultList(list);
		}
		return pageUtil;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> alarmCount() {
		String sql = "SELECT a.LINK_IP,MAX(a.MSG_LENGTH) MSG_LENGTH,MIN(a.MSG_LENGTH) MIN_LENGTH,"
				+ " MAX(CASE a.RESULT WHEN 0 THEN a.num ELSE 0 END) SUCCESS,"
				+ " MAX(CASE a.RESULT WHEN 1 THEN a.num ELSE 0 END) FAILURE "
				+ " FROM (SELECT w.LINK_IP,w.RESULT,COUNT(0)num,w.MSG_LENGTH,w.SERVER_CODE,w.RECD_DATETIME "
				+ " FROM cm_log_recv_send_dt w WHERE w.RESULT=0 OR w.RESULT=1 GROUP BY w.LINK_IP,w.RESULT ) a "
				+ " WHERE a.LINK_IP IS NOT NULL AND a.RECD_DATETIME BETWEEN 'T1' AND 'T2' "
				+ " GROUP BY a.LINK_IP;";



		Map<String,String> t = CommonUtil.getCurrentAndPreTime();
		String T1 = t.get("currentDay");
		String T2 = t.get("preDay");

		sql = sql.replace("T1", T1).replace("T2", T2);

		return hibernateDao.selectBySql(sql);
	}


}
