package com.bluedon.bfw.system.model.util;

/**
 * @author		: jason
 * @date		: 2015年1月19日
 * @module		: 系统模块
 * @comment	: 操作返回对象
 */
public class OperResult {
	
	private int resultCode;//返回码,0:表示成功,其他自定义
	private String msg;//返回描述
	private Object data;//返回数据对象封装
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
