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
    <title>编辑角色</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
  </head>
  <body class="gray-bg">
<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm">
							<input id="parentId" name="parentId" type="hidden" value="${pId}" /> 
							<input id="menuId" name="menuId" type="hidden" value="${menu.menuId}" />
							<div class="form-group">
								<label class="col-sm-3 control-label">上级菜单：</label>
								<div class="col-sm-8">
									<input id="" name="" class="form-control" type="text" value="${pName}" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单类型：</label>
								<div class="col-sm-8">
									<label class="radio-inline"> <input type="radio" <c:if test="${menu.menuType==0}">checked</c:if> name="type" value="0" /> 目录
									</label> <label class="radio-inline"> <input type="radio" <c:if test="${menu.menuType==1}">checked</c:if> name="type" value="1" /> 菜单
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单名称：</label>
								<div class="col-sm-8">
									<input id="name" name="name" class="form-control" type="text"
										value="${menu.name}" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">链接地址：</label>
								<div class="col-sm-8">
									<input id="url" name="url" class="form-control" type="text"
										value="${menu.url}">
								</div>
							</div>
								<div class="form-group">
								<label class="col-sm-3 control-label">排序号：</label>
								<div class="col-sm-8">
									<input id="orderNum" name="orderNum" class="form-control"
										type="text" value="${menu.orderNum}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">图标：</label>
								<div class="col-sm-5">
									<input id="icon" name="icon" class="form-control" type="text"
										placeholder="例如：fa fa-circle-o" value="${menu.icon}">
								</div>
								<input id="ico-btn" class="btn btn-warning" type="button" value="选择图标">
							</div>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button type="submit" class="btn btn-info">保存修改</button>
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
$(function() {
	validateRule();
	//打开图标列表
    $("#ico-btn").click(function(){
        layer.open({
            type: 2,
			title:'图标列表',
            content: prefix+'common/FontIcoList.html',
            area: ['480px', '90%'],
            offset:'15px',
            success: function(layero, index){
            }
        });
    });
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
		url : prefix + "menu/updateMenu",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			laryer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("保存成功",{icon:1});
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				layer.alert(data.msg,{icon:0})
			}

		}
	});

}
function validate() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入菜单名"
			},
			type : {
				required : icon + "请选择菜单类型"
			}
		}
	})
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入菜单名"
			},
			type : {
				required : icon + "请选择菜单类型"
			}
		}
	})
}
</script>
</body>
</html>
