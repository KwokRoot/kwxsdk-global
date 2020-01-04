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
 * 获取 AccessToken
 * @author Kwok
 */
@WebServlet("/getAccessToken")
public class GetAccessTokenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String randomStr = request.getParameter("randomStr");  //随机字符串
		String signature = request.getParameter("signature");  //加密字符串
		try{
			if ("".equals(randomStr) || randomStr == null || "".equals(signature) || signature == null) {
				response.getWriter().append("PARAMETER ERROR!");
			} else if (signature.equals(CommonsUtil.getSha1(randomStr + AppConfig.token))) {
				response.getWriter().append(AppConfig.getAccessToken());
			} else {
				response.getWriter().append("ENCRYPTION ERROR!");
			}
		}catch (Exception e) {
			response.getWriter().append("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
