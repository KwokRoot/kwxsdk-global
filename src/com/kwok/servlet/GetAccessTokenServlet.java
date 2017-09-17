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

		if ("".equals(randomStr) || randomStr == null || "".equals(signature) || signature == null) {
			response.getWriter().append("PARAMETER ERROR!");
		} else if (signature.equals(WeiXinUtil.getSha1(randomStr + AppConfig.token))) {
			try {
				response.getWriter().append(AppConfig.getAccessToken());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.getWriter().append("ENCRYPTION ERROR!");
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
