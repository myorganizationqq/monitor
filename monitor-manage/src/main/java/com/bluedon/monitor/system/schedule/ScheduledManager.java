package com.bluedon.monitor.system.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bluedon.monitor.system.service.communication.LogRecdsendService;

@Component("scheduledManager")
@Lazy(value = true)
public class ScheduledManager {
	// 日志记录对象
	private static final Logger log = Logger.getLogger(ScheduledManager.class);
	@Autowired
	private LogRecdsendService logRecdsendService;
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 任务一
	@Scheduled(cron = "0 0/30 * * * ?")
	public void autoCardCalculate() {
		String latestRecdDatime = logRecdsendService.getLatestRecdDatime();
		System.out.println(latestRecdDatime);
		
		System.out.println(simpleDateFormat.format(new Date()) + " ：执行中任务1……");
	}

}