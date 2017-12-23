package com.zrxjuly.wechat_read.wxmenu.pojo;

/**
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-10
 *
 */
public class AccessToken {
	// 获取到的凭证
	private String accessToken;
	// 凭证有效时间，单位：秒
	private int expiresin;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresin() {
		return expiresin;
	}
	public void setExpiresin(int expiresin) {
		this.expiresin = expiresin;
	}
	
	
}
