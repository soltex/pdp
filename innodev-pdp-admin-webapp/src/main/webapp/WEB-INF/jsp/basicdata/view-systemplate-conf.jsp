<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>系统模板配置信息管理</h1>

<c:forEach var="model" items="${dataMap }">
	<form:form action="/basicdata/save-systemplate-conf-action.jhtml" method="post" cssClass="required-validate">
		<div class="form-group">
			<label for="${model.key.code }">${model.key.desc }</label> 
			<textarea class="form-control" name="${model.key.code }"  required="required"  rows="5">${model.value }</textarea>
		</div>
		<button type="submit" class="btn btn-default">保存配置信息</button>
	</form:form>
</c:forEach>
