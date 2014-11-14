<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib uri="http://www.vanstone.com/weixin/tag/1.0" prefix="weixin" %>



	
	
	
<div class="bs-glyphicons">
	<a class="btn btn-default btn-lg" href="/${communityId }/myaccount/mobile-authenticate.jhtml?openId=${openId}"  data-ajax="container" ajax-history>
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">手机绑定</span>
	</a>
	<a class="btn btn-default btn-lg" href="/${communityId }/myaccount/room-authenticate.jhtml?openId=${openId}"  data-ajax="container" ajax-history>
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">房间号绑定</span>
	</a>
	<a class="btn btn-default btn-lg" href="/${communityId }/myaccount//update-base-proprietor-info.jhtml?openId=${openId}" data-ajax="container" ajax-history >
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">个人信息</span>
	</a>
	<a class="btn btn-default btn-lg" href="" >
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">申请访客二维码</span>
	</a>
	<a class="btn btn-default btn-lg" href="" >
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">申请报修</span>
	</a>
	<a class="btn btn-default btn-lg" href="" >
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">社区公告</span>
	</a>
	<a class="btn btn-default btn-lg" href="" >
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">常用知识</span>
	</a>
	<a class="btn btn-default btn-lg" href="" >
		<span class="glyphicon glyphicon-asterisk"></span>
		<span class="glyphicon-class">关于社区</span>
	</a>
</div>
	

