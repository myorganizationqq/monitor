package com.bluedon.monitor.common.util.model;
import java.util.HashMap;
import java.util.Map;


/**
 * @author  : jason
 * @date    : 2015年3月4日
 * @module  : 系统公共模块 
 * @comment : Model映射处理类生成器
 */
public class ModelMapperFactory {

	private static Map<String, ModelMapper> modelMapperList = new HashMap<String, ModelMapper>();

	/**
	 * 初始化时获取Model反射信息
	 * 
	 * @param model
	 * @return
	 */
	public static ModelMapper getModelMapper(Object model) {
		ModelMapper modelMapper = null;
		if (modelMapperList.get(model.getClass().getName()) == null) {
			synchronized (ModelMapperFactory.class) {
				modelMapper = new ModelMapper(model);
				modelMapperList.put(modelMapper.getClass().getName(),
						modelMapper);
			}
		} else {
			modelMapper = (ModelMapper) modelMapperList.get(model.getClass()
					.getName());
		}
		return modelMapper;
	}

}
