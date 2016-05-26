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
	$(function(){
		iFrameWidth();
		
	});
	function iFrameHeight() {   
		var ifm= document.getElementById("iframepage");   
		var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
		if(ifm != null && subWeb != null) {
		   //ifm.width = subWeb.body.scrollWidth;
		   //ifm.width = document.getElementById("content").scrollWidth;
		   ifm.height = subWeb.body.scrollHeight;
		}   
	}   
	function iFrameWidth(){
		var ifm= document.getElementById("iframepage");   
		if(ifm != null) {
			   ifm.width = document.getElementById("content").scrollWidth;
			   ifm.width = ifm.width-30;
			}   
	}
</script>
</head>
<body>
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-inner">
			<a class="navbar-brand" href="#"> <img
				alt="Charisma Logo" src="${ctx}/img/logo20.png" class="hidden-xs" />
				<span>Charisma</span></a>
			<div class="btn-group pull-right">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i><span
						class="hidden-sm hidden-xs">${loginUser.name}</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">首页</a></li>
					<li class="divider"></li>
					<li><a href="${appctx}/loginController/logout">退出</a></li>
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
						<ul class="nav nav-pills nav-stacked main-menu">
							<li class="nav-header">菜单列表</li>
							<c:forEach items="${listResourceMenu}" var="resourceMenu">
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
							</c:forEach>
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
				<iframe src="${appctx}/loginController/homePage" name='main_target' id="iframepage" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()"></iframe>
			</div>
			<hr>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
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
							<a href="#" class="btn btn-primary" data-dismiss="modal">Save
								changes</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer class="row">
			<p class="col-md-9 col-sm-9 col-xs-12 copyright">
				&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a>
				2012 - 2014
			</p>
			<p class="col-md-3 col-sm-3 col-xs-12 powered-by">
				Powered by: <a
					href="http://usman.it/free-responsive-admin-template">Charisma</a>
			</p>
		</footer>
	</div>
</body>
</html>
