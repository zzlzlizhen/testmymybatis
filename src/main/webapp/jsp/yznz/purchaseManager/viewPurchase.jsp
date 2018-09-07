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

</script>
</head>
<body>
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
				<input type="text" class="form-control" id="purchaseCount" value="${yznzCloth.purchaseCount}">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"> 销售数量：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="saleCount" value="${yznzCloth.saleCount}">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"> 原价：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="purchasePrice" value="${yznzCloth.purchasePrice}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 销售价格：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="preSalePrice" value="${yznzCloth.preSalePrice}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 购买类型：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="businessType" value="${yznzCloth.businessType}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 风格类型：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="styleType" value="${yznzCloth.styleType}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服分类：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="categoryType" value="${yznzCloth.categoryType}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 图片：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="picUrl" value="${yznzCloth.picUrl}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 商家地址：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="businessAddress"  value="${yznzCloth.businessAddress}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 添加记录人：<font
				color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="addBy"  value="${yznzCloth.addBy}">
			</div>
		</div>
	</form>
</body>
</html>
