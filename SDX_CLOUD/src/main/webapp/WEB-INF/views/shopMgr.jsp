<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="webkit" name="renderer">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>服装货品数据管理系统</title>
    <link href="css/lib/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/lib/zTreeStyle.css" rel="stylesheet" type="text/css">
    <link href="css/lib/jquery.toastmessage.css" rel="stylesheet" type="text/css">
    <link href="css/src/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header">
    <h1 class="logo">

        <span class="icon"></span>服装货品数据管理系统

    </h1>
    <nav class="topNav">
        <ul class="userMenu">
            <!--<li class="item"><a class="link">管理员</a></li>
            <li class="item"><a href="#" class="link">修改密码</a></li>-->
            <li class="item"><a href="#" class="logout">退出</a></li>
        </ul>
    </nav>

</div>

<div class="container">

    <div class="left">
        <ul class="menu">
            <!--<li class="item">
                <span class="glyphicon glyphicon-th-list"></span>
                <a class="link" href="hpManage/productMgr">货品管理</a>
            </li>-->
            <li class="item">
                <span class="glyphicon glyphicon-th-large sdxIcon"></span>
                <a class="link">功能</a>
                <!--<span class="glyphicon glyphicon-arrow-down rightIcon"></span>-->
                <ul class="subMenu">
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="hpgl/hpManage/productMgr">货品管理</a>
                    </li>
                </ul>
            </li>
            <li class="item">
                <span class="glyphicon glyphicon-cog sdxIcon"></span>
                <a class="link">基础设置</a>
                <!--<span class="glyphicon glyphicon-chevron-down sdxIcon rightIcon"></span>-->
                <ul class="subMenu">
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="hpgl/brand/brandMgr">品牌系列设置</a>
                    </li>
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="hpgl/category/categoryMgr">品类设置</a>
                    </li>
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="hpgl/size/sizeMgr">尺码设置</a>
                    </li>
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="hpgl/color/colorMgr">颜色设置</a>
                    </li>
                </ul>
            </li>

            <li class="item">
                <span class="glyphicon glyphicon-book sdxIcon"></span>
                <a class="link">管理</a>
                <!--<span class="glyphicon glyphicon-chevron-down sdxIcon rightIcon"></span>-->
                <ul class="subMenu">
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="dggl/brand/brandMgr">导购管理</a>
                    </li>
                    <li class="sItem">
                        <!--<span class="circle">原点</span>-->
                        <a class="sLink" href="hpgl/category/categoryMgr">搭配管理</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>


    <div class="right">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">店铺管理</h1>
                </div>
                <div class="panel-body" id="opt-body">
                    <div class="row">
                        <a class="btn btn-primary newBtn" href="dggl/appUser/">
                            <span class="glyphicon glyphicon-plus"></span> 新建
                        </a>

                        <div class="input-group tableSearchContainer col-md-6">
                            <input class="form-control" id="searchNo" type="text" placeholder="名称">
                                <span class="input-group-btn">
                                    <button id="searchBtn" class="btn btn-primary searchBtn" type="button">搜索</button>
                                </span>
                        </div>
                    </div>

                    <table id="myTable" class="dataTable mT0">
                        <thead>
                        <tr>
                            <th>名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>长沙五一路店</td>
                            <td><a href="#">编辑</a>&nbsp;&nbsp;<a class="delete" href="1">删除</a></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
    </div>

</div>


<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>

<script src="js/lib/jquery-2.0.3.min.js"></script>
<script src="js/lib/jquery.dataTables.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/jquery.toastmessage.js"></script>
<script src="js/src/config.js"></script>
<script src="js/src/functions.js"></script>
<script src="js/src/ZYTableHandler.js"></script>
<script src="js/src/guideMgr.js"></script>
</body>
</html>