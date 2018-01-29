<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>圣得西服饰搭配</title>
<base href="<%=request.getContextPath()%>/" />
<link rel="stylesheet" href="resources/css/src/swiper.min.css" />
<link rel="stylesheet" href="resources/css/src/shareResult.css" />
</head>
<body>
	<div class="title">${share.shareTitle}</div>
	<div class="describe">${share.shareContent}</div>

	<div id="scrollBar1">
		<p class="progressP scrollBar1_p1"></p>
		<div class="scrollBar1_line scrollBar1_line1"></div>
		<p class="progressP scrollBar1_p2"></p>
		<div class="scrollBar1_line scrollBar1_line2"></div>
		<p class="progressP scrollBar1_p3"></p>
		<div class="scrollBar1_line scrollBar1_line3"></div>
		<p class="progressP scrollBar1_p4"></p>
	</div>

	<div id="scrollBar2">
		<p class="progressP scrollBar2_p1"></p>
		<div class="scrollBar2_line scrollBar2_line1"></div>
		<p class="progressP scrollBar2_p2"></p>
		<div class="scrollBar2_line scrollBar2_line2"></div>
		<p class="progressP scrollBar2_p3"></p>
	</div>

	<div id="scrollBar3">
		<p class="progressP scrollBar3_p1"></p>
		<div class="scrollBar3_line scrollBar3_line1"></div>
		<p class="progressP scrollBar3_p2"></p>
	</div>

	<div class="swiper-container">
		<div class="swiper-wrapper"></div>
	</div>

	<!-- <input id="uploadData" type="button" value="click"/> -->

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/swiper.min.js"></script>
	<script type="text/javascript">
		var styleJsonString = '${share.style}';

		var styleObj = JSON.parse(styleJsonString);

		for (var i = 0; i < styleObj.length; i++) {

			var html = '<div class="swiper-slide">' + '<div id="imgContent">'
					+ '<img src="'
					+ styleObj[i].modelurl
					+ '" style="max-height: 100%; max-width: 100%;" /> '
					+ '</div>'
					+ '<div id="txtContent">'
					+ '<p class="txtContentTitle">时尚风</p>'
					+ '<div class="titleDiv">'
					+ '<p class="contentLine" />'
					+ '<p class="fzTitle">内搭</p>'
					+ '<p class="contentLine" />'
					+ '</div>'
					+

					'<div style="position: relative;">'
					+ '<img src="'+styleObj[i].innerClothUrl+'" class="clothImg" />'
					+ '<div class="clothText">'
					+ '<p class="clothTitleText">'
					+ styleObj[i].innerClothName
					+ '</p>'
					+ '<p class="clothNumText">'
					+ styleObj[i].innerClothNum
					+ '</p>'
					+ '</div>'
					+ '<a href="'+styleObj[i].innerClothShopUrl+'" target="_blank"><img src="img/shopping@1.5x.png" class="shopping" /></a>'
					+

					'</div>'
					+

					'<p class="contentBottomLine" />'
					+

					'<div class="titleDiv">'
					+ '<p class="contentLine" />'
					+ '<p class="fzTitle">外套</p>'
					+ '<p class="contentLine" />'
					+ '</div>'
					+

					'<div style="position: relative;">'
					+ '<img src="'+styleObj[i].outClothUrl+'" class="clothImg" />'
					+ '<div class="clothText">'
					+ '<p class="clothTitleText">'
					+ styleObj[i].outClothName
					+ '</p>'
					+ '<p class="clothNumText">'
					+ styleObj[i].outClothNum
					+ '</p>'
					+ '</div>'
					+ '<a href="'+styleObj[i].outClothShopUrl+'" target="_blank"><img src="img/shopping@1.5x.png" class="shopping" /></a>'
					+

					'</div>'
					+

					'<p class="contentBottomLine" />'
					+

					'<div class="titleDiv">'
					+ '<p class="contentLine" />'
					+ '<p class="fzTitle">裤装</p>'
					+ '<p class="contentLine" />'
					+ '</div>'
					+

					'<div style="position: relative;">'
					+ '<img src="'+styleObj[i].trousersUrl+'" class="trouserImg" />'
					+ '<div class="clothText">'
					+ '<p class="clothTitleText">'
					+ styleObj[i].trouserName
					+ '</p>'
					+ '<p class="clothNumText">'
					+ styleObj[i].trouserClothNum
					+ '</p>'
					+ '</div>'
					+ '<a href="'+styleObj[i].trouserShopUrl+'" target="_blank"><img src="img/shopping@1.5x.png" class="shopping" /></a>'
					+ '</div>'
					+ '<p class="contentBottomLine" />'
					+ '<img class="like" src="resources/images/app/like@1.5x.png" />'
					+ '</div>' + '</div>';
			$(".swiper-wrapper").append(html);
		}

		var swiper1 = new Swiper('.swiper-container', {
			roundLengths : true,
			initialSlide : 0,
			speed : 600,
			slidesPerView : "auto",
			centeredSlides : true,
			followFinger : false,
			on : {

				slideChangeTransitionEnd : function() {

					if (styleObj.length == 4) {
						$("#scrollBar2").hide();
						$("#scrollBar3").hide();
						if (this.activeIndex == 0) {
							$(".scrollBar1_p1").addClass("pYellow");
							$(".scrollBar1_p2").addClass("pNormal");
							$(".scrollBar1_p1").removeClass("pNormal");
							$(".scrollBar1_p2").removeClass("pYellow");
							$(".scrollBar1_line1").removeClass("lineYellow");
						} else if (this.activeIndex == 1) {
							$(".scrollBar1_p2").removeClass("pNormal");
							$(".scrollBar1_p2").addClass("pYellow");
							$(".scrollBar1_p3").removeClass("pYellow");
							$(".scrollBar1_line1").addClass("lineYellow");
							$(".scrollBar1_line2").removeClass("lineYellow");
						} else if (this.activeIndex == 2) {
							$(".scrollBar1_p3").addClass("pYellow");
							$(".scrollBar1_p4").removeClass("pYellow");
							$(".scrollBar1_line2").addClass("lineYellow");
							$(".scrollBar1_line3").removeClass("lineYellow");
						} else if (this.activeIndex == 3) {
							$(".scrollBar1_p4").addClass("pYellow");
							$(".scrollBar1_line3").addClass("lineYellow");
						}
					} else if (styleObj.length == 3) {
						$("#scrollBar1").hide();
						$("#scrollBar3").hide();
						if (this.activeIndex == 0) {
							$(".scrollBar2_p1").addClass("pYellow");
							$(".scrollBar2_p2").addClass("pNormal");
							$(".scrollBar2_p1").removeClass("pNormal");
							$(".scrollBar2_p2").removeClass("pYellow");
							$(".scrollBar2_line1").removeClass("lineYellow");
						} else if (this.activeIndex == 1) {
							$(".scrollBar2_p2").removeClass("pNormal");
							$(".scrollBar2_p2").addClass("pYellow");
							$(".scrollBar2_p3").removeClass("pYellow");
							$(".scrollBar2_line1").addClass("lineYellow");
							$(".scrollBar2_line2").removeClass("lineYellow");
						} else if (this.activeIndex == 2) {
							$(".scrollBar2_p3").addClass("pYellow");
							$(".scrollBar2_line2").addClass("lineYellow");
						}
					} else if (styleObj.length == 2) {
						$("#scrollBar1").hide();
						$("#scrollBar2").hide();
						if (this.activeIndex == 0) {
							$(".scrollBar3_p1").addClass("pYellow");
							$(".scrollBar3_p2").addClass("pNormal");
							$(".scrollBar3_p1").removeClass("pNormal");
							$(".scrollBar3_p2").removeClass("pYellow");
							$(".scrollBar3_line1").removeClass("lineYellow");
						} else if (this.activeIndex == 1) {
							$(".scrollBar3_p2").removeClass("pNormal");
							$(".scrollBar3_p2").addClass("pYellow");
							$(".scrollBar3_line1").addClass("lineYellow");
						}
					}

				},
			}
		});

		$(document).ready(function() {
			$(".scrollBar1_p1").addClass("pYellow");
			
			if (styleObj.length == 4){
				$("scrollBar2").hide();
				$("scrollBar3").hide();
			}
			else if (styleObj.length == 3){
				$("scrollBar1").hide();
				$("scrollBar3").hide();
			}
			else if (styleObj.length == 2){
				$("scrollBar1").hide();
				$("scrollBar2").hide();
			}
		});
	</script>
</body>

</html>