package com.example.fix;


import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import biz.onixs.fix.dictionary.Version;
import biz.onixs.fix.engine.Engine;
import biz.onixs.fix.engine.FirstMessageNotLogonException;
import biz.onixs.fix.engine.Session;
import biz.onixs.fix.engine.Session.InboundApplicationMessageArgs;
import biz.onixs.fix.engine.Session.InboundApplicationMessageListener;
import biz.onixs.fix.engine.Session.StateChangeArgs;
import biz.onixs.fix.engine.SessionState;
import biz.onixs.fix.parser.Message;
import biz.onixs.fix.tag.FIX44;
import biz.onixs.fix.tag.FIX44.Tag;

public class Main {

	public static void main(String[] args) {
		new Main().start();
	}

	public void start() {
		Engine engine = null;
		Session session = null;
		try {
			Properties properties = new Properties();

			properties.put("SocketConnectHost", "192.214.97.195");
			properties.put("SocketConnectPort", "41811");
			properties.put("ConnectionRetries.Interval", 1000);
			properties.put("LicenseFile", "D:/softwares/jars/OnixS.lic");
			
			
			properties.put("SenderCompID", "EKAPLUS");
			properties.put("TargetCompID", "REDITKTS");
			
			properties.put("OnbehalfOfCompID", "REDI");
			properties.put("OnbehalfOfSubID", "woner1");

			engine = Engine.init(properties);
			
			Message msg1 = Message.create("A", Version.FIX44);

			session = createSession(properties);

			session.resetLocalSequenceNumbers();
			
			session.setInSeqNum(7);
			
			session.setOutSeqNum(5);
			
			session.setInboundApplicationMessageListener(new InboundApplicationMessageListener() {
				@Override
				public void onInboundApplicationMessage(Object sender, InboundApplicationMessageArgs args) {
					Message rawMessage = args.getMsg();
					System.out.println(rawMessage);
				}
			});

			session.addStateChangeListener(new Session.StateChangeListener() {
				@Override
				public void onStateChange(Object arg0, StateChangeArgs arg1) {
					System.out.println(arg1.getNewState());
				}
			});
			
			System.out.println(session.getInSeqNum());
			System.out.println(session.getOutSeqNum());

			Message customLogonMessage = createLogon(properties);
			session.logonAsInitiator(properties.getProperty("SocketConnectHost"), Integer.parseInt(properties.getProperty("SocketConnectPort")), 30, customLogonMessage);
			
			System.out.println(session.getInSeqNum());
			System.out.println(session.getOutSeqNum());
			
			System.out.println("Press Enter to quit.");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.logout("The session is disconnected by the initiator.");
				session.dispose();
			}
			if(engine != null) {
				engine.shutdown();
			}
		}
	}

	private Session createSession(Properties properties) throws Exception {
		Session session = new Session(properties.getProperty("SenderCompID"), properties.getProperty("TargetCompID"), Version.getById("FIX.4.4"));
		return session;
	}

	private Message createLogon(Properties properties) {
		Message customLogonMessage = Message.create("A", Version.getById("FIX.4.4"));
		customLogonMessage.set(Tag.SenderCompID, properties.getProperty("SenderCompID"));
		customLogonMessage.set(Tag.TargetCompID, properties.getProperty("TargetCompID"));
		customLogonMessage.set(Tag.RawData, "pass");
		customLogonMessage.set(Tag.EncryptMethod, "0");
		customLogonMessage.set(Tag.ResetSeqNumFlag, "Y");
		
		customLogonMessage.set(115, properties.getProperty("OnbehalfOfCompID"));
		customLogonMessage.set(116, properties.getProperty("OnbehalfOfSubID"));
		
		return customLogonMessage;
	}

}
