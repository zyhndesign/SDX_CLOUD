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
    <div class="main">
        <div class="panel panel-default minHeight400">
            <div class="panel-heading">
                <h1 class="panel-title">货品管理</h1>
            </div>
            <div class="panel-body" id="opt-body">
                
                <!--<a class="btn btn-success" href="productCOU.html">
                    <span class="glyphicon glyphicon-save"></span> 导入
                </a>-->
                <div class="row">
                        <a class="btn btn-primary newBtn" href="hpManage/productCOU">
                            <span class="glyphicon glyphicon-plus"></span> 新建
                        </a>
                        <div class="input-group tableSearchContainer col-md-6">
                            <input class="form-control" id="searchNo" type="text" placeholder="货号">
                            <span class="input-group-btn">
                                <button id="searchBtn" class="btn btn-primary searchBtn" type="button">搜索</button>
                            </span>
                        </div>
                        <!--<div class="col-md-2">
                            <select class="form-control" id="searchStatus">
                                <option value="">全部</option>
                            </select>
                        </div>-->
                    </div>
                <div id="searchPanel" class="searchPanel">
                   
                    
                    <div class="row">
                        <label class="control-label col-md-1">品牌</label>
                        <div class="col-md-11">
                            <c:forEach var="b" items="${brand}">
                               <span class="item" data-type="brand" data-id="${b.id}">${b.name}</span>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row">
                        <label class="control-label col-md-1">品类</label>
                        <div class="col-md-11">
                           <c:forEach var="c" items="${category}">
                              <span class="item" data-type="category" data-id="${c.id}">${c.name}</span>
                           </c:forEach>
                        </div>
                    </div>

                    <div class="row">
                        <label class="control-label col-md-1">颜色</label>
                        <div class="col-md-11">
                            <c:forEach var="cl" items="${color}">
                              <span class="item" data-type="color" data-id="${cl.id}">${cl.name}</span>
                           </c:forEach>
                        </div>
                    </div>
                    <div class="row">
                        <label class="control-label col-md-1">尺码</label>
                        <div class="col-md-11">
                            <c:forEach var="s" items="${size}">
                              <span class="item" data-type="size" data-id="${s.id}">${s.name}</span>
                           </c:forEach>
                        </div>
                    </div>
                    <div class="collapseBtnCtrl" id="searchPanelCtrl" data-target="down">
                        <span class="glyphicon glyphicon-chevron-up"></span><span class="text">收起选项</span>
                    </div>
                </div>

                <table id="myTable" class="dataTable">
                    <thead>
                    <tr>
                        <th>图片</th>
                        <th>货号</th>
                        <th>品牌系列</th>
                        <th>品类</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</div>

<!--弹出界面-->
<div class="modal fade" id="showDetailModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title mustSetValue" data-name="hp_num"></h4>
            </div>
            <div class="modal-body">
                <p><label class="labelW100">图片:</label>&nbsp;<img class="mustSetSrc" width="100px"><p>
                <p><label class="labelW100">货号:</label>&nbsp;<span class="mustSetValue" data-name="hp_num"><span><p>
                <p><label class="labelW100">品牌系列:</label>&nbsp;<span class="mustSetValue" data-name="brandList"><span><p>
                <p><label class="labelW100">品类:</label>&nbsp;<span class="mustSetValue" data-name="categoryList"><span><p>
                <p><label class="labelW100">建档日期:</label>&nbsp;<span class="mustSetValue" data-name="createTime"><span><p>
                <p><label class="labelW100">年份/季节:</label>&nbsp;<span class="mustSetValue" data-name="timeCategory"><span><p>
                <p><label class="labelW100">颜色:</label>&nbsp;<span class="mustSetValue" data-name="colorList"><span><p>
                <p><label class="labelW100">尺寸:</label>&nbsp;<span class="mustSetValue" data-name="sizeList"><span><p>
                <p><label class="labelW100">链接:</label>&nbsp;<span class="mustSetValue" data-name="link"><span><p>
                <p><label class="labelW100">状态:</label>&nbsp;<span class="mustSetValue" data-name="status"><span><p>
                <p><label class="labelW100">价格:</label>&nbsp;<span class="mustSetValue" data-name="price"><span><p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<%@ include file="loading.jsp"%>

<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/js/lib/bootstrap.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/productMgr.js"></script>
</body>
</html>