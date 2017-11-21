package com.bluedon.monitor.project.service.communication;

import java.util.List;
import java.util.Map;

import com.bluedon.monitor.project.entity.transferTable.TransferTable;

public interface LogFtpService {
	String getLatestFtpDatetime();
	List<TransferTable> getTableList();
	List<Map<String, String>> getLogFtpPageList(String ftpIp,String fileName,String startT1,String startT2);
}
