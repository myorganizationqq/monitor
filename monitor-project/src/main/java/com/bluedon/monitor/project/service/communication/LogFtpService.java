package com.bluedon.monitor.project.service.communication;

import java.util.List;

import com.bluedon.monitor.project.entity.transferTable.TransferTable;

public interface LogFtpService {
	String getLatestFtpDatetime();
	List<TransferTable> getTableList();
	
}
