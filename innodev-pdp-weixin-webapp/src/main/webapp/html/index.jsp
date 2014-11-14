<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.vanstone.com/imgserver/tag/1.0" prefix="imgserver" %>

<%@ taglib uri="http://www.vanstone.com/weixin/tag/1.0" prefix="weixin" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="/favicon.ico">

	<title>互联物业控制台</title>

	<!-- Bootstrap core CSS -->
	<link href="/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="/styles/css/style.css" rel="stylesheet">

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/bootstrap-3.2.0-dist/js/ie10-viewport-bug-workaround.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<!--
	https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js
	https://oss.maxcdn.com/respond/1.4.2/respond.min.js
	-->
	<script src="/bootstrap-3.2.0-dist/js/html5shiv.min.js"></script>
	<script src="/bootstrap-3.2.0-dist/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>

<div class="container-fluid" id="container">
	
	
	<div class="row">
		<div class="col-md-4">
			 <span class="glyphicon glyphicon-asterisk"></span>
			 <a href=""><span class="glyphicon-class">手机绑定</span></a>
		</div>
		<div class="col-md-4">
			<a href="">房间号绑定</a>
		</div>
		<div class="col-md-4">
			<a href="">个人信息</a>
		</div>
		<div class="col-md-4">
			<a href="">申请访客二维码</a>
		</div>
		<div class="col-md-4">
			<a href="">申请报修</a>
		</div>
		<div class="col-md-4">
			<a href="">社区公告</a>
		</div>
		<div class="col-md-4">
			<a href="">常用知识</a>
		</div>
		<div class="col-md-4">
			<a href="">关于社区</a>
		</div>
	</div>
	
	
</div><!-- /container -->

<div id="background" style="display: none"></div>
<div id="progressBar" style="display: none">数据加载中，请稍等.</div>

<!-- Modal -->
<div class="modal fade" id="modal-dialog" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

		</div>
	</div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/bootstrap-3.2.0-dist/js/jquery-1.11.1.min.js"></script>
<script src="/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>

<script src="/jquery-validation-1.13.0-dist/jquery.validate.min.js"></script>
<script src="/jquery-validation-1.13.0-dist/additional-methods.min.js"></script>
<script src="/jquery-validation-1.13.0-dist/localization/messages_zh.min.js"></script>

<script src="/styles/js/dwz.core.js"></script>
<script src="/styles/js/dwz.history.js"></script>
<script src="/styles/js/dwz.ui.js"></script>
<script src="/styles/js/dwz.regional.zh.js"></script>
<script src="/styles/js/pdp.common.js"></script>
<script src="/styles/js/dwz.pagination.js"></script>
</body>
</html>
