package com.zrxjuly.wechat_read.wxmenu.resp;

/**
 * 视频消息
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-11
 */
public class VideoMessage extends BaseMessage {
	
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
