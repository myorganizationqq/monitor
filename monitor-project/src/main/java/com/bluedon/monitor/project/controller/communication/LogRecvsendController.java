package com.bluedon.monitor.project.controller.communication;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.communication.CmLogRecvsendDT;
import com.bluedon.monitor.project.service.communication.LogRecvsendService;
import com.bluedon.monitor.system.util.ToolUtil;

@Controller
@RequestMapping("/communication/logRecvsend")
public class LogRecvsendController {

	private static final Logger log = Logger.getLogger(LogRecvsendController.class);
	@Autowired
	private LogRecvsendService logRecvsendService;
	
	@RequestMapping(params = "toPageList")
	public ModelAndView toPageList(HttpServletRequest req) {
		return new ModelAndView("project/communication/logRecvsendList");
	}
	
	@RequestMapping(params = "getLogRecvsendList")
	public void pageList(CmLogRecvsendDT param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){
		//获取分页数据
		pageUtil = logRecvsendService.getPageList(param, pageUtil);

		//返回分页数据
		ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
	}
	
	@RequestMapping(params = "getChartData")
	public @ResponseBody Map<String, Object> getChartData(@Param String wrongType, CmLogRecvsendDT param) {
		Map<String, Object> map = new HashMap<>();
		if ("sucsCount".equals(wrongType)) {
			param.setResult("0");
            map = logRecvsendService.getTopLogRecvsend(param);
        }else if("failCount".equals(wrongType)) {
        	param.setResult("1");
        	map = logRecvsendService.getTopLogRecvsend(param);
        }else if("totalCount".equals(wrongType)) {
        	map = logRecvsendService.getTopTotalCount(param);
        }else if("maxMsgLengthCount".equals(wrongType)) {
        	map = logRecvsendService.getTopMaxMsgLength(param);
        }
		return map;
	}
	
}
