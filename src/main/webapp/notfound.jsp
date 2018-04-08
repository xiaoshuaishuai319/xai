<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>404</title>
<link rel="shortcut icon" href="<%=basePath%>image/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="<%=basePath%>css/error/style.css" rel='stylesheet' type='text/css'/><!-- style.css --> 
<link href="//fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Playball" rel="stylesheet">
</head>
<body>
<div id="particles-js"></div>
	<div class="main-w3layouts">
	<h1>Astounding Error Page</h1>
		<div class="main-agile">
			<h2>NotFound 404</h2>
			<p>请求的页面找不到了......</p>
		<div class="botton-w3ls"><a href="#">返回主页</a></div>
		</div>
		<div class="footer-w3l">
			<p class="agileinfo"> &copy;  All Rights Reserved | Power By JLPay</p>
		</div>
	</div>
	<script src="<%=basePath%>js/error/particles.js"></script>
	<script src="<%=basePath%>js/error/app.js"></script>
</body>
