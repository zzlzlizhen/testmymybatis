<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>
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
	var table;
	var delId,batchDelId;
	$(function() {
		table = $('#dataTables').dataTable(
						{
							"sDom" : "<'row'<'col-md-6'l>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
							"oLanguage" : {//语言设置
								"sLengthMenu" : "每页显示  _MENU_ 条记录",
								"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
								"oPaginate" : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								},
								"sZeroRecords" : "抱歉， 没有找到",
								"sInfoEmpty" : "没有数据"
							},
							"sPaginationType" : "bootstrap",
							"bAutoWidth" : true,//自动宽度
							"bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
							"bServerSide" : true, //开启服务器模式，使用服务器端处理配置datatable。注意：sAjaxSource参数也必须被给予为了给datatable源代码来获取所需的数据对于每个画。 这个翻译有点别扭。开启此模式后，你对datatables的每个操作 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值。
							"sAjaxSource" : "${appctx}/userController/getUsers",
							"fnInitComplete": function(oSettings, json) { 
								window.parent.iFrameHeight();
							},
							"fnServerParams": function (aoData) {  //查询条件
			                    aoData.push(
			                    	{ "name": "userName", "value": $("#userName").val()},
			                    	{ "name": "phone", "value": $("#phone").val()},
			                    	{ "name": "status", "value": $("#status").val()}
			                    );
			                },
							"aoColumnDefs" : [
									{
										"aTargets" : [ 0 ],
										"bVisible" : false,
										"bSortable": false,
										"sClass" : "text-center"
									},
									{
										"aTargets" : [ 1 ],
										"sClass" : "text-center",
										"sWidth": "15%",
										"bSortable": false
									},
									{
										"aTargets" : [ 2 ],	
										"sClass" : "text-center",
										"sWidth": "10%",
										"bSortable": false
									},
									{
										"aTargets" : [ 3 ],	
										"sClass" : "text-center",
										"sWidth": "15%",
										"bSortable": false
									},
									{
										"aTargets" : [ 4 ],	
										"sClass" : "text-center",
										"sWidth": "15%",
										"bSortable": false
									},
									{
										"aTargets" : [ 5 ],	
										"sClass" : "text-center",
										"sWidth": "10%",
										"bSortable": false,
										"mRender" : function(data,type,full){
											return full[5]==1?'<span class="label-success label label-default">启用</span>':'<span class="label-default label">停用</span>'
										}
									},
									{
										"aTargets" : [ 6 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "35%",
										"mData" : null,
										"mRender" : function(data, type, full) {
										return '<a class="btn btn-success" href="#" onclick="viewFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a>'
							            +'<a class="btn btn-info" style="margin-left:10px;" href="#" onclick="editFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-edit icon-white"></i>修改</a>'
							            +'<a class="btn btn-danger" style="margin-left:10px;" href="#" onclick="delFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-trash icon-white"></i>删除</a>'
							            +(full[5]==1?'<a class="btn btn-success" style="margin-left:10px;" href="#" onclick="stopFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-zoom-in icon-white"></i>停用</a>':'<a class="btn btn-success" style="margin-left:10px;" href="#" onclick="recoverFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-zoom-in icon-white"></i>启用</a>');
										}
									},
								]
						});
		
	});
	function searchFun(){
		table.fnDraw();
	}
	function viewFun(id){
		$("#myModal .modal-header").find("h3").html("用户详情");
		$("#myModal .modal-footer #saveBtn").html("保存");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").hide();
		$("#myModal").modal({
		    remote: "${appctx}/userController/viewPage/"+id
		});
	}
	function addFun(){
		$("#myModal .modal-header").find("h3").html("新增用户");
		$("#myModal .modal-footer #saveBtn").html("保存");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").attr("onclick","saveSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal({
		    remote: "${appctx}/userController/addPage"
		});
	}
	function editFun(id){
		$("#myModal .modal-header").find("h3").html("修改用户");
		$("#myModal .modal-footer #saveBtn").html("修改");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").attr("onclick","saveSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal({
		    remote: "${appctx}/userController/editPage/"+id
		});
	}
	function delFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("删除用户");
		$("#myModal .modal-footer #saveBtn").html("确定");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#myModal .modal-body").html(
				'<div class="alert alert-danger" id="alertId" style="display:none;">'
				+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
				+'<strong id="alertContent"></strong>'
				+'</div><h3>确定要删除该条数据？</h3>'
		);
		$("#saveBtn").attr("onclick","delSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal('show');
	}
	function delSubmit(){
		$.ajax({
	        url: '${appctx}/userController/del',
	        async: true,
	        contentType:"application/json",
	        type: 'POST',
	        data: JSON.stringify({id:delId}),
	        success: function(data , textStatus){
	          $("#alertId").show();
	          if(data.result=="success"){
	        	  $("#alertContent").html("删除成功！");
	        	  setTimeout("$('#myModal').modal('hide')",1000); 
	        	  table.fnDraw();
	          }else if(data.result=="error"){
	        	  $("#alertContent").html("删除失败，请联系管理员");
	          }
	        },
	        error: function(jqXHR , textStatus , errorThrown){
	        	$("#alertId").show();
	        	$("#alertContent").html("系统异常，请联系管理员");
	        }
	       
	      });
	}
	function stopFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("暂停用户");
		$("#myModal .modal-footer #saveBtn").html("确定");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#myModal .modal-body").html(
				'<div class="alert alert-danger" id="alertId" style="display:none;">'
				+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
				+'<strong id="alertContent"></strong>'
				+'</div><h3>确定要将该用户暂停吗？</h3>'
		);
		$("#saveBtn").attr("onclick","stopOrRecoverSubmit(0)");
		$("#saveBtn").show();
		$("#myModal").modal('show');
	}
	function recoverFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("启用用户");
		$("#myModal .modal-footer #saveBtn").html("确定");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#myModal .modal-body").html(
				'<div class="alert alert-danger" id="alertId" style="display:none;">'
				+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
				+'<strong id="alertContent"></strong>'
				+'</div><h3>确定要将该用户启动吗？</h3>'
		);
		$("#saveBtn").attr("onclick","stopOrRecoverSubmit(1)");
		$("#saveBtn").show();
		$("#myModal").modal('show');
	}
	function stopOrRecoverSubmit(stat){
		$.ajax({
	        url: '${appctx}/userController/stopOrRecover',
	        async: true,
	        contentType:"application/json",
	        type: 'POST',
	        data: JSON.stringify({id:delId,status:stat}),
	        success: function(data , textStatus){
	          $("#alertId").show();
	          if(data.result=="success"){
	        	  $("#alertContent").html("处理成功！");
	        	  setTimeout("$('#myModal').modal('hide')",1000); 
	        	  table.fnDraw();
	          }else if(data.result=="error"){
	        	  $("#alertContent").html("处理失败，请联系管理员");
	          }
	        },
	        error: function(jqXHR , textStatus , errorThrown){
	        	$("#alertId").show();
	        	$("#alertContent").html("系统异常，请联系管理员");
	        }
	       
	      });
	}
