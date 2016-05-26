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
	var setting = {
			check: {
				enable: true,chkboxType:{"Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {enable: true}
			},
			async: {
				enable: true,
				url:"${appctx}/resourceController/getCheckedResourceByRoleId/"+$("#id").val(),
				autoParam:["id","name=n"],
				dataFilter: filter
			}
		};
	$(function() {
		$.fn.zTree.init($("#roleTree"), setting);
	});
	 		
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function saveSubmit(){
		/* treeObj = $.fn.zTree.getZTreeObj("roleTree");
		alert(treeObj.getSelectedNodes().length); */
		var treeObj = $.fn.zTree.getZTreeObj("roleTree");
		var nodes = treeObj.getCheckedNodes(true);
		var checkIds="";
		if(nodes.length==0){
			$("#alertId").show();
			$("#alertContent").html("请选择该角色需要管理的资源菜单");
		}else{
			for(var i=0;i<nodes.length;i++){
				checkIds = checkIds + nodes[i].id+",";
			}
			checkIds = checkIds.substring(0,checkIds.length-1);
			var jsonData  = {};
			jsonData.id=$("#id").val();
			jsonData.remark=checkIds;
			$.ajax({
		        url: '${appctx}/roleController/auth',
		        async: true,
		        contentType:"application/json",
		        type: 'POST',
		        data: JSON.stringify(jsonData),
		        success: function(data , textStatus){
		          $("#alertId").show();
		          if(data.result=="success"){
		        	  $("#alertContent").html("保存成功！");
		        	  setTimeout("$('#myModal').modal('hide')",1000); 
		        	  $("#saveBtn").attr("onclick","");
		        	  table.fnDraw();
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
	<div class="alert alert-danger" id="alertId" style="display:none;">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong id="alertContent"></strong>
	</div>
	<input type="hidden" id="id" value="${id}"/>
	<div style="width:100%;">
		<ul id="roleTree" class="ztree" style="width:100%;"></ul>
	</div>
</body>
</html>
