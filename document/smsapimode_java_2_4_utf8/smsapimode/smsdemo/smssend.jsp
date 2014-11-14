<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<SCRIPT LANGUAGE="JavaScript" type="text/javascript" src="../share/sms.js"></SCRIPT>
<SCRIPT TYPE="text/javascript">
var APILength = new Array();
APILength[1] = 62;
APILength[2] = 56;
APILength[3] = 50;
APILength[4] = 56;
maxlen = 50;
function changeAPIType(type)
{
	maxlen = APILength[type];
	document.getElementById("MaxLen").innerHTML=maxlen;
}
</SCRIPT>
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
					<td bgcolor="#ECF4F8" class="text_blue14" align="center" ><strong> = 接口模式演示程序使用说明  -SMS短信模拟发送- =</strong></td>
				</tr>
			</table>
			<table width="90%"  border="1" align="center" cellpadding="1" cellspacing="0" bordercolorlight="#FFFFFF" bordercolor="#F5EEE8">
				<tr>
					<td width="16%" height="75" rowspan="4" bgcolor="#FFFFFF" scope="row"><font color="#000000"> <img src="../images/pic_1.jpg" width="66" height="66" hspace="10" vspace="10"><br>
						</font></td>
					<td width="84%" height="24" bgcolor="#FFFFFF" scope="row"> 发送 SMS 短信：<font color="#000000">&nbsp; </font></td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" scope="row">请输入要发送的手机号码以及短信内容</td>
				</tr>
			</table>
			<table width="90%" border="0" align="center"  cellpadding="2">
				<form id="sendForm" action="smssend_submit.jsp" method="post" onSubmit="return CheckForm(this)" >
					<tr>
						<td width="10%" valign="middle" align="right"><img src="../images/dot_arrow.gif" align="absmiddle">接收号码：</td>
						<td align="left" width="39%" valign="top">
							<table border="0">
								<tr>
									<td valign="middle">
										<textarea id="textphone" name="PhoneNum" class="formstyle" cols="55" rows="6"></textarea>
									</td>
								</tr>
							</table>
						</td>
						<td width="51%" align="left" valign="middle" style="line-height:20px">
							<font color="#FF0000">*</font>发送多个手机号码请用逗号“,”分开，如：13700000000,13900000000 </td>
					</tr>
					<tr>
						<td  valign="middle" align="right"><img src="../images/dot_arrow.gif" align="absmiddle">短信内容：</td>
						<td valign="middle">
							<table border="0">
								<tr>
									<td valign="middle">
										<div> 您已写了
											<input readonly value="0" type="text" name="chrLen" id="chrLen" class="formstyle" size=3 maxlength=3/>
											个字数，共
											<input readonly value="0" type="text" name="smsLen" class="formstyle" size=1 maxlength=1 />
											条短信，当前通道允许<span id="MaxLen" style="color:#FF6600">50</span>字/条</div>
										<div>
											<textarea name="Msg" id="Msg" wrap=physical class="formstyle" cols="55" rows="6" onKeyUp="smsCount(this.form,1);this.pos=document.selection.createRange();" onChange="smsCount(this.form,1);" onSelect="this.pos=document.selection.createRange();" onClick="this.pos=document.selection.createRange();"></textarea>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td align="left" valign="middle" style="line-height:20px">&nbsp;<font color="#FF0000">*</font>每条短信字数与所选通道有关，一次输入累计不能超过3条。</td>
					</tr>
					<tr>
						<td height="20" valign="middle" align="right"><img src="../images/dot_arrow.gif" align="absmiddle">发送通道：</td>
						<td valign="middle">
							<div>
								<select name="apitype" onChange="changeAPIType(this.value);";>
									<option value="2" > 通道二 (发送1条扣去1条)</option>
									<option value="3" selected> 即时通道(客服类推荐) (发送1条扣去1.3条)</option> 
								</select>
							</div>
						</td>
						<td align="left" valign="middle" >
						 <br>
							<table width="100%" border="1" cellspacing="0" cellpadding="1" bordercolor="#CCCCCC">
								<tr>
									<td>通道一和通道二：会对短信内容进行严格审核，不允许发送含有任何营销内容的短信。</td>
								</tr>
								<tr>
									<td ><font color="#FF6600">即时通道：专为需即时发送的客户开辟的绿色通道，发送速度很快，支持回复短信。</font></td>
								</tr>
								<tr>
									<td >营销通道：专为发送合法营销广告的客户开辟的通道，成功率较高。</td>
								</tr>
								<tr>
									<td ><font color="#FF0000">注：在发送高峰期，通道接口可能会出现涌堵现象，这时建议您更换通道来发送。</font></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="44" colspan="2" align="center"  >
							<input type="submit" name="Submit" class="button_orange"  value="  发 送" />
						</td>
						<td height="44" align="center" >&nbsp;&nbsp; </td>
					</tr>
				</form>
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
