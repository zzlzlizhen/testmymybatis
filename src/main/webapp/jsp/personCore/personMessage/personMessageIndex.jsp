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
							"sAjaxSource" : "${appctx}/messageController/getPersonMessages",
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
										"sWidth": "20%"
									},
									{
										"aTargets" : [ 2 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "40%"
									},
									{
										"aTargets" : [ 3 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "8%",
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
											return full[4]==1?'<span class="label-success label label-default">已读</span>':'<span class="label-danger label-default label">未读</span>'
										}
									},
									{
										"aTargets" : [ 5 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "14%"
									},
									{
										"aTargets" : [ 6 ],
										"sClass" : "text-center",
										"bSortable": false,
										"mData" : null,
										"sWidth": "10%",
										"mRender" : function(data, type, full) {
											return viewLinked(full[0]);
										}
									},
								]
						});
		
	});
	function viewLinked(id){
		return '<a class="btn btn-success" href="#" onclick="viewFun(\''+id+ '\')"><i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a>';
	}

	function searchFun(){
		table.fnDraw();
	}
	function viewFun(id){
		location.href="${appctx}/messageController/personViewPage/"+id;
	}

	
</script>
</head>
<body>
	<div class="ch-container">
		<div>
			<ul class="breadcrumb">
				<li><a href="#">个人中心</a></li>
				<li><a href="#">个人消息</a></li>
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
									消息类型：
									<select id="messageType">
										<option value="-1">全部</option>
										<option value="1">通知</option>
										<option value="2">提醒</option>
									</select>
									&nbsp;&nbsp;
									是否已读：
									<select id="messageStatus">
										<option value="-1">全部</option>
										<option value="1">已读</option>
										<option value="0">未读</option>
									</select>
									&nbsp;&nbsp;
									消息标题：<input type="text" placeholder="消息标题" id="messageHead">
									<a class="btn btn-primary" href="#" onclick="searchFun()">
										<i class="glyphicon glyphicon-search"></i>查询
									</a>
								</div>
							</div>
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
										<th>是否已读</th>
										<th>发布时间</th>
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
				<div class="modal-body">
					
				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" id="closeBtn" data-dismiss="modal">Close</a>
					<a href="#" onclick="" class="btn btn-primary" id="saveBtn">Save</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
