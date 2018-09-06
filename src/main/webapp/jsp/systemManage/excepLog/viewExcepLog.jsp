<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<div class="alert alert-danger" id="alertId" style="display: none;">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong id="alertContent">键值不能为空</strong>
	</div>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label"> 异常描述： </label>
			<div class="col-sm-9">
				<input type="text" class="form-control"
					value="${excepLog.exceptionDesc}" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 异常信息： </label>
			<div class="col-sm-9">
				<textarea class="form-control" rows="3">${excepLog.exceptionInfo}</textarea>
			</div>
		</div>
	</form>
</body>
</html>
