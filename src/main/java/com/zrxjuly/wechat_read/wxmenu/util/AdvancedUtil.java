package com.zrxjuly.wechat_read.wxmenu.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zrxjuly.wechat_read.model.WeChatUserInfo;
import com.zrxjuly.wechat_read.wxmenu.pojo.SNSUserInfo;
import com.zrxjuly.wechat_read.wxmenu.pojo.WeixinOauth2Token;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 高级接口类.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

	/**
	 * 获取网页授权凭证.
	 * @param appId 公众账号的唯一标识.
	 * @param appSecret 公众账号的密钥.
	 * @param code
	 * @return
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;

		// 拼接请求地址.
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);

		// 获取网页授权凭证,发送https请求.
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errorcode:{} errmsg:{}", errorCode, errMsg);
			}
		}

		return wat;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken 网页授权接口调用凭证
	 * @param openId 用户标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings({
		"unchecked", "deprecation"
	})
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		
		// 通过网页授权获取用户信息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}
	
	/**
	 * 获取用户信息.
	 * 描述：当用户关注公众号时，获取用户的信息并存入数据库.
	 * @param accessToken 接口访问凭证.
	 * @param openId 用户标识.
	 * @return
	 */
	public static WeChatUserInfo getUserInfo(String accessToken, String openId) {
		WeChatUserInfo weChatUserInfo = null;
		
		// 拼接请求地址.
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		
		// 获取用户基本信息.
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if (null != jsonObject) {
			try {
				weChatUserInfo = new WeChatUserInfo();
				
				// 用户的标识.
				weChatUserInfo.setOpenId(jsonObject.getString("openid"));
				
				// 关注状态：1-关注；0-未关注.
				weChatUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				
				// 关注时间-取最近关注时间.
				weChatUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
				
				// 用户昵称.
				weChatUserInfo.setNickname(jsonObject.getString("nickname"));
				
				// 用户性别：1-男；2-女；0-未知.
				weChatUserInfo.setSex(jsonObject.getInt("sex"));
				
				// 用户所在国家.
				weChatUserInfo.setCountry(jsonObject.getString("country"));
				
				// 用户所在省份.
				weChatUserInfo.setProvince(jsonObject.getString("province"));
				
				// 用户所在城市.
				weChatUserInfo.setCity(jsonObject.getString("city"));
				
				// 用户使用的语言.
				weChatUserInfo.setLanguage(jsonObject.getString("language"));
				
				// 用户头像.
				weChatUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weChatUserInfo.getSubscribe()) {
					log.error("用户 {} 已取消关注",weChatUserInfo.getOpenId());
				} else {
					int errorCode = jsonObject.getInt("errCode");
					String errMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errMsg);
				}
			}
		}
		
		return weChatUserInfo;
	}
	
}
