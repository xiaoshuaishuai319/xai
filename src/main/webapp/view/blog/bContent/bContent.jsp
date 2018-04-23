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
<div class="wrapper wrapper-content">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-body">
                <div class="fixed-table-toolbar">
                    <div class="columns pull-left">
                        <button type="button" class="btn  btn-primary"
                                onclick="add()">
                            <i class="fa fa-plus" aria-hidden="true"></i>添加
                        </button>
                        <button type="button" class="btn  btn-danger"
                                onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                    </div>
                    <div class="columns pull-right">
                        <button class="btn btn-success" onclick="reLoad()">查询</button>
                    </div>
                    <div class="columns pull-right col-md-2 nopadding">
                        <input id="searchName" type="text" class="form-control"
                               placeholder="">
                    </div>
                </div>
                <table id="exampleTable" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
    <!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
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
<jsp:include page="/common/footer.jsp"></jsp:include>
<script type="text/javascript">
var prefix = "${ctx}/blogmanager/bContent"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						// showRefresh : true,
						// showToggle : true,
						showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						// search : true, // 是否显示搜索框
						//showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"

						queryParams : function(params) {
							return {
								// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit : params.limit,
								offset : params.offset
							// name:$('#searchName').val(),
							// username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
								{
									visible : false,
									field : 'cid',
									title : ''
								},
								{
									field : 'title',
									title : '标题',
									width :320,
                                    formatter:function (value,row,index) {
                                        return '<a href="javascript:void(0);" onclick="preview(\''+ row.cid+ '\')">'+row.title+'</a>';
                                    }
                                },
								{
									field : 'author',
									title : '作者'
								},
								{
									visible : false,
									field : 'slug',
									title : 'slug'
								},
								{
									visible : false,
									field : 'created',
									title : '创建人id'
								},
								{
									visible : false,
									field : 'modified',
									title : '最近修改人id'
								},
								{
									visible : true,
									field : 'gtmModified',
									title : '最近修改时间',
									 formatter:function (value,row,index) {
                                        return format(value);
                                    }
								},
								{
									visible : false,
									field : 'content',
									title : '内容'
								},
								
								{
									visible : false,
									field : 'type',
									title : '类型'
								},
								{
									visible : false,
									field : 'tags',
									title : '标签'
								},
								{
									visible : false,
									field : 'categories',
									title : '分类'
								},
								{
									visible : false,
									field : 'hits',
									title : ''
								},
								{
									field : 'commentsNum',
									title : '评论数量',
									width :40
								},
								{
									field : 'status',
									title : '状态',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">草稿</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">发布</span>';
										}
									}
								},
								{
									field : 'allowComment',
									title : '开启评论',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">否</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">是</span>';
										}
									}
								},
								{
									visible : false,
									field : 'allowPing',
									title : '允许ping',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">否</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">是</span>';
										}
									}
								},
								{
									field : 'allowFeed',
									title : '允许订阅',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">否</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">是</span>';
										}
									}
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="javascript:void(0);" title="编辑" onclick="edit(\''
												+ row.cid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="javascript:void(0);" title="删除"  onclick="remove(\''
												+ row.cid
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="javascript:void(0);" title="预览" onclick="preview(\''
												+ row.cid
												+ '\')"><i class="fa fa-rocket"></i></a> ';
										return e + d +f;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	var addPage = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
	layer.full(addPage);
}
function edit(cid) {
	var editPage = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + cid // iframe的url
	});
	layer.full(editPage);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function preview(id) {
	window.open("${ctx}/blog/open/post/"+id);   
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['cid'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
function format(fmt) { 
	 var timestamp = fmt;
	 timestamp = new Date(timestamp);
	 var year = timestamp.getFullYear()+'-';
   	 var month = timestamp.getMonth()+1+'-';
     var date = timestamp.getDate()+'';
     var hours = timestamp.getHours()+':';
     var minutes = timestamp.getMinutes()+':';
     var seconds = timestamp.getSeconds()+'';
    return year+month+date+' '+hours+minutes+seconds; 
}
</script>
</body>
</html>
