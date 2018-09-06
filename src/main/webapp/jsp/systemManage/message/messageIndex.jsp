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
<style type="text/css">
/* #dataTables{
		font-size: 12px;
	} */
#opr {
	white-space: nowrap;
}
</style>
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
							"sAjaxSource" : "${appctx}/messageController/getMessages",
							"fnInitComplete": function(oSettings, json) { 
								window.parent.iFrameHeight();
							},
							"fnServerParams": function (aoData) {  //查询条件
			                    aoData.push(
		                    		{ "name": "messageHead", "value": $("#messageHead").val()},
			                    	{ "name": "messageType", "value": $("#messageType").val()},
			                    	{ "name": "messageStatus", "value": $("#messageStatus").val()}
			                    );
			                },
							"aoColumnDefs" : [
									{
										"aTargets" : [ 0 ],
										"bVisible" : false,
										"bSortable": false,
										"sWidth": "15%",
										"sClass" : "text-center"
									},
									{
										"aTargets" : [ 1 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "15%"
									},
									{
										"aTargets" : [ 2 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "20%"
									},
									{
										"aTargets" : [ 3 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "6%",
										"mRender" : function(data, type, full) {
											var msg;
											if(full[3]==1) msg='<span class="label-danger label label-default">通知</span>';
											else if(full[3]==2) msg='<span class="label-info label label-default">提醒</span>';
											return msg;
										}
									},
									{
										"aTargets" : [ 4 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "8%",
										"mRender" : function(data, type, full) {
											var msg;
											if(full[4]==1) msg='<span class="label-info label label-default">已保存</span>';
											else if(full[4]==2) msg='<span class="label-success label label-default">已发布</span>';
											else if(full[4]==3) msg='<span class="label-danger label label-default">已销毁</span>';
											return msg;
										}
									},
									{
										"aTargets" : [ 5 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "9%"
									},
									{
										"aTargets" : [ 6 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "9%"
									},
									{
										"aTargets" : [ 7 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "9%"
									},
									{
										"aTargets" : [ 8 ],
										"sClass" : "text-center",
										"bSortable": false,
										"mData" : null,
										"sWidth": "30%",
										"mRender" : function(data, type, full) {
											var msg;
											if(full[4]==1) msg=viewLinked(full[0])+updateLinked(full[0])+publishLinked(full[0])+deleteLinked(full[0]);
											else if(full[4]==2) msg=viewLinked(full[0])+destoryLinked(full[0]);
											else if(full[4]==3) msg=viewLinked(full[0])+deleteLinked(full[0]);
											return '<div id="opr">'+msg+'</div>';
										}
									},
								]
						});
		
	});
	function viewLinked(id){
		return '<a class="btn btn-success" href="#" onclick="viewFun(\''+id+ '\')"><i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a>';
	}
	function updateLinked(id){
		return '<a class="btn btn-info" style="margin-left:10px;" href="#" onclick="editFun(\''+ id+ '\')"><i class="glyphicon glyphicon-edit icon-white"></i>修改</a>'
	}
	function publishLinked(id){
		return '<a class="btn btn-danger" style="margin-left:10px;" href="#" onclick="publishFun(\''+ id+ '\')"><i class="glyphicon glyphicon-arrow-up"></i>发布</a>'
	}
	function deleteLinked(id){
		return '<a class="btn btn-danger" style="margin-left:10px;" href="#" onclick="delFun(\''+ id+ '\')"><i class="glyphicon glyphicon-trash icon-white"></i>删除</a>';
	}
	function destoryLinked(id){
		return '<a class="btn btn-danger" style="margin-left:10px;" href="#" onclick="destoryFun(\''+ id+ '\')"><i class="glyphicon glyphicon-arrow-down"></i>销毁</a>';
	}
	function selectAll(obj){
		if(obj.checked){
			$(".iCheck").each( function() {
			    $(this)[0].checked=true;
		    });
		}else{
			$(".iCheck").each( function() {
			    $(this)[0].checked=false;
		    });
		}
	}
	function searchFun(){
		table.fnDraw();
	}
	function viewFun(id){
		/* $("#myModal .modal-header").find("h3").html("系统参数详情");
		$("#myModal .modal-footer #saveBtn").html("保存");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").hide();
		$("#myModal").modal({
		    remote: "${appctx}/messageController/viewPage/"+id
		}); */
		location.href="${appctx}/messageController/viewPage/"+id;
		
	}
	function addFun(){
		$("#myModal .modal-header").find("h3").html("发布消息");
		$("#myModal .modal-footer #saveBtn").html("保存");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").attr("onclick","saveSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal({
		    remote: "${appctx}/messageController/addPage"
		});
	}
	function editFun(id){
		$("#myModal .modal-header").find("h3").html("修改信息");
		$("#myModal .modal-footer #saveBtn").html("修改");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").attr("onclick","saveSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal({
		    remote: "${appctx}/messageController/editPage/"+id
		});
	}
	
	function publishFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("发布消息");
		$("#myModal .modal-footer #saveBtn").html("确定");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#myModal .modal-body").html(
				'<div class="alert alert-danger" id="alertId" style="display:none;">'
				+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
				+'<strong id="alertContent">键值不能为空</strong>'
				+'</div><h3>确定要发布该条消息？</h3>'
		);
		$("#saveBtn").attr("onclick","publishSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal('show');
	}
	
	function publishSubmit(){
		$.ajax({
	        url: '${appctx}/messageController/publish',
	        async: true,
	        contentType:"application/json",
	        type: 'POST',
	        data: JSON.stringify({id:delId}),
	        success: function(data , textStatus){
	          $("#alertId").show();
	          if(data.result=="success"){
	        	  $("#alertContent").html("发布成功！");
	        	  setTimeout("$('#myModal').modal('hide')",1000); 
	        	  table.fnDraw();
	          }else if(data.result=="error"){
	        	  $("#alertContent").html("发布失败，请联系管理员");
	          }
	        },
	        error: function(jqXHR , textStatus , errorThrown){
	        	$("#alertId").show();
	        	$("#alertContent").html("系统异常，请联系管理员");
	        }
	       
	      });
	}
	function destoryFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("销毁消息");
		$("#myModal .modal-footer #saveBtn").html("确定");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#myModal .modal-body").html(
				'<div class="alert alert-danger" id="alertId" style="display:none;">'
				+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
				+'<strong id="alertContent">键值不能为空</strong>'
				+'</div><h3>确定要发布该条消息？</h3>'
		);
		$("#saveBtn").attr("onclick","destorySubmit()");
		$("#saveBtn").show();
		$("#myModal").modal('show');
	}
	
	function destorySubmit(){
		$.ajax({
	        url: '${appctx}/messageController/destory',
	        async: true,
	        contentType:"application/json",
	        type: 'POST',
	        data: JSON.stringify({id:delId}),
	        success: function(data , textStatus){
	          $("#alertId").show();
	          if(data.result=="success"){
	        	  $("#alertContent").html("销毁成功！");
	        	  setTimeout("$('#myModal').modal('hide')",1000); 
	        	  table.fnDraw();
	          }else if(data.result=="error"){
	        	  $("#alertContent").html("销毁失败，请联系管理员");
	          }
	        },
	        error: function(jqXHR , textStatus , errorThrown){
	        	$("#alertId").show();
	        	$("#alertContent").html("系统异常，请联系管理员");
	        }
	       
	      });
	}
	function delFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("删除消息");
		$("#myModal .modal-footer #saveBtn").html("确定");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#myModal .modal-body").html(
				'<div class="alert alert-danger" id="alertId" style="display:none;">'
				+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
				+'<strong id="alertContent">键值不能为空</strong>'
				+'</div><h3>确定要删除该条数据？</h3>'
		);
		$("#saveBtn").attr("onclick","delSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal('show');
	}
	function delSubmit(){
		$.ajax({
	        url: '${appctx}/messageController/del',
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
	
</script>
</head>
<body>
	<div class="ch-container">
		<div>
			<ul class="breadcrumb">
				<li><a href="#">系统管理</a></li>
				<li><a href="#">消息管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i>消息管理
						</h2>
					</div>
					<div class="box-content">
						<div class="btn-toolbar">
							<div class="pull-right">
								<div class="input-append">
									消息类型： <select id="messageType">
										<option value="-1">全部</option>
										<option value="1">通知</option>
										<option value="2">提醒</option>
									</select> &nbsp;&nbsp; 消息状态： <select id="messageStatus">
										<option value="-1">全部</option>
										<option value="1">已保存</option>
										<option value="2">已发布</option>
										<option value="3">已销毁</option>
									</select> &nbsp;&nbsp; 消息标题：<input type="text" placeholder="消息标题"
										id="messageHead"> <a class="btn btn-primary" href="#"
										onclick="searchFun()"> <i
										class="glyphicon glyphicon-search"></i>查询
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
										<th>消息标题</th>
										<th>消息内容</th>
										<th>类型</th>
										<th>状态</th>
										<th>创建时间</th>
										<th>发布时间</th>
										<th>销毁时间</th>
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
