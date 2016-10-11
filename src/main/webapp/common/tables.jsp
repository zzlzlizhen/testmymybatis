<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="appctx" value="${pageContext.request.contextPath}"></c:set>
<!-- Bootstrap -->
<link rel="stylesheet" href="${ctx}/asset/lib/bootstrap-2.3.2/css/bootstrap.min.css" media="screen">
<link rel="stylesheet" href="${ctx}/asset/lib/bootstrap-2.3.2/css/bootstrap-responsive.min.css" media="screen">
<!-- FontAwesome -->
<link rel="stylesheet" href="${ctx}/asset/lib/font-awesome-4.2.0/css/font-awesome.min.css">
<!-- DataTables CSS start-->
<link rel="stylesheet" href="${ctx}/asset/lib/dataTables-1.10.7/plugins/integration/bootstrap/2/dataTables.bootstrap.css">
<link rel="stylesheet" href="${ctx}/asset/lib/dataTables-1.10.7/plugins/integration/font-awesome/dataTables.fontAwesome.css">
<!-- DataTables CSS end-->
<script src="${ctx}/asset/lib/json2.js"></script>
<!-- JQuery -->
<script src="${ctx}/asset/lib/jquery-1.11.3.min.js"></script>
<!-- Bootstrap -->
<script src="${ctx}/asset/lib/bootstrap-2.3.2/js/bootstrap.min.js"></script>
<!-- SpinJS-->
<script src="${ctx}/asset/lib/spin-2.1.0/jquery.spin.merge.js"></script>
<!-- lhgdialog -->
<script src="${ctx}/asset/lib/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<!-- DataTables JS-->
<script src="${ctx}/asset/lib/dataTables-1.10.7/media/js/jquery.dataTables.js"></script>
<script src="${ctx}/asset/lib/dataTables-1.10.7/plugins/integration/bootstrap/2/dataTables.bootstrap.js"></script>
<!-- DataTables JS end-->
<script src="${ctx}/asset/js/constant.js"></script>
<script src="${ctx}/asset/js/user-manage.js"></script>