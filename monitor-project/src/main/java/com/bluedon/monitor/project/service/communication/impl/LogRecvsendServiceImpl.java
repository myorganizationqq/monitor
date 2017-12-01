package com.bluedon.monitor.project.service.communication.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.CommonUtil;
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
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("currentDay"));
			param.setDateTime2(map.get("preDay"));
		}
		String sql = "SELECT a.LINK_IP,MAX(a.MSG_LENGTH) MSG_LENGTH,"
						+ "MAX(CASE a.RESULT WHEN 0 THEN a.num ELSE 0 END) SUCCESS,"
						+ "MAX(CASE a.RESULT WHEN 1 THEN a.num ELSE 0 END) FAILURE "
					+ "FROM "
					+ "("
						+ "SELECT w.LINK_IP,w.RESULT,COUNT(0)num,w.MSG_LENGTH,w.SERVER_CODE,w.RECD_DATETIME "
						+ "FROM cm_log_recv_send_dt w WHERE w.RESULT=0 OR w.RESULT=1 GROUP BY w.LINK_IP,w.RESULT "
					+ ")a "
					+ "WHERE a.LINK_IP IS NOT NULL AND a.LINK_IP LIKE '%linkIp%' AND a.SERVER_CODE LIKE '%serverCode%'"
					+ " AND (a.RECD_DATETIME BETWEEN 'T1' AND 'T2') "
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
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("currentDay"));
			param.setDateTime2(map.get("preDay"));
		}
		String sql = "SELECT a.LINK_IP,MAX(a.MSG_LENGTH) MSG_LENGTH,"
						+ "MAX(CASE a.RESULT WHEN 0 THEN a.num ELSE 0 END) SUCCESS,"
						+ "MAX(CASE a.RESULT WHEN 1 THEN a.num ELSE 0 END) FAILURE "
					+ "FROM "
					+ "("
						+ "SELECT w.LINK_IP,w.RESULT,COUNT(0)num,w.MSG_LENGTH,w.SERVER_CODE,w.RECD_DATETIME "
						+ "FROM cm_log_recv_send_dt w WHERE w.RESULT=0 OR w.RESULT=1 GROUP BY w.LINK_IP,w.RESULT "
					+ ")a "
					+ "WHERE a.LINK_IP IS NOT NULL AND a.LINK_IP LIKE '%linkIp%' AND a.SERVER_CODE LIKE '%serverCode%'"
					+ " AND (a.RECD_DATETIME BETWEEN 'T1' AND 'T2') "
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

	@SuppressWarnings("unchecked")
	public Map<String, Object> getTopLogRecvsend(CmLogRecvsendDT param) {
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("currentDay"));
			param.setDateTime2(map.get("preDay"));
		}
		String sql = "SELECT LINK_IP,COUNT(1) NUM FROM cm_log_recv_send_dt WHERE LINK_IP IS NOT NULL AND RESULT=%s AND RECD_DATETIME BETWEEN '%s' AND '%s' GROUP BY LINK_IP ORDER BY NUM DESC LIMIT 5;";
		List<Map<String, Object>> list = hibernateDao.selectBySql(String.format(sql, param.getResult(), param.getDateTime1(), param.getDateTime2()));
		
		Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> objMap : list) {
            String linkIp = String.valueOf(objMap.get("LINK_IP"));
            int num = Integer.valueOf(String.valueOf(objMap.get("NUM")));
            map.put(linkIp, num);
        }
        return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getTopTotalCount(CmLogRecvsendDT param) {
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("currentDay"));
			param.setDateTime2(map.get("preDay"));
		}
		String sql = "SELECT LINK_IP,COUNT(1) NUM FROM cm_log_recv_send_dt WHERE LINK_IP IS NOT NULL AND RECD_DATETIME BETWEEN '%s' AND '%s' GROUP BY LINK_IP ORDER BY NUM DESC LIMIT 5;";
		List<Map<String, Object>> list = hibernateDao.selectBySql(String.format(sql, param.getDateTime1(), param.getDateTime2()));
		
		Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> objMap : list) {
            String linkIp = String.valueOf(objMap.get("LINK_IP"));
            int num = Integer.valueOf(String.valueOf(objMap.get("NUM")));
            map.put(linkIp, num);
        }
        return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getTopMaxMsgLength(CmLogRecvsendDT param) {
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("currentDay"));
			param.setDateTime2(map.get("preDay"));
		}
		String sql = "SELECT LINK_IP,MAX(MSG_LENGTH) NUM FROM cm_log_recv_send_dt WHERE LINK_IP IS NOT NULL AND RECD_DATETIME BETWEEN '%s' AND '%s' GROUP BY LINK_IP ORDER BY NUM DESC LIMIT 5;";
		List<Map<String, Object>> list = hibernateDao.selectBySql(String.format(sql, param.getDateTime1(), param.getDateTime2()));
		
		Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> objMap : list) {
            String linkIp = String.valueOf(objMap.get("LINK_IP"));
            int num = Integer.valueOf(String.valueOf(objMap.get("NUM")));
            map.put(linkIp, num);
        }
        return map;
	}

}
