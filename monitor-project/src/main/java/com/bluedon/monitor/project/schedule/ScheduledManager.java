package com.bluedon.monitor.project.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bluedon.monitor.project.common.ConnectVO;
import com.bluedon.monitor.project.common.Oracle2Mysql;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.communication.LogFtpService;
import com.bluedon.monitor.project.service.communication.LogRecdsendService;
import com.bluedon.monitor.project.util.PropertiesUtil;

@Component("scheduledManager")
@Lazy(value = true)
public class ScheduledManager {
	// 日志记录对象
	private static final Logger log = Logger.getLogger(ScheduledManager.class);
	@Autowired
	private LogFtpService logFtpService;
	@Autowired
	private LogRecdsendService logRecdsendService;
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 处理消息收发日志数据 0 0/30 * * * ?
	 * 1、先分别查询本地表中最大时间记录  2、按最大时间查询远程表数据  3、封装查询数据条件  4、处理入库
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Scheduled(cron = "0/5 * * * * ?")
	public void dealDataTask() throws Exception {
		log.info(simpleDateFormat.format(new Date()) + " ：开始执行任务...");
		
		List<TransferTable> list = logFtpService.getTableList();
		String latestFtpDatetime = logFtpService.getLatestFtpDatetime();
		String latestRecdDatetime = logRecdsendService.getLatestRecdDatetime();
		ConnectVO oracleConnectVO = new ConnectVO(PropertiesUtil.getValue("oracle.driverClassName"),
				PropertiesUtil.getValue("cm.oracle.url"), PropertiesUtil.getValue("cm.oracle.username"),
				PropertiesUtil.getValue("cm.oracle.password"));
		
		for(TransferTable table : list) {
			if("cm_log_ftp".equals(table.getImpTableName()) && latestFtpDatetime != null) {
				table.setExtraSql("FTP_DATETIME > " + "'" + latestFtpDatetime + "'");
			}
			if("cm_log_recv_send".equals(table.getImpTableName()) && latestRecdDatetime != null) {
				table.setExtraSql("RECD_DATETIME > " + "'" + latestRecdDatetime + "'");
			}
			Oracle2Mysql.tableInput(table, oracleConnectVO);
		}
		log.info(simpleDateFormat.format(new Date()) + " ：结束执行任务。");
	}

}