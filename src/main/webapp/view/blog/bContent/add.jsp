<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>XAI</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="小帅丶,人工智能,图像识别">
	<meta http-equiv="description" content="小帅丶博客">
  </head>
 <body class="gray-bg">
 <form class="form-horizontal m-t" id="signupForm">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="col-sm-12">
							<h4>发布文章</h4>
						</div>
							<input id="cid" name="cid" type="hidden">
							<div class="form-group">
								<label class="col-sm-1 control-label">标题：</label>
								<div class="col-sm-4">
									<input id="title" name="title" class="form-control" type="text">
								</div>
								<label class="col-sm-1 control-label">作者：</label>
								<div class="col-sm-4">
									<input id="author" name="author" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<input id="content" name="content" type="hidden"> <label
									class="col-sm-1 control-label">内容：</label>
								<div class="col-sm-11">
									<div class="ibox-content no-padding">
										<div id="content_sn" class="summernote"></div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-1 control-label">类型：</label>
								<div class="col-sm-11">
									<input id="categories" name="categories" class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">开启评论：</label>
								<div class="switch onoffswitch col-sm-1">
									<div class="onoffswitch">
										<input id="allowComment" name="allowComment" type="checkbox" value="1" class="onoffswitch-checkbox"> 
										<label class="onoffswitch-label" for="allowComment"> 
										<span class="onoffswitch-inner"></span>
										<span class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>

								<label class="col-sm-2 control-label">允许订阅：</label>
								<div class="switch onoffswitch col-sm-2">
									<div class="onoffswitch">
										<input id="allowFeed" name="allowFeed" type="checkbox" value="1" class="onoffswitch-checkbox">
										<label class="onoffswitch-label" for="allowFeed">
										 <span class="onoffswitch-inner"></span> 
										 <span class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
								<input id="status" name="status" type="hidden">
							</div>
							<div class="text-right form-group">
								<a class="btn btn-default waves-effect waves-light"
									onclick="returnList()">返回列表</a>
								<button type="button"
									class="btn btn-primary waves-effect waves-light" type="submit" onclick="save(1)">保存文章</button>
								<button type="button"
									class="btn btn-warning waves-effect waves-light"
									onclick="save(0)">存为草稿</button>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
<jsp:include page="/common/footer.jsp"></jsp:include>
<script src="${ctx}/js/ajax-util.js"></script>
<script type="text/javascript">
$().ready(function() {
	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
	validateRule();
});
$.validator.setDefaults({
	submitHandler:function() {
		save(1);
	}
});
function save(status) {
	$("#status").val(status);
	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	var title = $("#title").val();
	var author = $("#author").val();
	var content = $("#content").val();
	if(null==title||''==title){
		parent.layer.msg("请填写文章标题");
		return false;
	}
	if(null==content||''==content||'<p><br></p>'==content){
		parent.layer.msg("请填写文章内容");
		return false;
	}
	if(null==author||''==author){
		parent.layer.msg("请填写文章作者");
		return false;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "${ctx}/blogmanager/bContent/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(r) {
			if (r.code == 0) {
				parent.layer.msg("文章保存成功,文章编号为:"+r.msg);
				parent.reLoad();
				$("#cid").val(r.cid);
			} else {
				parent.layer.alert(r.msg)
			}
		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			title : "required",
			author : "required",
			content : "required"
		},
		messages : {
			title : "请填写文章标题",
			author : "请填写文章作者",
			content : "请填写文章内容"
		}
	});
}
function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
</script>
</body>
</html>
