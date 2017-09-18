package com.kwok.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import com.kwok.config.AppConfig;

public class UpdateTokenTask extends TimerTask{

	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
	
	@Override
	public void run() {
		
		try {
			
			AppConfig.setAccessToken();
			AppConfig.setJSAPITicket();
			System.out.println(sdf.format(new Date())+"--- 已定时更新 Token ---\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
