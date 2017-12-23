<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>完善信息</title>
		<meta content="width=device-width,user-scalable=0" name="viewport">
		
		<!-- 引入frozen样式 -->
		<link rel="stylesheet" href="frozen/css/frozen.css">
        <script src="frozen/js/lib/zeptojs/zepto.min.js"></script>
        <script src="frozen/js/frozen.js"></script>
		
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			table{
				border: 1px dashed #B9B9DD;
				font-size: 12pt;
			}
			td{
				border: 1px dashed #B9B9DD;
				word-break:break-all;
				word-wrap:vreak-word;
			}
		</style>
	
	</head>
	
	<body>
		<header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>完善信息</h1>
            <!-- <button class="ui-btn">回首页</button> -->
        </header>
        <div class="ui-footer ui-footer-stable ui-btn-group ui-border-t">
                <button class="ui-btn-lg">
                    拒绝
                </button>
                <button class="ui-btn-lg">
                    拒绝
                </button>
                <button class="ui-btn-lg ui-btn-primary">
                    同意
                </button>
            </div>
	</body>
</html>
