package com.kwok.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kwok.config.AppConfigProperties;

public class FileLoadListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	
    	String filePath=servletContextEvent.getServletContext().getInitParameter("AppConfig");
    	String fileRealPath = filePath;
    	
    	if(filePath.startsWith("class")){
    		fileRealPath = servletContextEvent.getServletContext().getRealPath("/WEB-INF/classes/" + filePath.substring(filePath.indexOf(":") + 1));
    	}else{
    		fileRealPath = servletContextEvent.getServletContext().getRealPath(filePath);
    	}
    	
    	//System.out.println(fileRealPath);
    	AppConfigProperties.setConfigPath(fileRealPath);
   
    }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }
	
}
