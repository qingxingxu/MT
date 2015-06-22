package edu.buaa.nlp.utils;

import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties prop = new Properties();	
	static{		
		try {
			//加载dbconfig.properties配置文件
			prop.load(Config.class.getResourceAsStream("dbconfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//设置常量
	public static final String CLASS_NAME = prop.getProperty("CLASS_NAME");
	public static final String DATABASE_URL = prop.getProperty("DATABASE_URL");
	public static final String SERVER_IP = prop.getProperty("SERVER_IP");
	public static final String SERVER_PORT = prop.getProperty("SERVER_PORT");
	public static final String DATABASE_SID = prop.getProperty("DATABASE_SID");
	public static final String USERNAME = prop.getProperty("USERNAME");
	public static final String PASSWORD = prop.getProperty("PASSWORD");
	
	//cp30
	public static final int MAX_POOL_SIZE=Integer.parseInt(prop.getProperty("MAX_POOL_SIZE"));
	public static final int MIN_POOL_SIZE=Integer.parseInt(prop.getProperty("MIN_POOL_SIZE"));
	public static final int MAX_STATEMENTS=Integer.parseInt(prop.getProperty("MAX_STATEMENTS"));
	public static final int MAX_STATEMENTS_PER_CONN=Integer.parseInt(prop.getProperty("MAX_STATEMENTS_PER_CONN"));
	public static final int ACQUIRE_INCREMENT=Integer.parseInt(prop.getProperty("ACQUIRE_INCREMENT"));
	public static final int MAX_IDLE_TIME=Integer.parseInt(prop.getProperty("MAX_IDLE_TIME"));
	public static final int MAX_CONNECTION_AGE=Integer.parseInt(prop.getProperty("MAX_CONNECTION_AGE"));
	
}
