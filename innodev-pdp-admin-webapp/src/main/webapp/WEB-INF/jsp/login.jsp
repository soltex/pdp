<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.vanstone.com/imgserver/tag/1.0" prefix="imgserver" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>互联物业App管理控制台</title>

	<!-- Bootstrap core CSS -->
	<link href="/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="/styles/css/signin.css" rel="stylesheet">
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
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form role="form" action="/login-action.jhtml" class="form-signin required-validate" method="post" id="loginForm">
					<div class="form-group">
						<label for="adminName">账户名</label> 
						<input type="text" class="form-control" name="userName" id="userName" required="required" />
					</div>
					<div class="form-group">
						<label for="adminPwd">密码</label> 
						<input type="password" class="form-control" name="userPwd" id="userPwd" required="required" />
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" name="rememerPassword"> 记住密码 </label>
					</div>
					<button type="submit" class="btn btn-default">登 录</button>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
		
	</div>
	<!-- /container -->
	
	
	
<div id="background" style="display: none"></div>
<div id="progressBar" style="display: none">数据加载中，请稍等.</div>
	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
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
<script src="/styles/js/dwz.pagination.js"></script>
<script src="/styles/js/dwz.ui.js"></script>
<script src="/styles/js/dwz.regional.zh.js"></script>

<script type="text/javascript">
	$('#loginForm').bind(DWZ.eventType.ajaxDone, function(event, json){
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			window.location.href=json.params.redirectUrl;
		}
	});
</script>

</body>
</html>

