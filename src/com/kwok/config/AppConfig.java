package com.kwok.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kwok.util.WeiXinUtil;

public class AppConfig {
	
	public static String AppID = AppConfigProperties.getAppID();
	public static String AppSecret = AppConfigProperties.getAppSecret();
	public static String token = AppConfigProperties.getToken();
	public static String EncodingAESKey = AppConfigProperties.getEncodingAESKey();
	
	
	private static String AccessToken;

	private static String JSAPITicket;

	public static String setAccessToken() throws Exception{
		
		String jsonStr = WeiXinUtil.getRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ AppID +"&secret=" +AppSecret);
		System.out.println(jsonStr);
		JsonElement  je = new JsonParser().parse(jsonStr);
		String returnStr = null;
		
		try {
			AccessToken= je.getAsJsonObject().get("access_token").getAsString();
			returnStr = "ok";
		} catch (Exception e) {
			returnStr = je.getAsJsonObject().get("errmsg").getAsString();
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
		
		String jsonStr = WeiXinUtil.getRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+AccessToken+"&type=jsapi");
		System.out.println(jsonStr);
		JsonElement  je = new JsonParser().parse(jsonStr);
		
		int errcode = je.getAsJsonObject().get("errcode").getAsInt();
		String returnStr = je.getAsJsonObject().get("errmsg").getAsString();
		
		if(errcode==0){
			JSAPITicket= je.getAsJsonObject().get("ticket").getAsString();
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
