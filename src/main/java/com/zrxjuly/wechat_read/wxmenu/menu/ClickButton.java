package com.zrxjuly.wechat_read.wxmenu.menu;

/**
 * click类型的按钮.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class ClickButton extends Button {
	
	private String type;
	private String key;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
