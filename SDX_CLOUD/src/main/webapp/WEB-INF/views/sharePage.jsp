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
	<input id="title" type="text" placeholder="设置标题" required="required" />
	<textarea id="shareContent" placeholder="添加导购推荐语（增加客户感兴趣程度，解释搭配详情内容）"
		rows="3" required="required"></textarea>
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
		<div class="swiper-wrapper">

			<c:forEach items="${match.matchlists}" var="match" varStatus="status">
				<div class="swiper-slide">
					<div id="imgContent">
						<img src="${match.modelurl}"
							style="max-height: 100%; max-width: 100%;" />
					</div>
					<div id="txtContent">
						<p class="txtContentTitle">时尚风</p>

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
							<a href="${match.innerClothShopUrl}" target="_blank"><img src="img/shopping@1.5x.png" class="shopping" /></a>

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
							 <a href="${match.innerClothShopUrl}" target="_blank"><img src="img/shopping@1.5x.png" class="shopping" /></a>

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
							<a href="${match.trouserShopUrl}" target="_blank"><img src="img/shopping@1.5x.png" class="shopping" /></a>

						</div>

						<p class="contentBottomLine" />

						<img class="like" src="resources/images/app/like@1.5x.png" />
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
						result = result + data.object + "|" + shareTitle + "|"
								+ shareContent;
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

			$("#keyWord1").bind("change", function() {
				style1 = $(this).val();
				$('#style1').empty();
				$('#style1').append("【" + style1 + "风格】");
				styles[0].styleValue = style1;

			});

			$("#keyWord2").bind("change", function() {
				style2 = $(this).val();
				$('#style2').empty();
				$('#style2').append("【" + style2 + "风格】");
				styles[1].styleValue = style2;
			});

			$("#keyWord3").bind("change", function() {
				style3 = $(this).val();
				$('#style3').empty();
				$('#style3').append("【" + style3 + "风格】");
				styles[2].styleValue = style3;
			});

			$("#keyWord4").bind("change", function() {
				style4 = $(this).val();
				$('#style4').empty();
				$('#style4').append("【" + style4 + "风格】");
				styles[3].styleValue = style4;
			});

			matchId = "${match.id}";
			userId = "${match.user.id}";

			<c:forEach items="${match.matchlists}" var="match" varStatus="status">
			<c:if test="${status.index == 0}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"styleValue" : style1,
				"modelurl" : "${match.modelurl}",
				"id" : "${match.id}"
			});
			</c:if>
			<c:if test="${status.index == 1}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"id" : "${match.id}",
				"modelurl" : "${match.modelurl}",
				"styleValue" : style2
			});
			</c:if>
			<c:if test="${status.index == 2}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
				"id" : "${match.id}",
				"modelurl" : "${match.modelurl}",
				"styleValue" : style3
			});
			</c:if>
			<c:if test="${status.index == 3}">
			styles.push({
				"outClothName" : "${match.outClothName}",
				"outClothShopUrl" : "${match.outClothShopUrl}",
				"innerClothName" : "${match.innerClothName}",
				"innerClothShopUrl" : "${match.innerClothShopUrl}",
				"trouserName" : "${match.trouserName}",
				"trouserShopUrl" : "${match.trouserShopUrl}",
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