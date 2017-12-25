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
		<i class="ui-icon-return" onclick="history.back()"></i>
		<h1>完善信息</h1>
		<button class="ui-btn">回首页</button>
	</header>
	<section class="ui-container ui-center">
		<!-- <form method="post" onsubmit="checkInput()"> -->
			<div class="ui-form ui-border-t">
				<input type="hidden" value="${userInfo.openId }" id="openId" name="openId" /> <img
					alt="" src="${userInfo.headImgUrl }" width="100%" height="260px">
				<div class="ui-form-item ui-border-b">
					<label>昵称</label> 
					<input type="text" id="nickName" name="nickname" placeholder="给自己取个文艺点的名字吧" value="${userInfo.nickname }" /> 
					<a class="ui-icon-close" onclick="clearName()"></a>
				</div>
				<div class="ui-form-item ui-form-item-radio ui-border-b">
					性别 <label class="ui-radio" for="radio" style="text-align: center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" ${userInfo.sex eq "1" ? "checked" : "" } name="sex" value="1" />男 
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="radio" ${userInfo.sex eq "2" ? "checked" : "" } name="sex" value="2" />女
					</label>
				</div>
				<!-- <div class="ui-form-item ui-form-item-l ui-border-b"> -->
				<div class="ui-form-item ui-border-b">
					<!-- <label class="ui-border-r">中国 +86</label> -->
					<label>手机号</label> 
					<input type="text" id="phoneNumber" name="phoneNumber" placeholder="请留下您宝贵的联系方式" value="${userInfo.phoneNumber }" />
					 <a class="ui-icon-close" onclick="clearNumber()"></a>
				</div>
				<div class="ui-form-item ui-border-b">
					<label>邮箱</label>
					<input type="text" id="email" name="email" placeholder="请输入您的邮箱地址" value="${userInfo.email }" />
					<a class="ui-icon-close" onclick="clearEmail()"></a>
				</div>
			</div>
			<!-- 底部 确认&取消按钮 -->
			<div class="ui-form-item ui-border-b ui-btn-group ui-border-t">
				<!-- <button class="ui-btn-lg" type="reset">取消</button> -->
				<button type="submit" class="ui-btn-lg ui-btn-primary" onclick="checkInput()">确认信息</button>
			</div>
			<!-- 
				<div class="ui-footer ui-footer-stable ui-btn-group ui-border-t">
					<button class="ui-btn-lg" type="reset">取消</button>
					<button class="ui-btn-lg ui-btn-primary">确认</button>
				</div> -->
		<!-- </form> -->
	</section>
</body>

<script type="text/javascript">
	function clearName() {
		$('#nickName').val('');
	}

	function clearNumber() {
		$('#phoneNumber').val('');
	}

	function clearEmail() {
		$('#email').val('');
	}
	
	function checkInput() {
		var openId = $('#openId').val();
		var nickname = $('#nickName').val();
		var sex = $('#sex').val();
		var phoneNumber = $("#phoneNumber").val();
		var email = $('#email').val();
		
		alert('aa');
		var dataSend="openId=" + openId + "&nickname=" + nickname + "&phoneNumber=" + phoneNumber + "&email=" + email;
		alert(dataSend);
		$.ajax({
			type : "get",
			url : "userInfo/updateUserInfo?",
			data : dataSend, 
			dataType : "text",
			success : function(data) {
				alert("更新成功");
			},
			error : function(result, status) {
				if (status == 'error') {
					alert("更新失败");
				}
			}
		}); 
	}
</script>
</html>
