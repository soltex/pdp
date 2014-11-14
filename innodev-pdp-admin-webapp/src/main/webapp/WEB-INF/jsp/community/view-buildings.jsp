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
	<li role="presentation"  class=""><a href="/community/update-project-company/${community.id }.jhtml" data-ajax="container"  data-history>所属项目公司</a></li>
	<li role="presentation"  class=""><a href="/community/add-community-images/${community.id }.jhtml" data-ajax="container"  data-history>社区图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/view-community-images/${community.id}.jhtml" data-ajax="container"  data-history>社区图片</a></li>
	<li role="presentation"  class="active"><a href="/community/view-buildings/${community.id}.jhtml" data-ajax="container"  data-history>社区楼号信息</a></li>
</ul>

<p></p>
<p><a href="/community/add-building/${community.id}.jhtml" class="btn btn-default" data-ajax="container" >增加楼号</a></p>

<c:forEach var="entry" items="${dataMap }">
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">楼号 ： <a href=""><strong>${entry.key.buildingNo }</strong></a></h3>
	</div>
	<div class="panel-body">
		<div class="row">
		<c:forEach var="room" items="${entry.value }">
			<div class="col-md-1"><a href="">${room.roomNo }</a></div>
		</c:forEach>
		</div>
	</div>
	<div class="panel-footer"><a href="" class="btn btn-info">添加房间号</a></div>
</div>
</c:forEach>