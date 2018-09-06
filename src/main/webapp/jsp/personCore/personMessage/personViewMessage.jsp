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
	$(function() {
	});
</script>
</head>
<body>
	<div class="ch-container">
		<div>
			<ul class="breadcrumb">
				<li><a href="${appctx}/indexController/homePage">首页</a></li>
				<li><a href="${appctx}/messageController/personMessageIndex">消息列表</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>信息内容</h2>
					</div>
					<div class="box-content">
						<div class="btn-toolbar">
							<h1>${message.messageHead}</h1>
						</div>
						<div style="height: 15px;"></div>

						<div class="row-fluid">
							<h3>${message.messageContent}</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
