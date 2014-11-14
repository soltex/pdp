<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<form:form action="/example/add-example-action.jhtml" method="post" cssClass="required-validate"  commandName="uploadfilesForm">
	<div class="form-group">
		<label for="regionsMultipartFile">枚举选择</label> 
		<select name="adminState">
			<c:forEach var="model" items="${exampleStates }">
				<option value="${model.code }">${model.desc }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="regionsMultipartFile">发布时间</label> 
		<input type="text" name="releaseTime" value="2012-12-12" />
	</div>
	<button type="submit" class="btn btn-default">保存</button> &nbsp; 
</form:form>