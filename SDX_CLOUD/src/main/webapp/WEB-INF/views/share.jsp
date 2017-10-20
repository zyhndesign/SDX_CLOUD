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
	margin-right: 0px;
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
	width: 110px;
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
	height:35px;
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
	background-position: 0px 5px;
	background-size: cover;
	width: 25px;
	height: 25px;
}

.weddingClothIcon {
	position: absolute;
	left: 3px;
	top: 15px;
	z-index: 5;
	background-image: url(resources/images/app/wedding-suit@1.5x.png);
	background-repeat: no-repeat;
	background-position: 0px 5px;
	background-size: cover;
	width: 25px;
	height: 25px;
}

.pantsClothIcon {
	position: absolute;
	left: 3px;
	top: 15px;
	z-index: 5;
	background-image: url(resources/images/app/pants@1.5x.png);
	background-repeat: no-repeat;
	background-position: 0px 5px;
	background-size: cover;
	width: 25px;
	height: 25px;
}
</style>
</head>
<body>
	<input id="title" type="text" placeholder="设置标题" required="required" />
	<textarea id="shareContent" placeholder="添加导购推荐语（增加客户感兴趣程度，解释搭配详情内容）" rows="3" required="required"></textarea>

	<c:forEach items="${match.matchlists}" var="match" varStatus="status">
	<c:if test="${status.index == 0}">
		<select id="keyWord1" class="keyWord">
			<option value="" disabled selected>搭配一：穿搭风格关键字</option>
			<option value="休闲">休闲风格</option>
			<option value="商务">商务风格</option>
			<option value="时尚">时尚风格</option>
		</select>
		<div id="content1" class="content">
			<img class="modelImg" src="${match.modelurl}" />
			<div id="modelContent" class="modelContent">
				<div id="style1" class="modelContent_title"></div>
	
				<div class="list">
					<i class="casualClothIcon"></i> <input  type="text" class="shop" value="${match.outClothName}" readonly="true" /> <a href="${match.innerClothShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="weddingClothIcon"></i> <input type="text" class="shop" value="${match.innerClothName}" readonly="true"> <a href="${match.outClothShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="pantsClothIcon"></i> <input type="text" class="shop" value="${match.trouserName}" readonly="true" /> <a href="${match.trouserShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${status.index == 1}">
		<select id="keyWord2" class="keyWord">
			<option value="" disabled selected>搭配二：穿搭风格关键字</option>
			<option value="休闲">休闲风格</option>
			<option value="商务">商务风格</option>
			<option value="时尚">时尚风格</option>
		</select>
		<div id="content2" class="content">
			<img class="modelImg" src="${match.modelurl}" />
			<div  class="modelContent">
				<div id="style2" class="modelContent_title"></div>
	
				<div class="list">
					<i class="casualClothIcon"></i> <input type="text" class="shop" value="${match.outClothName}" readonly="true" /> <a href="${match.innerClothShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="weddingClothIcon"></i> <input type="text" class="shop" value="${match.innerClothName}" readonly="true"> <a href="${match.outClothShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="pantsClothIcon"></i> <input type="text" class="shop" value="${match.trouserName}" readonly="true" /><a href="${match.trouserShopUrl}" target="_blank"> <img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
			</div>
		</div>
	</c:if>
	
	<c:if test="${status.index == 2}">
		<select id="keyWord3" class="keyWord">
			<option value="" disabled selected>搭配三：穿搭风格关键字</option>
			<option value="休闲">休闲风格</option>
			<option value="商务">商务风格</option>
			<option value="时尚">时尚风格</option>
		</select>
		<div id="content3" class="content">
			<img class="modelImg" src="${match.modelurl}" />
			<div class="modelContent">
				<div id="style3" class="modelContent_title"></div>
	
				<div class="list">
					<i class="casualClothIcon"></i> <input type="text" class="shop" value="${match.outClothName}" readonly="true" /> <a href="${match.innerClothShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="weddingClothIcon"></i> <input type="text" class="shop" value="${match.innerClothName}" readonly="true"> <a href="${match.outClothShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="pantsClothIcon"></i> <input type="text" class="shop" value="${match.trouserName}" readonly="true" /><a href="${match.trouserShopUrl}" target="_blank"> <img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
			</div>
		</div>
	</c:if>
	
	<c:if test="${status.index == 3}">
		<select id="keyWord4" class="keyWord">
			<option value="" disabled selected>搭配四：穿搭风格关键字</option>
			<option value="休闲">休闲风格</option>
			<option value="商务">商务风格</option>
			<option value="时尚">时尚风格</option>
		</select>
		<div id="content4" class="content">
			<img class="modelImg" src="${match.modelurl}" />
			<div class="modelContent">
				<div id="style4" class="modelContent_title"></div>
	
				<div class="list">
					<i class="casualClothIcon"></i> <input type="text" class="shop" value="${match.outClothName}" readonly="true" /><a href="${match.innerClothShopUrl}" target="_blank"> <img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="weddingClothIcon"></i> <input type="text" class="shop" value="${match.innerClothName}" readonly="true"><a href="${match.outClothShopUrl}" target="_blank"> <img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
	
				<div class="list">
					<i class="pantsClothIcon"></i> <input type="text" class="shop" value="${match.trouserName}" readonly="true" /><a href="${match.trouserShopUrl}" target="_blank"><img class="shopIcon"
						src="resources/images/app/yellow@1.5x.png"></a> 
				</div>
			</div>
		</div>
	</c:if>
	
	</c:forEach>
	<!-- <input id="uploadData" type="button" value="click"/> -->

	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script type="text/javascript">
	
	var style1 = "";
	var style2 = "";
	var style3 = "";
	var style4 = "";
	var jsonStyles = "";
	var matchId = 0;
	var userId = 0;
	var styles = [];
	
	function uploadShareContent(){

		var result = "";
		var shareContent = $("#shareContent").val();
		var shareTitle = $("#title").val();
		
		if (shareTitle == ""){
			 $("#title").attr('placeholder',"请输入分享标题！");
			 $("#title").addClass("invalid");
			 return;
		}
		
		
		if (shareContent == ""){
			 $("#shareContent").attr('placeholder',"请输入分享内容！");
			 $("#shareContent").addClass("invalid");
			 return;
		}
		
        jsonStyles = JSON.stringify(styles); 
        
		$.ajax({
		    url:'dggl/share/createShare',
		    type:'POST',
		    async:false,
		    data:{
		    	userId:userId,
		    	matchId:matchId,
		    	shareContent:shareContent,
		    	sharedlist:"",
		    	shareTitle:shareTitle,
		    	style:jsonStyles
		    },
		    timeout:5000,
		    dataType:'json',
		    beforeSend:function(xhr){
		       
		    },
		    success:function(data,textStatus,jqXHR){
		    	
		    	if (data.object != null){
		    		result = result + data.object+ "|" + shareTitle + "|" + shareContent;
		    	}
		    	
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		       
		    },
		    complete:function(){
		        console.log('结束')
		    }
		})
		
		return result;
	}
	
	$(document).ready(function(){

		$("#keyWord1").bind("change",function () {
			style1 = $(this).val();
			$('#style1').empty();
            $('#style1').append("【"+style1+"风格】");
            styles[0].styleValue = style1;
           
        }); 
        
        $("#keyWord2").bind("change",function () { 
        	style2 = $(this).val();
        	$('#style2').empty();
            $('#style2').append("【"+style2+"风格】");  
            styles[1].styleValue = style2;
        });
        
        $("#keyWord3").bind("change",function () { 
        	style3 = $(this).val();
        	$('#style3').empty();
            $('#style3').append("【"+style3+"风格】");
            styles[2].styleValue = style3;
        });
        
        $("#keyWord4").bind("change",function () { 
        	style4 = $(this).val();
        	$('#style4').empty();
            $('#style4').append("【"+style4+"风格】"); 
            styles[3].styleValue = style4;
        });
		
        matchId = "${match.id}";
        userId = "${match.user.id}";
        
          
        <c:forEach items="${match.matchlists}" var="match" varStatus="status">
        	<c:if test="${status.index == 0}">
        		styles.push(
        			{
        				"outClothName":"${match.outClothName}",
        				"outClothShopUrl":"${match.outClothShopUrl}",
        				"innerClothName":"${match.innerClothName}",
        				"innerClothShopUrl":"${match.innerClothShopUrl}",
        				"trouserName":"${match.trouserName}",
        				"trouserShopUrl":"${match.trouserShopUrl}",
        				"styleValue":style1,
        				"modelurl":"${match.modelurl}",
        				"id":"${match.id}"
        			}
        		);  
        	</c:if>
        	<c:if test="${status.index == 1}">
	    		styles.push(
	    			{
	    				"outClothName":"${match.outClothName}",
	    				"outClothShopUrl":"${match.outClothShopUrl}",
	    				"innerClothName":"${match.innerClothName}",
	    				"innerClothShopUrl":"${match.innerClothShopUrl}",
	    				"trouserName":"${match.trouserName}",
	    				"trouserShopUrl":"${match.trouserShopUrl}",
	    				"id":"${match.id}",
	    				"modelurl":"${match.modelurl}",
	    				"styleValue":style2
	    			}
	    		);  
	    	</c:if>
	    	<c:if test="${status.index == 2}">
				styles.push(
					{
						"outClothName":"${match.outClothName}",
						"outClothShopUrl":"${match.outClothShopUrl}",
						"innerClothName":"${match.innerClothName}",
						"innerClothShopUrl":"${match.innerClothShopUrl}",
						"trouserName":"${match.trouserName}",
						"trouserShopUrl":"${match.trouserShopUrl}",
						"id":"${match.id}",
						"modelurl":"${match.modelurl}",
						"styleValue":style3
					}
				);  
			</c:if>
			<c:if test="${status.index == 3}">
				styles.push(
					{
						"outClothName":"${match.outClothName}",
						"outClothShopUrl":"${match.outClothShopUrl}",
						"innerClothName":"${match.innerClothName}",
						"innerClothShopUrl":"${match.innerClothShopUrl}",
						"trouserName":"${match.trouserName}",
						"trouserShopUrl":"${match.trouserShopUrl}",
						"id":"${match.id}",
						"modelurl":"${match.modelurl}",
						"styleValue":style4
					}
				);  
			</c:if>
        </c:forEach>
         
        /*
        $("#uploadData").click(function(){
        	console.log(userId);
        	console.log(matchId);
        	console.log(jsonStyles);
        	var result = uploadShareContent();
        });*/
	});
	</script>
</body>

</html>