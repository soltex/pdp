<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.vanstone.com/imgserver/tag/1.0" prefix="imgserver" %>

<p></p>
<hr />
<p></p>
<div class="panel panel-default">
	<div class="panel-heading">
    	<h3 class="panel-title">请编辑上传图片信息，点击【保存图片信息】按钮进行图片信息保存</h3>
  	</div>
	<div class="panel-body">
		<form:form action="/community/add-community-images-action/${community.id }.jhtml" method="post" cssClass="required-validate" enctype="multipart/form-data"  commandName="uploadfilesForm" id="uploadfilesForm">
			<div class="row">
				<c:forEach var="imageBean" items="${imageBeans}" varStatus="status">
				<div class="col-md-3 ">
					<p><img src="/styles/images/progressBar/progressBar_m.gif" data-original='<imgserver:url scaleSize="e300x300" fileId="${imageBean.weedFile.fileid}" extName="${imageBean.weedFile.extName }"></imgserver:url>' class="img-thumbnail" /></p>
					<p>
					<select name="albumTypes[${status.index }]"  required="required">
						<option value="">=请选择相册类型=</option>
						<c:forEach var="albumType" items="${albumTypes }">
							<option value="${albumType.code }">${albumType.desc }</option>
						</c:forEach>
					</select>
					<input type="hidden" name="fids[${status.index }]" value="${imageBean.weedFile.fileid }"/>
					<input type="hidden" name="extNames[${status.index }]" value="${imageBean.weedFile.extName }"/>
						<select name="floorPlanTypes[${status.index }]" >
							<option value="">=请选择户型图=</option>
							<c:forEach var="floorPlanType" items="${floorPlanTypes }">
								<option value="${floorPlanType.code }">${floorPlanType.desc }</option>
							</c:forEach>
						</select>
					</p>
					<p>
						<input class="form-control" name="titles[${status.index }]" placeholder="请填写图片描述文字.." />
					</p>
					<p><button type="button" class="btn btn-danger"  onclick="deleteRegion(this)">删除</button></p>
				</div>
				</c:forEach>
			</div>
			<div class="panel-footer"><button type="submit" class="btn btn-primary" >保存图片信息</button></div>
		</form:form>
	</div>
</div>

