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
    <link href="resources/detail/js/lib/ionic/css/ionic.css" rel="stylesheet">
    <link href="resources/detail/css/src/productDetail.css" rel="stylesheet">
    <!-- ionic/angularjs js -->
    <script src="resources/detail/js/lib/ionic/js/ionic.bundle.js"></script>
    <!-- your app's js -->
    <script src="resources/detail/js/src/pDetail.js"></script>
</head>

<body ng-controller="super">
    <ion-content>
        <ion-slide-box style="margin:1%;">
            <ion-slide>
                <div class="box">
                    <img style="width: 100%;height: auto" src="${hp.imageUrl1}">
                </div>
            </ion-slide>
            <ion-slide>
                <div class="box">
                    <img style="width: 100%;height: auto" src="${hp.imageUrl2}">
                </div>
            </ion-slide>
            <ion-slide>
                <div class="box">
                    <img style="width: 100%;height: auto" src="${hp.imageUrl3}">
                </div>
            </ion-slide>
        </ion-slide-box>

        <ion-list class="ownList">
            <ion-item>
                <label class="ownLabel">商品名称</label>
                <div class="ownContent">
                    <b>${hp.hpName}</b>
                </div>
            </ion-item>
            <ion-item>
                <label class="ownLabel">颜色</label>
                <div class="ownContent ownColors">
                    <span class="ownColor"></span>
                </div>
            </ion-item>
            <!--<ion-item>
                <label class="ownLabel">已购用户</label>
                <br>
                <div class="ownContent ownCustomers">
                    <span class="ownCustomer">韩志</span>
                    <span class="ownCustomer">孙桥</span>
                    <span class="ownCustomer">李旺</span>
                </div>
            </ion-item>-->
        </ion-list>
    </ion-content>
</body>
</html>