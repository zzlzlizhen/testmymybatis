<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>修改个人信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<style type="text/css">
	.checkbox-inline{
		margin-left:10px;
		}
</style>
<script type="text/javascript">
	var phoneRegex = /^[1][0-9]{10}$/;
	var emailRegex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/;
	function updateInfo(){
		if($("#phone1").val().length>0&&!phoneRegex.test($("#phone1").val())){
			$("#alertId").show();
			$("#alertContent").html("请输入合法的手机号，如：15899999999");
		}else if($("#email1").val().length>0&&!emailRegex.test($("#email1").val())){
			$("#alertId").show();
			$("#alertContent").html("请输入合法邮箱，如：test@test.com");
		}
		else{
			var jsonData  = {};
			jsonData.phone=$("#phone1").val();
			jsonData.email=$("#email1").val();
			$.ajax({
		        url: '${appctx}/personInfoController/updatePersonInfo',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify(jsonData),
		        success: function(data , textStatus){
		          $("#alertId").show();
		          if(data.result=="success"){
		        	  $("#alertContent").html("保存成功！");
		          }else if(data.result=="phoneIsExist"){
		        	  $("#alertContent").html("该手机号已经存在，请重新输入！");
		          }else if(data.result=="error"){
		        	  $("#alertContent").html("保存失败，请联系管理员");
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
                        <span class="input-group-addon"><i class="glyphicon red">手机号：</i></span>
                        <input type="text" class="form-control" value="${loginUser.phone}" id="phone1" placeholder="请输入手机号">
                    </div>
                    <div class="clearfix"></div><br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon red">&nbsp;邮箱：</i></span>
                        <input type="text" class="form-control" value="${loginUser.email}" id="email1" placeholder="请输入邮箱">
                    </div>
                    <div class="clearfix"></div><br>
                    <p class="center col-md-5">
                    	<button type="button" class="btn btn-primary" onclick="updateInfo()">修改信息</button>
                    </p>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
