<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib uri="http://www.vanstone.com/weixin/tag/1.0" prefix="weixin" %>


<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active"><a href="/${communityId}/myaccount/update-base-proprietor-info.jhtml?openId=${myAccountForm.openId}" data-ajax="container" data-history>更新基本信息</a></li>
	<li role="presentation" class=""><a href="/${communityId}/myaccount/update-proprietor-header.jhtml?openId=${myAccountForm.openId}" data-ajax="container" data-history>更新头像信息</a></li>
</ul>

<form:form action="/${communityId}/myaccount/update-base-proprietor-info-action.jhtml" method="post" cssClass="required-validate" modelAttribute="myAccountForm">
	<form:hidden path="openId"  />
	<div class="form-group">
		<label for="email">邮件地址</label> 
		<form:input cssClass="form-control" path="email"  required="" />
	</div>
	<div class="form-group">
		<label for="fullName">业主姓名</label> 
		<form:input cssClass="form-control" path="fullName"  required="" />
	</div>
	<div class="form-group">
		<label for="nickName">昵称</label> 
		<form:input cssClass="form-control" path="nickName"  required="" />
	</div>
	<div class="form-group">
		<label for="gender">性别</label> 
		<form:radiobutton path="gender" value="1"/> 男 <form:radiobutton path="gender" value="0"/> 女 
	</div>
	<div class="form-group">
		<label for="profession">业主职业</label> 
		<form:input cssClass="form-control" path="profession"  required="" />
	</div>
	<div class="form-group">
		<label for="interest">兴趣爱好</label> 
		<form:input cssClass="form-control" path="interest"  required="" />
	</div>
	<button type="submit" class="btn btn-default">更新业主基本信息</button>
</form:form>

