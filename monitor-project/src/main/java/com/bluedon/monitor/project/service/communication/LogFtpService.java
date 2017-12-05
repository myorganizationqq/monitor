package com.bluedon.monitor.project.service.communication;

import java.util.List;
import java.util.Map;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.communication.CmLogFtpDT;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.system.service.IBaseService;

public interface LogFtpService extends IBaseService {
	String getLatestFtpDatetime();
	
	List<TransferTable> getTableList();
	
	List<Map<String, Object>> getLogFtpPageList(CmLogFtpDT param, PageUtil pageUtil);
	
	PageUtil getPageList(CmLogFtpDT param, PageUtil pageUtil);
	
	Map<String, Object> getTopLogFtp(CmLogFtpDT param);

	public List<Map<String, Integer>> alarmCount();
	
	Map<String, Object> getStatisticData(String noticeName,String beginDate,String endDate);
}
