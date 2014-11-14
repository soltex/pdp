<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.io.*"  import="java.util.*" errorPage=""%>

<%
    String errmess = ""; 
	Hashtable configTable=new Hashtable();
 	String filename=request.getRealPath("")+File.separator+"WEB-INF"+File.separator+"classess"+File.separator+"VCPConfig.ini";
	Properties prop=new Properties();
	File fp = new File(filename);
	if(!fp.exists()){
		errmess = "配置文件"+filename+"不存在!";
	}else{
		prop.load(new FileInputStream(fp));
		fp=null;
	}
	if(errmess.equals("")){
		configTable.put("VCPSERVER",prop.getProperty("VCPSERVER"));
		configTable.put("VCPSVPORT",prop.getProperty("VCPSVPORT"));
		configTable.put("VCPUSERID",prop.getProperty("VCPUSERID"));
		configTable.put("VCPPASSWD",prop.getProperty("VCPPASSWD"));

		String tag=request.getParameter("tag");
		if(tag!=null && tag.equals("1") && !((String)configTable.get("VCPUSERID")).equals("") && !((String)configTable.get("VCPPASSWD")).equals("") ){
			response.sendRedirect("smssend.jsp");   
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口模式演示程序</title>
<link href="../share/api_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-bottom: 0px;
	background-color: #CCCCCC;
}
-->
</style>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" bgcolor="#00314F"><img src="../images/api_01.jpg" width="713" height="87" /></td>
    <td align="left" bgcolor="#00314F">
	<table width="100%" height="80" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="right" style="padding-right:20px;">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td style="color:#FFFFFF;padding-right:20px;" align="right"><strong>时代互联接口模式源程序 Version 2.4</strong></td>
      </tr>
    </table>
	</td>
  </tr>  
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" background="../images/api_03.jpg"></td>
    <td bgcolor="#000000">&nbsp;</td>
  </tr>
  <tr>
    <td width="186" rowspan="2" valign="top" bgcolor="#787878">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="20" bgcolor="#000000"></td>
      </tr>
      <tr>
        <td>
		<!--%@ include file="left.inc.jsp"%-->
		<jsp:include page="left.inc.jsp" flush="true"/>
		</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
  </td>
    <td valign="top" bgcolor="#FFFFFF" style="padding:1px 0px 1px 1px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td align="left" bgcolor="#030504"><img src="../images/api_07.jpg" width="819" height="183" /></td>
		  </tr>
		  <tr>
			<td align="left"><img src="../images/api_08.jpg" width="819" height="12" /></td>
		  </tr>
		</table>
	</td>
  </tr>
  <tr>
    <td valign="top" style="padding:5px 5px 10px 10px;" bgcolor="#FFFFFF">
	<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
	  <tr>
			<td bgcolor="#ECF4F8" class="text_blue14" align="center" ><strong> = 接口模式演示程序使用说明  -SMS测试环境配置- =</strong></td>
	  </tr>
	</table>
  <table width="90%"  border="1" align="center" cellpadding="1" cellspacing="0" bordercolorlight="#FFFFFF" bordercolor="#F5EEE8">
	  <tr>
		<th width="16%" height="75" rowspan="7" bgcolor="#FFFFFF" scope="row">
		 <font color="#000000"><img src="../images/pic_1.jpg" width="66" height="66" hspace="10" vspace="10"/></font>
		 </th>
	</tr>
	 <tr>
	    <td bgcolor="#FFFFFF" scope="row" height="30"><b>配置文件例子：</b></td>
	 </tr>
	  <tr>
	    <td width="23%" bgcolor="#FFFFFF" scope="row" height="30">
		<font color="#FF00FF">VCPSERVER = sms.todaynic.com</font></td>
		<td width="61%">SCP 服务器地址，真实服务器为sms.todaynic.com，测试服务器为testxml.todaynic.com
		<br>使用测试服务器一定要使用测试帐号和密码；使用真实服务器是一定使用真实帐号和密码</td>
	 </tr>
	 <tr>
	    <td bgcolor="#FFFFFF" scope="row" height="30"><font color="#FF00FF">VCPSVPORT = 20002</font></td>
		<td>SCP服务器，在测试环境和真实环境，使用的接口均为20001</td>
	 </tr>
	 <tr>
	    <td bgcolor="#FFFFFF" scope="row" height="45"><font color="#FF00FF">VCPUSERID = ms1166</font></td>
		<td>真实短信帐号或测试帐号，如需要申请帐号请登录<strong><a href="http://www.now.cn/user/login.net?page=../mobile/apidownload.net" target="_blank">控制中心</a></strong>。</td>
	 </tr>
	  <tr>
	    <td bgcolor="#FFFFFF" scope="row" height="30"><font color="#FF00FF">VCPPASSWD = odqymj</font></td>
		<td>真实短信帐号密码或测试帐号密码</td>
	 </tr>
	 <tr>
	    <td colspan="2" height="30"><font color="red"><b>注意：</b></font><br>
								   		<dd>测试服务器，只是一个模拟的环境，用来查看返回信息和调试程序，不会真正发送到手机上，也不会扣除真实帐号上的余额；<br>
								   		<dd>真实服务器，真正发送到手机上并扣除用户余额。<br></td>
	 </tr>
	</table>
	<br> 
	<%if(errmess.equals("")){%>
	<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
	  <tr>
			<td bgcolor="#ECF4F8" class="text_blue14" align="center" ><strong> = 更改配置信息 =</strong></td>
	  </tr>
	</table>
	<table width="90%"  border="1" align="center" cellpadding="1" cellspacing="0" bordercolorlight="#FFFFFF" bordercolor="#F5EEE8">
	<form method="post" action="smsconf_submit.jsp" name="sms">
		<tr>
		<td width="16%" height="75" bgcolor="#FFFFFF" >&nbsp;</td>
		<td ><table border="1" align="center" cellpadding="4" cellspacing="3" bordercolor="#dddddd" width="100%">
          <tr >
            <td width="201" height="19" bgcolor="#eFeFeF"><font color="#44444">SCP服务器地址：</font></td>
            <td colspan="2" width="545" height="19"><select name="server">
                <option value="sms.todaynic.com" <% if(((String)configTable.get("VCPSERVER")).equals("sms.todaynic.com")) out.println("selected");%>>sms.todaynic.com（真实服务器）</option>
                <option value="testxml.todaynic.com" <% if(((String)configTable.get("VCPSERVER")).equals("testxml.todaynic.com")) out.println("selected");%>>testxml.todaynic.com（测试服务器）</option>
              </select>
                <font color="red"> * </font>&nbsp;&nbsp;请选择测试服务器还是真实服务器
              <!--INPUT VALUE="" TYPE="text" NAME="server" CLASS="formstyle" size=20 /-->
            </td>
          </tr>
          <tr >
            <td width="201" height="19" bgcolor="#eFeFeF"><font color="#44444">SCP服务器接口：</font></td>
            <td colspan="2" width="545" height="19"><input value="20002" type="text" name="port" class="formstyle" size=20 />
            </td>
          </tr>
          <tr >
            <td width="201" height="19" bgcolor="#eFeFeF"><font color="#44444">真实短信帐号或测试帐号：</font></td>
            <td colspan="2" width="545" height="19"><input  type="text" name="username" class="formstyle" size=20 value="<%=configTable.get("VCPUSERID")%>"/>
                <font color="red"> * （注意：真实帐号要使用真实的服务器，测试帐号要使用测试服务器）</font> </td>
          </tr>
          <tr>
            <td bgcolor="#eFeFeF"><font color="#44444">真实短信帐号密码或测试帐号密码：</font></td>
            <td colspan="2"><input type="text" name="pass" class="formstyle" size="20" value="<%=configTable.get("VCPPASSWD")%>" />
                <font color="red"> * </font> </td>
          </tr>
        </table>
		  <table border="0" align="center" cellpadding="4" cellspacing="3" bordercolor="#dddddd" width="100%">
			  <tr > 
				<td width="57%" height="19"   align="center">&nbsp;&nbsp;<INPUT TYPE="submit" NAME="Submit2" CLASS="button_orange"  value="确 认" />
				&nbsp;&nbsp;
				<INPUT TYPE="reset" NAME="clear" CLASS="buttonback"  value="重 填" /></td>
				<td width="43%">&nbsp;</td>
			  </tr>
		  	</table>
		  </td>
	  </tr>
	</form>
  </table>
  <% } else { %>
	<table width="90%"  border="1" align="center" cellpadding="1" cellspacing="0" bordercolorlight="#FFFFFF" bordercolor="#F5EEE8">
              <tr>
                <td width="19%" height="75"  bgcolor="#FFFFFF" scope="row"><font color="#000000">
					<img src="../images/failure.jpg" width="100" height="100" hspace="10" vspace="10"><br>
                  </font>
				</td>
				<td><font color='red'><%=errmess%></font></td>
              </tr>
            </table>
	<% } %>
	</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" align="center" bgcolor="#00314F" style="color:#FFFFFF">时代互联 版权所有</td>
  </tr>
</table>
</body>
</html>