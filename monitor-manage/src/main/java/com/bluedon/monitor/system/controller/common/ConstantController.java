package com.bluedon.monitor.system.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.bluedon.monitor.system.model.util.ComboBox;
import com.bluedon.monitor.system.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedon.monitor.system.util.ToolUtil;

/** 
 * @author :yangdandan 
 * @date：2015年4月17日
 * @module:
 * @comment:公共请求
 */
@Controller
@RequestMapping("/manage/common/constantController")
public class ConstantController {

	
	@RequestMapping(params="getCombobox")
	public void getCombobox(String mapName,HttpServletResponse rsp){
		Map<Integer, String> map=new HashMap<Integer, String>();
		
		if(mapName.equals("user_type")){//用户类别
			map= ConstantUtil.USER_TYPE;
		}
		
		if(mapName.equals("role_type")){//角色类别
			map=ConstantUtil.ROLE_TYPE;
		}
		
		if(mapName.equals("is_vaild")){//是否有效
			map=ConstantUtil.IS_VAILD;
		}
				
		
		List<ComboBox> cList=new ArrayList<ComboBox>();
		
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			ComboBox c = new ComboBox();
			c.setId(String.valueOf(entry.getKey()));
			c.setText(entry.getValue());
			cList.add(c);
		}
		
		ToolUtil.getCombo(rsp, cList);
			
	}
}
