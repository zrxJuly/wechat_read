package com.zrxjuly.wechat_read.wxmenu.resp;

/**
 * 语音消息
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-11
 */
public class VoiceMessage extends BaseMessage {
	
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
