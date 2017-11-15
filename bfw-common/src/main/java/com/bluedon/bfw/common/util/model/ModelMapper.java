package com.bluedon.bfw.common.util.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bluedon.bfw.common.util.StringUtil;

/**
 * @author  : jason
 * @date    : 2015年3月4日
 * @module  : 系统公共模块 
 * @comment : Model映射处理类
 */
public class ModelMapper {

	private Object model;

	// set field set
	private Set<Field> fieldSet = new HashSet<Field>();

	// set method of getter
	private Map<String, Method> getMethodMap = new HashMap<String, Method>();

	// set method of setter
	private Map<String, Method> setMethodMap = new HashMap<String, Method>();

	/**
	 * 构造时初始化相关信息
	 * 
	 * @param obj
	 */
	public ModelMapper(Object model) {
		initModel(model);
		this.model = model;
	}

	/**
	 * 初始化反射Model所有信息方法
	 * 
	 * @param model
	 */
	public void initModel(Object model) {
		Class[] modelClasses = { model.getClass(),
				model.getClass().getSuperclass() };
		List<Field> fields = new ArrayList<Field>();
		List<Method> methods = new ArrayList<Method>();
		// iterator Class
		for (Class clazz : modelClasses) {
			methods.addAll(Arrays.asList(clazz.getMethods()));
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			// iterator field
			for (Field tmpField : fields) {
				fieldSet.add(tmpField);
				for (Method method : methods) {
					// 存放对应属性的getter方法
					if (method.getName().equalsIgnoreCase(
							"get" + tmpField.getName())) {
						getMethodMap.put(tmpField.getName(), method);
					}
					if (method.getName().equalsIgnoreCase(
							"set" + tmpField.getName())) {
						setMethodMap.put(tmpField.getName(), method);
					}
				}
			}
		}

	}
	
	
	/**
	 * 将对象的所有属性值进行解码处理
	 * @param charType[可选]
	 *  为空则默认使用utf-8编码解码
	 * @return
	 */
	public Object decodeModel(String charType) {
		
		Iterator<Field> iter = fieldSet.iterator();
		
		if(StringUtil.isEmpty(charType)){//如果为空则默认给出解码字符集
			charType = "utf-8";
		}
		
		while (iter.hasNext()) {
			Field field = iter.next();
			Method setMethod = setMethodMap.get(field.getName());
			Method getMethod = getMethodMap.get(field.getName());
			Class[] types = setMethod.getParameterTypes();
			try {
				// 先调用getter方法获取值
				Object value = getMethod.invoke(this.model);
				if (value == null) {
					continue;
				}
				// 对参数进行解码操作
				String newValue = URLDecoder.decode(value.toString(), charType);
				// 在调用setter方法重新赋值
				if (types[0].getName().equals(Integer.class.getName())) {
					setMethod.invoke(this.model, Integer.valueOf(newValue));
				} else if (types[0].getName().equals(String.class.getName())) {
					setMethod.invoke(this.model, newValue);
				}
			} catch (Exception e) {
			}
		}
		return this.model;
	}
}
