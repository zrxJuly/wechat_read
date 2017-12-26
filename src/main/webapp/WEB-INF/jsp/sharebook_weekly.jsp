<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>享 悦 读</title>
<meta content="width=device-width,user-scalable=0" name="viewport">

<!-- 引入frozen样式 -->
<link rel="stylesheet" href="frozen/css/frozen.css">
<script type="text/javascript" src="frozen/js/lib/zeptojs/zepto.min.js"></script>
<script type="text/javascript" src="frozen/js/frozen.js"></script>

<!-- jQuery引入 -->
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-border-b">
		<i class="ui-icon-return" onclick="returnMain()"></i>
		<h1>本周荐书</h1>
		<!-- <button class="ui-btn">回首页</button> -->
	</header>
	<section class="ui-container ui-center">
		<div class="ui-form ui-border-t">
			<img src="${shareBook.imgUrl }" width="350px;" height="300px">
			<div class="ui-form-item ui-border-b">
				<label>书名</label>
				<input type="text" readonly="readonly" value="${shareBook.bookName }" />
			</div>
			<div class="ui-form-item ui-border-b">
				<label>作者</label>
				<input type="text" readonly="readonly" value="${shareBook.bookAuthor }" />
			</div>
			<div class="ui-form-item ui-border-b">
				<label>豆瓣评分</label>
				<input type="text" readonly="readonly" value="${shareBook.doubanScore }" />
			</div>
			<div class="ui-form-item ui-border-b">
				<label>内容简介</label>
				<input type="text" readonly="readonly" value="${shareBook.bookContent }" />
			</div>
		</div>
		<br />
	</section>
</body>
<script type="text/javascript">

	//关闭当前网页跳转到公众号主界面.
	function returnMain() {
		WeixinJSBridge.call('closeWindow');
	}	
</script>
</html>
