package com.zrxjuly.wechat_read;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zrxjuly.wechat_read.dao.WeChatUserInfoDAO;
import com.zrxjuly.wechat_read.model.WeChatUserInfo;

/**
 * wechat_read的测试类.
 * @author zhangrongxiang
 * @createDate 2017-12-13
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatReadApplicationTests {

	@Autowired
	private WeChatUserInfoDAO weChatUserInfoDAO;
	
	public static String fromUserName = "o2eJmwkTOremA_mHcWNfEqfVf_bc";
	
	/**
	 * 保存用户信息至数据库.
	 */
	@Test
	public void saveUserInfo() {
		WeChatUserInfo weChatUserInfo = new WeChatUserInfo();
		weChatUserInfo.setOpenId(fromUserName);
		weChatUserInfo.setSubscribe(1);
		weChatUserInfo.setSubscribeTime("2017-12-13");
		weChatUserInfo.setNickname("July");
		weChatUserInfo.setSex(2);
		weChatUserInfo.setCountry("China");
		weChatUserInfo.setProvince("Shandong");
		weChatUserInfo.setCity("Jinan");
		weChatUserInfo.setLanguage("zh_CN");
		weChatUserInfo.setHeadImgUrl("http://wx.qlogo.cn/mmopen/vi_32/AGmXI97aujacric5FmicoQN");
		weChatUserInfo.setLatitude("36.632816");
		weChatUserInfo.setLongitude("117.269501");
		weChatUserInfo.setPrecisions("30.000000");
		System.out.println("weChatUserInfoDAO======" + weChatUserInfoDAO);
		weChatUserInfoDAO.saveUserInfo(weChatUserInfo);
	}
	
	/**
	 * 更新用户地理位置信息.
	 */
	@Test
	public void testUpdateUserLocation() {
		WeChatUserInfo weChatUserInfo = new WeChatUserInfo();
		weChatUserInfo.setOpenId(fromUserName);
		weChatUserInfo.setLatitude("36.632816");
		weChatUserInfo.setLongitude("117.269501");
		weChatUserInfo.setPrecisions("30.0000000");
		weChatUserInfoDAO.updateUserLocation(weChatUserInfo);
	}
	
	/**
	 * 用户取消关注.
	 */
	@Test
	public void testCancleSubscribe() {
		weChatUserInfoDAO.cancleSubscribe(fromUserName);
	}
	
	/**
	 * 查询用户.
	 */
	@Test
	public void testSelectUserByOpenId() {
		WeChatUserInfo userinfo = weChatUserInfoDAO.selectUserByOpenId(fromUserName);
		System.out.println(userinfo);
	}
	
	/**
	 * 用户重新关注公众号.
	 */
	@Test
	public void testUserResubscribe() {
		weChatUserInfoDAO.userResubscribe(fromUserName);
	}
}
