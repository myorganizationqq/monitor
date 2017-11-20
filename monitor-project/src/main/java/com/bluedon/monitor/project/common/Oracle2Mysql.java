package com.bluedon.monitor.project.common;


import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author JiangFeng on 2017/11/9.
 */
public class Oracle2Mysql {

	private static Connection mysqlConn;
	private static Connection oracleConn;

	public Oracle2Mysql() {

	}

	public static List<List<String>> tableInput(TransferTable transferTable, ConnectVO oracleConnectVO)
			throws Exception {
		List<List<String>> findList = new ArrayList<List<String>>();
		oracleConn = Oracle_con.createIfNon(oracleConn, oracleConnectVO);
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		String sql = "SELECT " + transferTable.getTableColumn() + "  FROM "
				+ transferTable.getImpTableName();
		String extraSql = transferTable.getExtraSql();
		if (StringUtils.isNotBlank(extraSql)) {
			sql += " WHERE " + extraSql;
		}
		try {
			pre = oracleConn.prepareStatement(sql);
			resultSet = pre.executeQuery();
			String[] column = transferTable.getTableColumn().split(",");
			// 设置列数
			transferTable.setColumnNum(column.length);
			int i = 0;
			while (resultSet.next()) {
				List<String> minList = new ArrayList<String>();
				for (String each : column) {
					// MESSAGE为二进制类型，转文本无意义
					String columnVal = ("cm_log_recv_send".equals(transferTable.getImpTableName()) && "MESSAGE".equals(each))
					? null : resultSet.getString(each);
					
					minList.add(columnVal);
				}
				findList.add(minList);
				i ++;
				// 设置的每次提交大小为10000
				if (i % 10000 == 0) {
					executeManySql(findList, transferTable);
					findList.removeAll(findList);
					System.out.println(transferTable.getImpTableName() + ":" + i);
				}
			}
			// 最后别忘了提交剩余的
			if (findList.size() > 0) {
				executeManySql(findList, transferTable);
				System.out.println(transferTable.getImpTableName() + ":" + findList.size());
			}
			return findList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pre.close();// 关闭Statement
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				oracleConn.close();// 关闭Connection
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void executeManySql(List<List<String>> findList, TransferTable entity) throws SQLException {

		mysqlConn = Mysql_con.createIfNon(mysqlConn);

		mysqlConn.setAutoCommit(false);
		Statement stat = null;
		String pstSql = "insert into " + entity.getExpTableName() + " values (";
		List<String> tempList = new ArrayList<>();
		for (int i = 0; i < entity.getColumnNum(); i++) {
			tempList.add("?");
		}
		pstSql += StringUtils.join(tempList, ",") + ")";
		PreparedStatement pst = (PreparedStatement) mysqlConn.prepareStatement(pstSql);
		for (List<String> minList : findList) {
			for (int i = 0; i < minList.size(); i++) {
				pst.setString(i + 1, minList.get(i));
			}
			// 把一个SQL命令加入命令列表
			pst.addBatch();
		}
		// 执行批量更新
		pst.executeBatch();
		// 语句执行完毕，提交本事务
		mysqlConn.commit();
		pst.close();
		mysqlConn.close();// 一定要记住关闭连接，不然mysql回应为too many connection自我保护而断开。
	}

	public static void oracle2mysql(List<TransferTable> transferTables) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
		TimeZone t = sdf.getTimeZone();
		t.setRawOffset(0);
		sdf.setTimeZone(t);
		Long startTime = System.currentTimeMillis();
		ConnectVO oracleConnectVO = new ConnectVO(PropertiesUtil.getValue("oracle.driverClassName"),
				PropertiesUtil.getValue("oracle.url"), PropertiesUtil.getValue("oracle.username"),
				PropertiesUtil.getValue("oracle.password"));
		for (TransferTable transferTable : transferTables) {
			System.out.println(transferTable.getImpTableName() + "--------------------------------------------"
					+ transferTable.getExtraSql());
			mysqlConn = Mysql_con.createIfNon(mysqlConn);
			//清空表的代码,根据需要选择
			/*String delSql = "delete FROM " + transferTable.getExpTableName();
			if (StringUtils.isNotBlank(transferTable.getExtraSql())) {
				delSql += " WHERE " + transferTable.getExtraSql();
			}
			mysqlConn.createStatement().execute(delSql);*/
			try {
				List<List<String>> newDrug = tableInput(transferTable, oracleConnectVO);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			} finally {
				Mysql_con.close(mysqlConn);
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("用时：" + sdf.format(new Date(endTime - startTime)));
	}

	@SuppressWarnings("deprecation")
	public static void oralce2MysqlByPDBCM() throws Exception {
		ConnectVO oracleConnectVO = new ConnectVO(PropertiesUtil.getValue("oracle.driverClassName"),
				PropertiesUtil.getValue("cm.oracle.url"), PropertiesUtil.getValue("cm.oracle.username"),
				PropertiesUtil.getValue("cm.oracle.password"));
		TransferTable transferTable = new TransferTable();
		transferTable.setImpTableName("cm_log_recv_send");
		transferTable.setExpTableName("cm_log_recv_send_dt");
		transferTable.setTableColumn(
				"WATER_NO,RECD_DATETIME,LINK_IP,QUEUE_NAME,SERVER_CODE,LOG_TYPE,MSG_TYPE,MSG_SEQU,MESSAGE,RESULT,MSG_TRANS_TYPE,MSG_LENGTH");
		transferTable.setExtraSql("");
		transferTable.setColumnNum(0);
		/*
		 * mysqlConn = Mysql_con.createIfNon(mysqlConn); // 根据条件清空表,也可以不清空,看需要
		 * String delSql = "delete FROM " + transferTable.getExpTableName(); if
		 * (StringUtils.isNotBlank(transferTable.getExtraSql())) { delSql +=
		 * " WHERE " + transferTable.getExtraSql(); }
		 * mysqlConn.createStatement().execute(delSql);
		 */
		List<List<String>> newDrug = tableInput(transferTable, oracleConnectVO);
	
	}
	
	public static void main(String[] args) throws Exception {
		oralce2MysqlByPDBCM();
	}

}