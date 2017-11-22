package com.bluedon.monitor.project.service.communication;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.communication.CmLogRecvsendDT;

public interface LogRecvsendService {
	String getLatestRecdDatetime();
	
	PageUtil getPageList(CmLogRecvsendDT param, PageUtil pageUtil);
}
