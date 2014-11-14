<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib uri="http://www.vanstone.com/weixin/tag/1.0" prefix="weixin" %>


		
<form:form action="/${communityId}/myaccount/room-authenticate-action.jhtml" method="post" cssClass="required-validate" modelAttribute="myAccountForm">
	
	<div class="row">
			
			<c:forEach var="entry" items="${buildingRoomsMap }">
				<div class="col-md-3">
					<p>楼号 ：${entry.key.buildingNo }</p>
					<form:select path="roomIds" multiple="true" size="50" >
						<c:forEach var="room" items="${entry.value }">
							<form:option value="${room.id}">${room.roomNo }</form:option>	
						</c:forEach>
					</form:select>
				</div>
			</c:forEach>
			<form:hidden path="openId" />
	</div>
	<p>
		<button type="submit" class="btn btn-default">绑定房间号</button>
	</p>
</form:form>
