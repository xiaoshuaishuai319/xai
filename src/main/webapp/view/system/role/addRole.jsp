<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加角色</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
  </head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>   </h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm">
							<input id="userId" name="userId" type="hidden"> <input
								id="menuIds" name="menuIds" type="hidden">
							<div class="form-group">
								<label class="col-sm-3 control-label">角色名：</label>
								<div class="col-sm-8">
									<input id="roleName" name="roleName" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">备注：</label>
								<div class="col-sm-8">
									<input id="remark" name="remark" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单权限：</label>
								<div class="col-sm-8">
									<div id="menuTree"></div>
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
	</div>
	<div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	var menuIds;
	$(function() {
		getMenuTreeData();
		validateRule();
	});
	$.validator.setDefaults({
		submitHandler : function() {
			getAllSelectNodes();
			save();
		}
	});
	
	function getAllSelectNodes() {
		var ref = $('#menuTree').jstree(true); // 获得整个树
	
		menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
	
		$("#menuTree").find(".jstree-undetermined").each(
				function(i, element) {
					menuIds.push($(element).closest('.jstree-node').attr(
							"id"));
				});
	}
	function getMenuTreeData() {
		$.ajax({
			type : "GET",
			url : "<%=basePath%>/menu/treeMenu",
			success : function(menuTree) {
				loadMenuTree(menuTree);
			}
		});
	}
	function loadMenuTree(menuTree) {
		$('#menuTree').jstree({
			'core' : {
				'data' : menuTree
			},
			"checkbox" : {
				"three_state" : true,
			},
			"plugins" : [ "wholerow", "checkbox" ]
		});
		//$('#menuTree').jstree("open_all");
	
	}
	//保存操作
	function save() {
		$('#menuIds').val(menuIds);
		var role = $('#signupForm').serialize();
		$.ajax({
			cache : true,
			type : "POST",
			url : "<%=basePath%>/role/saveRole",
			data : role, // formid
			async : false,
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功",{icon:1});
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				} else {
					parent.layer.msg(data.msg,{icon:0});
				}
			}
		});
	}
	
	function validateRule() {
		var icon = "<i class='fa fa-times-circle'></i> ";
		$("#signupForm").validate({
			rules : {
				roleName : {
					required : true
				}
			},
			messages : {
				roleName : {
					required : icon + "请输入角色名"
				}
			}
		});
	}
	</script>
</body>
</html>
