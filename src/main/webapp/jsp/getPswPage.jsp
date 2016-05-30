<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">
    <style type="text/css">
    	ul{
    		list-style: none;
    		display: block;
    	}
    	.mod-sub-nav{
    		height: 34px;
    		background: url(${ctx}/img/myimage/mod_sub_nav.png) no-repeat 0 0;
    		margin: 30px 30px;
    		line-height: 34px;
    		margin-left:100px;
    		color: #666;
    		font-size: 16px;
    		font-family: "Microsoft Yahei",\5fae\8f6f\96c5\9ed1,\9ed1\4f53;
    	}
    	.mod-sub-nav li{
    		float:left;
    		width:232px;
    		font-size:16px;
    	}
    	.list1-active{
    		background: url(${ctx}/img/myimage/sub_nav_1.png) no-repeat 0 0;
    	}
    	.list2-active{
    		background: url(${ctx}/img/myimage/sub_nav_2.png) no-repeat 0 0;
    	}
    	.list3-active{
    		background: url(${ctx}/img/myimage/sub_nav_3.png) no-repeat 0 0;
    	}
    </style>
    <script type="text/javascript">
    	var currOper = 1;
    	function nextOper(){
    		if(currOper==1){
    			$(".mod-sub-nav .mod-sub-list1").removeClass("list1-active");
        		$(".mod-sub-nav .mod-sub-list2").addClass("list2-active");
        		$("#prex").removeAttr("disabled");
    		}else if(currOper==2){
    			$(".mod-sub-nav .mod-sub-list2").removeClass("list2-active");
        		$(".mod-sub-nav .mod-sub-list3").addClass("list3-active");
    			$("#next").attr("disabled","disabled");
    		}
    		currOper++;
    	}
    	function prexOper(){
    		if(currOper==3){
    			$(".mod-sub-nav .mod-sub-list3").removeClass("list3-active");
        		$(".mod-sub-nav .mod-sub-list2").addClass("list2-active");
        		$("#next").removeAttr("disabled");
    		}else if(currOper==2){
    			$(".mod-sub-nav .mod-sub-list2").removeClass("list2-active");
        		$(".mod-sub-nav .mod-sub-list1").addClass("list1-active");
        		$("#prex").attr("disabled","disabled");
    		}
    		currOper--;
    	}
    	$(function(){
    		//document.onkeydown=keyDownSearch;
    		$("input").keypress(function(e){
        		// 兼容FF和IE和Opera    
        		var theEvent = e || window.event;    
                var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
     		   	if (code == 13) {
     		   		loginFunction();
     		   	}
     		 });
    	});
    	
        /* function keyDownSearch(e) {    
             var theEvent = e || window.event;    
             var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
             if (code == 13) {    
                 loginFunction()
             }    
        }   */

    	function loginFunction(){
    		if($("#username").val()==null||(!$("#username").val().length>0)){
    			$("#alertContent").html("请输入用户名");
    			$("#alertId").show();
    		}else if($("#password").val()==null||(!$("#password").val().length>0)){
    			$("#alertId").show();
    			$("#alertContent").html("请输入密码");
    		}
    		else{
    			$.ajax({
    		        url: '${appctx}/loginController/loginValid',
    		        async: true,
    		        contentType:"application/json",
    		        type: 'POST',
    		        data: JSON.stringify({name:$("#username").val(),pwd:$("#password").val()}),
    		        success: function(data , textStatus){
    		          if(data.result=="success"){
    		        	  location.href="${appctx}/loginController/loginSuccess";
    		          }else if(data.result=="userStop"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("已停用，请联系系统管理员！");
    		          }else if(data.result=="infoError"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("用户名或者密码不对！");
    		          }else if(data.result=="error"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("登录失败，请联系管理员！");
    		          }
    		        },
    		        error: function(jqXHR , textStatus , errorThrown){
    		        	$("#alertId").show();
    		        	$("#alertContent").html("系统异常，请联系管理员！");
    		        }
    		      });
    		}
    	}
    </script>
</head>
<body>
<div class="ch-container">
    <div class="row">
    <div class="row">
        <div class="col-md-12 center login-header">
            <h2>找回密码</h2>
        </div>
    </div>
    <div class="row">
        <div class="well col-md-8 center login-box">
            <div class="alert alert-danger" id="alertId" style="display: none;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong id="alertContent"></strong>
			</div>
            <!-- <form class="form-horizontal">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="text" class="form-control" id="username" placeholder="Username">
                    </div>
                    <div class="clearfix"></div><br>

                    <div cloginId="input-gronext input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i>100pan>
                        <input type="password" class="form-controlloginId="password" placeholder="Password">
                    </div>
                    <div class="clearfix"></div><br>
                </fieldset>
            </form> -->
            <ul class="mod-sub-nav">
            	<li class="mod-sub-list1 list1-active">确认账号</li>
            	<li class="mod-sub-list2">安全验证</li>
            	<li class="mod-sub-list3">重置密码</li>
            </ul>
            <div class="row">
               <div class="col-md-6" style="text-align: left;padding-left:25px;padding-left:200px;">
               		<button type="button" class="btn btn-default" id="prex" onclick="prexOper()">上一步</button>
               </div>
               <div class="col-md-6" style="text-align: right;padding-right:25px;padding-right:200px;">
               		<button type="button" class="btn btn-primary" id="next" onclick="nextOper()">下一步</button>
               </div>
            </div>
            <!-- <div class="row">
             	<div class="col-md-6" style="text-align: left;padding-left:200px;">
             		<button type="button" class="btn btn-info" id="prex" onclick="prexOper()">上一步</button>
             	</div>
             	<div class="center col-md-6" style="text-align: right;padding-right:200px;">
             		<button type="button" class="btn btn-primary" id="next" onclick="nextOper()">下一步</button>
             	</div>
            </div> -->
            <!-- <p class="center col-md-4" id="initButton">
	           	<button type="button" class="btn btn-primary" id="next" onclick="nextOper()">下一步</button>
	        </p> -->
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div><!--/.fluid-container-->

</body>
</html>
