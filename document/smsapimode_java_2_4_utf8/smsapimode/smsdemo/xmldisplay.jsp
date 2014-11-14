<%//@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String reqstr = request.getParameter("tag");
	if(reqstr.equals("sendxml")){
		String smsstr = (String)session.getAttribute("SmsSendXml"); 
		out.println(smsstr);
	}else{
		String smsstr = (String)session.getAttribute("SmsRecXml"); 
		out.println(smsstr);
	}

%>