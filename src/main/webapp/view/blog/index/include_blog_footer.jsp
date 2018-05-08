<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
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
<style type="text/css">
	@media (max-width:768px){
		.side{ display:none;  }
		}
	@media (min-width:768px){
		.side{ display:block;  }
		}
	.side{
		position: fixed;
	    width: 50px;
	    right: 5%;
	    bottom: 15%;
	    z-index: 100;
	}
	.side ul{
		list-style:none;
	}
	.side ul li {
	    width: 35px;
	    height: 35px;
	    float: left;
	    position: relative;
	    margin-top: 9px;
	}
	.side ul li .qqbdimg {
	    padding: 7px;
	    margin-top: 5px;
	    margin-bottom: 1px;
	    /* margin-left: 22px; */
	    position: absolute;
	    width: 53px;
	    height: 54px;
	    /* margin-left: 9px; */
	    bottom: 0;
	    right: -7px;
	    transition: all 0.3s;
	    background: #fff;
	    opacity: 0;
	    filter: Alpha(opacity=80);
	    color: #fff;
	    font: 14px/54px "微软雅黑";
	    overflow: hidden;
	}
	.side ul li .qqbdimg:hover{
		overflow:hidden;
		padding: 7px;
	    margin-top: 5px;
	    width:124px;
	    height:124px;
	    opacity:1;
	    z-index:100;
	    right:0;
	    background:rgb(142, 142, 142);
	}
	.side ul li img {
	    width: 100%;
	    height: 100%;
	}
	.side ul li .sidetop {
	    height: 100%;
	    display: block;
	    background: #2f4050;
	    padding: 2px 0px;
	    text-align: center;
	    color: #fff;
	    border-radius: 50%;
	    opacity: .5;
	}
</style>
  </head>
	<div>
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<ul class="list-inline text-center">
						<li><a href="http://wpa.qq.com/msgrd?v=3&uin=783021975&site=qq&menu=yes"> <span class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-qq fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li><a href="https://my.oschina.net/xshuai"> <span class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-home fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li><a href="https://gitee.com/xshuai/xai"> <span class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-github fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
					</ul>
					<p class="copyright text-muted">Copyright &copy; xsshome.cn
						2018</p>
					<p class="copyright text-muted">小帅ICP备20180001号</p>

				</div>
			</div>
		</div>
	</footer>
	<div class="side">
		<ul>
			<li>
				<div class="qqbdimg">
					<img src="${ctx}/image/blog/qqxs.jpg" title="联系小帅丶" />
				</div>
				<a class="sidetop" href="javascript:scroll(0,0)" id="qqbd" title="学习交流"><i class="fa fa-qq"></i></a>
			</li>
			<li>
				<a class="sidetop" href="javascript:scroll(0,0)" title="返回顶部"><i class="fa fa-arrow-up"></i></a>
			</li>
		</ul>
	</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
	<!-- blog js -->
	<script src="${ctx}/js/clean-blog.min.js"></script>
</div>