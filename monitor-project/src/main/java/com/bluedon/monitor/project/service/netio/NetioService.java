package com.bluedon.monitor.project.service.netio;

import java.util.List;
import java.util.Map;

import com.bluedon.monitor.system.service.IBaseService;

public interface NetioService extends IBaseService {
	Map<String, Object> queryDataByTime(String time);
	List<Map<String, Object>> queryData(String serverInfoId);
	Map<String, Object> querySNMPInfo(String serverInfoId);
	
}
