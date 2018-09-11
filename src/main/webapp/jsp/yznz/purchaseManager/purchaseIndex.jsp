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
							"sAjaxSource" : "${appctx}/purchaseManagerController/getPages",
							"fnInitComplete": function(oSettings, json) { 
								window.parent.iFrameHeight();
							},
							"fnServerParams": function (aoData) {  //查询条件
			                    aoData.push(
			                    	{ "name": "searchId", "value": $("#searchId").val()}
			                    );
			                },
							"aoColumnDefs" : [
									{
										"aTargets" : [ 0 ],
										"mData" : null,
										"sWidth": "10%",
										"bSortable": false,
										"sClass" : "td-checkbox text-center",
										"mRender" : function(data, type, full) {
											return '<input id='+full[0]+' type="checkbox" name="iCheck" class="iCheck"></input>';
										}
									},
									{
										"aTargets" : [ 1 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "15%",
										"mRender" : function(data, type, full) {
											return '<img src='+full[1]+' width="100%" height="100%">';
										}
									},
									{
										"aTargets" : [ 2 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "15%",
										"mRender" : function(data, type, full) {
											return full[2];
										}
									},
									{
										"aTargets" : [ 3 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "15%",
										"mRender" : function(data, type, full) {
											return full[3];
										}
									},
									{
										"aTargets" : [ 4 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "15%",
										"mRender" : function(data, type, full) {
											return full[4];
										}
									},
									{
										"aTargets" : [ 5 ],
										"sClass" : "text-center",
										"bSortable": false,
										"sWidth": "15%",
										"mRender" : function(data, type, full) {
											return full[5];
										}
									},
									{
										"aTargets" : [ 6 ],
										"sClass" : "text-center",
										"bSortable": false,
										"mData" : null,
										"sWidth": "30%",
										"mRender" : function(data, type, full) {
										return '<a class="btn btn-success" href="#" onclick="viewFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a>'
							            +'<a class="btn btn-info" style="margin-left:10px;" href="#" onclick="editFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-edit icon-white"></i>修改</a>'
							            +'<a class="btn btn-danger" style="margin-left:10px;" href="#" onclick="delFun(\''+ full[0]+ '\')"><i class="glyphicon glyphicon-trash icon-white"></i>删除</a>';
										}
									},
								]
						});
		
	});
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
		$("#myModal .modal-header").find("h3").html("进货管理详情");
		$("#myModal .modal-footer #saveBtn").html("保存");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").hide();
		$("#myModal").modal({
		    remote: "${appctx}/purchaseManagerController/viewPage/"+id
		});
	}
	function addFun(){
		$("#myModal .modal-header").find("h3").html("新增进货管理参数");
		$("#myModal .modal-footer #saveBtn").html("保存");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").attr("onclick","saveSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal({
		    remote: "${appctx}/purchaseManagerController/addPage"
		});
	}
	function editFun(id){
		$("#myModal .modal-header").find("h3").html("修改进货管理详情");
		$("#myModal .modal-footer #saveBtn").html("修改");
		$("#myModal .modal-footer #closeBtn").html("取消");
		$("#saveBtn").attr("onclick","saveSubmit()");
		$("#saveBtn").show();
		$("#myModal").modal({
		    remote: "${appctx}/purchaseManagerController/editPage/"+id
		});
	}
	function delFun(id){
		delId = id;
		$("#myModal .modal-header").find("h3").html("删除进货管理");
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
	        url: '${appctx}/purchaseManagerController/del',
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
	function batchDelFun(){
		batchDelId="";
		var delSize = $('#dataTables input[name="iCheck"]:checked').size();
		if(delSize>0){
			$("#myModal .modal-header").find("h3").html("批量删除系统管理");
			$("#myModal .modal-footer #saveBtn").html("确定");
			$("#myModal .modal-footer #closeBtn").html("取消");
			$("#myModal .modal-body").html(
					'<div class="alert alert-danger" id="alertId" style="display:none;">'
					+'<button type="button" class="close" data-dismiss="alert">&times;</button>'
					+'<strong id="alertContent">键值不能为空</strong>'
					+'</div><h3>您选择了'+delSize+'条记录，确定要全部删除吗？</h3>'
			);
			$("#saveBtn").attr("onclick","batchDelSubmit()");
			$("#saveBtn").show();
			$('#dataTables input[name="iCheck"]:checked').each(function(n){
				batchDelId = batchDelId+$(this).attr("id")+",";
			});
			batchDelId = batchDelId.substring(0,batchDelId.length-1);
		}else{
			$("#myModal .modal-header").find("h3").html("批量删除进货管理");
			$("#myModal .modal-footer #saveBtn").html("确定");
			$("#myModal .modal-footer #closeBtn").html("取消");
			$("#myModal .modal-body").html("<h3>请选择要删除的数据</h3>");
			$("#saveBtn").attr("onclick","bachDelSelectInfo()");
			$("#saveBtn").show();
		}
	}
	/*function translationAttr(attrVlue){
		if((attrVlue.indexOf("YZNZ_CLOUTHES_SIZE")) > -1){
			return "尺寸";
		}else if((attrVlue.indexOf("YZNZ_CLOUTHES_STYLE")) > -1){
			return "风格";
		}else if((attrVlue.indexOf("YZNZ_CLOUTHES_COURLE")) > -1){
			return "颜色";
		}else if((attrVlue.indexOf("YZNZ_CLOUTHES_CATEGORY")) > -1){
			return "种类";
		}else{
			return "未知";
		}
	}*/
	function bachDelSelectInfo(){
		$("#myModal").modal('hide');
	}
	function batchDelSubmit(){
		$.ajax({
	        url: '${appctx}/purchaseManagerController/batchDel',
	        async: true,
	        contentType:"application/json",
	        type: 'POST',
	        data: JSON.stringify({id:batchDelId}),
	        success: function(data , textStatus){
	          $("#alertId").show();
	          if(data.result=="success"){
	        	  $("#alertContent").html("批量删除成功"+data.delSize+"条数据！");
	        	  setTimeout("$('#myModal').modal('hide')",1000); 
	        	  table.fnDraw();
	          }else if(data.result=="error"){
	        	  $("#alertContent").html("批量删除失败，请联系管理员");
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
				<li><a href="#">进货管理</a></li>
				<li><a href="#">进货管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i>进货管理
						</h2>
					</div>
					<div class="box-content">
						<div class="btn-toolbar">
							<div class="pull-right">
								<div class="input-append">
									<select id="searchId">
										<option value="">全部</option>
										<option value="YZNZ_CLOUTHES_SIZE">尺寸</option>
										<option value="YZNZ_CLOUTHES_STYLE">风格</option>
										<option value="YZNZ_CLOUTHES_COLOUR">颜色</option>
										<option value="YZNZ_CLOUTHES_CATEGORY">类型</option>
									</select> <a class="btn btn-primary" href="#" onclick="searchFun()">
										<i class="glyphicon glyphicon-search"></i>查询
									</a>
								</div>
							</div>
							<a class="btn btn-primary" href="#" onclick="addFun()"> <i
								class="glyphicon glyphicon-plus"></i>添加
							</a> <a class="btn btn-danger" href="#" onclick="batchDelFun()"
								data-toggle="modal" data-target="#myModal"> <i
								class="glyphicon glyphicon-trash "></i>批量删除
							</a>
						</div>
						<div style="height: 15px;"></div>

						<div class="row-fluid">
							<table id="dataTables"
								class="table table-striped table-bordered table-hover table-condensed datatable">
								<thead>
									<tr>
										<th><input type="checkbox" onclick="selectAll(this)"
											name="cb-check-all"></th>
										<th>图片</th>
										<th>名字</th>
										<th>类型</th>
										<th>数量</th>
										<th>剩余数量</th>
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
