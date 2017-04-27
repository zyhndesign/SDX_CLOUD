    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
<html ng-app="app">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title></title>
    <base href="<%=request.getContextPath() %>/" />
    <link href="resources/detail/css/src/matchDetail.css" rel="stylesheet">
</head>
<body>
    <div class="detail">
        <h2 class="title">商务男装造型</h2>
        <p class="date">2016-11-11</p>
        <p class="excerpt">亲爱的顾客您好！这次搭配的内容是冬季商品系列新品，为您推送适合本次服务的搭配方案。祝您生活愉快！</p>
        <img class="mainImage" src="data/nDetail/1/people.png">
        <div class="images">
            <img class="image" src="data/nDetail/1/1.png">
            <img class="image" src="data/nDetail/1/2.png">
            <img class="image" src="data/nDetail/1/3.png">
        </div>
        <div class="zan"></div>
        <br>
        <img class="mainImage" src="data/nDetail/2/people.png">
        <div class="images">
            <img class="image" src="data/nDetail/2/1.png">
            <img class="image" src="data/nDetail/2/2.png">
            <img class="image" src="data/nDetail/2/3.png">
        </div>
        <div class="zan"></div>
        <br>
        <img class="mainImage" src="data/nDetail/3/people.png">
        <div class="images">
            <img class="image" src="data/nDetail/3/1.png">
            <img class="image" src="data/nDetail/3/2.png">
            <img class="image" src="data/nDetail/3/3.png">
        </div>
        <div class="zan"></div>
        <br>
        <img class="mainImage" src="data/nDetail/4/people.png">
        <div class="images">
            <img class="image" src="data/nDetail/4/1.png">
            <img class="image" src="data/nDetail/4/2.png">
            <img class="image" src="data/nDetail/4/3.png">
        </div>
        <div class="zan"></div>
    </div>

    <script>
        var zans=document.getElementsByClassName("zan");
        for(var i= 0,len=zans.length;i<len;i++){
            zans[i].onclick=(function(el){
                return function(){
                    el.className="zan active";
                }
            })(zans[i]);
        }
    </script>
</body>
</html>