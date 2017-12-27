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
		<h1>荐书历史</h1>
		<!-- <button class="ui-btn">回首页</button> -->
	</header>
	<section class="ui-container">
		<section id="list">
			<div class="demo-item">
				<p class="demo-desc">享悦读，爱阅读</p>
				<a href="http://5ze33x.natappfree.cc/userInfo/shareBookWeekly">
					<div class="demo-block">
						<ul class="ui-list ui-list-pure ui-border-tb">
							<li class="ui-border-t">
								<p>
									<span></span><span class="date">${weeklyHistory.updateTime }</span>
								</p>
								<h4>《 ${weeklyHistory.bookName } 》</h4>
							</li>
						</ul>
					</div>
				</a>
			</div>
		</section>
	</section>
</body>
<script type="text/javascript">
	//关闭当前网页跳转到公众号主界面.
	function returnMain() {
		WeixinJSBridge.call('closeWindow');
	}
</script>
</html>
