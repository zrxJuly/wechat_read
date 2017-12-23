package com.zrxjuly.wechat_read;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 项目启动：运行main方法.
 * Spring Boot项目启动类.
 * 说明:若要支持JSP，必须继承SpringBootServletInitializer.
 * @author zhangrongxiang
 *
 */
@SpringBootApplication
//mapper 接口类扫描包配置
@MapperScan("com.zrxjuly.wechat_read.dao")
public class WechatReadApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WechatReadApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WechatReadApplication.class, args);
	}
}
