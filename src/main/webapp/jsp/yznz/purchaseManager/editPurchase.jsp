<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		var orginValue = $("#paramKey").val();
		$("#paramKey").val(translationAttr(orginValue));
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
		        url: '${appctx}/clothesManagerController/edit',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify({id:$("#id").val(),paramKey:'${yznzCloth.name}',paramValue:$("#paramValue").val()}),
		        success: function(data , textStatus){
		          $("#alertId").show();
		          if(data.result=="success"){
		        	  $("#alertContent").html("修改成功！");
		        	  setTimeout("$('#myModal').modal('hide')",1000); 
		        	  $("#saveBtn").attr("onclick","");
		        	  table.fnDraw();
		          }else if(data.result=="paramKeyIsExist"){
		        	  $("#alertContent").html("键已经存在，请重新换一个！");
		          }else if(data.result=="error"){
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
	<div class="alert alert-danger" id="alertId" style="display: none;">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong id="alertContent"></strong>
	</div>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服名字：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="clothName" value="${yznzCloth.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 购买数量：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="purchaseCount" value="${yznzCloth.name}">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"> 销售数量：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="saleCount" value="${yznzCloth.name}">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"> 原价：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="purchasePrice" value="${yznzCloth.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 销售价格：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="preSalePrice" value="${yznzCloth.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 购买类型：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="businessType" value="${yznzCloth.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 风格类型：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="styleType" value="${yznzCloth.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服分类：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="categoryType" value="${value="${yznzCloth.name}"}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 图片：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="picUrl" value="${value="${yznzCloth.name}"}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 商家地址：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="businessAddress" value="${systemParam.id}" value="${yznzCloth.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 添加记录人：<font
				color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="addBy"  value="${value="${yznzCloth.name}"}">
			</div>
		</div>
	</form>
</html>