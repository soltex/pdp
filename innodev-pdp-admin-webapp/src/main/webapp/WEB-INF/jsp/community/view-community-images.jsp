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
	<li role="presentation"  class=""><a href="/community/update-community-qr/${community.id }.jhtml" data-ajax="container"  data-history>社区二维码图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/update-project-company/${community.id }.jhtml" data-ajax="container"  data-history>所属项目公司</a></li>
	<li role="presentation"  class=""><a href="/community/add-community-images/${community.id }.jhtml" data-ajax="container"  data-history>社区图片信息</a></li>
	<li role="presentation"  class="active"><a href="/community/view-community-images/${community.id}.jhtml" data-ajax="container"  data-history>社区图片</a></li>
	<li role="presentation"  class=""><a href="/community/view-buildings/${community.id}.jhtml" data-ajax="container"  data-history>社区楼号信息</a></li>
</ul>

<p></p>

<c:forEach var="entry" items="${dataMap }">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">${entry.key.desc }</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<c:forEach var="image" items="${entry.value }">
					<div class="col-md-3 col-lg-2">
						<p><img src="/styles/images/progressBar/progressBar_m.gif" data-original='<imgserver:url scaleSize="e300x300" fileId="${image.imageBean.weedFile.fileid}" extName="${image.imageBean.weedFile.extName }"></imgserver:url>' class="img-thumbnail" /></p>
						<p>${image.title }</p>
						<p><a href="/community/delete-community-image/${ community.id}/${image.id }.jhtml?rel=container" class="btn btn-danger" data-todo="ajaxTodo"  title="确认删除？">删除</a></p>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</c:forEach>