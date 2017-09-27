package com.kwok.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.kwok.config.AppConfig;
import com.kwok.util.WeiXinUtil;

@WebServlet("/OAuthCallBackServlet")
public class OAuthCallBackServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String ReqAccessTokenPath = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AppConfig.AppID + "&secret="+ AppConfig.AppSecret + "&code=" + code + "&grant_type=authorization_code";
		
		try {
			String jsonStr = WeiXinUtil.getRequest(ReqAccessTokenPath);
			JsonElement  je = new JsonParser().parse(jsonStr);
			
			if (je.getAsJsonObject().get("openid") != null) {
				
				String openid = je.getAsJsonObject().get("openid").getAsString();
				/*
				 * 对 openid 进行操作。
				 */
				System.out.println(openid);
				
			}else{
				//System.err.println(jsonStr); //防止微信服务器重复发起请求(微信服务器重复发起请求时，由于 code 只能使用一次，处理该 GET 请求用该 code 再次获取 openid 和 access_token 会返回错误。)。
			}
			
		} catch (Exception e){
			System.err.println("获取 openid 发生错误。");
			e.printStackTrace();
		} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
