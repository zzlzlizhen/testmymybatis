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
	<input type="hidden" id="id" value="${user.id}" />
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label"> 用户名：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="userName1"
					value="${user.name}" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 角色：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<c:forEach items="${allRoles}" var="role">
					<label class="checkbox-inline"> <input type="checkbox"
						<c:if test="${role.updateTime=='1'}">checked=checked</c:if>
						name="checkRoles" value="${role.id}" disabled="disabled">${role.roleName}
					</label>
				</c:forEach>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 手机号： </label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="phone1" maxlength="11"
					value="${user.phone}" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 邮箱： </label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="email1"
					value="${user.email}" disabled="disabled">
			</div>
		</div>
	</form>
</body>
</html>
