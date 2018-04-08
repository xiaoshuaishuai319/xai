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
<link rel="shortcut icon" href="${ctx}/image/favicon.ico">
<link href="${ctx}/bootstrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx}/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx}/bootstrap/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/plugins/jsTree/style.min.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/plugins/jqTreeGrid/jquery.treegrid.css" rel="stylesheet">
<!--summernote css -->
<link href="${ctx}/bootstrap/css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/animate.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
