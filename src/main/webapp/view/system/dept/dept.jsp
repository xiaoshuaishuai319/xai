<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>部门管理</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
  </head>
  <body class="gray-bg">
	<div class="wrapper wrapper-content ">
		<div class="col-sm-12">
			<div class="ibox">
				<div class="ibox-body">
					<div id="exampleToolbar" role="group">
						<button type="button" class="btn  btn-primary" onclick="add(0)">
							<i class="fa fa-plus hidden" aria-hidden="true"></i>添加部门</button>
					</div>
					<table id="exampleTable" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
	</div>
		<div>
			<script type="text/javascript">
				var s_add_h = 'hidden';
				var s_edit_h = 'hidden';
				var s_remove_h = 'hidden';
			</script>
		</div>
		<div>
			<script type="text/javascript">
				s_add_h = '';
			</script>
		</div>
		<div>
			<script type="text/javascript">
				s_edit_h = '';
			</script>
		</div>
		<div>
			<script type="text/javascript">
				var s_remove_h = '';
			</script>
		</div>
	<div>
	  <jsp:include page="/common/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
	var prefix = "<%=basePath%>";
	$(function() {
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTreeTable(
			{
				id : 'deptId',
				code : 'deptId',
                parentCode : 'parentId',
				type : "GET", // 请求数据的ajax类型
				url : prefix + 'dept/listDept', // 请求数据的ajax的url
				ajaxParams : {}, // 请求数据的ajax的data属性
				expandColumn : '1', // 在哪一列上面显示展开按钮
				striped : true, // 是否各行渐变色
				bordered : true, // 是否显示边框
				expandAll : false, // 是否全部展开
				// toolbar : '#exampleToolbar',
				columns : [
					{
						title : '编号',
						field : 'deptId',
						visible : false,
						align : 'center',
						valign : 'middle',
						width : '50px'
					},
					{
						field : 'name',
						title : '部门名称'
					},
					{
						field : 'orderNum',
						title : '排序'
					},
					{
						field : 'delFlag',
						title : '状态',
						align : 'center',
						formatter : function(item, index) {
							if (item.delFlag == '0') {
								return '<span class="label label-danger">禁用</span>';
							} else if (item.delFlag == '1') {
								return '<span class="label label-primary">正常</span>';
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(item, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '"  mce_href="#" title="编辑" onclick="edit(\''
								+ item.deptId
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '"  title="增加下級"  mce_href="#" onclick="add(\''
								+ item.deptId
								+ '\')"><i class="fa fa-plus"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '"  title="删除"  mce_href="#" onclick="removeone(\''
								+ item.deptId
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm＂  title="备用"  mce_href="#" onclick="resetPwd(\''
								+ item.deptId
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e + a + d;
						}
					} ]
			});
}
function add(pId) {
	layer.open({
		type : 2,
		title : '增加部门',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + 'dept/addDept/' + pId
	});
}
function reLoad() {
	load();
}
function edit(id) {
	layer.open({
		type : 2,
		title : '修改部门',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + 'dept/editDept/' + id // iframe的url
	});
}
function removeone(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "dept/removeDept",
			type : "post",
			data : {
				'deptId' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg,{icon:1});
					reLoad();
				} else {
					layer.msg(r.msg,{icon:0});
				}
			}
		});
	})
}
</script>
</body>
</html>
