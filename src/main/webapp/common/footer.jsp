<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
   //设置无缓存
   response.setHeader("progma","no-cache");   
   response.setHeader("Cache-Control","no-cache");   
%> 
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<script src="${ctx}/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx}/bootstrap/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/jsTree/jstree.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/jqTreeGrid/jquery.treegrid.min.js"></script>
<script src="${ctx}/bootstrap/js/plugins/jqTreeGrid/jquery.treegrid.extension.js"></script>
<script src="${ctx}/bootstrap/js/plugins/jqTreeGrid/jquery.treegrid.bootstrap3.js"></script>
<script src="${ctx}/bootstrap/js/plugins/chosen/chosen.jquery.js"></script>
<script src="${ctx}/bootstrap/js/plugins/layer/layer.min.js"></script>
<script src="${ctx}/js/content.js?v=1.0.0"></script>
<!--summernote-->
<script src="${ctx}/bootstrap/js/plugins/summernote/summernote.js"></script>
<script src="${ctx}/bootstrap/js/plugins/summernote/summernote-zh-CN.min.js"></script>