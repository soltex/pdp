<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>



<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class=""><a href="/admin/view-admins.jhtml" data-ajax="container" data-history>管理员信息</a></li>
	<li role="presentation" class="active"><a href="/admin/add-admin.jhtml" data-ajax="container" data-history>添加管理员</a></li>
</ul>





<form:form action="/admin/add-admin-action.jhtml" method="post" cssClass="required-validate">
	<div class="form-group">
		<label for="adminName">管理员账号</label> 
		<input type="text" class="form-control" name="adminName"  required="required" />
	</div>
	<div class="form-group">
		<label for="adminPwd">管理员密码</label> 
		<input type="text" class="form-control" name="adminPwd"  /> 
		<small>如不输入，则随机生成密码。</small>
	</div>
	<button type="submit" class="btn btn-default">保存</button>
</form:form>


