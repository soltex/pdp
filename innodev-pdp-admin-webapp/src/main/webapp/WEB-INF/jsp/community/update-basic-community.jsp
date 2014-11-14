<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>社区信息-${community.communityName }【${community.id}】</h1>
<ul class="nav nav-tabs" role="tablist">
	<li role="presentation"  class="active"><a href="/community/update-basic-community/${community.id }.jhtml" data-ajax="container" data-history>社区基本信息</a></li>
	<li role="presentation"  class=""><a href="/community/update-community-qr/${community.id }.jhtml" data-ajax="container"  data-history>社区二维码图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/update-project-company/${community.id }.jhtml" data-ajax="container"  data-history>所属项目公司</a></li>
	<li role="presentation"  class=""><a href="/community/add-community-images/${community.id }.jhtml" data-ajax="container"  data-history>社区图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/view-community-images/${community.id}.jhtml" data-ajax="container"  data-history>社区图片</a></li>
	<li role="presentation"  class=""><a href="/community/view-buildings/${community.id}.jhtml" data-ajax="container"  data-history>社区楼号信息</a></li>
</ul>

<form:form action="/community/update-basic-community-action/${community.id }.jhtml" method="post" cssClass="required-validate" commandName="communityForm">
	
	<div class="form-group">
		<label for="communityName">地区信息</label> 
		<div class="form-inline">
			<div class="form-group">
				<label class="sr-only" for="provinceId">省份</label>
				<form:select path="provinceId"  required="required" onchange="selectProvince(this, 'cities_span_id')">
					<form:option value="">==请选择省份信息==</form:option>
					<c:forEach var="province" items="${provinces }">
					<form:option value="${province.id }">${province.title }</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<div class="form-group">
					<label class="sr-only" for="cityId">城市</label>
					<span id="cities_span_id"  required="required">
						<form:select path="cityId" >
							<option>==请选择城市信息==</option>
							<c:forEach var="city" items="${cities }">
							<form:option value="${city.id }">${city.title }</form:option>
							</c:forEach>
						</form:select>
					</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="communityName">社区名称</label> 
		<form:input cssClass="form-control" path="communityName"  required="required"  />
	</div>
	
	<div class="form-group">
		<label for="longitude">经纬度</label> 
		<div class="form-inline">
			<div class="form-group">
				<label class="sr-only" for="longitude">longitude</label>
				<form:input  cssClass="form-control" path="longitude" placeholder="经度" />
			</div>
			<div class="form-group">
				<div class="form-group">
					<label class="sr-only" for="latitude">latitude</label>
					<form:input  cssClass="form-control" path="latitude" placeholder="维度" />
				</div>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="content">社区介绍</label>
		<form:textarea path="content" cssClass="form-control" rows="5" />
	</div>
	<div class="form-group">
		<label for="avgPrice">平均价格</label>
		<form:input  cssClass="form-control"  path="avgPrice" />
	</div>
	<div class="form-group">
		<label for="avgPrice">社区地址</label>
		<form:input  cssClass="form-control"  path="address" />
	</div>
	
	<div class="form-group">
		<button type="submit" class="btn btn-default">编辑社区基本信息</button>
	</div>
</form:form>

