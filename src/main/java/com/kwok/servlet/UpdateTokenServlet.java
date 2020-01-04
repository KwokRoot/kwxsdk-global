package com.kwok.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kwok.config.AppConfig;
import com.kwok.util.WeiXinUtil;

/**
 * 强制更新 AccessToken, JSAPITicket
 * @author Kwok
 */
@WebServlet("/updateToken")

public class UpdateTokenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String randomStr = request.getParameter("randomStr");
		String signature = request.getParameter("signature");

		if ("".equals(randomStr) || randomStr == null || "".equals(signature) || signature == null) {
			
			response.getWriter().append("PARAMETER ERROR!");
		
		} else if (signature.equals(WeiXinUtil.getSha1(randomStr + AppConfig.token))) {
			
			String AccessTokenReString = null;
			String JSAPITicketReString = null;
			try {
				AccessTokenReString = AppConfig.setAccessToken();
				JSAPITicketReString = AppConfig.setJSAPITicket();
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setHeader("Content-type", "text/html;charset=UTF-8"); 
			response.setCharacterEncoding("UTF-8");
			if("ok".equals(AccessTokenReString) && "ok".equals(JSAPITicketReString)){
				response.getWriter().append("------已手动更新 Token SUCCESS------");
			}else{
				response.getWriter().append("AccessToken : " + AccessTokenReString)
					.append("\n<br>\n")
					.append("JSAPITicket : " + JSAPITicketReString);
			}
			
		} else {
			response.getWriter().append("ENCRYPTION ERROR!");
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
