<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>短信管理</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active"><a href="/basicdata/sms/view-sms-conf.jhtml" data-history>短信配置管理</a></li>
	<li role="presentation"><a href="/basicdata/sms-tools.jhtml" data-ajax="container" data-history>批量短信测试</a></li>
</ul>
<form:form action="/basicdata/save-sms-conf-action.jhtml" method="post" cssClass="required-validate">
	<c:forEach var="model" items="${dataMap }">
		<div class="form-group">
			<label for="${model.key.code }">${model.key.desc }</label> 
			<input type="text" class="form-control" name="${model.key.code }"  required="required" value="${model.value }"/>
		</div>
	</c:forEach>
	<button type="submit" class="btn btn-default">保存配置信息</button>
</form:form>