</script>
</head>
<body>
	<div class="ch-container">
		<div>
			<ul class="breadcrumb">
				<li><a href="#">权限管理</a></li>
				<li><a href="#">用户管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i>用户列表
						</h2>
					</div>
					<div class="box-content">
						<div class="btn-toolbar">
							<div class="pull-right">
								<div class="input-append">
									用户名：<input type="text" placeholder="用户名" id="userName">
									手机号：<input type="text" placeholder="手机号" id="phone">
									状态：<select id="status">
										<option value="">全部</option>
										<option value="1">启用</option>
										<option value="0">停用</option>
									</select> <a class="btn btn-primary" href="#" onclick="searchFun()">
										<i class="glyphicon glyphicon-search"></i>查询
									</a>
								</div>
							</div>
							<a class="btn btn-primary" href="#" onclick="addFun()"> <i
								class="glyphicon glyphicon-plus"></i>添加
							</a>
						</div>
						<div style="height: 15px;"></div>

						<div class="row-fluid">
							<table id="dataTables"
								class="table table-striped table-bordered table-hover table-condensed datatable">
								<thead>
									<tr>
										<th>id</th>
										<th>用户名</th>
										<th>手机号</th>
										<th>邮箱</th>
										<th>创建时间</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3>Settings</h3>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" id="closeBtn"
						data-dismiss="modal">Close</a> <a href="#" onclick=""
						class="btn btn-primary" id="saveBtn">Save</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
