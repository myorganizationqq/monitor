package com.bluedon.monitor.project.service.communication.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
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
	public List<TransferTable> getTableList() {
		return hibernateDao.queryForList("SELECT t FROM TransferTable t WHERE t.stat = 0", new Object[]{}, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLogFtpPageList(CmLogFtpDT param, PageUtil pageUtil) {
		String sql = "SELECT w.FTP_IP,w.FILENAME,COUNT(0) NUM,"
				   + "CASE WHEN w.OPER_CODE=1 THEN '下载' ELSE '上传' END OPER_CODE,"
				   + "CASE WHEN w.RESULT=1 THEN '成功' ELSE '失败' END RESULT "
				   + "FROM cm_log_ftp_dt w "
				   + "WHERE w.FTP_IP LIKE '%ftpIp%' AND w.FILENAME LIKE '%fileName%'"
				   + (param.getDateTime1()!="" && param.getDateTime2()!="" ? " AND (w.START_DATETIME BETWEEN 'T1' AND 'T2') " : " ")
				   + "GROUP BY w.FTP_IP,w.RESULT,w.OPER_CODE LIMIT start, length;";
		
		Integer start = (pageUtil.getPage() - 1) * pageUtil.getRows();
		
		sql = sql.replace("ftpIp", param.getFtpIp()==null ? "" : param.getFtpIp()).replace("fileName", param.getFileName()==null ? "" : param.getFileName())
				 .replace("T1", param.getDateTime1()).replace("T2", param.getDateTime2())
				 .replace("start", start.toString()).replace("length", pageUtil.getRows()+"");
		
		return hibernateDao.selectBySql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public int getAllCount(CmLogFtpDT param, PageUtil pageUtil) {
		String sql = "SELECT w.FTP_IP,w.FILENAME,COUNT(0) NUM,"
				   + "CASE WHEN w.OPER_CODE=1 THEN '下载' ELSE '上传' END OPER_CODE,"
				   + "CASE WHEN w.RESULT=1 THEN '成功' ELSE '失败' END RESULT "
				   + "FROM cm_log_ftp_dt w "
				   + "WHERE w.FTP_IP LIKE '%ftpIp%' AND w.FILENAME LIKE '%fileName%'"
				   + (param.getDateTime1()!="" && param.getDateTime2()!="" ? " AND (w.START_DATETIME BETWEEN 'T1' AND 'T2') " : " ")
				   + "GROUP BY w.FTP_IP,w.RESULT,w.OPER_CODE";
		
		sql = sql.replace("ftpIp", param.getFtpIp()==null ? "" : param.getFtpIp()).replace("fileName", param.getFileName()==null ? "" : param.getFileName())
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
	public Map<String, Object> getTopLogFtp(int result) {
		String sql = "SELECT FTP_IP,COUNT(1) NUM FROM cm_log_ftp_dt WHERE result=%s GROUP BY FTP_IP ORDER BY NUM DESC LIMIT 5;";
		List<Map<String, Object>> list = hibernateDao.selectBySql(String.format(sql, result));
		
		Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> stringObjectMap : list) {
            String ftpIp = String.valueOf(stringObjectMap.get("FTP_IP"));
            int num = Integer.valueOf(String.valueOf(stringObjectMap.get("NUM")));
            map.put(ftpIp, num);
        }
        return map;
	}
	
	
}
