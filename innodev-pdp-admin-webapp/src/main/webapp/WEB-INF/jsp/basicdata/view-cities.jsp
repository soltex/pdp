<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>区域信息管理</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation"  ><a href="/basicdata/view-provinces.jhtml" data-ajax="container" data-history>省份信息管理</a></li>
	<li role="presentation"  class="active"><a href="/basicdata/view-cities.jhtml" data-ajax="container" data-history>城市信息管理</a></li>
	<li role="presentation" class=""><a href="/basicdata/batch-import-regions.jhtml"" data-ajax="container" data-history>批量信息导入</a></li>
</ul>

<form:form action="/basicdata/add-city-action.jhtml" method="post" cssClass="required-validate">
	<div class="form-group">
		<label for="provinceId">省份名称</label>
		<select class="form-control" name="provinceId">
			<c:forEach var="model" items="${provinces}">
				<c:choose>
					<c:when test="${province.id eq model.id }">
						<option value="${model.id }" selected="selected">${model.title }</option>
					</c:when>
					<c:otherwise>
						<option value="${model.id }">${model.title }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> 
	</div>
	<div class="form-group">
		<label for="title">城市名称</label> 
		<input type="text" class="form-control" name="title"  required="required" />
	</div>
	<button type="submit" class="btn btn-default">保存</button>
</form:form>

<h5></h5>
<div class="table-responsive">
	<table class="table table-hover">
		<thead>
		<tr>
			<th>城市ID</th>
			<th>省份名称</th>
			<th>城市名称</th>
			<th>首字母</th>
			<th>旗下社区数量</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="city" items="${cities }">
		<tr>
			<td>${city.id }</td>
			<td><a href="/basicdata/view-cities.jhtml?provinceId=${city.province.id }" data-ajax="container">${city.province.title }</a></td>
			<td>${city.title }</td>
			<td>${city.firstLetter }</td>
			<td>${city.communityCount }</td>
			<td>
			<a href="/basicdata/update-city/${city.id }.jhtml">编辑</a>
			<c:if test="${city.communityCount <=0 }">
				 | <a href="/basicdata/delete-city/${city.id }.jhtml">删除</a>
			</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

