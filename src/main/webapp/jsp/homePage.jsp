<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Free HTML5 Bootstrap Admin Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="ch-container">
		<div>
			<ul class="breadcrumb">
				<li><a href="#">首页</a></li>
			</ul>
		</div>
		<div class=" row">
			<div class="col-md-3 col-sm-3 col-xs-6">
				<a data-toggle="tooltip" title="6 new members."
					class="well top-block" href="#"> <i
					class="glyphicon glyphicon-user blue"></i>
					<div>系统注册人数</div>
					<div>1000</div> <span class="notification">6</span>
				</a>
			</div>

			<div class="col-md-3 col-sm-3 col-xs-6">
				<a data-toggle="tooltip" title="4 new pro members."
					class="well top-block" href="#"> <i
					class="glyphicon glyphicon-star green"></i>
					<div>每天在线人数</div>
					<div>100</div> <span class="notification green">4</span>
				</a>
			</div>

			<div class="col-md-3 col-sm-3 col-xs-6">
				<a data-toggle="tooltip" title="$34 new sales."
					class="well top-block" href="#"> <i
					class="glyphicon glyphicon-shopping-cart yellow"></i>
					<div>总销售额</div>
					<div>100000</div> <span class="notification yellow">$34</span>
				</a>
			</div>

			<div class="col-md-3 col-sm-3 col-xs-6">
				<a data-toggle="tooltip" title="12 new messages."
					class="well top-block" href="#"> <i
					class="glyphicon glyphicon-envelope red"></i>
					<div>待处理消息</div>
					<div>25</div> <span class="notification red">12</span>
				</a>
			</div>
		</div>

		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-info-sign"></i>框架介绍
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round btn-default">
								<i class="glyphicon glyphicon-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="box-content row">
						<div class="col-lg-7 col-md-12">
							<h1>
								框架说明<br> 
								<small>基于java架构的一个企业级项目快速开发平台</small>
							</h1>
							<p>
								1、本框架是由maven基于springMVC，mybatis，spring整合的框架<br>
								2、前台是使用bootstrap框架搭建的页面结构，交互采用的是jquery的插件处理<br>
								2、数据库mysql，缓存redis集群<br>
								3、消息通信rabbitmq，webService(cxf)，webSocket<br>
								4、任务调度quartz<br>
								5、邮件发送mail，短信发送sms<br>
								6、日志记录log4j<br>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
