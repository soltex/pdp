<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib uri="http://www.vanstone.com/weixin/tag/1.0" prefix="weixin" %>


		
<form:form action="/${communityId}/myaccount/mobile-authenticate-action.jhtml" method="post" cssClass="required-validate" modelAttribute="myAccountForm">
	<div class="form-group">
		<label for="openId">手机号</label> 
		<form:input cssClass="form-control" path="mobile"  required="required" />
		<form:hidden path="openId"  />
	</div>
	<div class="form-group">
		<label for="adminName">验证码</label> 
		<form:input cssClass="form-control" path="validatecode"  required="required" /> 
		<a href="javascript:void(0)"  onclick="retrievalValidatecode()" class="btn btn-default">点击发送</a>
	</div>
	<button type="submit" class="btn btn-default">绑定验证</button>
</form:form>

<script type="text/javascript">
	function retrievalValidatecode() {
		var mobileval = $("#mobile").val();
		$.get("/common/retrieval-validatecode/" + mobileval + ".jhtml");
	}
</script>
