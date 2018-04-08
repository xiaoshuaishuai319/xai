<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录-管理平台</title>
<link rel="shortcut icon" href="<%=basePath%>image/favicon.ico">
<!-- login CSS -->
<link rel="stylesheet" href="<%=basePath%>css/login/style.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
<style>
body {
	height: 100%;
	background: #294d45;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
footer{  
    clear: both;  
    display: block;  
    position: absolute;  
    bottom: 100px;  
} 
</style>
<script src="<%=basePath%>js/login/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/login/verificationNumbers.js" type="text/javascript"></script>
<script src="<%=basePath%>js/login/Particleground.js" type="text/javascript"></script>
<script src="<%=basePath%>bootstrap/js/plugins/layer/layer.min.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
    if (window.top!=null && window.top.document.URL!=document.URL){    
        window.top.location= document.URL;     
    } 
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
});
function login(){
	var uname = $("#username").val();
	var passd = $("#password").val();
	if(uname.length!=0&&passd.length!=0){
		$.ajax({
			type: "POST",
            url: "<%=basePath%>mvcdo/login",
            data: $('#signupForm').serialize(),
            success: function (r) {
                if (r.code == 0) {
                    parent.location.href = '<%=basePath%>mvcdo/index';
                } else {
                    layer.msg(r.msg,{time:2000});
                }
            }
		});
	}else{
       alert("账户和密码不能为空");
    }
}
</script>
</head>
<body>
	<dl class="admin_login">
		<dt>
			<strong>管理系统</strong> <em>Manager System</em>
		</dt>
		<form id="signupForm">
		<dd class="user_icon">
			<input type="text" name="username" id="username" placeholder="账户" class="login_txtbx">
		</dd>
		<dd class="pwd_icon">
			<input type="password" name="password" id="password" placeholder="密码" class="login_txtbx">
		</dd>
		<dd>
			<input type="button" value="立即登陆" class="submit_btn" onclick="login()">
		</dd>
		</form>
	</dl>
</body>
</html>