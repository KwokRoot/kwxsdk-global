package com.kwok.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kwok.config.AppConfig;
import com.kwok.util.CommonsUtil;

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

		try{
			if ("".equals(randomStr) || randomStr == null || "".equals(signature) || signature == null) {
				
				response.getWriter().append("PARAMETER ERROR!");
			
			} else if (signature.equals(CommonsUtil.getSha1(randomStr + AppConfig.token))) {
				
				String AccessTokenReString = null;
				String JSAPITicketReString = null;
				AccessTokenReString = AppConfig.setAccessToken();
				JSAPITicketReString = AppConfig.setJSAPITicket();
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
		}catch (Exception e) {
			response.getWriter().append("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
