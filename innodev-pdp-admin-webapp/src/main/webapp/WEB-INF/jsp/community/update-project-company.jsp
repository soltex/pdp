<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>社区信息-${community.communityName }【${community.id}】</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation"  class=""><a href="/community/update-basic-community/${community.id }.jhtml" data-ajax="container" data-history>社区基本信息</a></li>
	<li role="presentation"  class=""><a href="/community/update-community-qr/${community.id }.jhtml" data-ajax="container"  data-history>社区二维码图片信息</a></li>
	<li role="presentation"  class="active"><a href="/community/update-project-company/${community.id }.jhtml" data-ajax="container"  data-history>所属项目公司</a></li>
	<li role="presentation"  class=""><a href="/community/add-community-images/${community.id }.jhtml" data-ajax="container"  data-history>社区图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/view-community-images/${community.id}.jhtml" data-ajax="container"  data-history>社区图片</a></li>
	<li role="presentation"  class=""><a href="/community/view-buildings/${community.id}.jhtml" data-ajax="container"  data-history>社区楼号信息</a></li>
</ul>


<form:form action="/community/update-project-company-action/${community.id }.jhtml" method="post" cssClass="required-validate" commandName="communityForm">
	
	<div class="form-group">
		<label for="companyName">工程公司名称</label>
		<form:input  cssClass="form-control"  path="companyName" required="required" />
	</div>
	
	<div class="form-group">
		<label for="notificationMail">通知邮件</label>
		<form:input  cssClass="form-control"  path="notificationMail" required="required"/>
	</div>
	
	<div class="form-group">
		<button type="submit" class="btn btn-default">更新项目公司信息</button>
	</div>
	
</form:form>