<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="container">


		<div class="left">
			<%@ include file="menu.jsp"%>
		</div>

		<div class="right">
			<div class="panel panel-default minHeight400">
				<div class="panel-heading">
					<h1 class="panel-title">品类设置</h1>
				</div>
				<div class="panel-body" id="opt-body">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="loading.jsp"%>

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/jquery.ztree.all.min.js"></script>
	<script src="resources/js/lib/bootstrap.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYTreeHandler.js"></script>
	<script src="resources/js/src/categoryMgr.js"></script>
</body>
</html>