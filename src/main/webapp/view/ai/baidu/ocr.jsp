<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>文字识别</title>
    <jsp:include page="/common/header.jsp"></jsp:include>
    <link href="<%=basePath%>/bootstrap/css/layui.css" rel="stylesheet">
    <style type="text/css">
    #exampleTable{
    table-layout: fixed;
    word-break:break-all; 
    word-wrap:break-all;
	}
	.cmcbsearch{
		height: 30px;
		font-size: inherit;
		width: 168px;
	}
	.faceimage{
    border-radius:50%;
}
    </style>
  </head>
  <body class="gray-bg">
	<div class="wrapper wrapper-content ">
		<div class="col-sm-12">
			<div class="ibox">
				<div class="ibox-body">
					<div class="columns pull-left">
					微信昵称:<input id="nickName" class="cmcbsearch" placeholder="请输入微信昵称" type="text">
					<button class="btn btn-success" onclick="reLoad()"> 查 询</button>
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
		load()
	});
 function load() {
	$('#exampleTable').bootstrapTable({
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "bdocr/listOcrGeneral", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				// search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						nikeName : $('#nikeName').val()
					};
				},
				columns : [
					{
						field: 'Number',  
						width:25,
					    formatter: function (value, row, index) {  
					    return index+1;  
						}  
					},
					{
						checkbox : true
					},{
						field : 'ocrId', // 列字段名
						title : '序号', // 列标题,
						visible:false,
						width:50
					},{
						field : 'logId',
						title : '日志ID',
						visible:false,
						width:100
					},{
						field : 'imagePath',
						title : '识别的图片',
						width:70,
						formatter:function (value,row,index) {
							var values='.'+value;
							var a = '<img class="faceimage" src="'+values+'" width="66px" height="66px" onerror="this.src=\'./image/loadfail.png\'">';
							return a;
                        }						
					},{
						field : 'words',
						title : '识别的内容',
						width:110,
						formatter:function(value,row,index){
							var values = value.substring(0,35)+"...";
							return values;
						}
					},{
						field : 'wordsResultNum',
						title : '识别个数',
						width:80
					},{
						field : 'direction',
						title : '图片朝向',
						width:100,
						formatter:function(value,row,index){
							var values = "";
							if(value==-1){
								values="未定义";
							}else if(value==0){
								values="正向";
							}else if(value==1){
								values="逆时针90度";
							}else if(value==2){
								values="逆时针180度";
							}else if(value==3){
								values="逆时针270度";
							}else{
								values="未知朝向";
							}
							return values;
						}
					},{
						field : 'errorCode',
						title : '错误对应码',
						width:100,
						visible:false
					},{
						field : 'errorMsg',
						title : '错误描述',
						width:100,
						visible:false
					},{
						field : 'enterType',
						title : '访问类型',
						width:80
					},{
						field : 'apiType',
						title : '接口类型',
						width:80
					},{
						field : 'openId',
						title : '微信openid',
						width:110
					},{
						field : 'nikeName',
						title : '微信昵称',
						width:70
					},{
						title : '操作',
						field : 'id',
						align : 'center',
						width:130,
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '"  title="查看详情" onclick="view(\''+ row.ocrId+ '\')"><i class="fa fa-edit "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_edit_h + '" " title="删除"  onclick="remove(\''+ row.ocrId+'\')"><i class="fa fa-remove"></i></a> ';
							return e+d;
						}
					} ]
			});
}
	function reLoad() {
		$('#exampleTable').bootstrapTable('refresh');
	}
	//删除操作
	function remove(id) {
		layer.confirm('确定要删除选中的内容？', {btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				url : prefix + "bdocr/removeOcrGeneral",
				type : "post",
				data : {
					'id' : id
				},
				success : function(r) {
					if (r.code == 0) {
						layer.msg("删除成功",{icon:1});
						reLoad();
					} else {
						layer.msg(r.msg,{icon:0});
					}
				}
			});
		})
	}
	//批量删除操作
	function batchRemove() {
		var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
		if (rows.length == 0) {
			layer.msg("请选择要删除的数据",{icon:3});
			return;
		}
		layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?",{btn : [ '确定', '取消' ]
			}, function() {
			var ids = new Array();
			$.each(rows, function(i, row) {
						ids[i] = row['ocrId'];
					});
					console.log(ids);
					$.ajax({
						type : 'POST',
						data : {
							"ids" : ids
						},
						url : prefix + 'bdocr/batchRemoveOcrGeneral',
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
					layer.msg("取消操作",{icon:1});
				});
			}
</script>
</body>
</html>