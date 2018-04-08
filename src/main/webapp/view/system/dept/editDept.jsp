<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>编辑部门</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
  </head>
 <body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm">
							<input id="deptId" name="deptId" value="${sysDept.deptId}"
								class="hidden">
							<div class="form-group">
								<label class="col-sm-3 control-label ">上级部门：</label>
								<div class="col-sm-8">
								<input value="${parentDeptName}"
										class="form-control" type="text" readonly="true" />
									<input class="form-control hidden" type="text" id="parentId" name="parentId" value="${sysDept.parentId}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门名称：</label>
								<div class="col-sm-8">
									<input id="name" name="name"value="${sysDept.name}"
										class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序：</label>
								<div class="col-sm-8">
									<input id="orderNum" name="orderNum" value="${sysDept.orderNum}"
										class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-8">
									<input id="delFlag" name="delFlag" value="${sysDept.delFlag}"
										class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button type="submit" class="btn btn-primary">提交修改</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
var prefix = "<%=basePath%>";
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix+"dept/updateDept",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功",{icon:1});
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg,{icon:0})
			}

		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}
</script>
</body>
</html>
