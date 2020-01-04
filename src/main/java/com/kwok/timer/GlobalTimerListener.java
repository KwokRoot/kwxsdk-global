package com.kwok.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GlobalTimerListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("----------定时器已经启动---------");
		GlobalTimer.getTimer().schedule(new UpdateTokenTask(), 0, 6840000); // 6840000 1.9小时
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		GlobalTimer.getTimer().cancel();
	}
	
}
