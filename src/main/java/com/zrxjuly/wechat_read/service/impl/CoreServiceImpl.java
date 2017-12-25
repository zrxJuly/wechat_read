package com.zrxjuly.wechat_read.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrxjuly.wechat_read.dao.WeChatUserInfoDAO;
import com.zrxjuly.wechat_read.model.WeChatUserInfo;
import com.zrxjuly.wechat_read.service.CoreService;
import com.zrxjuly.wechat_read.wxmenu.req.TextMessage;
import com.zrxjuly.wechat_read.wxmenu.resp.Article;
import com.zrxjuly.wechat_read.wxmenu.resp.NewsMessage;
import com.zrxjuly.wechat_read.wxmenu.util.AdvancedUtil;
import com.zrxjuly.wechat_read.wxmenu.util.CommonUtil;
import com.zrxjuly.wechat_read.wxmenu.util.MessageUtil;


/**
 * 核心服务 接口实现类.
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-10
 */
@Service
public class CoreServiceImpl implements CoreService {
	
	/**
	 * WeChatUserInfoDAO.
	 */
	@Autowired
	private WeChatUserInfoDAO weChatUserInfoDAO;

	/**
	 * 处理微信发来的请求.
	 * 
	 * @param request
	 * @return xml
	 */
	@Override
	public String processRequest(HttpServletRequest request) {
		
		// xml格式的消息数据.
		String respXml = null;
		
		// 默认返回的文本消息数据.
		String respContent = "“我们曾如此渴望命运的波澜，到最后才发现：人生最曼妙的风景，竟是内心的淡定与从容。"
				+ "我们曾如此期盼外界的认可，到最后才知道：世界是自己的，与他人毫无关系。”" 
				+ "——杨绛";
		
		try {
			
			// 调用parseXml方法解析请求消息.
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			// 发送方账号.
			String fromUserName = requestMap.get("FromUserName");
			
			// 开发者微信号.
			String toUserName = requestMap.get("ToUserName");
			
			// 消息类型.
			String msgType = requestMap.get("MsgType");
			
			// 回复文本消息.
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			// 事件推送.
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				
				// 事件类型.
				String eventType = requestMap.get("Event");
				
				// 关注.
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("一件事，坚持一周是心血来潮，坚持一年是喜欢，坚持一生，才是深到骨子里的热爱~~\n\n欢迎加入我的小江湖——享悦读^.^\n\n*享悦读，爱阅读* ");
					respXml = MessageUtil.messageToXml(textMessage);
					
					// TODO: 1.判断用户以前是否关注过该公众号，若未关注，新增用户；若关注过，将用户的subscribe改为1.
					String accessToken = CommonUtil.getToken("wx70884f69b015fb7a", "b96fda8ef5c57ec321d9d5f473c272d2").getAccessToken();
					WeChatUserInfo weChatUserInfo = AdvancedUtil.getUserInfo(accessToken, fromUserName);
					
					if (weChatUserInfo != null) {
						
						// 验证用户是否关注过公众号.
						WeChatUserInfo userInfo = weChatUserInfoDAO.selectUserByOpenId(fromUserName);
						
						if (userInfo != null) {
							
							// 用户重新关注，将用户的subscribe修改为1.
							weChatUserInfoDAO.userResubscribe(fromUserName);
						}
						
						// 将用户信息保存至数据库.
						weChatUserInfoDAO.saveUserInfo(weChatUserInfo);
					}
					
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					
					// 取消关注,不用给任何回复,并将用户的subscribe修改为0.
					weChatUserInfoDAO.cancleSubscribe(fromUserName);
					
				} else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 地理位置信息.
					
					// 纬度.
					String latitude = requestMap.get("Latitude");
					
					// 经度.
					String longitude = requestMap.get("Longitude");
					
					// 精度.
					String precision = requestMap.get("Precision");
					System.out.println("latitude:" + latitude + "\n longitude:" + longitude + "\n precision:" + precision);
					
					if (latitude != null && longitude != null && precision != null) {
						WeChatUserInfo weChatUserInfo = new WeChatUserInfo();
						weChatUserInfo.setOpenId(fromUserName);
						weChatUserInfo.setLatitude(latitude);
						weChatUserInfo.setLongitude(longitude);
						weChatUserInfo.setPrecisions(precision);
						weChatUserInfoDAO.updateUserLocation(weChatUserInfo);
					}
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单.
					
					// 事件KEY值，与创建菜单时的key值对应.
					String eventKey = requestMap.get("EventKey");
					System.out.println("eventKey ====" + eventKey);
					
					// 根据key值判断用户点击的菜单的按钮.
					if (eventKey.equals("shareYourBook")) {
						
						//================添加文章=====================
						// 文章.
						Article article = new Article();
						
						// 文章标题.
						article.setTitle("小王子");
						
						// 文章描述.
						article.setDescription("重要的东西是看不见的");
						
						// 图片的url.
						article.setPicUrl("/wechat_read/WebRoot/img/little_prince.jpg");
						
						// 点击进行url跳转.
						article.setUrl("https://book.douban.com/subject/1084336/");
						
						// 第二篇文章.
						Article article2 = new Article();
						
						// 第二篇文章的标题.
						article2.setTitle("《人生》——路遥");
						
						// 描述内容.
						article2.setDescription("");
						
						// 图片的url.
						article2.setPicUrl("/wechat_read/WebRoot/img/little_prince.jpg");
						
						// 点击进行url跳转.
						article2.setUrl("https://book.douban.com/subject/1084336/");
						// =====================================
						
						// 一个或多个文章存放到list中.
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						articleList.add(article2);
						// 创建图文消息.
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("good")) {
						textMessage.setContent("");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			} else { // 处理用户发送的消息.
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息.
					respContent = "读书多了，容颜自然改变，许多时候，自己可能以为许多看过的书籍都成了过眼云烟，不复记忆，其实他们仍是潜在的。\n" +
							"在气质里，在谈吐上，在胸襟的无涯，当然也可能显露在生活和文字里。\n" +
							"——三毛 《送你一匹马》";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息.
					respContent = "是的，现实是不能以个人的意志为转移的。谁如果要离开自己的现实，就等于离开地球。\n" +
							"一个人应该有理想，甚至应该有幻想，但他千万不能抛开现实生活，去盲目追求实际上还不能得到的东西。" +
							"尤其对于刚踏入生活道路的年轻人来说，这应该是一个最重要的认识。\n" +
							"——《人生》 路遥";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息.
					respContent = "生活不能等待别人来安排，要自己去争取和奋斗；不论其结果是喜是悲，但可以慰藉的是，你总不枉在这世界上活了一场。\n" +
							"有了这样的认识，你就会珍重生活，而不会玩世不恭；同时，也会给人自身注入一种强大的内在力量。\n" +
							"——《平凡的世界》 路遥";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 地理位置消息.
					respContent = "这一圈小山在冬天特别可爱，好像是把济南放在一个小摇篮里，它们安静不动地低声地说“你们放心吧，这儿准保暖和。”\n" +
							"——《济南的冬天》 老舍";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息.
					respContent = "一个人至少拥有一个梦想，有一个理由去坚强。心若没有栖息的地方，到哪里都是在流浪。\n ——三毛";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息.
					respContent = "人，真是奇怪，没有外人来证明你，就往往看不出自己的价值。\n ——三毛 《撒哈拉的故事》";
				}
				
				// 设置文本消息的内容.
				textMessage.setContent(respContent);
				// 将文本消息对象转换成XML.
				respXml = MessageUtil.messageToXml(textMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
