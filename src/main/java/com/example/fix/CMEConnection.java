package com.example.fix;

import java.util.Properties;

import biz.onixs.fix.dictionary.Version;
import biz.onixs.fix.engine.Engine;
import biz.onixs.fix.engine.Session;
import biz.onixs.fix.parser.Message;
import biz.onixs.fix.tag.Tag;

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
			prop.put("url", "https://servicesnr.cmegroup.com/cmestp/query");
			prop.put("senderCompID", "eka_canada_inc");
			prop.put("senderSubID", "API_EKA");
			prop.put("password", "Enc0mp@ss1");
			prop.put("ProcessCancelledDeals", "TRUE");
			prop.put("SubscribeOrQuery", "Subscribe");
			prop.put("pollingInterval", 60000);
			prop.put("ExecutionFacility", "CME");
			prop.put("DaysInThePastAllowedToQuery", "5");
			prop.put("SslProtocol", "Tls12");
			prop.put("targetCompId", "CME");
			prop.put("LicenseFile", "D:/softwares/jars/OnixS.lic");

			
			if(Engine.isNotInited()) {
				engine.init(prop);
				System.out.println("Engine initiated");
			}
			
			session = new Session(prop.getProperty("senderCompID"), 
					prop.getProperty("targetCompId"), Version.FIX44);
			
			System.out.println("Session : "+ session.toStringDetailed());
			
			
			Message customLogonMessage = createLogon(prop);
			
			
			
			session.logonAsInitiator(prop.getProperty("url"), 
					Integer.parseInt(prop.getProperty("CounterpartyPort")), 30, false, customLogonMessage);

			
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
