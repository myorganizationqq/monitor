package com.bluedon.bfw.system.service.common;

import java.util.Map;

/**
 * @author liangliwen
 * @date 2015年4月20日
 * @module
 * @comment 获取字典实现接口
 */
public interface IDictionaryService {

	//获取证件类型集合
	public Map<String, String> getCardTypeMap();
	

}
