<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>



<form:form cssClass="form-inline required-validate" role="form" commandName="communityForm" action="/community/view-communities.jhtml" method="get">
	<div class="form-group">
		<label class="sr-only" for="key">关键字</label> 
		<form:input path="key" cssClass="form-control" />
	</div>
	<button type="submit" class="btn btn-default">检索</button> <a class="btn btn-info"  href="/community/new-community.jhtml" data-ajax="container">新建社区</a> 
</form:form>




<div class="table-responsive">
	<table class="table table-hover">
		<thead>
		<tr>
			<th>省份城市</th>
			<th>社区名称</th>
			<th>经纬度</th>
			<th>社区地址</th>
			<th>社区状态</th>
			<th>项目公司名称</th>
			<th>通知邮件</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="model" items="${pageInfo.objects }">
		<tr>
			<td>【${model.city.province.title}】${model.city.title }</td>
			<td>${model.communityName }</td>
			<td>${model.longitude },${model.latitude }</td>
			<td>${model.address }</td>
			<td>${model.infoState.desc }</td>
			<td>${model.projectCompany.companyName }</td>
			<td>${model.projectCompany.companyEmail }</td>
			<td>
				<a href="/community/update-basic-community/${model.id }.jhtml"  data-ajax="container" data-history>编辑</a>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<ul class="pagination">
	<li><a href="/community/view-communities.jhtml?p=1&amp;key=${communityForm.key }" data-ajax="container" >1</a></li>
	<li><a href="/community/view-communities.jhtml?p=${pageInfo.prePageNo }&amp;key=${communityForm.key } " data-ajax="container" >«</a></li>
	<c:forEach begin="${pageInfo.rangeOfFirst }" end="${pageInfo.rangeOfEnd }" var="i">
	<c:choose>
		<c:when test="${pageInfo.pageNo eq i }">
			<li class="active"><span>${i } <span class="sr-only">(current)</span></span></li>
		</c:when>
		<c:otherwise>
			<li><a href="/community/view-communities.jhtml?p=${i }&amp;key=${communityForm.key }" data-ajax="container" >${i }</a></li>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	<li><a href="/community/view-communities.jhtml?p=${pageInfo.nextPageNo }&amp;key=${communityForm.key }" data-ajax="container" >»</a></li>
	<li><a href="/community/view-communities.jhtml?p=${pageInfo.pages}&amp;key=${communityForm.key }" data-ajax="container" >${pageInfo.pages }</a></li>
</ul>