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
import com.bluedon.monitor.project.service.communication.LogRecvsendService;
import com.bluedon.monitor.project.util.PropertiesUtil;

@Component("scheduledManager")
@Lazy(value = true)
public class ScheduledManager {
	// 日志记录对象
	private static final Logger log = Logger.getLogger(ScheduledManager.class);
	@Autowired
	private LogFtpService logFtpService;
	@Autowired
	private LogRecvsendService logRecvsendService;
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 处理消息收发日志、FTP文件数据 0 0/30 * * * ?
	 * 1、先分别查询本地表中最大时间记录  2、设置查询条件查询远程数据  3、处理入库
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Scheduled(cron = "0 0/2 * * * ?")
	public void dealDataTask() throws Exception {
		log.info(simpleDateFormat.format(new Date()) + " ：开始执行任务...");
		
		List<TransferTable> list = logFtpService.getTableList();
		String latestFtpDatetime = logFtpService.getLatestFtpDatetime();
		String latestRecvDatetime = logRecvsendService.getLatestRecdDatetime();
		
		ConnectVO oracleConnectVO = new ConnectVO(PropertiesUtil.getValue("oracle.driverClassName"),
				PropertiesUtil.getValue("cm.oracle.url"), PropertiesUtil.getValue("cm.oracle.username"),
				PropertiesUtil.getValue("cm.oracle.password"));
		
		// 设置查询Oracle远程库的条件
		String sql = "%s > TO_DATE('%s','yyyy-mm-dd HH24:MI:SS')";
		
		for(TransferTable table : list) {
			if("cm_log_ftp".equals(table.getImpTableName()) && latestFtpDatetime != null) {
				table.setExtraSql(String.format(sql, "FTP_DATETIME", latestFtpDatetime));
			}
			if("cm_log_recv_send".equals(table.getImpTableName()) && latestRecvDatetime != null) {
				table.setExtraSql(String.format(sql, "RECD_DATETIME", latestRecvDatetime));
			}
			List<List<String>> resultList = Oracle2Mysql.tableInput(table, oracleConnectVO);
			log.info(table.getImpTableName() + "表：共有" + resultList.size() + "条记录入库...");
		}
		log.info(simpleDateFormat.format(new Date()) + " ：结束执行任务。");
	}

}