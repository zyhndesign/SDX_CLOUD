    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>
        <%@ include file="head.jsp"%>
        </head>
        <body>

        <%@ include file="header.jsp"%>

        <div class="container">

        <div class="left">
        <%@ include file="menu.jsp"%>
        </div>


    <div class="right">
            <div class="panel panel-default">

                <div class="panel-heading">
                    <h1 class="panel-title">店铺录入/修改</h1>
                </div>
                <div class="panel-body" id="opt-body">
                    <c:choose>
                    <c:when test="${empty shop}">
                    <form class="form-horizontal" id="myForm" action="dggl/shop/create" method="post">
                    </c:when>
                    <c:otherwise>
                    <form class="form-horizontal" id="myForm" action="dggl/shop/update" method="post">
                    <input type="hidden" name="id" value="${shop.id}">
                    </c:otherwise>
                    </c:choose>

                        <div class="form-group">
                            <label  class="control-label col-md-2">名称*</label>
                            <div class="col-md-6 input-group">
                                <input type="text" id="name" class="form-control" value="${shop.shopname}" name="shopname">
                            </div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <button type="submit" class="col-md-offset-2 btn btn-primary saveBtn">保存</button>
                        </div>
                    </form>

                </div>
            </div>
    </div>

</div>


<%@ include file="loading.jsp"%>

<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/jquery.form.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/bootstrap.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/ZYFormHandler.js"></script>
<script src="resources/js/src/shopCOU.js"></script>
</body>
</html>