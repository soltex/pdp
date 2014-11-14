<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<form:form action="/basicdata/batch-send-mail-action.jhtml" method="post" cssClass="required-validate" commandName="mailToolsForm">
	<div class="form-group">
		<label for="mailContent">群发内容</label> 
		<form:input cssClass="form-control" path="mailTitle"  required="required"  />
	</div>
	<div class="form-group">
		<label for="mailContent">邮件内容</label> 
		<form:textarea cssClass="form-control" path="mailContent"  required="required"  rows="5"></form:textarea>
	</div>
	<button type="submit" class="btn btn-default">批量发送邮件</button>
</form:form>