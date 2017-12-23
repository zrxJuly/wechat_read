package com.zrxjuly.wechat_read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author zhangrongxiang
 * 
 */
@RestController
public class WeChatReadController {
	
	@Value("${application.hello:Hello Angel}")
	private String hello;
	
	// 返回给前端字符串.
	@RequestMapping("/hello")
	public String hello(Map<String, Object> map) {
		System.out.println("hello===" + hello);
		map.put("hello", "goodiiiiiiiiiiii");
		return "hello";
	}
	
	// 返回到视图hello.jsp.
	@RequestMapping("/hi")
	public ModelAndView index() {
		ModelMap model = new ModelMap();
		model.addAttribute("name", "Spring Boot");
		return new ModelAndView("user_authorize", model);
	}
	
	
	
}
