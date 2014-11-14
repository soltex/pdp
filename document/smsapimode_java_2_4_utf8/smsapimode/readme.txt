时代互联EPP-API接口模式说明
=========================================================================

版权所有 (c) 2000-2008 时代互联  www.todaynic.com

-------------------------------------------------------------------------

本压缩包包括如下内容:

	index.html 是演示入口程序。
	doc  目录下为API接口模式相关的说明文档.
	smsdemo  目录下为jsp程序与API接口模式连接并进行业务开展的演示程序.
	WEB-INF/classess/VCPConfig.ini  为API配置文件
	WEB-INF/lib   为API开发的扩展包。
	images  目录下为演示程序所用到的图片资源．
	share 目录下为演示程序用到的js及css．
	readme.txt  您正在看的文件.
	
关于时代互联SCP体系API接口模式的介绍,请登录www.todaynic.com网站上关于API接口模式部分.

如果您需要测试接口,请登陆到短信平台,下载接口代码，并申请测试帐号和测试密码．

>>>接口模式演示程序系统环境要求：	 
	1. 运行机器系统必须安装有 JRE1.5 (含1.5以上)版本的JAVA运行环境支持。
	2. 服务器或者虚拟主机必须支持jsp (如：Tomcat 、weblogic等)；
	* 另外要调用API(com.todaynic.client.email.Email.class和com.todaynic.client.domain.Domain.class)扩展开发，
    	  您的系统必须安装有JDK1.5 和JRE1.5 (含1.5以上)版本的JAVA开发、运行环境支持。
    	  在开发环境中直接应用、包含: \WEB-INF\lib 目录下的 smsapi.jar、filter.jar和 jdom.jar 包进行开发。

>>>接口模式演示程序配置说明：
	1、用户下载到：smsapimode_java_2_1程序包，解压
	2、解压后，直接把其中的演示程序包 :smsdemo 拷贝到您的服务器或者虚拟主机的web应用执行目录(webapps)中。
	3、请把/WEB-INF/下的文件，拷贝到你服务器或者虚拟主机的/WEB-INF/目录下。
	4、运行演示程序之前必须配置：注意，这一步很关键!!!
	
        * 找到当前路径：/WEB-INF/classess/VCPConfig.ini,打开文件修改： 
	  1 接口服务器 主机：VCPSERVER  (测试环境接口服务器:testxml.todaynic.com )
					(真实环境接口服务器:sms.todaynic.com)
	  2 接口服务器 端口：VCPSVPORT  (测试环境和真实环境，使用的接口均为20002)
	  3 短信用户   帐户：VCPUSERID （如：VCPUSERID=ms1166）	
	  4 短信用户   密码：VCPPASSWD （如：VCPPASSWD=odqymj）
	* 如没有短信帐号、密码，请到http://www.now.cn/mobile 立即申请！开通短信服务，即得帐号、密码！
	* 做完以上操作保存Config.ini。

	注意：
	1】使用时测试帐号时，必须使用测试服务器（testxml.todaynic.com），使用测试帐号时只是一个模拟程序，
用来查看返回信息、调试程序，不会扣除真实帐号上的余额的，手机也不会收到信息。
	2】使用真实帐号时，必须使用真实服务器（sms.todaynic.com），这时才是真正能收发短信。

>>>API接口程序的工作信息流如下:

-------------------               -------------------               -------------------
|                 |               |                 |               |                 |
|客户端jsp 程序   |<------XML---->|   API接口程序   |<------XML---->|    TODAYNIC SCP |
|                 |<------------->|                 |<------------->|      java 核心  |
|                 |               |                 |               |                 |
-------------------               -------------------               -------------------

	
