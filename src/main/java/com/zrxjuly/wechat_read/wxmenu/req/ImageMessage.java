package com.zrxjuly.wechat_read.wxmenu.req;


/**
 * 图片消息.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class ImageMessage extends BaseMessage {
	
	// 图片链接.
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
