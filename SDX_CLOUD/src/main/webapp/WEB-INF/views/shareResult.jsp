<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分享</title>
<base href="<%=request.getContextPath()%>/" />
<style type="text/css">
#title {
	width: 96%;
	display: block;
	margin: 10px auto;
	padding: 10px;
}

#shareContent {
	width: 96%;
	display: block;
	margin: 10px auto;
	padding: 10px;
}

.keyWord {
	width: 98%;
	display: block;
	margin: 0px auto;
	padding: 10px;
	appearance: none;
	-moz-appearance: none;
	-webkit-appearance: none;
	background: url("resources/images/app/arrow.png") no-repeat scroll right
		center transparent;
}

.modelImg {
	float: left;
	width: auto;
	height: 240px;
	margin: 10px;
}

.modelContent {
	float: right;
	margin-right: 10px;
	font-size: 14px;
}

.content {
	width: 98%;
	height: 260px;
	margin: 10px auto;
	background-color: #F2F2F2;
	font-size: 16px;
}

.clothIcon {
	width: 25px;
	height: 25px;
	margin: 5px;
}

.shop {
	position: relative;
	width: 120px;
	padding: 10px 10px 10px 30px;
	border: 1px solid;
	border-color: #E2E1E0;
	left: 0px;
}

.list {
	width: 200px;
	height: 37px;
	margin: 20px;
	position: relative;
}

.shopIcon {
	width: 33px;
	height: 35px;
	position: relative;
	top: 13px;
	left: -5px;
	background-color: #FED817;
}

.modelContent_title {
	width: 200px;
	text-align: center;
	background-color: #F2F2F2;
	font-size: 20px;
	color: #696768;
	margin-top: 20px;
}

.casualClothIcon {
	position: absolute;
	left: 3px;
	top: 15px;
	z-index: 5;
	background-image: url(resources/images/app/casual-t-shirt-@1.5x.png);
	background-repeat: no-repeat;
	background-position: 0px 0px;
	background-size: cover;
	width: 20px;
	height: 20px;
}

.weddingClothIcon {
	position: absolute;
	left: 3px;
	top: 15px;
	z-index: 5;
	background-image: url(resources/images/app/wedding-suit@1.5x.png);
	background-repeat: no-repeat;
	background-position: 0px 0px;
	background-size: cover;
	width: 20px;
	height: 20px;
}

.pantsClothIcon {
	position: absolute;
	left: 3px;
	top: 15px;
	z-index: 5;
	background-image: url(resources/images/app/pants@1.5x.png);
	background-repeat: no-repeat;
	background-position: 0px 0px;
	background-size: cover;
	width: 20px;
	height: 20px;
}
</style>
</head>
<body>
	<input id="title" type="text" placeholder="设置标题" readonly="true" value="${share.shareTitle}"/>
	<textarea id="shareContent" placeholder="添加导购推荐语（增加客户感兴趣程度，解释搭配详情内容）" rows="3" readonly="true"></textarea>

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script type="text/javascript">
		$("#shareContent").val("${share.shareContent}");
		var styleJsonString = '${share.style}';
		
		var styleObj = JSON.parse(styleJsonString);
		
		for (var i = 0; i < styleObj.length; i++){
			styleObj[i].outClothName
			styleObj[i].outClothShopUrl
			styleObj[i].innerClothName
			styleObj[i].innerClothShopUrl
			styleObj[i].trouserName
			styleObj[i].trouserShopUrl
			styleObj[i].styleValue

			var html = '<div class="content">' +
				'<img class="modelImg" src="'+styleObj[i].modelurl+'" />' +
					'<div class="modelContent">' +
						'<div id="style4" class="modelContent_title">【'+styleObj[i].styleValue+'风格】</div>'+
						'<div class="list">'+
							'<i class="casualClothIcon"></i> <input type="text" class="shop" value="'+styleObj[i].outClothName+'" readonly="true" /><a href="'+styleObj[i].innerClothShopUrl+'" target="_blank"> <img class="shopIcon" src="resources/images/app/yellow@1.5x.png"></a>'+ 
						'</div>' +
						'<div class="list">'+
							'<i class="weddingClothIcon"></i> <input type="text" class="shop" value="'+styleObj[i].innerClothName+'" readonly="true"><a href="'+styleObj[i].outClothShopUrl+'" target="_blank"> <img class="shopIcon" src="resources/images/app/yellow@1.5x.png"></a>'+ 
						'</div>' +
						'<div class="list">' +
							'<i class="pantsClothIcon"></i> <input type="text" class="shop" value="'+styleObj[i].trouserName+'" readonly="true" /><a href="'+styleObj[i].trouserShopUrl+'" target="_blank"><img class="shopIcon" src="resources/images/app/yellow@1.5x.png"></a> '+
						'</div></div></div>';
			$(document.body).append(html);
		}
		
	</script>
</body>

</html>