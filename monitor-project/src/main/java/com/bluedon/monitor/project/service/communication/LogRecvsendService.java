package com.bluedon.monitor.project.service.communication;

import java.util.List;
import java.util.Map;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.communication.CmLogRecvsendDT;

public interface LogRecvsendService {
	String getLatestRecdDatetime();
	
	PageUtil getPageList(CmLogRecvsendDT param, PageUtil pageUtil);
	
	List<Map<String, Object>> getLogRecvsendPageList(CmLogRecvsendDT param, PageUtil pageUtil);

	List<Map<String, Object>> alarmCount();
}
