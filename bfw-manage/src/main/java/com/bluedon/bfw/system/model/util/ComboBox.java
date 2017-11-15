package com.bluedon.bfw.system.model.util;


/** 
 * @author :yangdandan 
 * @date：2015年4月17日
 * @module:系统模块
 * @comment:返回操作对象combobox
 */
public class ComboBox {

	private String id;
	private String text;
	private boolean selected;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
