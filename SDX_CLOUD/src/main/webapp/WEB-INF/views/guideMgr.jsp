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
                    <h1 class="panel-title">导购管理</h1>
                </div>
                <div class="panel-body" id="opt-body">
                    <div class="row">
                        <a class="btn btn-primary newBtn" href="dggl/appUser/guideCOU">
                            <span class="glyphicon glyphicon-plus"></span> 新建
                        </a>

                        <div class="input-group tableSearchContainer col-md-6">
                            <input class="form-control" id="searchName" type="text" placeholder="姓名">
                                <span class="input-group-btn">
                                    <button id="searchBtn" class="btn btn-primary searchBtn" type="button">搜索</button>
                                </span>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 pL0">
                            <table id="myTable" class="dataTable mT0 ">
                                <thead>
                                    <tr>
                                        <th>图片</th>
                                        <th>姓名</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!--<tr>
                                        <td><img class="thumb" src="images/app/defaultThumb.png"></td>
                                        <td>小李<p>长沙五一路店</p></td>
                                        <td><a href="#">业绩</a>&nbsp;&nbsp;<a href="#">编辑</a>&nbsp;&nbsp;
                                            <a class="delete" href="1">删除</a>
                                        </td>
                                    </tr>-->
                                </tbody>
                            </table>
                        </div>

                        <div class="col-md-4 pR0">
                            <ul id="allShop" class="list-group">
                                <li class="list-group-item list-group-title">全部门店</li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
    </div>

</div>


<%@ include file="loading.jsp"%>

<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/js/lib/bootstrap.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/ZYTableHandler.js"></script>
<script src="resources/js/src/guideMgr.js"></script>
</body>
</html>