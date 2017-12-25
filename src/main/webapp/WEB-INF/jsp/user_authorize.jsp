<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<title>享 悦 读</title>
		<meta content="width=device-width,user-scalable=0" name="viewport">
		
		<!-- 引入frozen样式 -->
		<link rel="stylesheet" href="frozen/css/frozen.css">
		<script src="frozen/js/lib/zeptojs/zepto.min.js"></script>
		<script src="frozen/js/frozen.js"></script>
	</head>
	<body>
		<header class="ui-header ui-header-positive ui-border-b">
			<i class="ui-icon-return" onclick="history.back()"></i><h1>完善信息</h1><button class="ui-btn">回首页</button>
		</header>
		<section class="ui-container ui-center">
			<form action="#">
				<div class="ui-form ui-border-t">
					<input type="text" value="${userInfo.openId }" name="open_id"/>
					<div class="ui-form-item ui-border-b">
						<label>昵称</label>
						<input type="text" placeholder="给自己取个文艺点的名字吧" value="${userInfo.nickname }" />
						<a href="#" class="ui-icon-close">
						</a>
					</div>
					<div class="ui-form-item ui-form-item-radio ui-border-b">
						性别
						<label class="ui-radio" for="radio" style="text-align: center">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" checked="checked" name="radio" />男
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="radio" />女
						</label>
					</div>
					<!-- <div class="ui-form-item ui-form-item-l ui-border-b"> -->
					<div class="ui-form-item ui-border-b">
						<!-- <label class="ui-border-r">中国 +86</label> -->
						<label>手机号</label>
						<input type="text" placeholder="请留下您宝贵的联系方式" />
						<a href="#" class="ui-icon-close">
						</a>
					</div>
					<div class="ui-form-item ui-border-b">
						<!-- <label class="ui-border-r">中国 +86</label> -->
						<label>邮箱</label>
						<input type="text" placeholder="请输入您的邮箱地址" />
						<a href="#" class="ui-icon-close">
						</a>
					</div>
				</div>
				<%-- <%
					} else {
						out.print("未获取到用户信息！");
					}
				%> --%>
				<!-- 底部 确认&取消按钮 -->
				<div class="ui-footer ui-footer-stable ui-btn-group ui-border-t">
					<button class="ui-btn-lg" type="reset">取消</button>
					<button class="ui-btn-lg ui-btn-primary">确认</button>
				</div>
			</form>
		</section>
	</body>
</html>
