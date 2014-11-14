<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.vanstone.com/imgserver/tag/1.0" prefix="imgserver" %>

callback result : <br />
code : ${callbackResult.code} <br />
state : ${callbackResult.state} <br />

====================================

accessTokenResult : <br />
accessToken : ${accessTokenResult.accessToken} <br />
expireIn : ${accessTokenResult.expireIn}  <br />
refreshToken : ${accessTokenResult.refreshToken}  <br />
openId : ${accessTokenResult.openId}  <br />
scope : ${accessTokenResult.scope}  <br />