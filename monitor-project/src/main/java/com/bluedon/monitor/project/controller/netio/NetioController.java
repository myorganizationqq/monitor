package com.bluedon.monitor.project.controller.netio;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jboss.logging.Param;
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
		serverInfoId = "27";
		List<Map<String, Object>> list = netioService.queryData(serverInfoId);
		request.setAttribute("list", list);
		request.setAttribute("device", netioService.querySNMPInfo(serverInfoId));
		return "project/netio/businessMonitor";
	}
	
	@RequestMapping("/appSystem.do")
	public String appSystem() {
		return "project/applicationSystemView/applicationSystemView";
	}
	
	@RequestMapping("/getChartData")
	public @ResponseBody Map<String, Object> getChartData(@Param String time) {
		return netioService.queryDataByTime(time);
	}
	
}
