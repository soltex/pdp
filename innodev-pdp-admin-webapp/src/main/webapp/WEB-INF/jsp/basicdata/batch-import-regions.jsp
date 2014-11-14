<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h1>区域信息管理</h1>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation"  ><a href="/basicdata/view-provinces.jhtml" data-ajax="container" data-history>省份信息管理</a></li>
	<li role="presentation"  class=""><a href="/basicdata/view-cities.jhtml" data-ajax="container" data-history>城市信息管理</a></li>
	<li role="presentation" class="active"><a href="/basicdata/batch-import-regions.jhtml"" data-ajax="container" data-history>批量信息导入</a></li>
</ul>

<form:form action="/basicdata/batch-import-regions-action.jhtml" method="post" cssClass="required-validate" enctype="multipart/form-data">
	<div class="form-group">
		<label for="regionsMultipartFile">区域数据文件（请参考以下Excel模板文件）</label> 
		<input type="file" class="form-control" name="regionsMultipartFile"  required="required" />
	</div>
	<button type="submit" class="btn btn-default">保存</button> &nbsp; 
	<a href="/region-template.xls" target="_blank">区域信息数据模板下载</a>
</form:form>

