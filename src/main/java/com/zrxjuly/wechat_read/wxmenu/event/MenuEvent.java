package com.zrxjuly.wechat_read.wxmenu.event;

/**
 * 自定义菜单事件.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class MenuEvent extends BaseEvent {
	
	// 事件KYE值，与自定义菜单接口中KEY值对应.
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
}
