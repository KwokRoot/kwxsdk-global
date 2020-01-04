package com.kwok.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 从配置文件中加载 App 配置参数
 * @author Kwok
 */
public class AppConfigProperties {

	private static String AppID;
	private static String AppSecret;
	private static String token;
	private static String EncodingAESKey;
	
	public static void setConfigPath(String filePath) {
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(filePath));
		} catch (IOException e) {
			System.err.println("Properties 加载配置文件路径有误。");
			e.printStackTrace();
		}
		
		AppID = prop.getProperty("AppID");
		AppSecret = prop.getProperty("AppSecret");
		token = prop.getProperty("token");
		EncodingAESKey = prop.getProperty("EncodingAESKey");
		
	}

	public static String getAppID(){
		return AppID;
	}
	
	public static String getAppSecret(){
		return AppSecret;
	}
	
	public static String getToken(){
		return token;
	}
	
	public static String getEncodingAESKey(){
		return EncodingAESKey;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("conf/app.properties").getPath());
		AppConfigProperties.setConfigPath(Thread.currentThread().getContextClassLoader().getResource("conf/app.properties").getPath());
		System.out.println(AppConfigProperties.AppID);
	}
	
}
