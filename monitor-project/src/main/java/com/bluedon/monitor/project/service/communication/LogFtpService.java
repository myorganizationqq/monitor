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
	
	List<Map<String, String>> getLogFtpPageList(String ftpIp,String fileName,String startT1,String startT2);
	
	PageUtil getPageList(CmLogFtpDT param, PageUtil pageUtil);
}
