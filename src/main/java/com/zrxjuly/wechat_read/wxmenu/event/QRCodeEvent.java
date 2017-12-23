package com.zrxjuly.wechat_read.wxmenu.event;

/**
 * 扫描带参数二维码事件.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class QRCodeEvent extends BaseEvent {

	// 事件KEY值.
	private String EventKey;
	
	// 用于换取二维码图片.
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
}
