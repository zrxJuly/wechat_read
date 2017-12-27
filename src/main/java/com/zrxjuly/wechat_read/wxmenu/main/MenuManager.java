package com.zrxjuly.wechat_read.wxmenu.main;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zrxjuly.wechat_read.wxmenu.menu.Button;
import com.zrxjuly.wechat_read.wxmenu.menu.ClickButton;
import com.zrxjuly.wechat_read.wxmenu.menu.ComplexButton;
import com.zrxjuly.wechat_read.wxmenu.menu.Menu;
import com.zrxjuly.wechat_read.wxmenu.menu.ViewButton;
import com.zrxjuly.wechat_read.wxmenu.pojo.AccessToken;
import com.zrxjuly.wechat_read.wxmenu.util.CommonUtil;
import com.zrxjuly.wechat_read.wxmenu.util.MenuUtil;

/**
 * 菜单管理器类-创建自定义菜单.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	private static String appId;
	private static String appSecret;
	
	static {
		
		// 加载wechat.properties属性配置文件信息.
		ResourceBundle resourceBundle = ResourceBundle.getBundle("wechat");
		appId = resourceBundle.getString("appId");
		appSecret = resourceBundle.getString("appSecret");
	}
	
	/**
	 * 定义菜单结构.
	 * 描述：微信菜单结构，主菜单最多有3个，每个主菜单的子菜单最多有5个.
	 *     按钮的类型：
	 *       1.click（点击事件）
	 *           属性：type:按钮类型，为click.
	 *               name:按钮名称，显示在按钮表面.
	 *               key:按钮键值，开发者通过key的值来判断用户点击的是哪一个按钮.
	 *       2.view（访问网页）
	 *           属性：type：按钮类型，为view.
	 *               name：按钮名称，显示在按钮表面.
	 *               url:按钮链接，点击按钮后访问的网页链接(url不能为空，否则创建菜单失败).
	 * 微阅读，享悦读。
	 * @return
	 */
	private static Menu getMenu() {

		// 主菜单1下的子菜单1.
		ClickButton mainBtn1Sub1 = new ClickButton();
		mainBtn1Sub1.setName("好书周荐");
		mainBtn1Sub1.setType("click");
		mainBtn1Sub1.setKey("shareYourBook");
		
		// 主菜单1下的子菜单2.
		ViewButton mainBtn1Sub2 = new ViewButton();
		mainBtn1Sub2.setName("我要分享");
		mainBtn1Sub2.setType("view");
		mainBtn1Sub2.setUrl("http://www.baidu.com");
		
		// 主菜单2下的子菜单1---好文悦读.
//		ViewButton mainBtn2Sub1 = new ViewButton();
//		mainBtn2Sub1.setName("网页授权");
//		mainBtn2Sub1.setType("view");
//		mainBtn2Sub1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx70884f69b015fb7a&redirect_uri=http%3A%2F%2Ftz8q67.natappfree.cc%2Fwechat_read%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		ClickButton mainBtn2Sub1 = new ClickButton();
		mainBtn2Sub1.setName("好文悦读");
		mainBtn2Sub1.setType("click");
		mainBtn2Sub1.setKey("readArticle");
		
		// 主菜单3下的子菜单1-----链接按钮.
		ViewButton mainBtn3Sub1 = new ViewButton();
		mainBtn3Sub1.setName("用户信息");
		mainBtn3Sub1.setType("view");
		// 对URL进行编码.TODO:1.此处的公网的地址在每次重启映射工具的时候随forwarding改变.
		String oauthUrl = "http://5ze33x.natappfree.cc/userInfo/userInfoEdit";
		oauthUrl = CommonUtil.urlEncodeUTF8(oauthUrl);
		System.out.println(CommonUtil.urlEncodeUTF8(oauthUrl));
		
		String redirect_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
				+ "&redirect_uri=" + oauthUrl
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		
		mainBtn3Sub1.setUrl(redirect_url);
		
		// 主菜单3下的子菜单2-----周荐历史.
		ViewButton mainBtn3Sub2 = new ViewButton();
		mainBtn3Sub2.setName("周荐历史");
		mainBtn3Sub2.setType("view");
		// TODO：修改公网地址 weekly_history.jsp
		mainBtn3Sub2.setUrl("http://5ze33x.natappfree.cc/userInfo/weeklyHistory");
		
		// 主菜单3下的子菜单2-----我分享的.
		ClickButton mainBtn3Sub3 = new ClickButton();
		mainBtn3Sub3.setName("我分享的");
		mainBtn3Sub3.setType("click");
		mainBtn3Sub3.setKey("userShare");
		
		// 主菜单1.
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("享");
		mainBtn1.setSub_button(new Button[] {mainBtn1Sub1, mainBtn1Sub2});

		// 主菜单2.
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("悦");
		mainBtn2.setSub_button(new Button[] {
			mainBtn2Sub1
		});

		// 主菜单3.
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("读");
		mainBtn3.setSub_button(new Button[] {
			mainBtn3Sub1,mainBtn3Sub2,mainBtn3Sub3
		});

		// 将三个主菜单封装到Menu中.
		Menu menu = new Menu();
		menu.setButton(new Button[] {
			mainBtn1, mainBtn2, mainBtn3
		});

		return menu;
	}

	public static void main(String[] args) {

		// 调用接口获取凭证.
		AccessToken token = CommonUtil.getToken(appId, appSecret);
		System.out.println("============access_token=========\n" + token);
		try {
			if (token != null) {
				
				// 创建菜单.
				boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
				
				// 判断菜单创建结果.
				if (result) {
					log.info("\n===============菜单创建成功==============");
				} else {
					log.info("\n===============菜单创建失败==============");
				}
			}
		} catch (Exception e) {
			log.info("\n===========token为空=========");
			e.printStackTrace();
		}
	}
}
