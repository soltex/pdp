<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<form:form action="/community/newcommunity-action.jhtml" method="post" cssClass="required-validate" commandName="communityForm">
	
	<div class="form-group">
		<label for="communityName">地区信息</label> 
		<div class="form-inline">
			<div class="form-group">
				<label class="sr-only" for="provinceId">省份</label>
				<select name="provinceIds"  required="required" onchange="selectProvince(this, 'cities_span_id')">
					<option value="">==请选择省份信息==</option>
					<c:forEach var="province" items="${provinces }">
					<option value="${province.id }">${province.title }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<div class="form-group">
					<label class="sr-only" for="cityId">城市</label>
					<span id="cities_span_id"  required="required">
						<select name="cityId" >
							<option>==请选择城市信息==</option>
						</select>
					</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="communityId">社区唯一ID（不能重复,编辑完成不能修改，请慎重填写）</label> 
		<form:input path="communityId" cssClass="form-control" required="required"  />
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
		<label for="qrOfCommunityFsFile">社区微信二维码</label>
		<input type="file" name="qrOfCommunityMultipartFile" required="required"/>
	</div>
	
	<div class="form-group">
		<label for="companyName">工程公司名称</label>
		<form:input  cssClass="form-control"  path="companyName" required="required"/>
	</div>
	
	<div class="form-group">
		<label for="notificationMail">通知邮件</label>
		<form:input  cssClass="form-control"  path="notificationMail" required="required"/>
	</div>
	
	<div class="form-group">
		<button type="submit" class="btn btn-default">新建社区信息</button> <a class="btn btn-danger"  href="/community/view-communities.jhtml" data-ajax="container">取消并返回</a>
	</div>
</form:form>