<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.vanstone.com/imgserver/tag/1.0" prefix="imgserver" %>


<h1>社区信息-${community.communityName }【${community.id}】</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation"  class=""><a href="/community/update-basic-community/${community.id }.jhtml" data-ajax="container" data-history>社区基本信息</a></li>
	<li role="presentation"  class="active"><a href="/community/update-community-qr/${community.id }.jhtml" data-ajax="container"  data-history>社区二维码图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/update-project-company/${community.id }.jhtml" data-ajax="container"  data-history>所属项目公司</a></li>
	<li role="presentation"  class=""><a href="/community/add-community-images/${community.id }.jhtml" data-ajax="container"  data-history>社区图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/view-community-images/${community.id}.jhtml" data-ajax="container"  data-history>社区图片</a></li>
	<li role="presentation"  class=""><a href="/community/view-buildings/${community.id}.jhtml" data-ajax="container"  data-history>社区楼号信息</a></li>
</ul>


<form:form action="/community/update-community-qr-action/${community.id }.jhtml" method="post" cssClass="required-validate" commandName="communityForm">

	<div class="form-group">
		<div class="form-inline">
			<div class="form-group">
				<c:if test="${community.qrImageBean != null }">
					<img src="<imgserver:url scaleSize="e300x300" fileId="${community.qrImageBean.weedFile.fileid }" extName="${community.qrImageBean.weedFile.extName}"></imgserver:url>" class="img-thumbnail" alt="社区二维码图片" width="200px" />
				</c:if>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="form-group">
			<div class="form-group">
				<label for="qrOfCommunityFsFile">社区微信二维码</label>
				<input type="file" name="qrOfCommunityMultipartFile" required="required" class="form-control" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-default">修改社区二维码信息</button> 
		<c:if test="${community.qrImageBean != null }">
			<a href="/community/delete-community-qr/${community.id }.jhtml" class="btn btn-danger"  data-todo="ajaxTodo"  title="确认删除？">删除二维码图片</a>
		</c:if>
		<a class="btn btn-info"  href="/community/view-communities.jhtml" data-ajax="container" >返回社区列表</a>
	</div>
</form:form>
