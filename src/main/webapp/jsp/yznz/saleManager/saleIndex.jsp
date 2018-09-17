<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/14 0014
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/common/common.jsp"%>

<html>
<head>
    <!--确保bootstrap开发的网站对移动设备友好，确保适当的绘制和触屏缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
</head>
<body>
    <div class="ch-container">
        <div>
            <ul class="breadcrumb">
                <li><a href="#">销售流水</a></li>
                <li><a href="#"> 销售流水</a></li>
            </ul>
        </div>
        <div class="row">
            <div class="box col-lg-12">
                <div class="box-inner">
                    <div class="box-header well" data-original-title="">
                        <h2>
                            <i class="glyphicon glyphicon-user"></i>售卖管理
                        </h2>
                    </div>
                    <div class="box-content">
                        <div class="btn-toolbar">
                            <div class="pull-right">
                                <div class="input-append">
                                    <select id="searchId">
                                        <option value="">全部</option>
                                        <option value="YZNZ_CLOUTHES_SIZE">尺寸</option>
                                        <option value="YZNZ_CLOUTHES_STYLE">风格</option>
                                        <option value="YZNZ_CLOUTHES_COLOUR">颜色</option>
                                        <option value="YZNZ_CLOUTHES_CATEGORY">类型</option>
                                    </select> <a class="btn btn-primary" href="#" onclick="searchFun()">
                                    <i class="glyphicon glyphicon-search"></i>查询
                                </a>
                                </div>
                            </div>
                            <a class="btn btn-primary" href="#" onclick="addFun()"> <i
                                    class="glyphicon glyphicon-plus"></i>添加
                            </a> <a class="btn btn-danger" href="#" onclick="batchDelFun()"
                                    data-toggle="modal" data-target="#myModal"> <i
                                class="glyphicon glyphicon-trash "></i>批量删除
                        </a>
                        </div>
                        <div style="height: 15px"></div>
                        <div class="row-fluid">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <div>

    </div>
</body>
</html>
