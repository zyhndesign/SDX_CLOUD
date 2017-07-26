
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
<title></title>
<base href="<%=request.getContextPath()%>/" />
<link href="resources/detail/css/src/matchDetail.css" rel="stylesheet">
</head>
<body>
	<div class="detail">
		<h2 class="title">${match.seriesname}</h2>
		<p class="date">${match.createtime}</p>
		<p class="excerpt">亲爱的顾客您好！这次搭配的内容是${match.seriesname}，为您推送适合本次服务的搭配方案。祝您生活愉快！</p>

		<c:forEach items="${match.matchlists}" var="m">
			<img class="mainImage" src="${m.modelurl}">
			<div class="images">
				<img class="image" src="${m.innerClothUrl}"> <img class="image" src="${m.outClothUrl}"> <img class="image" src="${m.trousersUrl}">
			</div>
			<div class="zan"></div>
			<br>
		</c:forEach>

	</div>

	<script>
		var zans = document.getElementsByClassName("zan");
		for (var i = 0, len = zans.length; i < len; i++) {
			zans[i].onclick = (function(el) {
				return function() {
					el.className = "zan active";
				}
			})(zans[i]);
		}
	</script>
</body>
</html>