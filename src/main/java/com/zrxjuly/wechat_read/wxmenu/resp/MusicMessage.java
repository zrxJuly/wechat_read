package com.zrxjuly.wechat_read.wxmenu.resp;

/**
 * 音乐消息
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-11
 */
public class MusicMessage extends BaseMessage {
	
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
