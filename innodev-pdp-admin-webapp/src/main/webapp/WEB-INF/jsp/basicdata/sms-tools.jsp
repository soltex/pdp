<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>短信管理</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" ><a href="/basicdata/view-sms-conf.jhtml" data-ajax="container" data-history>短信配置管理</a></li>
	<li role="presentation" class="active"><a href="/basicdata/sms-tools.jhtml" data-ajax="container" data-history>批量短信测试</a></li>
</ul>

<form:form action="/basicdata/batch-send-sms-action.jhtml" method="post" cssClass="required-validate" commandName="smsToolsForm">
	<div class="form-group">
		<label for="mobiles">手机号(批量手机号请使用逗号分隔或者回车换行)</label> 
		<form:textarea path="mobiles" cssClass="form-control" required="required"  rows="4"></form:textarea>
	</div>
	<div class="form-group">
		<label for="smsContent">短信内容</label> 
		<form:textarea cssClass="form-control" path="smsContent"  required="required"  rows="6"></form:textarea>
	</div>
	<button type="submit" class="btn btn-default">批量发送短信</button>
</form:form>
