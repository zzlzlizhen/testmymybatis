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
		if($("#clothName").val()==null||(!$("#clothName").val().length>0)){
			$("#alertId").show();
			$("#alertContent").html("衣服名字不能为空");
		}else if($("#purchaseCount").val()==null||(!$("#purchaseCount").val().length>0)){
			$("#alertId").show();
			$("#alertContent").html("衣服的购买数量不能为空");
		}else if($("#saleCount").val()==null || (!$("#saleCount").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服销售数量不能为空");
		}else if($("#purchasePrice").val()==null || (!$("#purchasePrice").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服原价不能为空");
		}else if($("#preSalePrice").val()==null || (!$("#preSalePrice").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服销售价格不能为空");
		}else if($("#businessType").val()==null || (!$("#businessType").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服购买类型不能为空");
		}else if($("#styleType").val()==null || (!$("#styleType").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服风格类型不能为空");
		}else if($("#categoryType").val()==null || (!$("#categoryType").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服分类不能为空");
		}else if($("#picUrl").val()==null || (!$("#picUrl").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("衣服图片不能为空");
		}else if($("#businessAddress").val()==null || (!$("#businessAddress").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("购买衣服商家地址不能为空");
		}else if($("#addBy").val()==null || (!$("#addBy").val().length > 0)){
			$("#alertId").show();
			$("#alertContent").html("添加记录人不能为空");
		}
		else{
			$.ajax({
		        url: '${appctx}/purchaseManagerController/add',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify({clothName:$("#clothName").val(),purchaseCount:$("#purchaseCount").val(),saleCount:$("#saleCount").val(),purchasePrice:$("#purchasePrice").val(),prechasePrice:$("#prechasePrice").val(),businessType:$("#businessType").val(),styleType:$("#styleType").val(),categoryType:$("#categoryType").val(),picUrl:$("#picUrl").val(),businessAddress:$("#businessAddress").val(),addBy:$("#addBy").val()}),
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
		<strong id="alertContent"></strong>
	</div>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服名字：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="clothName">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 购买数量：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="purchaseCount">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"> 销售数量：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="saleCount">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"> 原价：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="purchasePrice">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 销售价格：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="preSalePrice">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 购买类型：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="businessType">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 风格类型：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="styleType">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服分类：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="categoryType">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 图片：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="picUrl">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 商家地址：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="businessAddress">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 添加记录人：<font
				color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="addBy">
			</div>
		</div>
	</form>
</body>
</html>
