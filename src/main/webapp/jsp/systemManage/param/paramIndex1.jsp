<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">
	<script type="text/javascript">
		$(function(){
			//datatable
		    $('#dataTables').dataTable({
		        "sDom": "<'row'<'col-md-6'l>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
		        "sPaginationType": "bootstrap",
		        "oLanguage": {//语言设置
		              "sLengthMenu": "每页显示  _MENU_ 条记录",  
		              "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		              "oPaginate": {
		                  "sFirst": "首页",
		                  "sPrevious": "前一页",
		                  "sNext": "后一页",
		                  "sLast": "尾页"
		                  },
		              "sZeroRecords": "抱歉， 没有找到",
		              "sInfoEmpty": "没有数据"
		            },
		        "bAutoWidth": true,//自动宽度
		        "bProcessing": true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
	            "bServerSide": true, //开启服务器模式，使用服务器端处理配置datatable。注意：sAjaxSource参数也必须被给予为了给datatable源代码来获取所需的数据对于每个画。 这个翻译有点别扭。开启此模式后，你对datatables的每个操作 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值。
	            "bStateSave": true, //保存cookie
	            "sAjaxSource": "${appctx}/paramController/getParams",
	            /* "aoColumnDefs": [
						{ 
							"bVisible": false, 
							"aTargets": [ 0 ]
						},
						{ 
							"bVisible": false, 
							"aTargets": [ 1 ]
						},
						{ 
							"aTargets" : [4], 
							"mRender" : function(data, type, full){ 
								return '<button type="button" class="btn btn-small btn-primary btn-edit">修改</button><button type="button" style="margin-left:10px;" class="btn btn-small btn-danger btn-del">删除</button>';
						} 
					}
					
				] */
				
		    });
		});
	</script>
</head>
<body>
<div class="ch-container">
   <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">Tables</a>
            </li>
        </ul>
    </div>

    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i>系统参数</h2>
    </div>
    <div class="box-content">
    <div class="btn-toolbar">
		<div class="pull-right">
			<div class="input-append">
				键/值：<input type="text" placeholder="键/值" id="fuzzy-search">
				<button type="button" class="btn" id="btn-search"><i class="fa fa-search"></i>查询</button>
			</div>
		</div>
		<button type="button" class="btn btn-primary" id="btn-add"><i class="fa fa-plus"></i> 添加</button>
	</div>
    <div style="height:15px;"></div>
    <table id="dataTables" class="table table-striped table-bordered bootstrap-datatable responsive">
    <thead>
    <tr>
        <th>id</th>
        <th>键</th>
        <th>值</th>
        <th>创建时间</th>
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
    <hr>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">Ã</button>
                    <h3>Settings</h3>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
