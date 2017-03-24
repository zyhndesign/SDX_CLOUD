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
                <h1 class="panel-title">导购录入/修改</h1>
            </div>
            <div class="panel-body" id="opt-body">

                <c:choose>
                <c:when test="${empty guide}">
                <form class="form-horizontal" id="myForm" action="dggl/appUser/create" method="post">
                </c:when>
                <c:otherwise>
                <form class="form-horizontal" id="myForm" action="dggl/appUser/update" method="post">
                <input type="hidden" name="id" value="${guide.id}">
                </c:otherwise>
                </c:choose>

                    <div class="form-group">
                        <label  class="control-label col-md-2">姓名*</label>
                        <div class="col-md-6 input-group">
                            <input type="text" class="form-control" value="${guide.name}" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">店铺*</label>
                        <div class="col-md-6 input-group">
                            <select class="form-control" name="shop">
                                <c:forEach var="s" items="${shops}">
                                    <c:choose>
                                    <c:when test="${guide.shop==s.id}">
                                    <option value="${s.id}" selected="selected">${s.shopname}</option>
                                    </c:when>
                                    <c:otherwise>
                                    <option value="${s.id}">${s.shopname}</option>
                                    </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
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


<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>

<script src="js/lib/jquery-2.0.3.min.js"></script>
<script src="js/lib/jquery.form.js"></script>
<script src="js/lib/jquery.validate.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/jquery.toastmessage.js"></script>
<script src="js/src/config.js"></script>
<script src="js/src/functions.js"></script>
<script src="js/src/ZYFormHandler.js"></script>
<script src="js/src/guideCOU.js"></script>
</body>
</html>