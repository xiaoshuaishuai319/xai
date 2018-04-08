<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>编辑用户</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
  </head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<form class="form-horizontal m-t" id="signupForm">
						<input id="userId" name="userId" value="${user.userId}" type="hidden">
						<div class="form-group">
							<label class="col-sm-3 control-label">姓名：</label>
							<div class="col-sm-8">
								<input id="name" name="name" class="form-control" type="text" value="${user.name}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">用户名：</label>
							<div class="col-sm-8">
								<input id="username" name="username" class="form-control"
									type="text" value="${user.username}" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">部门：</label>
							<div class="col-sm-8">
								<input id="deptId" name="deptId" class="hidden" value="${user.deptId}"> 
								<input id="deptName" name="deptName" class="form-control" type="text" style="cursor: pointer;" onclick="openDept()" readonly="readonly" placeholder="所属部门" value="${user.deptName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">E-mail：</label>
							<div class="col-sm-8">
								<input id="email" name="email" class="form-control" type="email" value="${user.email}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">状态:</label>
							<div class="col-sm-8">
								<label class="radio-inline"> 
								<input  <c:if test="${user.status==1}">checked</c:if> type="radio" name="status" value="1" />
									正常
								</label> 
								<label class="radio-inline"> 
								<input <c:if test="${user.status==0}">checked</c:if> type="radio" name="status" value="0" />
									禁用
								</label>
							</div>
						</div>
						<input type="hidden" name="roleIds" id="roleIds" value="${roleIds}">
						<div class="form-group">
							<label class="col-sm-3 control-label">角色</label>
							<div class="col-sm-8">
								<c:forEach items="${roles}" var="role">
									<label class="checkbox-inline"> <input name="role"
										type="checkbox" value="${role.roleId}" <c:if test="${role.roleSign==true}">checked="checked"</c:if>>${role.roleName}
									</label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<button type="submit" class="btn btn-primary">提交</button>
							</div>
						</div>
					</form>
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
			$("#roleIds").val(getCheckedRoles());
			$.ajax({
				cache : true,
				type : "POST",
				url : prefix+"user/updateUser",
				data : $('#signupForm').serialize(),// formid
				async : false,
				error : function(request) {
					alert("Connection error");
				},
				success : function(data) {
					if (data.code == 0) {
						parent.layer.msg(data.msg,{icon:1});
						parent.reLoad();
						var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
						parent.layer.close(index);
					} else {
						parent.layer.msg(data.msg,{icon:0});
					}

				}
			});

		}
		function getCheckedRoles() {
			var adIds = "";
			$("input:checkbox[name=role]:checked").each(function(i) {
				if (0 == i) {
					adIds = $(this).val();
				} else {
					adIds += ("," + $(this).val());
				}
			});
			return adIds;
		}
		function setCheckedRoles() {
			var roleIds = $("#roleIds").val();
			alert(roleIds);
			var adIds = "";
			$("input:checkbox[name=role]:checked").each(function(i) {
				if (0 == i) {
					adIds = $(this).val();
				} else {
					adIds += ("," + $(this).val());
				}
			});
			return adIds;
		}
		function validateRule() {
			var icon = "<i class='fa fa-times-circle'></i> ";
			$("#signupForm").validate({
				rules : {
					name : {
						required : true
					},
					username : {
						required : true,
						minlength : 2
					},
					password : {
						required : true,
						minlength : 6
					},
					confirm_password : {
						required : true,
						minlength : 6,
						equalTo : "#password"
					},
					email : {
						required : true,
						email : true
					},
					topic : {
						required : "#newsletter:checked",
						minlength : 2
					},
					agree : "required"
				},
				messages : {

					name : {
						required : icon + "请输入姓名"
					},
					username : {
						required : icon + "请输入您的用户名",
						minlength : icon + "用户名必须两个字符以上"
					},
					password : {
						required : icon + "请输入您的密码",
						minlength : icon + "密码必须6个字符以上"
					},
					confirm_password : {
						required : icon + "请再次输入密码",
						minlength : icon + "密码必须6个字符以上",
						equalTo : icon + "两次输入的密码不一致"
					},
					email : icon + "请输入您的E-mail",
				}
			})
		}
		var openDept = function() {
			layer.open({
				type : 2,
				title : "选择部门",
				area : [ '300px', '450px' ],
				offset:['15px'],
				content : prefix+"dept/treeView"
			})
		}
		function loadDept(deptId, deptName) {
			$("#deptId").val(deptId);
			$("#deptName").val(deptName);
		}
	</script>
</body>
</html>
