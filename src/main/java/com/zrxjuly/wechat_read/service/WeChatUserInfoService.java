package com.zrxjuly.wechat_read.service;

import com.zrxjuly.wechat_read.model.WeChatUserInfo;

/**
 * 
 * @author zhangrongxiang
 *
 */
public interface WeChatUserInfoService {
	
	/**
	 * 通过用户的openId查询用户信息.
	 * @param openId
	 * @return
	 */
	WeChatUserInfo selectUserByOpenId(String openId);
}
