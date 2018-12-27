package com.example.fix;

import java.net.URI;
import java.util.Properties;

import biz.onixs.fix.dictionary.Version;
import biz.onixs.fix.engine.Engine;
import biz.onixs.fix.engine.Session;
import biz.onixs.fix.parser.Message;
import biz.onixs.fix.tag.Tag;
import biz.onixs.util.settings.PropertyBasedSettings;

public class CMEConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testConnection();
	
	}
	
	public static void testConnection() {
		Engine engine = null;
		Session session = null;
		Properties prop = new Properties();
		try {
			
			
			
			prop.put("uri", new URI ("https://servicesnr.cmegroup.com/cmestp/query"));
			prop.put("senderCompID", "eka_canada_inc");
			prop.put("senderSubID", "API_EKA");
			prop.put("password", "Enc0mp@ss1");
			prop.put("ProcessCancelledDeals", "TRUE");
			prop.put("SubscribeOrQuery", "Subscribe");
			prop.put("pollingInterval", 60000);
			prop.put("ExecutionFacility", "CME");
			prop.put("DaysInThePastAllowedToQuery", "5");
			prop.put("SslProtocol", "Tls12");
			prop.put("targetCompId", "CMESTPFIX2");
			prop.put("LicenseFile", "D:/softwares/jars/OnixS.lic");
			prop.put("MsgSeqNum", 1);
			prop.put("SendingTime", System.currentTimeMillis());
			prop.put("EncryptMethod",0);
			prop.put("HeartBtInt",30);
			prop.put("ResetSeqNumFlag",'Y');
			prop.put("port","");
			//prop.put("host","");
			

			
			if(Engine.isNotInited()) {
				engine.init(prop);
				System.out.println("Engine initiated");
			}
			
			/*session = new Session(prop.getProperty("senderCompID"), 
					prop.getProperty("targetCompId"), Version.FIX44);*/
			
			final String url = "https://servicesnr.cmegroup.com/cmestp/query";
			final String senderCompId = "SenderCompId";
			final String senderSubId = "SenderSubId";
			final String password = "Password";

			final Session session1 = new Session(new URL(url), senderCompId, senderSubId, password);
			
			
			Message customLogonMessage = createLogon(prop);
			
			session.logonAsInitiator(prop.getProperty("url"), 0,
					30, false, customLogonMessage);

			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Message createLogon(Properties properties) {
		Message customLogonMessage = Message.create("A", Version.FIX44);
		customLogonMessage.set(Tag.Username, properties.getProperty("senderSubID"));
		customLogonMessage.set(Tag.Password, properties.getProperty("password"));
		//customLogonMessage.set(Tag.EncryptMethod, "0");
		customLogonMessage.set(Tag.SenderSubID, properties.getProperty("senderSubID"));
		//customLogonMessage.set(Tag.ResetSeqNumFlag, "Y");
		//customLogonMessage.set(Ice.Tag.UserType, "1");
		//customLogonMessage.set(Ice.Tag.StrategyPreference, 1);
		return customLogonMessage;
	}

}
