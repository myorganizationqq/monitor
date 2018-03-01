package com.bluedon.monitor.project.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bluedon.monitor.project.common.ConnectVO;
import com.bluedon.monitor.project.common.Oracle2Mysql;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.communication.LogFtpService;
import com.bluedon.monitor.project.service.communication.LogRecvsendService;
import com.bluedon.monitor.project.util.PropertiesUtil;

@Component("scheduledManager")
@Lazy(value = false)
public class ScheduledManager {
	// 日志记录对象
	private static final Logger log = Logger.getLogger(ScheduledManager.class);
	@Autowired
	protected JdbcTemplate jdbcTemplate;
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
	@Scheduled(cron = "0 0 0 * * ?")
	public void dealDataTask() throws Exception {
		log.info(simpleDateFormat.format(new Date()) + " ：开始执行任务cm_log_ftp、cm_log_recv_send...");
		
		List<TransferTable> list = logFtpService.getTableList(0);
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
			Oracle2Mysql.tableInput(table, oracleConnectVO);
		}
		log.info(simpleDateFormat.format(new Date()) + " ：结束执行任务。");
	}
	
	@SuppressWarnings("deprecation")
	@Scheduled(cron = "0 0 0 * * ?")
	public void dealLCCDataTask() throws Exception {
		log.info(simpleDateFormat.format(new Date()) + " ：开始执行任务op_prm_dev_code、op_prm_lcc_line、op_prm_line_tram...");
		
		List<TransferTable> list = logFtpService.getTableList(2);
		
		ConnectVO oracleConnVO = new ConnectVO(PropertiesUtil.getValue("oracle.driverClassName"),
				PropertiesUtil.getValue("op.oracle.url"), PropertiesUtil.getValue("op.oracle.username"),
				PropertiesUtil.getValue("op.oracle.password"));
		
		jdbcTemplate.execute("delete from op_prm_lcc_dt");
		
		for(TransferTable table : list) {
			if("op_prm_dev_code".equals(table.getImpTableName())) {
				dealOpPrmDevCodeData(table, oracleConnVO);
			} else {
				Oracle2Mysql.tableInput(table, oracleConnVO);
			}
		}
		log.info(simpleDateFormat.format(new Date()) + " ：结束执行任务。");
	}
	
	@SuppressWarnings("deprecation")
	public void dealOpPrmDevCodeData(TransferTable table, ConnectVO connectVO) {
		List<Map<String, String>> list = Oracle2Mysql.getOpPrmDevCodeListData(table, connectVO);
		if(list == null) return;
		String[] sqls = new String[ list.size() ];
		String sql = "insert into op_prm_lcc_dt(line_id,line_name,lcc_ip) values('%s','%s','%s')";
		for (int i = 0; i < list.size(); i++) {
			String lineName = list.get(i).get("LINE_ID") + list.get(i).get("STATION_ID")
					+ list.get(i).get("DEV_TYPE_ID") + list.get(i).get("DEVICE_ID");
			sqls[i] = String.format(sql, list.get(i).get("LINE_ID"), lineName, list.get(i).get("IP_ADDRESS"));
		}
		jdbcTemplate.batchUpdate(sqls);
		log.info(table.getImpTableName() + ":" + list.size());
	}

}