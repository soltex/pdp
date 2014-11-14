<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

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
	<link href="/pnotify/pnotify.core.css" rel="stylesheet" type="text/css" />
	<link href="/pnotify/pnotify.buttons.css" rel="stylesheet" type="text/css" />
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

<!-- Fixed navbar -->






<div class="navbar navbar-inverse  navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.html" data-history="index">互联物业控制台</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.html" data-history>首页</a></li>
				
				<li class="active"><a href="/example/add-example.jhtml" data-history>枚举例子</a></li>
				
				<li><a href="/community/view-communities.jhtml" data-history>社区管理</a></li>
				<li><a href="/syslog/search-syslogs.jhtml" data-history>系统日志</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">基础数据管理<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/basicdata/view-provinces.jhtml" data-history>地区数据管理</a></li>
						<li><a href="/basicdata/view-sms-conf.jhtml" data-history>短信平台管理</a></li>
						<li><a href="/basicdata/view-mail-conf.jhtml" data-history>邮件配置管理</a></li>
						<li><a href="/basicdata/view-systemplate-conf.jhtml" data-history>系统模板管理</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">系统信息管理<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" data-history>数据库自动备份</a></li>
						<li><a href="/system/view-systeminfo.jhtml" data-history>系统系统</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">微信管理<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">微信群发</a></li>
						<li><a href="/weixin/weixin-menu-conf.jhtml" data-history>微信菜单管理</a></li>
					</ul>
				</li>
				
				
				<li><a href="/admin/view-admins.jhtml" data-history>管理员管理</a></li>
				
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/profile/update-profile-password.jhtml" data-toggle="modal" data-target="#modal-dialog">修改密码</a></li>
				<li><a href="/logout.jhtml">退出</a></li>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</div>










<div class="container-fluid" id="container">
	
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

<!-- PNotify -->
<script type="text/javascript" src="/pnotify/pnotify.core.js"></script>
<script type="text/javascript" src="/pnotify/pnotify.buttons.js"></script>

	
<script src="/styles/js/dwz.core.js"></script>
<script src="/styles/js/dwz.history.js"></script>
<script src="/styles/js/dwz.ui.js"></script>
<script src="/styles/js/dwz.regional.zh.js"></script>
<script src="/styles/js/pdp.common.js"></script>
<script src="/styles/js/dwz.pagination.js"></script>

<script src="/styles/js/vanstone.pnotify.js"></script>

<script src="/styles/js/jquery.lazyload.min.js"></script>
</body>
</html>
