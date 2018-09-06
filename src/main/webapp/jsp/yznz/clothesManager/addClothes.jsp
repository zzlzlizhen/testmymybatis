<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	function saveSubmit(){
		if($("#paramKey").val()==null||(!$("#paramKey").val().length>0)){
			$("#alertId").show();
			$("#alertContent").html("键不能为空");
		}else if($("#paramValue").val()==null||(!$("#paramValue").val().length>0)){
			$("#alertId").show();
			$("#alertContent").html("值不能为空");
		}
		else{
			$.ajax({
		        url: '${appctx}/clothesManagerController/add',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify({paramKey:$("#paramKey").val(),paramValue:$("#paramValue").val()}),
		        success: function(data , textStatus){
		          $("#alertId").show();
		          if(data.result=="success"){
		        	  $("#alertContent").html("保存成功！");
		        	  setTimeout("$('#myModal').modal('hide')",1000); 
		        	  $("#saveBtn").attr("onclick","");
		        	  table.fnDraw();
		          }else if(data.result=="paramKeyIsExist"){
		        	  $("#alertContent").html("键已经存在，请重新换一个！");
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
	<div class="alert alert-danger" id="alertId" style="display: none;">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong id="alertContent">键值不能为空</strong>
	</div>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服属性： </label>
			<div class="col-sm-9">
				<select id="paramKey" class="col-sm-9 form-control">
					<option value="YZNZ_CLOUTHES_SIZE">尺寸</option>
					<option value="YZNZ_CLOUTHES_STYLE">风格</option>
					<option value="YZNZ_CLOUTHES_COLOUR">颜色</option>
					<option value="YZNZ_CLOUTHES_CATEGORY">类型</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"> 值： </label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="3" id="paramValue"></textarea>
			</div>
		</div>
	</form>
</body>
</html>
