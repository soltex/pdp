<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>



<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active"><a href="/admin/view-admins.jhtml" data-ajax="container" data-history>管理员信息</a></li>
	<li role="presentation" class=""><a href="/admin/add-admin.jhtml" data-ajax="container" data-history>添加管理员</a></li>
</ul>


<div class="table-responsive">
	<table class="table table-hover">
		<thead>
		<tr>
			<th>管理员名称</th>
			<th>管理员密码</th>
			<th>上次登录时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="model" items="${pageInfo.objects }">
		<tr>
			<td>${model.adminName }</td>
			<td>${model.adminPwd }</td>
			<td><c:if test="${model.lastLoginTime != null }"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${model.lastLoginTime }" /></c:if></td>
			<td>
				<a href="/admin/reset-password.jhtml?p=${pageInfo.pageNo }&id=${model.id}&rel=container"  data-ajax="container"  data-todo="ajaxTodo" >密码重置</a>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<ul class="pagination">
	<li><a href="/admin/view-admins.jhtml?p=1" data-ajax="container" >1</a></li>
	<li><a href="/admin/view-admins.jhtml?p=${pageInfo.prePageNo }" data-ajax="container" >«</a></li>
	<c:forEach begin="${pageInfo.rangeOfFirst }" end="${pageInfo.rangeOfEnd }" var="i">
	<c:choose>
		<c:when test="${pageInfo.pageNo eq i }">
			<li class="active"><span>${i } <span class="sr-only">(current)</span></span></li>
		</c:when>
		<c:otherwise>
			<li><a href="/admin/view-admins.jhtml?p=${i }" data-ajax="container" >${i }</a></li>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	<li><a href="/admin/view-admins.jhtml?p=${pageInfo.nextPageNo }" data-ajax="container" >»</a></li>
	<li><a href="/admin/view-admins.jhtml?p=${pageInfo.pages}" data-ajax="container" >${pageInfo.pages }</a></li>
</ul>