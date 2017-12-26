package com.zrxjuly.wechat_read.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zrxjuly.wechat_read.service.CoreService;
import com.zrxjuly.wechat_read.wxmenu.util.SignUtil;

/**
 * 请求处理的核心类.
 * @author zhangrongxiang
 * @createDate 2017-12-9
 *
 */
@RestController
@RequestMapping("wechat_read")
public class CoreController extends HttpServlet {
	private static final long serialVersionUID = -3344978504206357286L;
	private Log logger = LogFactory.getLog(CoreController.class);
	
	/**
	 * 注入coreService.
	 */
	@Autowired
	private CoreService coreService;

	/**
	 * 请求校验(确认请求来自微信服务器).
	 */
	@RequestMapping(value= "/coreServlet", method=RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("开始请求校验");
		
		// 微信加密签名.
		String signature = request.getParameter("signature");
		logger.info("微信加密签名" + signature);
		
		// 时间戳.
		String timestamp = request.getParameter("timestamp");
		logger.info("时间戳" + timestamp);
		
		// 随机数.
		String nonce = request.getParameter("nonce");
		logger.info("随机数" + nonce);
		
		// 随机字符串.
		String echostr = request.getParameter("echostr");
		logger.info("随机字符串" + echostr);

		PrintWriter out = response.getWriter();

		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
		try {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				logger.info("=======请求校验成功======" + echostr);
				out.print(echostr);
			}
		} catch (NullPointerException e) {
			// 捕获空指针异常，当请求校验失败的时候页面跳转到index.jsp.
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息.
	 */
	@RequestMapping(value= "/coreServlet", method=RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		logger.info("请求进入");
		
		// 微信加密签名.
		String signature = request.getParameter("signature");
		
		// 时间戳.
		String timestamp = request.getParameter("timestamp");
		
		// 随机数.
		String nonce = request.getParameter("nonce");
		
		PrintWriter out = response.getWriter();
		
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			
			// 调用核心服务类接收处理请求.
			String respXml = coreService.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}

}
