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
    		list-style:none;
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
    	.input-group{
    		width:400px;
    		margin:auto;
    	}
    </style>
    <script type="text/javascript">
    	var currOper = 1;
    	var countdown = 0;
    	var phoneRegex = /^[1][0-9]{10}$/;
    	function nextOper(){
    		if(currOper==1){
    			validatePhone();
    		}else if(currOper==2){
    			validateSecurity();
    		}else{
    			resetPsw();
    		}
    	}
    	function prexOper(){
    		if(currOper==3){
    			$(".mod-sub-nav .mod-sub-list3").removeClass("list3-active");
        		$(".mod-sub-nav .mod-sub-list2").addClass("list2-active");
        		$("#next").removeAttr("disabled");
        		$("#form3").hide();
        		$("#form2").show();
    		}else if(currOper==2){
    			$(".mod-sub-nav .mod-sub-list2").removeClass("list2-active");
        		$(".mod-sub-nav .mod-sub-list1").addClass("list1-active");
        		$("#prex").attr("disabled","disabled");
        		$("#form2").hide();
        		$("#form1").show();
    		}
    		currOper--;
    	}
    	function resetPsw(){
    		if($("#password1").val()==null||(!$("#password1").val().length>0)){
       			$("#alertId").show();
       			$("#alertContent").html("请输入密码");
       		}else if($("#password2").val()==null||(!$("#password2").val().length>0)){
       			$("#alertId").show();
       			$("#alertContent").html("请再次输入密码");
       		}else if($("#password1").val()!=$("#password2").val()){
       			$("#alertId").show();
       			$("#alertContent").html("二次输入的密码不一致，请重新输入");
       		}else{
       			$.ajax({
    		        url: '${appctx}/loginController/getPwdValidateResetPsw',
    		        async: true,
    		        contentType:"application/json",
    		        type: 'POST',
    		        data: JSON.stringify({pwd:$("#password1").val(),name:$("#resetPswUserName").val()}),
    		        success: function(data , textStatus){
    		          if(data.result=="success"){
    		        	  $("#alertId").show();
    		        	  countdown = 5;
   			        	  settime1();
    		          }else if(data.result=="error"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("重置密码失败，请联系管理员！");
    		          }
    		        },
    		        error: function(jqXHR , textStatus , errorThrown){
    		        	$("#alertId").show();
    		        	$("#alertContent").html("系统异常，请联系管理员！");
    		        }
    		      });
       		}
    	}
    	//验证用户输入的验证码是否正确
    	function validateSecurity(){
    		if($("#security").val()==null||(!$("#security").val().length>0)){
    			$("#alertContent").html("请输入验证码");
    			$("#alertId").show();
    		}else{
    			$.ajax({
    		        url: '${appctx}/loginController/getPwdValidateSecurity',
    		        async: true,
    		        contentType:"application/json",
    		        type: 'POST',
    		        data: JSON.stringify({securityCode:$("#security").val()}),
    		        success: function(data , textStatus){
    		          if(data.result=="success"){
    		        	  	$(".mod-sub-nav .mod-sub-list2").removeClass("list2-active");
    		        		$(".mod-sub-nav .mod-sub-list3").addClass("list3-active");
    		    			$("#form2").hide();
    		        		$("#form3").show();
    		        		$("#alertId").hide();
    		        		currOper++;
    		          }else if(data.result=="securityError"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("您输入的验证码不对，请重新输入");
    		          }else if(data.result=="error"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("验证码验证失败，请联系管理员！");
    		          }
    		        },
    		        error: function(jqXHR , textStatus , errorThrown){
    		        	$("#alertId").show();
    		        	$("#alertContent").html("系统异常，请联系管理员！");
    		        }
    		      });
    		}
    	}
    	//验证用户输入的用户名和手机号是否正确
    	function validatePhone(){
    		if($("#username").val()==null||(!$("#username").val().length>0)){
    			$("#alertContent").html("请输入用户名");
    			$("#alertId").show();
    		}else if($("#telephone").val()==null||(!$("#telephone").val().length>0)||(!phoneRegex.test($("#telephone").val()))){
       			$("#alertId").show();
       			$("#alertContent").html("请输入预留合法的手机号，如：15899999999");
       		}else{
    			$.ajax({
    		        url: '${appctx}/loginController/getPwdValidateUserNameAndPhone',
    		        async: true,
    		        contentType:"application/json",
    		        type: 'POST',
    		        data: JSON.stringify({name:$("#username").val(),phone:$("#telephone").val()}),
    		        success: function(data , textStatus){
    		          if(data.result=="success"){
    		        	 $(".mod-sub-nav .mod-sub-list1").removeClass("list1-active");
    		        	 $(".mod-sub-nav .mod-sub-list2").addClass("list2-active");
    		        	 $("#prex").removeAttr("disabled");
    		        	 $("#form1").hide();
    		        	 $("#form2").show();
    		        	 $("#alertId").show();
   		        	  	 $("#alertContent").html("发送成功,验证码是"+data.securityCode);
   		        	  	 $("#resetPswUserName").val(data.resetPswUserName);
   		        	  	 currOper++;
    		          }else if(data.result=="infoError"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("用户名或者手机号输入不对！");
    		          }else if(data.result=="error"){
    		        	  $("#alertId").show();
    		        	  $("#alertContent").html("验证用户名失败，请联系管理员！");
    		          }
    		        },
    		        error: function(jqXHR , textStatus , errorThrown){
    		        	$("#alertId").show();
    		        	$("#alertContent").html("系统异常，请联系管理员！");
    		        }
    		      });
    		}
    	}
    	function settime1(){
    		if (countdown == 0) { 
    			location.href="${appctx}/index.jsp";
        		return;
        	} else { 
        		$("#alertContent").html("重置密码成功，"+countdown+"秒后跳转到<a herf='/index.jsp'>登录<a>页面");
        		countdown--; 
        	} 
    		setTimeout(function() { 
    			settime1(); 
    		},1000);
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
            <ul class="mod-sub-nav">
            	<li class="mod-sub-list1 list1-active">确认账号</li>
            	<li class="mod-sub-list2">安全验证</li>
            	<li class="mod-sub-list3">重置密码</li>
            </ul>
            <div class="alert alert-danger" id="alertId" style="display: none;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong id="alertContent"></strong>
			</div>
            <form class="form-horizontal" id="form1">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="clearfix"></div><br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-earphone red"></i></span>
                        <input type="text" class="form-control" id="telephone" maxlength="11" placeholder="请输入手机号">
                    </div>
                </fieldset>
            </form>
            <form class="form-horizontal" id="form2" style="display: none;">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="red">验证码</i></span>
                        <input type="text" class="form-control" id="security" maxlength="6" placeholder="请输入验证码">
                        <!-- <span>
                        	<button type="button" class="btn btn-primary" id="loginId" onclick="loginFunction()">获取验证码</button>
                        </span> -->
                    </div>
                    <div class="clearfix"></div><br>
                </fieldset>
            </form>
            <form class="form-horizontal" id="form3" style="display: none;">
                <fieldset>
                	<input type="hidden" id="resetPswUserName"/> 
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input type="password" class="form-control" id="password1" placeholder="请输入密码">
                    </div>
                    <div class="clearfix"></div><br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input type="password" class="form-control" id="password2" placeholder="请再次输入密码">
                    </div>
                    <div class="clearfix"></div><br>
                </fieldset>
            </form>
            <div class="row">
                <div class="col-md-6" style="text-align: left;padding-left:25px;"><a href="${appctx}/index.jsp">返回登录</a></div>
                </div>
                <div class="clearfix">
            </div>
            <div class="row">
               <div class="col-md-6" style="text-align: left;padding-left:200px;">
               		<button type="button" class="btn btn-default" id="prex" onclick="prexOper()">上一步</button>
               </div>
               <div class="col-md-6" style="text-align: right;padding-right:200px;">
               		<button type="button" class="btn btn-primary" id="next" onclick="nextOper()">下一步</button>
               </div>
            </div>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div><!--/.fluid-container-->

</body>
</html>
