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
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<div id="deptTree"></div>
				</div>
			</div>
		</div>
	</div>
	<div>
	  <jsp:include page="/common/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
	var prefix = "<%=basePath%>";
	$(document).ready(function() {
		getTreeData()
	});
	function getTreeData() {
		$.ajax({
			type : "GET",
			url : prefix+"dept/tree",
			success : function(tree) {
				loadTree(tree);
			}
		});
	}
	function loadTree(tree) {
		$('#deptTree').jstree({
			'core' : {
				'data' : tree
			},
			"plugins" : [ "search" ]
		});
		$('#deptTree').jstree().open_all();
	}
	$('#deptTree').on("changed.jstree", function(e, data) {
		if (data.node.id != -1) {
			parent.loadDept(data.node.id, data.node.text);
			var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
			parent.layer.close(index);
		}
	});
</script>
</body>
</html>
