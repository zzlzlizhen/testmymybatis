<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="appctx" value="${pageContext.request.contextPath}"></c:set>
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
	var phoneRegex = /^[1][0-9]{10}$/;
	var emailRegex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/;
	function saveSubmit(){
		if($("#userName1").val()==null||(!$("#userName1").val().length>0)){
			$("#alertId").show();
			$("#alertContent").html("用户名字不能为空");
		}else if($("input:checkbox[name='checkRoles']:checked").length==0){
			$("#alertId").show();
			$("#alertContent").html("请选择角色");
		}
		else if($("#phone1").val().length>0&&!phoneRegex.test($("#phone1").val())){
			$("#alertId").show();
			$("#alertContent").html("请输入合法的手机号，如：15899999999");
		}else if($("#email1").val().length>0&&!emailRegex.test($("#email1").val())){
			$("#alertId").show();
			$("#alertContent").html("请输入合法邮箱，如：test@test.com");
		}
		else{
			var jsonData  = {};
			var userRoleIds = "";
			$.each($("input:checkbox[name='checkRoles']:checked"),function(){
				userRoleIds = userRoleIds + $(this).val() + ",";
			});
			userRoleIds = userRoleIds.substring(0,userRoleIds.length-1);
			jsonData.id=$("#id").val();
			jsonData.name=$("#userName1").val();
			jsonData.phone=$("#phone1").val();
			jsonData.email=$("#email1").val();
			jsonData.pwd=userRoleIds;
			$.ajax({
		        url: '${appctx}/userController/edit',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify(jsonData),
		        success: function(data , textStatus){
		          $("#alertId").show();
		          if(data.result=="success"){
		        	  $("#alertContent").html("修改成功！");
		        	  setTimeout("$('#myModal').modal('hide')",1000); 
		        	  $("#saveBtn").attr("onclick","");
		        	  table.fnDraw();
		          }else if(data.result=="nameIsExist"){
		        	  $("#alertContent").html("用户名已经存在，请重新输入！");
		          }else if(data.result=="phoneIsExist"){
		        	  $("#alertContent").html("该手机号已经存在，请重新输入！");
		          }
		          else if(data.result=="error"){
		        	  $("#alertContent").html("修改失败，请联系管理员");
		          }
		        },
		        error: function(jqXHR , textStatus , errorThrown){
		        	$("#alertId").show();
		        	$("#alertContent").html("系统异常，请联系管理员");
		        }
		      });
		}
	}
</script>
</head>
<body>
	<div class="alert alert-danger" id="alertId" style="display:none;">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong id="alertContent"></strong>
	</div>
	<input type="hidden" id="id" value="${user.id}"/>
	<form class="form-horizontal" role="form">
	   <div class="form-group">
	      <label class="col-sm-3 control-label">
	         用户名：<font color="red">*</font>
	      </label>
	      <div class="col-sm-9">
	         <input type="text" class="form-control" id="userName1" value="${user.name}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label class="col-sm-3 control-label">
	                        角色：<font color="red">*</font>
	      </label>
	      <div class="col-sm-9">
	      	<c:forEach items="${allRoles}" var="role">
      			<label class="checkbox-inline">
				  <input type="checkbox" <c:if test="${role.updateTime=='1'}">checked=checked</c:if> name="checkRoles" value="${role.id}">${role.roleName}
				</label>
	      	</c:forEach>
	      </div>
	   </div>
	   <div class="form-group">
	      <label class="col-sm-3 control-label">
	         手机号：
	      </label>
	      <div class="col-sm-9">
	         <input type="text" class="form-control" id="phone1" maxlength="11" value="${user.phone}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label class="col-sm-3 control-label">
	         邮箱：
	      </label>
	      <div class="col-sm-9">
	         <input type="text" class="form-control" id="email1" value="${user.email}">
	      </div>
	   </div>
	</form>
</body>
</html>
