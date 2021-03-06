package com.bluedon.monitor.common.util;


import com.alibaba.druid.util.StringUtils;

/**
 * @author  : jason
 * @date    : 2015年3月4日
 * @module  : 系统公共模块 
 * @comment : 字符串处理类
 */
public class StringUtil extends StringUtils {
	
	
	/**
	 * 检验字符串是否为空
	 * @param str
	 * 	null : true<br/>
	 *  ''   : true<br/>
	 *  ' '  : true<br/>
	 *  'a'  : false<br/>
	 *  ' a ': false<br/>
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(StringUtils.isEmpty(str)||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	
	
}
