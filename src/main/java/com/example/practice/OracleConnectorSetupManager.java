package com.example.practice;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.TransMeta;
import org.springframework.stereotype.Service;

public class OracleConnectorSetupManager {

	private static Database database;
	
	private static final String FORCE_IDENTIFIERS_TO_LOWERCASE = "FORCE_IDENTIFIERS_TO_LOWERCASE";
	private static final String FORCE_IDENTIFIERS_TO_UPPERCASE = "FORCE_IDENTIFIERS_TO_UPPERCASE";
	private static final String QUOTE_ALL_FIELDS = "QUOTE_ALL_FIELDS";
	private static final String PRESERVE_RESERVED_WORD_CASE = "PRESERVE_RESERVED_WORD_CASE";
	private static final String SUPPORTS_BOOLEAN_DATA_TYPE = "SUPPORTS_BOOLEAN_DATA_TYPE";
	private static final String SUPPORTS_TIMESTAMP_DATA_TYPE = "SUPPORTS_TIMESTAMP_DATA_TYPE";
	private static final String PORT_NUMBER = "PORT_NUMBER";
	
	public static void main(String[] args) {
		System.out.println("testConnection response: " + testConnection());

	}
	
	public static String testConnection() {
		try {
			
			JSONObject form = new JSONObject("{\"password\":\"root\",\"database\":\"localdbsql\",\"instance_name\":\"\",\"port\":\"3306\",\"name\":\"CTRM Connector\",\"host\":\"127.0.0.1\",\"id\":\"682\",\"username\":\"root\"}");
			String instanceName=form.getString("instance_name");
			String host=form.getString("host");
			String databaseName=form.getString("database");
			String port=form.getString("port");
			String databaseType="MySql";
			String userName=form.getString("username");
			String password=form.getString("password");
			KettleEnvironment.init();
	    	DatabaseMeta objDatabaseMeta = new DatabaseMeta();
			objDatabaseMeta.setName("DatabaseMeta");
			if(instanceName != null) objDatabaseMeta.setSQLServerInstance(instanceName);
			objDatabaseMeta.setHostname(host);
			objDatabaseMeta.setDBName(databaseName);
			objDatabaseMeta.setDBPort(port);
			objDatabaseMeta.setDatabaseType(databaseType);
			objDatabaseMeta.setUsername(userName);
			objDatabaseMeta.setPassword(password);
			
			Properties attributes = new Properties();
			attributes.setProperty(FORCE_IDENTIFIERS_TO_LOWERCASE, "N");
			attributes.setProperty(FORCE_IDENTIFIERS_TO_UPPERCASE, "N");
			attributes.setProperty(QUOTE_ALL_FIELDS, "N");
			attributes.setProperty(PRESERVE_RESERVED_WORD_CASE, "N");
			attributes.setProperty("IS_CLUSTERED", "N");
			attributes.setProperty(SUPPORTS_BOOLEAN_DATA_TYPE, "Y");
			attributes.setProperty(SUPPORTS_TIMESTAMP_DATA_TYPE, "Y");
			attributes.setProperty(PORT_NUMBER, port);
			objDatabaseMeta.setAttributes(attributes);
	    	
			TransMeta transMeta = new TransMeta();
		    transMeta.setName("SQLTransMeta");
		    database = new Database(transMeta, objDatabaseMeta);
			database.connect();
			
			return "Success";
		} catch (KettleDatabaseException e) {
			System.out.println("1 "+ e.getMessage());
			System.out.println("2 "+ e.getLocalizedMessage());
			System.out.println("3 "+ e.getSuperMessage());
			e.printStackTrace();
			return "failed";
		} catch (KettleException e) {
			return e.getLocalizedMessage();
	}
	}

}
