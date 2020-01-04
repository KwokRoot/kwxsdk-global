package com.kwok.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kwok.util.CommonsUtil;

public class AppConfig {
	
	public static String AppID;
	public static String AppSecret;
	public static String token;
	public static String EncodingAESKey;
	
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
	
	
	private static String AccessToken;

	private static String JSAPITicket;

	public static String setAccessToken() throws Exception{
		
		String jsonStr = CommonsUtil.getRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ AppID +"&secret=" +AppSecret);
		System.out.println(jsonStr);
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		String returnStr = null;
		
		try {
			AccessToken= jsonObject.getString("access_token");
			returnStr = "ok";
		} catch (Exception e) {
			returnStr = jsonObject.getString("errmsg");
		}
		
		return returnStr;
		
	}
	
	public static String getAccessToken() throws Exception {
		if(AccessToken==null){
			setAccessToken();
		}
		return AccessToken;
	}
	
	
	public static String setJSAPITicket() throws Exception{
		
		if(AccessToken==null){
			setAccessToken();
		}
		
		String jsonStr = CommonsUtil.getRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+AccessToken+"&type=jsapi");
		System.out.println(jsonStr);
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		
		int errcode = jsonObject.getIntValue("errcode");
		String returnStr = jsonObject.getString("errmsg");
		
		if(errcode==0){
			JSAPITicket= jsonObject.getString("ticket");
		}
		
		return returnStr;
		
	}

	public static String getJSAPITicket() throws Exception {
		if(JSAPITicket==null){
			setJSAPITicket();
		}
		return JSAPITicket;
	}
	
}
