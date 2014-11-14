<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>区域信息管理</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation"  class="active"><a href="/basicdata/view-provinces.jhtml" data-ajax="container" data-history>省份信息管理</a></li>
	<li role="presentation" ><a href="/basicdata/view-cities.jhtml" data-ajax="container" data-history>城市信息管理</a></li>
	<li role="presentation" class=""><a href="/basicdata/batch-import-regions.jhtml"" data-ajax="container" data-history>批量信息导入</a></li>
</ul>

<form:form action="/basicdata/add-province-action.jhtml" method="post" cssClass="required-validate">
	<div class="form-group">
		<label for="title">省份名称</label> 
		<input type="text" class="form-control" name="title"  required="required" />
	</div>
	<button type="submit" class="btn btn-default">保存</button>
</form:form>


<div class="table-responsive">
	<table class="table table-hover">
		<thead>
		<tr>
			<th>省份ID</th>
			<th>省份名称</th>
			<th>首字母</th>
			<th>城市数量</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="province" items="${provinces }">
		<tr>
			<td>${province.id }</td>
			<td><a href="/basicdata/view-cities.jhtml?provinceId=${province.id }" data-ajax="container">${province.title }</a></td>
			<td>${province.firstLetter }</td>
			<td>${province.cityCount }</td>
			<td>
			<a href="/basicdata/update-province/${province.id }.jhtml" data-toggle="modal" data-target="#modal-dialog">编辑</a>
			<c:if test="${province.cityCount <=0 }">
				 | 
				<a href="/basicdata/delete-province/${province.id }.jhtml?rel=container"  data-todo="ajaxTodo"  title="确认删除？">删除</a>
			</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

