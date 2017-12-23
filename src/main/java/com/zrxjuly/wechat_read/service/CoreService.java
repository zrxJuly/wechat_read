package com.zrxjuly.wechat_read.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 核心服务 接口类.
 * 
 * @author zhangrongxiang
 * @createDate 2017-12-10
 */
public interface CoreService {
	
	/**
	 * 处理微信发来的请求.
	 * 
	 * @param request
	 * @return xml
	 */
	public String processRequest(HttpServletRequest request);
}
