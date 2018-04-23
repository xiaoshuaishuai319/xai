<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="小帅丶,人工智能,图像识别">
	<meta http-equiv="description" content="小帅丶博客">
  </head>
  <body>
    <div id="content" class="summernote"></div>
	<script type="text/javascript">
		$().ready(function() {
			$('.summernote').summernote({
				lang : 'zh-CN'
			});
			var content = /*[[${bContent.content}]]*/
			$('#content').code(content);
			$('.summernote').destroy();
		});
	</script>
  </body>
</html>
