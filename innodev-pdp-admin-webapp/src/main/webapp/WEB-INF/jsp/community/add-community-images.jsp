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
	<li role="presentation"  class="active"><a href="/community/add-community-images/${community.id }.jhtml" data-ajax="container"  data-history>社区图片信息</a></li>
	<li role="presentation"  class=""><a href="/community/view-community-images/${community.id}.jhtml" data-ajax="container"  data-history>社区图片</a></li>
	<li role="presentation"  class=""><a href="/community/view-buildings/${community.id}.jhtml" data-ajax="container"  data-history>社区楼号信息</a></li>
</ul>

<form:form action="/community/upload-community-images-action/${community.id }.jhtml" method="post" cssClass="required-validate" enctype="multipart/form-data"  commandName="uploadfilesForm" id="uploadfilesForm">
	<div class="form-group">
		<label for="regionsMultipartFile">多文件选择</label> 
		<input type="file" class="form-control" name="uploadFiles"  required="required" multiple="multiple"/>
		<input type="hidden" name="rel" value="images_region"/>
	</div>
	<button type="submit" class="btn btn-default">批量上传图片文件</button> 
</form:form>


<span id="images_region">
	
</span>

<script type="text/javascript">
	$('#uploadfilesForm').bind(DWZ.eventType.ajaxDone, function(event, json){
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			var htmlstr = "";
			for (var i=0;i<json.params.imageBeans.length;i++) {
				htmlstr = htmlstr + "<div class='col-md-4'><img class='img-rounded' src='http://192.168.1.120:9333/" + json.params.imageBeans[i].weedFile.fileid + "." + json.params.imageBeans[i].weedFile.extName + "' /></div>";
			}
			$("#images_region").html(htmlstr);
		}
	});
</script>
