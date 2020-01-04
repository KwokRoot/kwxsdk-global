package com.kwok.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 通用工具类
 * @author Kwok
 */
public class CommonsUtil {

	private static ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	
	/**
	 * 日期转化为格式化日期字符串
	 * @author Kwok
	 */
	public static String formatDate(Date date){
		return simpleDateFormat.get().format(date);
	}
	
	/**
	 * 格式化日期字符串(yyyy-MM-dd HH:mm:ss)转化为日期
	 * @author Kwok
	 * @throws Exception 
	 */
	public static Date parseDate(String dateStr) throws Exception{
		return simpleDateFormat.get().parse(dateStr);
	}
	
	
	public static String getRequest(String urlStr) throws Exception {

		URL url = new URL(urlStr);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		// //设置连接属性
		httpConn.setRequestMethod("GET");// 设置URL请求方法
		httpConn.setRequestProperty("Charset", "UTF-8");

		// 获得响应状态
		int responseCode = httpConn.getResponseCode();
		
		StringBuffer sb = new StringBuffer();
		String readLine;
		BufferedReader responseReader;
		// 处理响应流，必须与服务器响应流输出的编码一致
		responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
		while ((readLine = responseReader.readLine()) != null) {
			sb.append(readLine).append("\n");
		}
		responseReader.close();
		
		if (HttpURLConnection.HTTP_OK != responseCode) {
			throw new Exception("Get 请求返回异常，返回信息：" + sb.toString());
		}
		
		return sb.toString();
	}
	
	
	public static String getSha1(String str) throws Exception{
	    if (null == str || 0 == str.length()){
	        return null;
	    }
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'a', 'b', 'c', 'd', 'e', 'f'};
        
	    MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(str.getBytes("UTF-8"));
         
        byte[] md = mdTemp.digest();
        int j = md.length;
        char[] buf = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        
        return new String(buf);
	}
	
	
	/*
	 * 随机生成固定长度字符串
	 * @author Kwok
	 */
	public static String getRandomStr(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
}
