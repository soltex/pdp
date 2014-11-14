<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="table-responsive">
	<table class="table table-hover">
		<thead>
		<tr>
			<th>【模块】日志类型</th>
			<th>日志级别</th>
			<th>日志内容</th>
			<th>运行时参数</th>
			<th>请求参数</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="model" items="${pageInfo.objects }">
		<tr>
			<td>【${model.globalLogType.sysModule.desc}】${model.globalLogType.desc }</td>
			<td>${model.logLevel.desc }</td>
			<td>${model.logContent }</td>
			<td>${model.runtimeParams }</td>
			<td>${model.requestParams }</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<ul class="pagination">
	<li><a href="/syslog/search-syslogs.jhtml?p=1&amp;key=" data-ajax="container" >1</a></li>
	<li><a href="/syslog/search-syslogs.jhtml?p=${pageInfo.prePageNo }&amp;key= " data-ajax="container" >«</a></li>
	<c:forEach begin="${pageInfo.rangeOfFirst }" end="${pageInfo.rangeOfEnd }" var="i">
	<c:choose>
		<c:when test="${pageInfo.pageNo eq i }">
			<li class="active"><span>${i } <span class="sr-only">(current)</span></span></li>
		</c:when>
		<c:otherwise>
			<li><a href="/syslog/search-syslogs.jhtml?p=${i }&amp;key=" data-ajax="container" >${i }</a></li>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	<li><a href="/syslog/search-syslogs.jhtml?p=${pageInfo.nextPageNo }&amp;key=" data-ajax="container" >»</a></li>
	<li><a href="/syslog/search-syslogs.jhtml?p=${pageInfo.pages}&amp;key=" data-ajax="container" >${pageInfo.pages }</a></li>
</ul>