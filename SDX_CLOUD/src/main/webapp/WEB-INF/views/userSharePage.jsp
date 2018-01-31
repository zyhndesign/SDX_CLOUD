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
<link rel="stylesheet" href="resources/css/src/userShareResult.css" />
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

	<div class="bottomBar">
		<img src="resources/images/app/likeNormal@1.5x.png" class="likeBtn"/>
		<p class="likeText">快来点赞我吧！</p>
		<div class="orderBtn">一键下单</div>
	</div>
	
	<!-- <input id="uploadData" type="button" value="click"/> -->

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/swiper.min.js"></script>
	<script type="text/javascript">
		var styleJsonString = '${share.style}';

		var like1 = 0;
		var like2 = 0;
		var like3 = 0;
		var like4 = 0;
		
		var matchId = '${share.match.id}';
		
		var userId = '${share.user.id}';
		var vipId = Math.ceil(Math.random()*10000000);;
		
		var styleObj = JSON.parse(styleJsonString);

		for (var i = 0; i < styleObj.length; i++) {

			var html = '<div class="swiper-slide">' + '<div id="imgContent">'
					+ '<img src="'
					+ styleObj[i].modelurl
					+ '" style="display:block;width:100%;" /> '
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
					+ '<a href="'+styleObj[i].innerClothShopUrl+'" target="_blank"><img src="resources/images/app/shopping@1.5x.png" class="shopping" /></a>'
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
					+ '<a href="'+styleObj[i].outClothShopUrl+'" target="_blank"><img src="resources/images/app/shopping@1.5x.png" class="shopping" /></a>'
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
					+ '<a href="'+styleObj[i].trouserShopUrl+'" target="_blank"><img src="resources/images/app/shopping@1.5x.png" class="shopping" /></a>'
					+ '</div>'
					+ '</div>' + '</div>';
			$(".swiper-wrapper").append(html);
		}

		var activeIndex = 0;
		
		var swiper1 = new Swiper('.swiper-container', {
			roundLengths : true,
			initialSlide : 0,
			speed : 600,
			slidesPerView : "auto",
			centeredSlides : true,
			followFinger : false,
			on : {

				slideChangeTransitionEnd : function() {
					activeIndex = this.activeIndex;
					if (styleObj.length == 4) {
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
						
						if (like4 == 1){
							$(".likeBtn").attr('src','resources/images/app/likeNormal@1.5x.png');
						}
						else{
							$(".likeBtn").attr('src','resources/images/app/like@1.5x.png');
						}
					} else if (styleObj.length == 3) {
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
						if (like3 == 1){
							$(".likeBtn").attr('src','resources/images/app/likeNormal@1.5x.png');
						}
						else{
							$(".likeBtn").attr('src','resources/images/app/like@1.5x.png');
						}
					} else if (styleObj.length == 2) {
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
						if (like2 == 1){
							$(".likeBtn").attr('src','resources/images/app/likeNormal@1.5x.png');
						}
						else{
							$(".likeBtn").attr('src','resources/images/app/like@1.5x.png');
						}
					}
					else if (styleObj.length == 1){
						if (like1 == 1){
							$(".likeBtn").attr('src','resources/images/app/likeNormal@1.5x.png');
						}
						else{
							$(".likeBtn").attr('src','resources/images/app/like@1.5x.png');
						}
					}

				},
			}
		});

		function addLike(userId,matchId,matchlistId,vipId){
			$.ajax({
				url : 'dggl/feedback/createFeedback',
				type : 'POST',
				async : false,
				data : {
					userId : userId,
					matchId : matchId,
					matchlistId : matchlistId,
					vipId : vipId
				},
				timeout : 5000,
				dataType : 'json',
				success : function(data, textStatus, jqXHR) {

					if (data.resultCode == 200 || data.resultCode == 300){
						
						$(".likeBtn").attr('src','resources/images/app/like@1.5x.png');
						if (activeIndex == 0){
							like1 = 1;
						}
						else if (activeIndex == 1){
							like1 = 2;
						}
						else if (activeIndex == 2){
							like1 = 3;
						}
						else if (activeIndex == 3){
							like1 = 4;
						}
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误')

				},
				complete : function() {
					console.log('结束')
				}
			})
		}
		
		$(document).ready(function() {
			$(".scrollBar1_p1").addClass("pYellow");
			
			if (styleObj.length == 4){
				$("#scrollBar2").hide();
				$("#scrollBar3").hide();
			}
			else if (styleObj.length == 3){
				$("#scrollBar1").hide();
				$("#scrollBar3").hide();
			}
			else if (styleObj.length == 2){
				$("#scrollBar1").hide();
				$("#scrollBar2").hide();
			}
			
			$(".orderBtn").click(function(){
				location.href = styleObj[activeIndex].outClothShopUrl;
			});
			
			$(".likeBtn").click(function(){
				if (activeIndex == 0){
					if (like1 == 0){
						addLike(userId,matchId,styleObj[0].id,vipId);
					}
				}
				else if (activeIndex == 1){
					if (like1 == 1){
						addLike(userId,matchId,styleObj[1].id,vipId);
					}
				}
				else if (activeIndex == 2){
					if (like1 == 2){
						addLike(userId,matchId,styleObj[2].id,vipId);
					}
				}
				else if (activeIndex == 3){
					if (like1 == 3){
						addLike(userId,matchId,styleObj[3].id,vipId);
					}	
				}
			});
		});
	</script>
</body>

</html>