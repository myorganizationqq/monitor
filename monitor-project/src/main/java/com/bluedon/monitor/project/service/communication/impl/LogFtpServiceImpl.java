package com.bluedon.monitor.project.service.communication.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.communication.CmLogFtpDT;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.communication.LogFtpService;
import com.bluedon.monitor.system.service.BaseServiceImpl;

@Service
public class LogFtpServiceImpl extends BaseServiceImpl implements LogFtpService {
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<CmLogFtpDT> hibernateDao;

	@SuppressWarnings("unchecked")
	public String getLatestFtpDatetime() {
		List<Map<String, Object>> list = hibernateDao.selectBySql("SELECT MAX(FTP_DATETIME) FTP_DATETIME FROM cm_log_ftp_dt LIMIT 1;");
		if(list != null && list.size() > 0) {
			Object ftpDatetime = list.get(0).get("FTP_DATETIME");
			return ftpDatetime == null ? null : ftpDatetime.toString().substring(0, 19);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TransferTable> getTableList(int stat) {
		return hibernateDao.queryForList("SELECT t FROM TransferTable t WHERE t.stat = ?", new Object[]{stat}, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLogFtpPageList(CmLogFtpDT param, PageUtil pageUtil) {
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("preDay"));
			param.setDateTime2(map.get("currentDay"));
		}
		String sql = "SELECT w.FTP_IP,p.line_name,COUNT(0) NUM,"
				   + "CASE WHEN w.OPER_CODE=1 THEN '下载' ELSE '上传' END OPER_CODE,"
				   + "CASE WHEN w.RESULT=1 THEN '成功' ELSE '失败' END RESULT "
				   + "FROM cm_log_ftp_dt w, op_prm_lcc_dt p "
				   + "WHERE w.FTP_IP = p.lcc_ip AND w.FTP_IP LIKE '%ftpIp%' AND p.line_name LIKE '%lineName%'"
				   + " AND (w.START_DATETIME BETWEEN 'T1' AND 'T2') "
				   + "GROUP BY w.FTP_IP,w.RESULT,w.OPER_CODE LIMIT start, length;";
		
		Integer start = (pageUtil.getPage() - 1) * pageUtil.getRows();
		
		sql = sql.replace("ftpIp", param.getFtpIp()==null ? "" : param.getFtpIp()).replace("lineName", param.getLineName()==null ? "" : param.getLineName())
				 .replace("T1", param.getDateTime1()).replace("T2", param.getDateTime2())
				 .replace("start", start.toString()).replace("length", pageUtil.getRows()+"");
		
		return hibernateDao.selectBySql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public int getAllCount(CmLogFtpDT param, PageUtil pageUtil) {
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("preDay"));
			param.setDateTime2(map.get("currentDay"));
		}
		String sql = "SELECT w.FTP_IP,p.line_name,COUNT(0) NUM,"
				   + "CASE WHEN w.OPER_CODE=1 THEN '下载' ELSE '上传' END OPER_CODE,"
				   + "CASE WHEN w.RESULT=1 THEN '成功' ELSE '失败' END RESULT "
				   + "FROM cm_log_ftp_dt w, op_prm_lcc_dt p "
				   + "WHERE w.FTP_IP = p.lcc_ip AND w.FTP_IP LIKE '%ftpIp%' AND p.line_name LIKE '%lineName%'"
				   + " AND (w.START_DATETIME BETWEEN 'T1' AND 'T2') "
				   + "GROUP BY w.FTP_IP,w.RESULT,w.OPER_CODE";
		
		sql = sql.replace("ftpIp", param.getFtpIp()==null ? "" : param.getFtpIp()).replace("lineName", param.getLineName()==null ? "" : param.getLineName())
				 .replace("T1", param.getDateTime1()).replace("T2", param.getDateTime2());
		
		List<Map<String, Object>> list = hibernateDao.selectBySql(sql);
		return list !=null ? list.size() : 0;
	}

	@Override
	public PageUtil getPageList(CmLogFtpDT param, PageUtil pageUtil) {
		List<Map<String, Object>> list = getLogFtpPageList(param, pageUtil);

		// 获取总记录数
		int count = getAllCount(param, pageUtil);

		pageUtil.setTotalRecordNumber(count);

		if (pageUtil.fetchPaging()) {
			pageUtil.setResultList(list);
		}
		return pageUtil;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getTopLogFtp(CmLogFtpDT param) {
		if("".equals(param.getDateTime1()) || "".equals(param.getDateTime2())) {
			Map<String,String> map = CommonUtil.getCurrentAndPreTime();
			param.setDateTime1(map.get("preDay"));
			param.setDateTime2(map.get("currentDay"));
		}
		String sql = "SELECT p.line_name,COUNT(1) NUM FROM cm_log_ftp_dt f,op_prm_lcc_dt p WHERE p.lcc_ip = f.FTP_IP AND f.result=%s AND f.FTP_DATETIME BETWEEN '%s' AND '%s' GROUP BY f.FTP_IP ORDER BY NUM DESC LIMIT 5;";
		List<Map<String, Object>> list = hibernateDao.selectBySql(String.format(sql, param.getResult(), param.getDateTime1(), param.getDateTime2()));
		
		Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> stringObjectMap : list) {
            String ftpIp = String.valueOf(stringObjectMap.get("line_name"));
            int num = Integer.valueOf(String.valueOf(stringObjectMap.get("NUM")));
            map.put(ftpIp, num);
        }
        return map;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Integer>> alarmCount() {
		String sql0 = "SELECT FTP_IP,COUNT(1) NUM FROM cm_log_ftp_dt WHERE result=0 AND FTP_DATETIME BETWEEN 'T1' AND 'T2' GROUP BY FTP_IP;";
		String sql1 = "SELECT FTP_IP,COUNT(1) NUM FROM cm_log_ftp_dt WHERE result=1 AND FTP_DATETIME BETWEEN 'T1' AND 'T2' GROUP BY FTP_IP;";

		Map<String,String> t = CommonUtil.getCurrentAndPreTime();

		String T1 = t.get("preDay");
		String T2 = t.get("currentDay");

		sql0 = sql0.replace("T1", T1).replace("T2", T2);
		sql1 = sql1.replace("T1", T1).replace("T2", T2);

		List<Map<String, Object>> list0 = hibernateDao.selectBySql(sql0);
		List<Map<String, Object>> list1 = hibernateDao.selectBySql(sql1);

		Map<String, Integer> map0 = new HashMap<>();
		Map<String, Integer> map1 = new HashMap<>();

		for (Map<String, Object> stringObjectMap : list0) {
			String ftpIp = String.valueOf(stringObjectMap.get("FTP_IP"));
			int num = Integer.valueOf(String.valueOf(stringObjectMap.get("NUM")));
			map0.put(ftpIp, num);
		}
		for (Map<String, Object> stringObjectMap : list1) {
			String ftpIp = String.valueOf(stringObjectMap.get("FTP_IP"));
			int num = Integer.valueOf(String.valueOf(stringObjectMap.get("NUM")));
			map1.put(ftpIp, num);
		}

		List <Map <String, Integer>> result = new ArrayList <>();
		result.add(map0);
		result.add(map1);
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getStatisticData(String noticeName,String beginDate,String endDate) {
		String sql = "SELECT notice_index FROM alarm_notice WHERE notice_name = 'tN'"
				   + " AND DATE_FORMAT(DATE_ADD(create_date,INTERVAL -1 DAY),'%Y-%m-%d') BETWEEN 'T1' AND 'T2'";
		List<Map<String, Object>> list = hibernateDao.selectBySql(sql.replace("tN", noticeName).replace("T1", beginDate).replace("T2", endDate));
        HashMap<String, Integer> dataMap = new HashMap<>();
        for (Map<String, Object> map : list) {
            String noticeIndex = map.get("notice_index").toString();
            if (StringUtils.isNotBlank(noticeIndex)){
                String[] strArr = noticeIndex.split("，");
                for (String str : strArr) {
                    try {
                        String[] val = str.split("：");
                        String key = val[0];
                        int value = Integer.valueOf(val[1]);
                        dataMap.put(key, dataMap.get(key) == null ? value : dataMap.get(key) + value);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
		return dataMap;
	}

}
