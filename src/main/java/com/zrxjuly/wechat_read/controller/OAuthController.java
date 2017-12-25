package com.zrxjuly.wechat_read.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zrxjuly.wechat_read.model.WeChatUserInfo;
import com.zrxjuly.wechat_read.service.WeChatUserInfoService;
import com.zrxjuly.wechat_read.wxmenu.pojo.WeixinOauth2Token;
import com.zrxjuly.wechat_read.wxmenu.util.AdvancedUtil;

/**
 * 接收和处理授权回调请求.
 * 描述：通过网页授权获取用户信息，如果要在网页中得到用户信息，就必须先引导用户进入网页授权页面(需要获取用户的appId和appSecret)；
 *     用户同意授权后会跳转到回调地址redirect_uri,redirect_uri是授权回调请求处理程序的访问地址；
 *     在处理程序中，开发者能够获取到code，再通过code获取access_token,最终得到用户信息.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 * @since : 1.0
 *
 */
@RestController
@RequestMapping("userInfo")
public class OAuthController {

	/**
	 * 注入weChatUserInfoService.
	 */
	@Autowired
	private WeChatUserInfoService weChatUserInfoService;
	
	@RequestMapping(value= "/userInfoEdit", method=RequestMethod.GET)
	public ModelAndView userInfoEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 设置请求和响应的编码方式.
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		
		ModelAndView mav = new ModelAndView();
		
		// 用户同意授权后，能获取到code.
		String code = request.getParameter("code");
		System.out.println("用户授权code============" + code);
		
		// 用户同意授权.
		if (!"authdeny".equals(code)) {
			
			// 加载wechat.properties属性配置文件信息.
			ResourceBundle resourceBundle = ResourceBundle.getBundle("wechat");
			String appId = resourceBundle.getString("appId");
			String appSecret = resourceBundle.getString("appSecret");
			
			// 通过开发者的appId和appSecret和用户同意授权后获取到的code获取网页授权相关信息.
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appId, appSecret, code);
			
			//网页授权接口访问凭证-网页授权用到.
			// String accessToken = weixinOauth2Token.getAccessToken();
			
			// 用户标识.
			String openId = weixinOauth2Token.getOpenId();
			
			// 获取用户信息-网页授权用到.
			// SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
			
			// 通过openId查询用户信息 回显.
			WeChatUserInfo weChatUserInfo = weChatUserInfoService.selectUserByOpenId(openId);
			
			// 设置要传递的参数.
			// mav.addObject("userInfo", snsUserInfo);
			mav.addObject("userInfo", weChatUserInfo);
			mav.setViewName("user_authorize");
		}
		
		return mav;
	}

}
