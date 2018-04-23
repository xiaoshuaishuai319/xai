<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>XAI</title>
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
   	<header class="intro-header" style="background-image: url('${ctx}/image/blog/home-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="site-heading">
						<h1>小帅丶博客</h1>
						<span class="subheading">平生不做皱眉事,世上应无切齿人</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<div id="incomeNum"></div>
				<div class="clearfix">
					<p id="flagLoaded" style="display: none; text-align: center;">已全部加载</p>
					<a id="flagLoad" style="display: none;" class="btn btn-secondary float-right" 
						href="javascript:void(0)" onclick="nextPage()">更早 &rarr;</a>
				</div>
			</div>
		</div>
	</div>
	</body>
<jsp:include page="/view/blog/index/include_blog_footer.jsp"></jsp:include>
<script type="text/javascript">
		var limit = 10;
		var currentPage = 0;
		var total;
		$(function(){
			bindList(0);
		});
		function nextPage() {
			bindList(currentPage * limit)
		}
		function bindList(offset) {
			$
				.ajax({
					url : 'blog/open/list?type=article&limit=10&offset=' + offset,
					method : 'get',
					dataType : 'json',
					success : function(data) {
						var rows = data.rows;
						total = data.total;
						var htmlText = "";
						for (i = 0; i < rows.length; i++) {
							htmlText += '<div class="post-preview">';
							htmlText += '<a href="${ctx}/blog/open/post/' + rows[i].cid + '">';
							htmlText += '<h2 class="post-title">';
							htmlText += rows[i].title;
							htmlText += '</h2>';
							htmlText += '</a>';
							htmlText += '<p class="post-meta">作者：<a href="#">'
								+ rows[i].author
								+ '</a> &nbsp;&nbsp; '
								+ format(rows[i].gtmModified) + '</p>';
							htmlText += '</div>';
							htmlText += '<hr>';
						}
						$("#incomeNum").append(htmlText);
						document.getElementById("flagLoad").style.display = "block";
						currentPage++;
						if (total <= currentPage * limit) {
							document.getElementById("flagLoaded").style.display = "block";
							document.getElementById("flagLoad").style.display = "none";
						}
					}
				});
		}

	function format(fmt) {
		var timestamp = fmt;
		timestamp = new Date(timestamp);
		var year = timestamp.getFullYear() + '-';
		var month = timestamp.getMonth() + 1 + '-';
		var date = timestamp.getDate() + '';
		var hours = timestamp.getHours();
		if(hours<10){
			hours ='0'+hours; 
		}
		var minutes = timestamp.getMinutes();
		if(minutes<10){
			minutes ='0'+minutes; 
		}
		var seconds = timestamp.getSeconds() + '';
		if(seconds<10){
			seconds ='0'+seconds; 
		}
		return year + month + date + ' ' + hours + ':' + minutes + ':' + seconds;
	}
</script>
  </body>
</html>
