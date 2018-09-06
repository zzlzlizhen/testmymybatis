<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>修改密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<style type="text/css">
.checkbox-inline {
	margin-left: 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		
	});
	function updatePassword(){
		if($("#oldPassword").val()==null||(!$("#oldPassword").val().length>0)){
   			$("#alertId").show();
   			$("#alertContent").html("请输入旧密码");
   		}else if($("#newPassword").val()==null||(!$("#newPassword").val().length>0)){
   			$("#alertId").show();
   			$("#alertContent").html("请输入新密码");
   		}else if($("#newPassword1").val()==null||(!$("#newPassword1").val().length>0)){
   			$("#alertId").show();
   			$("#alertContent").html("请再次输入新密码");
   		}else if($("#newPassword").val()!=$("#newPassword1").val()){
   			$("#alertId").show();
   			$("#alertContent").html("二次输入的密码不一致，请重新输入");
   		}else{
   			$.ajax({
		        url: '${appctx}/personInfoController/updatePersonPassword',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify({pwd:$("#newPassword").val(),phone:$("#oldPassword").val()}),
		        success: function(data , textStatus){
		          if(data.result=="success"){
		        	  $("#alertId").show();
		        	  $("#alertContent").html("修改密码成功!");
		          }else if(data.result=="oldPwsError"){
		        	  $("#alertId").show();
		        	  $("#alertContent").html("输入的旧密码不对，请重新输入!");
		          }else if(data.result=="pswIsExist"){
		        	  $("#alertId").show();
		        	  $("#alertContent").html("新设置的密码不能与原密码一致，请修改!");
		          }else if(data.result=="error"){
		        	  $("#alertId").show();
		        	  $("#alertContent").html("重置密码失败，请联系管理员！");
		          }
		        },
		        error: function(jqXHR , textStatus , errorThrown){
		        	$("#alertId").show();
		        	$("#alertContent").html("系统异常，请联系管理员！");
		        }
		      });
   		}
	}
</script>
</head>
<body>
	<div class="ch-container">
		<div class="row">
			<div class="well col-md-5 center login-box">
				<div class="alert alert-danger" id="alertId" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong id="alertContent"></strong>
				</div>
				<form class="form-horizontal">
					<fieldset>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock red"></i></span> <input type="password"
								class="form-control" id="oldPassword" placeholder="请输入旧密码">
						</div>
						<div class="clearfix"></div>
						<br>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock red"></i></span> <input type="password"
								class="form-control" id="newPassword" placeholder="请输入新密码">
						</div>
						<div class="clearfix"></div>
						<br>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock red"></i></span> <input type="password"
								class="form-control" id="newPassword1" placeholder="请再次输入新密码">
						</div>
						<div class="clearfix"></div>
						<p class="center col-md-5">
							<button type="button" class="btn btn-primary"
								onclick="updatePassword()">修改密码</button>
						</p>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
