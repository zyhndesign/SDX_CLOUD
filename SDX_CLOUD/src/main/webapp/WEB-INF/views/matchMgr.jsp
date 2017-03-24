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
                <h1 class="panel-title">货品管理</h1>
            </div>
            <div class="panel-body" id="opt-body">
                <div class="row">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default">周数据</button>
                        <button type="button" class="btn btn-default">月数据</button>
                        <button type="button" class="btn btn-default">年数据</button>
                    </div>

                    <div class="input-group tableSearchContainer col-md-6">
                        <input class="form-control" id="searchNo" type="text" placeholder="姓名">
                            <span class="input-group-btn">
                                <button id="searchBtn" class="btn btn-primary searchBtn" type="button">搜索</button>
                            </span>
                    </div>
                </div>

                <table id="myTable" class="dataTable tableShowAsList">
                    <thead>
                    <tr>
                        <th>日期</th>
                        <th>图片</th>
                        <th>点赞数</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>
                        <tr>
                            <td>2017-09-08</td>
                            <td><img class="thumb" src="resources/images/app/short.png"></td>
                            <td><span class="glyphicon glyphicon-heart"></span>24</td>
                        </tr>

                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>

<%@ include file="loading.jsp"%>


<script src="js/lib/jquery-2.0.3.min.js"></script>
<script src="js/lib/jquery.dataTables.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/jquery.toastmessage.js"></script>
<script src="js/src/config.js"></script>
<script src="js/src/functions.js"></script>
<script src="js/src/ZYTableHandler.js"></script>
<script src="js/src/matchMgr.js"></script>
</body>
</html>