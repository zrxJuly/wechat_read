package com.zrxjuly.wechat_read.dao;

import com.zrxjuly.wechat_read.model.WeChatUserInfo;

/**
 * 数据库操作层.
 * @author zhangrongxiang
 * @createDate 2017-12-13
 *
 */
public interface WeChatUserInfoDAO {

	/**
	 * 保存用户信息至数据库. 
	 * @param weChatUserInfo
	 */
	public Integer saveUserInfo(WeChatUserInfo weChatUserInfo);
	
	/**
	 * 更新用户的地理位置信息.
	 * @param weChatUserInfo
	 * @return
	 */
	public Integer updateUserLocation(WeChatUserInfo weChatUserInfo);
	
	/**
	 * 用户取消关注.
	 * @param openId
	 * @return
	 */
	public Integer cancleSubscribe(String openId);
	
	/**
	 * 查询用户信息.
	 * @param openId
	 * @return
	 */
	public WeChatUserInfo selectUserByOpenId(String openId);
	
	/**
	 * 用户重新关注公众号.
	 * @param fromUserName
	 */
	public void userResubscribe(String fromUserName);
}
