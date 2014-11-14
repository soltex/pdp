<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<form:form action="/uploadfiles-action.jhtml" method="post" cssClass="required-validate" enctype="multipart/form-data"  commandName="uploadfilesForm">
	<div class="form-group">
		<label for="regionsMultipartFile">多文件选择</label> 
		<input type="file" class="form-control" name="uploadFiles"  required="required" multiple="multiple"/>
	</div>
	<button type="submit" class="btn btn-default">保存</button> &nbsp; 
</form:form>