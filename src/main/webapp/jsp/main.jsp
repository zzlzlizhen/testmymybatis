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
.dropdown-menu li {list-style-type:none;}
</style>
<script type="text/javascript">
	var ws = null;  
	var url = null;  
	var urlPath = "/testmybatis/websocket;jsessionid=<%=request.getSession(true).getId()%>";
	$(function() {
		iFrameWidth();
		initMenu();
		$(".close").click(function(){
			$("#myHomeModal").hide();
		});
		initWebSocket();
	});
	function initWebSocket(){
		if (window.location.protocol == 'http:') {  
            url = 'ws://' + window.location.host + urlPath;  
        } else {  
            url = 'wss://' + window.location.host + urlPath;  
        }  
		ws = new WebSocket(url); 
        ws.onopen = function () {  
        };  
        ws.onmessage = function (event) {  
        	var url = "";
        	$("#myHomeModal").show();
           	var jsonData = JSON.parse(event.data);
            $("#messageHead").html(jsonData.messageHead);
            url = "${appctx}/messageController/personViewPage/"+jsonData.id;
            $("#messageBody").html('<a href="javascript:void(0)" onclick="linkedHerf(\''+url+'\')">'+jsonData.messageContent+'</a>');
        };  
        ws.onclose = function (event) {  
        };  
	}
	function iFrameHeight() {
		var ifm = document.getElementById("iframepage");
		var subWeb = document.frames ? document.frames["iframepage"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			//ifm.width = subWeb.body.scrollWidth;
			//ifm.width = document.getElementById("content").scrollWidth;
			ifm.height = subWeb.body.scrollHeight;
		}
	}
	function iFrameWidth() {
		var ifm = document.getElementById("iframepage");
		if (ifm != null) {
			ifm.width = document.getElementById("content").scrollWidth;
			ifm.width = ifm.width - 30;
		}
	}
	function initMenu() {
		$.ajax({
			url : '${appctx}/resourceController/getResourceMenuByUserId',
			async : true,
			contentType : "application/json",
			type : 'POST',
			success : function(data, textStatus) {
				var htmlMenu = "<li class='nav-header'>菜单列表</li>";
				$.each(data, function() {
					htmlMenu = htmlMenu + getHtmlMenu(this);
				});
				htmlMenu = htmlMenu + "<li class='nav-header'></li>";
				$("#menuList").html(htmlMenu);
				initSidebar()
			},
			error : function(jqXHR, textStatus, errorThrown) {

			}
		});
	}
	//拼接一个父菜单下所有的列表信息
	function getHtmlMenu(obj) {
		var htmlMenu = "";
		if (obj.hasChild) {
			htmlMenu = htmlMenu
					+ "<li class='accordion'><a href='"+obj.resourceUrl+"'><i class='"+obj.icons+"'></i><span>"
					+ obj.resourceName
					+ "</span></a><ul class='nav nav-pills nav-stacked  main-menu'>";
			$.each(obj.childResource, function() {
				htmlMenu = htmlMenu + getHtmlMenu(this);
			})
			htmlMenu = htmlMenu + "</ul></li>";
		} else {
			var resourceUrl = "";
			if(obj.resourceUrl=="#"){
				htmlMenu = htmlMenu
				+ "<li><a><i class='"+obj.icons+"'></i><span>"
				+ obj.resourceName + "</span></a></li>";
			}
			else{
				resourceUrl = '${appctx}' + obj.resourceUrl;
				htmlMenu = htmlMenu
				+ "<li><a class='ajax-link'  target='main_target' href='"+resourceUrl+"'><i class='"+obj.icons+"'></i><span>"
				+ obj.resourceName + "</span></a></li>";
			}
			
		}
		return htmlMenu;
	}
	
	function initSidebar(){
		$('a.ajax-link').click(function (e) {
	        if (!$('#is-ajax').prop('checked') || $(this).parent().hasClass('active')) return;
	        e.preventDefault();
	        $('.sidebar-nav').removeClass('active');
	        $('#loading').remove();
	        $('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	        var $clink = $(this);
	        History.pushState(null, null, $clink.attr('href'));
	        $('ul.main-menu li.active').removeClass('active');
	        $clink.parent('li').addClass('active');
	    });

	    $('.accordion > a').click(function (e) {
	        e.preventDefault();
	        var $ul = $(this).siblings('ul');
	        var $li = $(this).parent();
	        if ($ul.is(':visible')) $li.removeClass('active');
	        else                    $li.addClass('active');
	        $ul.slideToggle();
	    });
	}
	function linkedHerf(url){
		main_target.location.href=url
		$("#myHomeModal").hide();
	}
</script>
</head>
<body>
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-inner">
			<a class="navbar-brand" href="#"> <img alt="Charisma Logo"
				src="${ctx}/img/logo20.png" class="hidden-xs" /> <span>Charisma</span></a>
			<div class="btn-group pull-right">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i><span
						class="hidden-sm hidden-xs">${loginUser.name}</span> <span
						class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a target='main_target' href="${appctx}/indexController/homePage">首页</a></li>
					<li class="divider"></li>
					<li><a href="${appctx}/indexController/logout/${loginUser.name}">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">
			<div id="leftMenu" class="col-sm-2 col-lg-2">
				<div class="sidebar-nav">
					<div class="nav-canvas">
						<div class="nav-sm nav nav-stacked"></div>
						<ul class="nav nav-pills nav-stacked main-menu" id="menuList">
							<%-- <c:forEach items="${listResourceMenu}" var="resourceMenu">
								<c:choose>
									<c:when test="${resourceMenu.hasChild==true}">
										<li class="accordion"><a href="${resourceMenu.resourceUrl}"><i class="${resourceMenu.icons}"></i><span>${resourceMenu.resourceName}</span></a>
											<ul class="nav nav-pills nav-stacked">
												<c:forEach items="${resourceMenu.childResource}" var="childMenu">
													<li><a class="ajax-link"  target='main_target' href="${appctx}${childMenu.resourceUrl}"><i class="${childMenu.icons}"></i><span>${childMenu.resourceName}</span></a></li>
												</c:forEach>
											</ul>
										</li>
									</c:when>
									<c:otherwise>
										<li><a class="ajax-link"  target='main_target' href="${appctx}${resourceMenu.resourceUrl}"><i class="${resourceMenu.icons}"></i><span>${resourceMenu.resourceName}</span></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach> --%>
							<%-- <li class="accordion"><a href="#"><i class="glyphicon glyphicon-plus"></i><span>bootstrap使用</span></a>
								<ul class="nav nav-pills nav-stacked">
									<li><a class="ajax-link" target='main_target' href="${appctx}/bootstrapController/dashboard"><i class="glyphicon glyphicon-home"></i><span> Dashboard</span></a></li>
									<li><a class="ajax-link" target='main_target' href="${appctx}/bootstrapController/ui"><i class="glyphicon glyphicon-eye-open"></i><span> UI Features</span></a></li>
									<li><a class="ajax-link" target='main_target' href="${appctx}/bootstrapController/form"><i class="glyphicon glyphicon-edit"></i><span> Forms</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/chart"><i class="glyphicon glyphicon-list-alt"></i><span> Charts</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/typography"><i class="glyphicon glyphicon-font"></i><span> Typography</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/gallery"><i class="glyphicon glyphicon-picture"></i><span> Gallery</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/table"><i class="glyphicon glyphicon-align-justify"></i><span> Tables</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/calendar"><i class="glyphicon glyphicon-calendar"></i><span> Calendar</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/grid"><i class="glyphicon glyphicon-th"></i><span> Grid</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/tour"><i class="glyphicon glyphicon-globe"></i><span> Tour</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/icon"><i class="glyphicon glyphicon-star"></i><span> Icons</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/error"><i class="glyphicon glyphicon-ban-circle"></i><span> Error Page</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/bootstrapController/loginPage"><i class="glyphicon glyphicon-lock"></i><span> Login Page</span></a></li>
								</ul>
							</li>
							<li class="accordion"><a href="#"><i class="glyphicon glyphicon-plus"></i><span>权限管理</span></a>
								<ul class="nav nav-pills nav-stacked">
									<li><a class="ajax-link"  target='main_target' href="${appctx}/userController/userIndex"><i class="glyphicon glyphicon-align-justify"></i><span>用户管理</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/roleController/roleIndex"><i class="glyphicon glyphicon-calendar"></i><span>角色管理</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/resourceController/resourceIndex"><i class="glyphicon glyphicon-th"></i><span>资源管理</span></a></li>
								</ul>
							</li>
							<li class="accordion"><a href="#"><i class="glyphicon glyphicon-plus"></i><span>系统管理</span></a>
								<ul class="nav nav-pills nav-stacked">
									<li><a class="ajax-link"  target='main_target' href="${appctx}/paramController/paramIndex2"><i class="glyphicon glyphicon-align-justify"></i><span>日志统计</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/visitorController/visitorIndex"><i class="glyphicon glyphicon-calendar"></i><span>访客管理</span></a></li>
									<li><a class="ajax-link"  target='main_target' href="${appctx}/paramController/paramIndex"><i class="glyphicon glyphicon-th"></i><span>系统参数</span></a></li>
								</ul>
							</li> --%>
						</ul>
					</div>
				</div>
			</div>
			<div id="content" class="col-lg-10 col-sm-10">
				<iframe src="${appctx}/indexController/homePage" name='main_target'
					id="iframepage" frameborder="0" scrolling="no" marginheight="0"
					marginwidth="0" onLoad="iFrameHeight()"></iframe>
			</div>
			<hr>
			<div id="myHomeModal" style="display: none;">
				<div class="modal-dialog" style="width:350px;height:175px;position: absolute;right:3px;bottom:3px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close">&times;</button>
							<font color="red" id="messageHead">消息提醒</font>
						</div>
						<div class="modal-body" id="messageBody" style="height:150px;">
							<!-- <a href="javascript:void(0)" onclick="linkedHerf()">请重新设置密码</a> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer style="text-align: center">
       		<p style="text-align:center;">Copyright@2016-2020 @author lizhen</p>
   		</footer>
	</div>
</body>
</html>
