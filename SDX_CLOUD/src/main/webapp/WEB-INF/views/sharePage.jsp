<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no">
<title>圣得西服饰搭配</title>
<base href="<%=request.getContextPath()%>/" />
<link rel="stylesheet" href="resources/css/src/swiper.min.css" />
<link rel="stylesheet" href="resources/css/src/shareResult.css" />
</head>
<body>
	<input id="title" type="text" placeholder="设置标题" required="required" />
	<textarea id="shareContent" placeholder="添加导购推荐语（增加客户感兴趣程度，解释搭配详情内容）" rows="3" required="required"></textarea>
	<div class="keyWord">
		<select id="keyWordSelect">
			<option value="" disabled selected>搭配一：穿搭风格关键字</option>
			<option value="休闲">休闲风格</option>
			<option value="商务">商务风格</option>
			<option value="时尚">时尚风格</option>
		</select>
	</div>

	<div id="scrollBar1">
		<p class="progressP scrollBar1_p1"></p>
		<div class="scrollBar1_line scrollBar1_line1"></div>
		<p class="progressP scrollBar1_p2"></p>
		<div class="scrollBar1_line scrollBar1_line2"></div>
		<p class="progressP scrollBar1_p3"></p>
		<div class="scrollBar1_line scrollBar1_line3"></div>
		<p class="progressP scrollBar1_p4"></p>
	</div>

	<div id="scrollBar2" style="display:none;">
		<p class="progressP scrollBar2_p1"></p>
		<div class="scrollBar2_line scrollBar2_line1"></div>
		<p class="progressP scrollBar2_p2"></p>
		<div class="scrollBar2_line scrollBar2_line2"></div>
		<p class="progressP scrollBar2_p3"></p>
	</div>

	<div id="scrollBar3" style="display:none;">
		<p class="progressP scrollBar3_p1"></p>
		<div class="scrollBar3_line scrollBar3_line1"></div>
		<p class="progressP scrollBar3_p2"></p>
	</div>

	<div class="swiper-container">
		<div class="swiper-wrapper">

			<c:forEach items="${match.matchlists}" var="match" varStatus="status">
				
				<div class="swiper-slide">
					<div id="imgContent">
						<img src="${match.modelurl}"
							style="display:block;width:100%;" />
					</div>
					<div id="txtContent">
						<p id="txtContentTitle${status.count}" class="txtContentTitle">时尚风</p>

						<div class="titleDiv">
							<p class="contentLine" />
							<p class="fzTitle">内搭</p>
							<p class="contentLine" />
						</div>

						<div style="position: relative;">
							<img src="${match.innerClothUrl}" class="clothImg" />
							<div class="clothText">
								<p class="clothTitleText">${match.innerClothName}</p>
								<p class="clothNumText">${match.innerClothNum}</p>
							</div>
							<a href="${match.innerClothShopUrl}" target="_blank"><img src="resources/images/app/shopping@1.5x.png" class="shopping" /></a>

						</div>

						<p class="contentBottomLine" />

						<div class="titleDiv">
							<p class="contentLine" />
							<p class="fzTitle">外套</p>
							<p class="contentLine" />
						</div>

						<div style="position: relative;">
							<img src="${match.outClothUrl}" class="clothImg" />
							<div class="clothText">
								<p class="clothTitleText">${match.outClothName}</p>
								<p class="clothNumText">${match.outClothNum}</p>
							</div>
							 <a href="${match.innerClothShopUrl}" target="_blank"><img src="resources/images/app/shopping@1.5x.png" class="shopping" /></a>

						</div>

						<p class="contentBottomLine" />

						<div class="titleDiv">
							<p class="contentLine" />
							<p class="fzTitle">裤装</p>
							<p class="contentLine" />
						</div>

						<div style="position: relative;">
							<img src="${match.trousersUrl}" class="trouserImg" />
							<div class="clothText">
								<p class="clothTitleText">${match.trouserName}</p>
								<p class="clothNumText">${match.trouserClothNum}</p>
							</div>
							<a href="${match.trouserShopUrl}" target="_blank"><img src="resources/images/app/shopping@1.5x.png" class="shopping" /></a>

						</div>

						<p class="contentBottomLine" />

					</div>
				</div>

			</c:forEach>

		</div>
	</div>

	
	<!-- <input id="uploadData" type="button" value="click"/> -->

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/swiper.min.js"></script>
	<script type="text/javascript">
		var style1 = "";
		var style2 = "";
		var style3 = "";
		var style4 = "";
		var jsonStyles = "";
		var matchId = 0;
		var userId = 0;
		var styles = [];
		var activeIndex = 0;
		
		function uploadShareContent() {

			var result = "";
			var shareContent = $("#shareContent").val();
			var shareTitle = $("#title").val();

			if (shareTitle == "") {
				$("#title").attr('placeholder', "请输入分享标题！");
				$("#title").addClass("invalid");
				return;
			}

			if (shareContent == "") {
				$("#shareContent").attr('placeholder', "请输入分享内容！");
				$("#shareContent").addClass("invalid");
				return;
			}

			jsonStyles = JSON.stringify(styles);

			$.ajax({
				url : 'dggl/share/createShare',
				type : 'POST',
				async : false,
				data : {
					userId : userId,
					matchId : matchId,
					shareContent : shareContent,
					sharedlist : "",
					shareTitle : shareTitle,
					style : jsonStyles
				},
				timeout : 5000,
				dataType : 'json',
				beforeSend : function(xhr) {

				},
				success : function(data, textStatus, jqXHR) {

					if (data.object != null) {
						result = result + data.object + "|" + shareTitle + "|" + shareContent;
					}

				},
				error : function(xhr, textStatus) {
					console.log('错误')

				},
				complete : function() {
					console.log('结束')
				}
			})

			return result;
		}

		
		
		$(document).ready(function() {

			var matchListsLength = '${match.matchlists.size()}';
			$(".scrollBar1_p1").addClass("pYellow");
			
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
						if (matchListsLength == 4) {
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
						} else if (matchListsLength == 3) {
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
						} else if (matchListsLength == 2) {
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
						
			if (matchListsLength == 4){
				$("#scrollBar2").hide();
				$("#scrollBar3").hide();
			}
			else if (matchListsLength == 3){
				$("#scrollBar1").hide();
				$("#scrollBar3").hide();
			}
			else if (matchListsLength == 2){
				$("#scrollBar1").hide();
				$("#scrollBar2").hide();
			}
			else{
				$("#scrollBar1").hide();
				$("#scrollBar2").hide();
				$("#scrollBar3").hide();
			}
			
			$("#keyWordSelect").bind("change", function() {
				console.log(activeIndex);
				
				if (activeIndex == 0){
					style1 = $(this).val();
					$('#txtContentTitle1').empty();
					$('#txtContentTitle1').append("" + style1 + "风");
				}
				else if (activeIndex == 1){
					style2 = $(this).val();
					$('#txtContentTitle2').empty();
					$('#txtContentTitle2').append("" + style2 + "风");
				}
				else if (activeIndex == 2){
					style3 = $(this).val();
					$('#txtContentTitle3').empty();
					$('#txtContentTitle3').append("" + style3 + "风");
				}
				else if (activeIndex == 3){
					style4 = $(this).val();
					$('#txtContentTitle4').empty();
					$('#txtContentTitle4').append("" + style4 + "风");
					console.log("style4:" + style4);
				}

			});

			matchId = "${match.id}";
			userId = "${match.user.id}";

			<c:forEach items="${match.matchlists}" var="match" varStatus="status">
			<c:if test="${status.index == 0}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"outClothNum" : "${match.outClothNum}",
				"outClothUrl":"${match.outClothUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"innerClothNum" : "${match.innerClothNum}",
				"innerClothUrl":"${match.innerClothUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"trouserClothNum":"${match.trouserClothNum}",
				"trousersUrl":"${match.trousersUrl}",
				"styleValue" : style1,
				"modelurl" : "${match.modelurl}",
				"id" : "${match.id}"
			});
			</c:if>
			<c:if test="${status.index == 1}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"outClothNum" : "${match.outClothNum}",
				"outClothUrl":"${match.outClothUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"innerClothNum" : "${match.innerClothNum}",
				"innerClothUrl":"${match.innerClothUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"trouserClothNum":"${match.trouserClothNum}",
				"trousersUrl":"${match.trousersUrl}",
				"id" : "${match.id}",
				"modelurl" : "${match.modelurl}",
				"styleValue" : style2
			});
			</c:if>
			<c:if test="${status.index == 2}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"outClothNum" : "${match.outClothNum}",
				"outClothUrl":"${match.outClothUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"innerClothNum" : "${match.innerClothNum}",
				"innerClothUrl":"${match.innerClothUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"trouserClothNum":"${match.trouserClothNum}",
				"trousersUrl":"${match.trousersUrl}",
				"id" : "${match.id}",
				"modelurl" : "${match.modelurl}",
				"styleValue" : style3
			});
			</c:if>
			<c:if test="${status.index == 3}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"outClothNum" : "${match.outClothNum}",
				"outClothUrl":"${match.outClothUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"innerClothNum" : "${match.innerClothNum}",
				"innerClothUrl":"${match.innerClothUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"trouserClothNum":"${match.trouserClothNum}",
				"trousersUrl":"${match.trousersUrl}",
				"id" : "${match.id}",
				"modelurl" : "${match.modelurl}",
				"styleValue" : style4
			});
			</c:if>
			</c:forEach>

			
		});
	</script>
</body>

</html>