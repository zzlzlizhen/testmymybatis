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
<!-- chart libraries start -->
<script src="${ctx}/bower_components/flot/excanvas.min.js"></script>
<script src="${ctx}/bower_components/flot/jquery.flot.js"></script>
<script src="${ctx}/bower_components/flot/jquery.flot.pie.js"></script>
<script src="${ctx}/bower_components/flot/jquery.flot.stack.js"></script>
<script src="${ctx}/bower_components/flot/jquery.flot.resize.js"></script>
<!-- chart libraries end -->
<script src="${ctx}/js/init-chart.js"></script>
<script type="text/javascript">
		$(function(){
			//initChart();
			initChart();
		});
	</script>
</head>

<body>
	<div class="ch-container">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Charts</a></li>
		</ul>


		<div class="row">

			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-list-alt"></i> Chart with points
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round btn-default"><i
								class="glyphicon glyphicon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round btn-default"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
								class="btn btn-close btn-round btn-default"><i
								class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div id="sincos" class="center" style="height: 300px"></div>
						<p id="hoverdata">
							Mouse position at (<span id="x">0</span>, <span id="y">0</span>).
							<span id="clickdata"></span>
						</p>
					</div>
				</div>
			</div>

			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-list-alt"></i> Flot
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round btn-default"><i
								class="glyphicon glyphicon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round btn-default"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
								class="btn btn-close btn-round btn-default"><i
								class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div id="flotchart" class="center" style="height: 300px"></div>
					</div>
				</div>
			</div>

			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-list-alt"></i> Stack Example
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round btn-default"><i
								class="glyphicon glyphicon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round btn-default"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
								class="btn btn-close btn-round btn-default"><i
								class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div id="stackchart" class="center" style="height: 300px;"></div>

						<p class="stackControls center">
							<input class="btn btn-default" type="button"
								value="With stacking"> <input class="btn btn-default"
								type="button" value="Without stacking">
						</p>

						<p class="graphControls center">
							<input class="btn btn-primary" type="button" value="Bars">
							<input class="btn btn-primary" type="button" value="Lines">
							<input class="btn btn-primary" type="button"
								value="Lines with steps">
						</p>
					</div>
				</div>
			</div>

		</div>
		<!--/row-->

		<div class="row">
			<div class="box col-md-4">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-list-alt"></i> Pie
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round btn-default"><i
								class="glyphicon glyphicon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round btn-default"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
								class="btn btn-close btn-round btn-default"><i
								class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div id="piechart" style="height: 300px"></div>
					</div>
				</div>
			</div>

			<div class="box col-md-4">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-list-alt"></i> Realtime
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round btn-default"><i
								class="glyphicon glyphicon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round btn-default"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
								class="btn btn-close btn-round btn-default"><i
								class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div id="realtimechart" style="height: 190px;"></div>
						<p>You can update a chart periodically to get a real-time
							effect by using a timer to insert the new data in the plot and
							redraw it.</p>

						<p>
							Time between updates: <input id="updateInterval" type="text"
								value="" style="text-align: right; width: 5em">
							milliseconds
						</p>
					</div>
				</div>
			</div>

			<div class="box col-md-4">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-list-alt"></i> Donut
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round btn-default"><i
								class="glyphicon glyphicon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round btn-default"><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
								class="btn btn-close btn-round btn-default"><i
								class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div id="donutchart" style="height: 300px;"></div>
					</div>
				</div>
			</div>
		</div>
		<!--/row-->
		<hr>
	</div>
</body>
</html>
