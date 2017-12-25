package com.zrxjuly.wechat_read.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrxjuly.wechat_read.dao.WeChatUserInfoDAO;
import com.zrxjuly.wechat_read.model.WeChatUserInfo;
import com.zrxjuly.wechat_read.service.WeChatUserInfoService;

/**
 * 
 * @author zhangrongxiang
 *
 */
@Service
public class WeChatUserInfoServiceImpl implements WeChatUserInfoService {
	
	/**
	 * 注入WeChatUserInfoDAO.
	 */
	@Autowired
	private WeChatUserInfoDAO weChatUserInfoDAO;
	
	@Override
	public WeChatUserInfo selectUserByOpenId(String openId) {
		return weChatUserInfoDAO.selectUserByOpenId(openId);
	}

}