package com.bluedon.monitor.project.service.communication.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.communication.LogFtpService;
import com.bluedon.monitor.system.entity.TbCommonDepart;

@Service
public class LogFtpServiceImpl implements LogFtpService {
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonDepart> hibernateDao;

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
	public List<TransferTable> getTableList() {
		return hibernateDao.queryForList("SELECT t FROM TransferTable t WHERE t.stat = 0", new Object[]{}, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getLogFtpPageList(String ftpIp,String fileName,String startT1,String startT2) {
		String sql = "SELECT a.FTP_IP FTP_IP,a.FILENAME,"
						+ "MAX(CASE a.OPER_CODE WHEN 0 THEN a.num ELSE 0 END) SUCCESS,"
						+ "MAX(CASE a.OPER_CODE WHEN 1 THEN a.num ELSE 0 END) FAILURE "
					+ "FROM "
					+ "("
						+ "SELECT w.FTP_IP,w.FILENAME,w.OPER_CODE,w.START_DATETIME,COUNT(0)num "
						+ "FROM cm_log_ftp_dt w WHERE w.OPER_CODE=0 OR w.OPER_CODE=1 GROUP BY w.FTP_IP,w.OPER_CODE "
					+ ")a "
					+ "WHERE a.FTP_IP LIKE '%ftpIp%' AND a.FILENAME LIKE '%fileName%' AND (a.START_DATETIME BETWEEN 'T1' AND 'T2') "
					+ "GROUP BY a.FTP_IP;";
		sql = sql.replace("ftpIp", ftpIp).replace("fileName", fileName).replace("T1", startT1).replace("T2", startT2);
		return hibernateDao.selectBySql(sql);
	}
	
}
