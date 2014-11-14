/**
 * 
 */
package cn.com.innodev.pdp.sms;


/**
 * 
 * @author shipeng
 */
public class SMSMainApp {
	
	/**
	 * 	
	 	Hashtable configTable=new Hashtable();
		configTable.put("VCPSERVER",prop.getProperty("VCPSERVER"));
		configTable.put("VCPSVPORT",prop.getProperty("VCPSVPORT"));
		configTable.put("VCPUSERID",prop.getProperty("VCPUSERID"));
		configTable.put("VCPPASSWD",prop.getProperty("VCPPASSWD"));
	   	
	   String PhoneNumber=request.getParameter("PhoneNum");
	   String MsgAbout=request.getParameter("smsAbout");
	   String SendTime=	"0";	 //即时发送	//request.getParameter("dtTime");
	   String MsgContent=request.getParameter("Msg");
	   String type=request.getParameter("apitype");    //通道选择: 0：默认通道； 2：通道2； 3：即时通道
	   SMS smssender=new SMS(configTable);
	   smssender.sendSMS(PhoneNumber,MsgContent,SendTime,type);
	   String sendXml=smssender.getSendXml();
	   Hashtable recTable=smssender.getRespData();
	   String receiveXml=smssender.getRecieveXml();
	   String code=smssender.getCode();
	   String recmsg=smssender.getMsg();
	   session.setAttribute("SmsSendXml", sendXml);
	   session.setAttribute("SmsRecXml", receiveXml);
	 * @param args
	 */
	public static void main(String[] args) {
//		Properties prop = new Properties();
//		try {
//			prop.load(SMSMainApp.class.getResourceAsStream("/VCPConfig.ini"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Hashtable<String,String> configTable=new Hashtable<String,String>();
//		configTable.put("VCPSERVER",prop.getProperty("VCPSERVER"));
//		configTable.put("VCPSVPORT",prop.getProperty("VCPSVPORT"));
//		configTable.put("VCPUSERID",prop.getProperty("VCPUSERID"));
//		configTable.put("VCPPASSWD",prop.getProperty("VCPPASSWD"));
//		SMS sms = new SMS(configTable);
//		try {
//			boolean isok = sms.sendSMS("13426173105","互联物业提醒您,您的验证码为 【4421】","0","3");
//			String receiveXml = sms.getSendXml();
//			String code = sms.getCode();
//			String msg = sms.getMsg();
//			System.out.println(isok);
//			System.out.println(receiveXml);
//			System.out.println(code);
//			System.out.println(msg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/core-application-context.xml","classpath*:META-INF/*-application-context-module.xml");
////		System.out.println(configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, Constants.SMS_Conf.VCPSERVER.getCode()));
//		SMS sms = SMSCreator.getInstance().createSMS("13426173105", "你好啊，呵呵呵呵呵");
//		sms.send(true);
//		System.out.println("发送完毕!");
	}
}
