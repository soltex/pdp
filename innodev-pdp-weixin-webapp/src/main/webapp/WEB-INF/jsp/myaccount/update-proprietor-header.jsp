<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib uri="http://www.vanstone.com/weixin/tag/1.0" prefix="weixin" %>
<%@ taglib uri="http://www.vanstone.com/imgserver/tag/1.0" prefix="imgserver" %>



<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class=""><a href="/${communityId}/myaccount/update-base-proprietor-info.jhtml?openId=${myAccountForm.openId}" data-ajax="container" data-history>更新基本信息</a></li>
	<li role="presentation" class="active"><a href="/${communityId}/myaccount/update-proprietor-header.jhtml?openId=${myAccountForm.openId}" data-ajax="container" data-history>更新头像信息</a></li>
</ul>

<div class="row">
  <div class="col-xs-6 col-md-3">
  	 <c:if test="${proprietor.headImageBean != null}">
	     <img data-src="<imgserver:url scaleSize="e300x300" fileId="${proprietor.headImageBean.weedFile.fileid}" extName="${image.imageBean.weedFile.extName }"></imgserver:url>" alt="个人头像" />
  	 </c:if>
  </div>
</div>

<form:form action="/${communityId}/myaccount/update-proprietor-header-action.jhtml" method="post" cssClass="required-validate" modelAttribute="myAccountForm" enctype="multipart/form-data" >
	<form:hidden path="openId"  />
	<div class="form-group">
		<label for="email">头像</label> 
		<input type="file" name="headerMultipartFile" class="form-control" />
	</div>
	<button type="submit" class="btn btn-default">更新头像信息</button>
</form:form>

