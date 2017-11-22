package com.bluedon.monitor.project.controller.communication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		System.out.println("toPageList.....");
		return new ModelAndView("project/communication/logRecvsendList");
	}
	
	@RequestMapping(params = "getLogRecvsendList")
	public void pageList(CmLogRecvsendDT param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){
		//获取分页数据
		pageUtil = logRecvsendService.getPageList(param, pageUtil);

		//返回分页数据
		ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
	}
	
}
