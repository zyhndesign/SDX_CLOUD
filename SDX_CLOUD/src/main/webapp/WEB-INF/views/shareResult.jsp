<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>圣得西服饰搭配</title>
<base href="<%=request.getContextPath()%>/" />
<style type="text/css">
.shareTitle {
	width: 96%;
	display: block;
	margin: 10px auto;
	text-align:center;
	padding: 10px;
	font-size: 46px;
}

.shareContent {
	width: 90%;
	display: block;
	margin: 10px auto;
	padding: 10px;
	font-size: 36px;
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
	height: 700px;
	margin: 10px;
}

.modelContent {
	float: right;
	margin-right: 10px;
	font-size: 14px;
}

.content {
	width: 98%;
	height: 720px;
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


/*--------------SLIDER----------------*/

#slider {
	width: 90%;
	margin: 0 auto;
}


/*--------------CONTROLS--------------*/

/*position controls*/
.controls li {
	top: 50%;
	margin-top: -30px;
}

.controls li:nth-child(1) {
	left: 0;
}

.controls li:nth-child(2) {
	right: 0;
}


/*------------PAGINATION------------*/

/*style pagination*/
.pagination li {
	background-color: #ddd;
}

.pagination li.active {
	background-color: #000;
}


/*-------------HELPERS----------------*/

.responsive {
	width: 100%;
	height: auto;
}

.clearfix:after {
	content: "";
	display: table;
	clear: both;
}

img {
	display: block;
}

html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure, 
footer, header, hgroup, menu, nav, section {
	display: block;
}
body {
	line-height: 1;
}
ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
</style>
</head>
<body>
	<p class="shareTitle">${share.shareTitle}</p>
	<p class="shareContent">${share.shareContent}</p>
	
	<div id="slider">
			<ul class="slides">
				
			</ul>
			<ul class="controls">
				<li><img src="resources/images/app/prev.png" alt="previous"></li>
				<li><img src="resources/images/app/next.png" alt="next"></li>
			</ul>
			<ul class="pagination">
				<li class="active"></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		
	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/easySlider.js"></script>
	<script type="text/javascript">


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

			var html = '<li><div class="content">' +
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
						'</div></div></div></li>';
			$(".slides").append(html);
		}
		
		$("#slider").easySlider( {
				slideSpeed: 500,
				autoSlide: false,
				paginationSpacing: "15px",
				paginationDiameter: "10px",
				paginationPositionFromBottom: "0px",
				slidesClass: ".slides",
				controlsClass: ".controls",
				paginationClass: ".pagination"					
		});
	</script>
</body>

</html>