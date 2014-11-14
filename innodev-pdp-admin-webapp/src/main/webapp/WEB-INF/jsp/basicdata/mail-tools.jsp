<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>邮件配置信息管理</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" ><a href="/basicdata/view-mail-conf.jhtml" data-ajax="container" data-history>邮件配置管理</a></li>
	<li role="presentation" class="active"><a href="/basicdata/mail-tools.jhtml" data-ajax="container" data-history>批量邮件测试</a></li>
</ul>

<form:form action="/basicdata/batch-send-mail-action.jhtml" method="post" cssClass="required-validate" commandName="mailToolsForm">
	<div class="form-group">
		<label for="emails">收件地址(批量邮件地址请使用逗号分隔或者回车换行)</label> 
		<form:textarea path="emails" cssClass="form-control" required="required"  rows="4"></form:textarea>
	</div>
	<div class="form-group">
		<label for="mailContent">邮件标题</label> 
		<form:input cssClass="form-control" path="mailTitle"  required="required"  />
	</div>
	<div class="form-group">
		<label for="mailContent">邮件内容</label> 
		<form:textarea cssClass="form-control" path="mailContent"  required="required"  rows="5"></form:textarea>
	</div>
	<button type="submit" class="btn btn-default">批量发送邮件</button>
</form:form>
