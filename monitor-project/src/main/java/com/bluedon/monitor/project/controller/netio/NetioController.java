package com.bluedon.monitor.project.controller.netio;

import java.util.Map;
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
	
	@RequestMapping("/getChartData")
	public @ResponseBody Map<String, Object> getChartData(@Param String time) {
		return netioService.queryDataByTime(time);
	}
	
}
