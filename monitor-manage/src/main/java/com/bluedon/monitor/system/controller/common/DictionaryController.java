package com.bluedon.monitor.system.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/** 
 * @author :liangliwen 
 * @date：2015年4月20日
 * @module:
 * @comment:字典请求
 */
@Controller
@RequestMapping("/manage/common/dictionaryController")
public class DictionaryController {
	

	@RequestMapping(params="getCombobox")
	public void getCombobox(String mapName,HttpServletResponse rsp){

	}
	

}
