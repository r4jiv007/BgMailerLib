BgMailerLib 1.0
=====================

Android Library to send Email , can be used to send via background service or thread
It also supports Attachments.
**How to use it**

	There are two ways to use this library ,
	1. If you want to use your own background thread/service 
	   implementation :-

		 Mailer m = new MailerImpl(this);
		 String usrname = "<your email id>";
		 String passwd = "<password>";
		 String[] address = { " <your array of address>" };
		 m.addRecepients(address);
		 m.setUserName(usrname);
		 m.setPassword(passwd);
		 m.setSubject("<Subject>");
		 m.setBody("<Body>");
		 m.addAttachment("<Absolute path to file>", "<filename>");
		 m.send();

	2. If you dont want to implement your thread/service then you can use 
	   MailerBackgroundImpl, which uses Intent service to send mail in 
       background :-

		 Mailer m = new MailerBackgroundImpl(this);
		 String usrname = "<your email id>";
		 String passwd = "<password>";
		 String[] address = { " <your array of address>" };
		 m.addRecepients(address);
		 m.setUserName(usrname);
		 m.setPassword(passwd);
		 m.setSubject("<Subject>");
		 m.setBody("<Body>");
		 m.addAttachment("<Absolute path to file>", "<filename>");
		 m.send();


            
**Manifest**
	    
	    If you are using MailerBackgroundImpl, you have to declare this service 
	    in manifest:-
	    
		    <service android:name="my.bgmailer.lib.MailingService" >
	            <intent-filter>
	                <action android:name="bg.mail.service" />
	            </intent-filter>
	          </service>
			

	    You have to include these permissions :-
            <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
            <uses-permission android:name="android.permission.INTERNET"/>
            
            
**Changelog**

            - version 1.0
            First commit, Initial version

**Credits**

            © 2014-2015 Rajiv Singh     
            
**License**

GNU GENERAL PUBLIC LICENSE Version 2


