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
    $(".myfile").fileinput({
        //上传的地址
        uploadUrl:"${appctx}/purchaseManagerController/uploadFile",
        uploadAsync : true, //默认异步上传
        showUpload : false, //是否显示上传按钮,跟随文本框的那个
        showRemove : false, //显示移除按钮,跟随文本框的那个
        showCaption : true,//是否显示标题,就是那个文本框
        showPreview : true, //是否显示预览,不写默认为true
        dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount : 1, //表示允许同时上传的最大文件个数
        enctype : 'multipart/form-data',
        validateInitialCount : true,
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
        allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
        allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
        language : 'zh'
    })
    //异步上传返回结果处理
    $('.myfile').on('fileerror', function(event, data, msg) {
        console.log("fileerror");
        console.log(data);
    });
    //异步上传返回结果处理
    $(".myfile").on("fileuploaded", function(event, data, previewId, index) {
        console.log("fileuploaded");
        var ref = $(this).attr("data-ref");
        $("input[name='" + ref + "']").val(data.response.url);

    });

    //同步上传错误处理
    $('.myfile').on('filebatchuploaderror', function(event, data, msg) {
        console.log("filebatchuploaderror");
        console.log(data);
    });

    //同步上传返回结果处理
    $(".myfile").on("filebatchuploadsuccess",
        function(event, data, previewId, index) {
            console.log("filebatchuploadsuccess");
            console.log(data);
        });

    //上传前
    $('.myfile').on('filepreupload', function(event, data, previewId, index) {
        console.log("filepreupload");
    });
	function saveSubmit(){
		if($("#clothName1").val()==null||(!$("#clothName1").val().length>0)){
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
			var jsonData  = {};
			jsonData.id=$("#id").val();
			jsonData.name=$("#clothName1").val();
			jsonData.purchaseCount=$("#purchaseCount").val();
			jsonData.saleCount=$("#saleCount").val();
			jsonData.purchasePrice=$("#purchasePrice").val();
			jsonData.preSalePrice=$("#preSalePrice").val();
			jsonData.businessType=$("#businessType").val();
			jsonData.categoryType=$("#categoryType").val();
			jsonData.styleType=$("#styleType").val();
			jsonData.picUrl=$("#picUrl").val();
			jsonData.businessAddress=$("#businessAddress").val();
			jsonData.addBy=$("#addBy").val();
			$.ajax({
		        url: '${appctx}/purchaseManagerController/edit',
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
	<input type="hidden" id="id" value="${yznzCloth.id}" />
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label"> 衣服名字：<font color="red">*</font>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="clothName1" value="${yznzCloth.name}">
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
				<input type="file" name="myfile"
					   class="col-sm-9 myfile" value="" id="picUrl" /> <input type="hidden"
																			  name="url2" value="${yznzCloth.picUrl}">
				<%--<input type="text" class="form-control" id="picUrl" value="${yznzCloth.picUrl}">--%>
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
</html>
