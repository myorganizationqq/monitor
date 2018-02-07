package com.bluedon.monitor.project.service.netio;

import java.util.Map;

import com.bluedon.monitor.system.service.IBaseService;

public interface NetioService extends IBaseService {
	Map<String, Object> queryDataByTime(String time);
	
}
