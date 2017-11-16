package com.bluedon.monitor.system.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluedon.monitor.system.model.util.OperResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bluedon.monitor.common.util.ConfigReader;

/**
 * @author : jason
 * @date : 2015年1月19日
 * @module : 系统模块
 * @comment : 功能辅助类
 */
public class ToolUtil {

	// 监控信息存放

	// 日志记录对象
	private static final Logger log = Logger.getLogger(ToolUtil.class);

	/**
	 * 根据类型和编号获取中文描述
	 * 
	 * @param type
	 * <br/>
	 *            0.通用描述<br/>
	 *            1.登录<br/>
	 *            2.监控<br/>
	 * @param pointer
	 *            各个类型的描述编号
	 * @return
	 */
	public static String getMsgFromMessage(int type, String pointer) {
		String result = "";
		switch (type) {
		case 0:
			result = ConfigReader.getProperty("common_zh",
					"common_description_" + pointer);
			break;
		case 1:
			result = ConfigReader.getProperty("common_zh", "login_description_"
					+ pointer);
			break;
		case 2:
			result = ConfigReader.getProperty("common_zh",
					"monitor_description_" + pointer);
			break;
		default:
			break;
		}

		return result;
	}

	
	

	/**
	 * 以easyUI-DataGrid格式输出Ajax请求
	 * 
	 * @param response
	 *            请求响应对象
	 * @param results
	 *            参数集合
	 * @param total
	 *            总记录数
	 */
	public static void getDataGrid(HttpServletResponse response, List results,
			int total) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");		
		// 开始输出
		StringBuffer sbuffer = new StringBuffer("");
		sbuffer.append("{");
		sbuffer.append("\"total\":");
		sbuffer.append(total);
		sbuffer.append(",\"rows\":");
		// 验证
		if (results == null || results.size() <= 0) {
			sbuffer.append("[]");
		}else{
			sbuffer.append(JSONArray.fromObject(results));
		}
		
		sbuffer.append("}");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(sbuffer.toString());
			pw.flush();
			log.debug("output string : " + sbuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}
	
	/**
	 * 将OperResult以Ajax请求方式输出
	 * 
	 * @param response
	 *            响应对象
	 * @param result
	 *            要返回的数据
	 */
	public static void getData(HttpServletResponse response, OperResult result) {
		// 验证
		if (result == null) {
			return;
		}
		
		String str = JSONObject.fromObject(result).toString();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(str);
			pw.flush();
			log.debug("output string : " + str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
	/**
	 * 将List以Ajax请求方式输出
	 * 
	 * @param response
	 *            响应对象
	 * @param result
	 *            要返回的数据
	 */
	public static void getCombo(HttpServletResponse response, List result) {
		// 验证
		if (result == null) {
			return;
		}
		
		String str = JSONArray.fromObject(result).toString();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(str);
			pw.flush();
			log.debug("output string : " + str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

	
	/**
	 * 获取请求IP方法
	 * 
	 * @param request
	 *            请求对象
	 * @return 请求IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
