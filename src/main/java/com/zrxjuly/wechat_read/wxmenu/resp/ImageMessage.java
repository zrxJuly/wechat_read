package com.zrxjuly.wechat_read.wxmenu.resp;

/**
 * 图片消息
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-11
 */
public class ImageMessage extends BaseMessage {
	
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
