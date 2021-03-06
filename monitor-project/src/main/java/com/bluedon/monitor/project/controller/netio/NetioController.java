package com.bluedon.monitor.project.controller.netio;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluedon.monitor.project.service.netio.NetioService;

@Controller
@RequestMapping("/netio")
public class NetioController {
	private static final Logger log = Logger.getLogger(NetioController.class);
	@Autowired
	private NetioService netioService;
	
	@RequestMapping("/index.do")
	public String index(String serverInfoId, HttpServletRequest request) {
		List<Map<String, Object>> list = netioService.queryData(serverInfoId);
		request.setAttribute("list", list);
		request.setAttribute("device", netioService.querySNMPInfo(serverInfoId));
		return "project/netio/businessMonitor";
	}
	
	// 应用系统视图菜单
	@RequestMapping("/appSystem.do")
	public String appSystem() {
		return "project/applicationSystemView/applicationSystemView";
	}
	
	// 应用系统报表
	@RequestMapping("/systemReport.do")
	public String systemReport() {
		return "project/statisticReport/appSystemReport";
	}
	
	@RequestMapping("/getChartData")
	public @ResponseBody Map<String, Object> getChartData(String time, String serverInfoId) {
		Map<String, Object> map = netioService.queryDataByTime(time); // 数据流量、数据包
		Map<String, Object> cpuData = netioService.queryCPUData(serverInfoId);
		Map<String, Object> memData = netioService.queryMemData(serverInfoId);
		map.put("cpuData", cpuData);
		map.put("memData", memData);
		return map;
	}
	
}
