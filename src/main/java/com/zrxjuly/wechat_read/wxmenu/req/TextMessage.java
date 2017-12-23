package com.zrxjuly.wechat_read.wxmenu.req;


/**
 * 文本消息.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class TextMessage extends BaseMessage {
	
	// 消息内容.
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
