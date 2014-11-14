<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="java.io.*" import="com.todaynic.client.mobile.*" import="com.todaynic.client.mobile.*" errorPage=""%>

<%   
 	String filename=request.getRealPath("")+File.separator+"WEB-INF"+File.separator+"classess"+File.separator+"VCPConfig.ini";
	//out.println(filename);
	Properties prop=new Properties();
	try{
		File fp = new File(filename);
		if(!fp.exists()){
			out.println("读取不到配置文件"+filename);
			out.close();
		}
		prop.load(new FileInputStream(fp));
		fp=null;
	}catch(Exception e){
		out.println("read file error");
	}
	Hashtable configTable=new Hashtable();
	configTable.put("VCPSERVER",prop.getProperty("VCPSERVER"));
	configTable.put("VCPSVPORT",prop.getProperty("VCPSVPORT"));
	configTable.put("VCPUSERID",prop.getProperty("VCPUSERID"));
	configTable.put("VCPPASSWD",prop.getProperty("VCPPASSWD"));

 
   
   SMS smssender=new SMS(configTable);
   smssender.readSMS();
   String sendXml=smssender.getSendXml();
   //Hashtable recTable=smssender.getRespData();
   String receiveXml=smssender.getRecieveXml();
   String code=smssender.getCode();
   String recmsg=smssender.getMsg();
   session.setAttribute("SmsSendXml", sendXml);
   session.setAttribute("SmsRecXml", receiveXml);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口模式演示程序</title>
<link href="../share/api_style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" bgcolor="#00314F"><img src="../images/api_01.jpg" width="713" height="87" /></td>
    <td align="left" bgcolor="#00314F"><table width="100%" height="80" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="right" style="padding-right:20px;">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td style="color:#FFFFFF;padding-right:20px;" align="right"><strong>时代互联接口模式源程序 Version 2.4</strong></td>
      </tr>
    </table></td>
  </tr>  
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" background="../images/api_03.jpg"></td>
    <td bgcolor="#000000">&nbsp;</td>
  </tr>
  <tr>
    <td width="186" rowspan="2" valign="top" bgcolor="#787878"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="20" bgcolor="#000000"></td>
      </tr>
      <tr>
        <td><jsp:include page="left.inc.jsp" flush="true"/></td>
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
	<table width="100%"  border="1" align="center" cellpadding="1" cellspacing="0" bordercolorlight="#FFFFFF" bordercolor="#ECF4F8" style="margin-top:15px;">
      <tr>
        <td scope="row">
			<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
			  <tr>
					<td bgcolor="#ECF4F8" class="text_blue14" align="center"><strong> = 接口模式演示程序使用说明  -回复短信查询- =</strong></td>
			  </tr>
			</table>
          <table width="90%"  border="1" align="center" cellpadding="1" cellspacing="0" bordercolorlight="#FFFFFF" bordercolor="#F5EEE8">
              <tr>
                <td width="16%" height="75" rowspan="5" bgcolor="#FFFFFF" scope="row"><font color="#000000">
				<img src="../images/pic_1.jpg" width="66" height="66" hspace="10" vspace="10"><br>
                </font></td>
                <td width="84%" height="24" bgcolor="#FFFFFF" scope="row"> 返回结果说明：<font color="#000000">&nbsp;                </font></td>
              </tr>
              <tr>
                <td height="23" bgcolor="#FFFFFF" scope="row"><font color="#000000"> 状态：
				<%=code%>
				 </font></td>
              </tr>
              <tr>
                <td height="24" bgcolor="#FFFFFF" scope="row"> 信息：<font color="#000000"><%=recmsg%></font></td>
              </tr>
              <tr>
                <td height="24" bgcolor="#FFFFFF" scope="row" > 注：<font color="#000000">每次查询只能取到一条回复短信，多次查询时间间隔要大于五秒。</font></td>
              </tr>
             </table>
              <table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th height="29" align="left" scope="row">
				  发送的XML信息如下：
                  </th>
                </tr>
                <tr>
                  <th height="17" align="center" scope="row">
				    <iframe name="sendXML"  src="xmldisplay.jsp?tag=sendxml" class="formstyle" width="100%" height="250"></iframe>
				  </th>
                </tr>
                <tr>
                  <th height="27" align="left" scope="row">返回的XML信息如下:</th>
                </tr>
                <tr>
                  <th align="center" scope="row">
				  	<iframe name="respXML" src="xmldisplay.jsp?tag=resxml" class="formstyle" width="100%" height="250"></iframe>
				  </th>
                </tr>
            </table>
			<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td height="29" align="center">
			 <input name="next" type="button" value="进行发送短信" onClick="location.href='smssend.jsp'" class="button_orange">
			&nbsp;&nbsp;&nbsp;
			<!--input name="return" type="button" value="上一步" class="buttonback" onClick="location.href='sms_.php'"-->
			  </td>
			</tr>
		</table>
          </td>
      </tr>
    </table>

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
