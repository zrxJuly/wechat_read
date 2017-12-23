package com.zrxjuly.wechat_read.wxmenu.resp;

/**
 * 视频model
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-11
 */
public class Video {
	
	// 媒体文件id
	private String MediaId;
	
	// 缩略图的媒体id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
