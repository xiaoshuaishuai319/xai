<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>角色管理</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
  </head>
  <body class="gray-bg">
	<div class="wrapper wrapper-content ">
		<div class="col-sm-12">
			<div class="ibox">
				<div class="ibox-body">
					<div id="exampleToolbar" role="group">
						<button type="button" class="btn btn-primary" onclick="add()">
							<i class="fa fa-plus" aria-hidden="true"></i>添加
						</button>
						<button type="button" class="btn btn-danger" onclick="batchRemove()">
							<i class="fa fa-trash" aria-hidden="true"></i>删除
						</button>
					</div>
					<table id="exampleTable" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
		<div>
			<script type="text/javascript">
				var s_edit_h = 'hidden';
				var s_remove_h = 'hidden';
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
		$('#exampleTable').bootstrapTable({
							method : 'get', // 服务器数据的请求方式 get or post
							url : prefix + "role/listRole", // 服务器数据的加载地址
							striped : true, // 设置为true会有隔行变色效果
							dataType : "json", // 服务器返回的数据类型
							pagination : true, // 设置为true会在底部显示分页条
							// queryParamsType : "limit",
							// //设置为limit则会发送符合RESTFull格式的参数
							singleSelect : false, // 设置为true将禁止多选
							iconSize : 'outline',
							toolbar : '#exampleToolbar',
							pageSize : 10, // 如果设置了分页，每页数据条数
							pageNumber : 1, // 如果设置了分布，首页页码
							search : true, // 是否显示搜索框
							showColumns : true, // 是否显示内容下拉框（选择显示的列）
							sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
							// 返回false将会终止请求
							columns : [
									{ // 列配置项
										// 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
										checkbox : true
									// 列表中显示复选框
									},
									{
										field : 'roleId', // 列字段名
										title : '序号' // 列标题
									},
									{
										field : 'roleName',
										title : '角色名'
									},
									{
										field : 'remark',
										title : '备注'
									},
									{
										title : '操作',
										field : 'roleId',
										align : 'center',
										formatter : function(value, row,index) {
											var e = '<a class="btn btn-primary btn-sm '
													+ s_edit_h
													+ '"  title="编辑" onclick="edit(\''
													+ row.roleId
													+ '\')"><i class="fa fa-edit"></i></a> ';
											var d = '<a class="btn btn-warning btn-sm '
													+ s_remove_h
													+ '" title="删除"  onclick="remove(\''
													+ row.roleId
													+ '\')"><i class="fa fa-remove"></i></a> ';
											return e + d;
										}
									} ]
						});
	}
	function reLoad() {
		$('#exampleTable').bootstrapTable('refresh');
	}
	//添加操作
	function add() {
		// iframe层
		layer.open({
			type : 2,
			title : '添加角色',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '800px', '520px' ],
			content : prefix + 'role/addRole' // iframe的url
		});
	}
	//删除操作
	function remove(id) {
		layer.confirm('确定要删除选中的记录？', {btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				url : prefix + "role/removeRole",
				type : "post",
				data : {
					'id' : id
				},
				success : function(r) {
					if (r.code == 0) {
						layer.msg("删除成功");
						reLoad();
					} else {
						layer.msg(r.msg);
					}
				}
			});
		})
	}
	//编辑操作
	function edit(id) {
		layer.open({
			type : 2,
			title : '修改角色',
			maxmin : true,
			shadeClose : true, // 点击遮罩关闭层
			area : [ '800px', '520px' ],
			content : prefix + 'role/editRole/' + id // iframe的url
		});
	}
	//批量删除操作
	function batchRemove() {
		var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
		if (rows.length == 0) {
			layer.msg("请选择要删除的数据",{icon:3});
			return;
		}
		layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {btn : [ '确定', '取消' ]
		}, function() {
			var ids = new Array();
			$.each(rows, function(i, row) {
						ids[i] = row['roleId'];
					});
					console.log(ids);
					$.ajax({
						type : 'POST',
						data : {
							"ids" : ids
						},
						url : prefix + 'role/batchRemoveRole',
						success : function(r) {
							if (r.code == 0) {
								layer.msg(r.msg,{icon:1});
								reLoad();
							} else {
								layer.msg(r.msg,{icon:0});
							}
						}
					});
				}, function() {
					layer.msg('取消操作',{icon:1});
				});
			}
	</script>
</body>
</html>
