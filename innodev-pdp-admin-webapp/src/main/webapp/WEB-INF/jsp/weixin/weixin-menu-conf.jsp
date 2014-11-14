<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<div class="row">
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<div class="col-md-4"></div>
	<div class="col-md-4" style="text-align: center">
		<form:form action="/weixin/reinitial-weixin-menu-action.jhtml" method="post" cssClass="required-validate"> 
			<button type="submit" class="btn btn-default btn-lg" data-loading-text="Loading..." class="btn btn-primary" autocomplete="true">初始化微信菜单信息</button>
		</form:form>
	</div>
	<div class="col-md-4"></div>
</div>
