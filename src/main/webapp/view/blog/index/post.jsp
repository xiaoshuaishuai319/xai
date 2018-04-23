<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <jsp:include page="/common/header.jsp"></jsp:include>
    <jsp:include page="/common/blog_header.jsp"></jsp:include>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="小帅丶,人工智能,图像识别">
	<meta http-equiv="description" content="小帅丶博客">
  </head>
  <body>
	<jsp:include page="/view/blog/index/include_blog_nav.jsp"></jsp:include>
	<!-- Page Header -->
	<header class="intro-header"
		style="background-image: url('${ctx}/image/blog/post-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="post-heading">
						<h1>${bContent.title}</h1>
						<span class="meta">作者:
						 <a href="#">${bContent.author}</a>&nbsp;&nbsp;
						 最后修改时间:<a href="#">${gtmModified}</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Post Content -->
	<article>
		<div class="container">
			<div class="col-sm-11">
				<div id="content" class="summernote">
				${bContent.content}
				</div>
			</div>
		</div>
	</article>
	<hr>
<jsp:include page="/view/blog/index/include_blog_footer.jsp"></jsp:include>
<script type="text/javascript">
		$().ready(function() {
			$('.summernote').summernote({
				lang : 'zh-CN'
			});
			var content = /*[[${bContent.content}]]*/
			$('.summernote').summernote('code',content);
			$('.summernote').summernote('destroy');
		}); 
</script>
  </body>
</html>
