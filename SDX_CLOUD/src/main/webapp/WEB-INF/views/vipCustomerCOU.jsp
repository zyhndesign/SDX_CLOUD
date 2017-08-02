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
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">VIP客户管理 | 录入 修改</h1>
				</div>
				<div class="panel-body" id="opt-body">
					<c:choose>
						<c:when test="${empty vipuser}">
							<form class="form-horizontal" id="myForm" action="/dggl/appVipUser/create" method="post">
						</c:when>
						<c:otherwise>
							<form class="form-horizontal" id="myForm" action="/dggl/appVipUser/update" method="post">
								<input type="hidden" name="id" value="${vipuser.id}">
						</c:otherwise>
					</c:choose>
					<hr>
					<div class="form-inline">
						<div class="form-group col-md-6">
							<label for="shopname" class="control-label col-md-4">渠道简称*</label> <input type="text" id="shopname" class="form-control col-md-2" value="${vipuser.shopname}"
								name="shopname">
						</div>

						<div class="form-group col-md-6">
							<label for="cardnumber" class="control-label col-md-4">VIP编号*</label> <input type="text" id="cardnumber" class="form-control col-md-2" value="${vipuser.cardnumber}"
								name="cardnumber">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group col-md-6">
							<label for="vipname" class="control-label col-md-4">vip名称*</label> <input type="text" id="vipname" class="form-control col-md-2" value="${vipuser.vipname}"
								name="vipname">
						</div>

						<div class="form-group col-md-6">
							<label for="phonenumber" class="control-label col-md-4">手机*</label> <input type="text" id="phonenumber" class="form-control col-md-2" value="${vipuser.phonenumber}"
								name="phonenumber">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group col-md-6">
							<label for="birthday" class="control-label col-md-4">出生日期*</label> <input type="text" id="birthday" class="form-control col-md-2" value="${vipuser.birthday}"
								name="birthday">
						</div>

						<div class="form-group col-md-6">
							<label for="mgr_username" class="control-label col-md-4">维护人*</label> 
							<select id="mgr_username" class="form-control col-md-2">
								<option></option>
							</select>
							
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group col-md-6">
							<label for="gender" class="control-label col-md-4">性别录入</label>
							<input type="radio" value="${vipuser.gender}" name="gender">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="${vipuser.gender}" name="gender">女
						</div>
						<div class="form-group col-md-6">
							<label for="consumesum" class="control-label col-md-4">累计消费金额*</label> <input type="text" id="consumesum" class="form-control col-md-2" value="${vipuser.consumesum}"
								name="consumesum">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group col-md-6">
							<label for="integral" class="control-label col-md-4">积分*</label> <input type="text" id="integral" class="form-control col-md-2" value="${vipuser.integral}"
								name="integral">
						</div>

						<div class="form-group col-md-6">
							<label for="consumenumber" class="control-label col-md-4">累计消费次数*</label> <input type="text" id="consumenumber" class="form-control col-md-2"
								value="${vipuser.consumenumber}" name="consumenumber">
						</div>
					</div>
					<hr>
					<div class="form-group">
						<button type="submit" class="col-md-offset-2 btn btn-primary saveBtn">保存</button>
					</div>
					</form>


				</div>
			</div>
		</div>

	</div>


	<%@ include file="loading.jsp"%>

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/jquery.dataTables.min.js"></script>
	<script src="resources/js/lib/bootstrap.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYTableHandler.js"></script>
	<script src="resources/js/src/vipCustomerCOU.js"></script>
</body>
</html>