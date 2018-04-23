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
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="col-sm-12">
							<h4>发布文章</h4>
						</div>
						<form class="form-horizontal m-t" id="signupForm">
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
							<input id="created" name="created" class="form-control"
								type="hidden"> <input id="ownerId" name="ownerId"
								class="form-control" type="hidden">
							<div class="form-group">
								<label class="col-sm-1 control-label">邮箱：</label>
								<div class="col-sm-4">
									<input id="email" name="email" class="form-control" type="text">
								</div>
								<label class="col-sm-1 control-label">链接地址：</label>
								<div class="col-sm-4">
									<input id="url" name="url" class="form-control" type="text">
								</div>
							</div>
							<input id="ip" name="ip" class="form-control" type="hidden">
							<input id="agent" name="agent" class="form-control" type="hidden">
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
									<input id="type" name="type" class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">开启评论：</label>
								<div class="switch onoffswitch col-sm-1">
									<div class="onoffswitch">
										<input type="checkbox" checked="" class="onoffswitch-checkbox"
											id="example1"> <label class="onoffswitch-label"
											for="example1"> <span class="onoffswitch-inner"></span>
											<span class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>

								<label class="col-sm-2 control-label">允许订阅：</label>
								<div class="switch onoffswitch col-sm-2">
									<div class="onoffswitch">
										<input type="checkbox" checked="" class="onoffswitch-checkbox"
											id="example3"> <label class="onoffswitch-label"
											for="example3"> <span class="onoffswitch-inner"></span>
											<span class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>

							<input id="parent" name="parent" class="form-control"
								type="hidden">
							<div class="text-right form-group">
								<a class="btn btn-default waves-effect waves-light"
									href="/admin/article">返回列表</a>
								<button type="button"
									class="btn btn-primary waves-effect waves-light"
									onclick="save()">保存文章</button>
								<button type="button"
									class="btn btn-warning waves-effect waves-light"
									onclick="subArticle('draft');">存为草稿</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
<script type="text/javascript">
	$().ready(function() {
		$('.summernote').summernote({
			height : '220px',
			lang : 'zh-CN'
		});
		validateRule();
	});
	$.validator.setDefaults({
		submitHandler : function() {
			save();
		}
	});
	function save() {
		var content_sn = $("#content_sn").code();
		$("#content").val(content_sn);
		$.ajax({
			cache : true,
			type : "POST",
			url : "${ctx}/blogmanager/bComments/save",
			data : $('#signupForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});

	}
	function validateRule() {
		var icon = "<i class='fa fa-times-circle'></i> ";
		$("#signupForm").validate({
			rules : {
				title : {
					required : true
				}
			},
			messages : {
				title : {
					required : icon + "请输入标题"
				}
			}
		})
	}
</script>
</body>
</html>
