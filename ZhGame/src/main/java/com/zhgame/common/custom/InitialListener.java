package com.zhgame.common.custom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitialListener implements ServletContextListener{
    private static Logger logger = LoggerFactory.getLogger(InitialListener.class);
    public static Map<String, String> SERVER_ERROR_MSG = new HashMap<String, String>();
    public static Map<String, String> CLIENT_ERROR_MSG = new HashMap<String, String>();

    // 实现其中的销毁函数
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("this is last destroyeed");
    }

    // 实现其中的初始化函数，当有事件发生时即触发
 	public void contextInitialized(ServletContextEvent event) {
 		initProperties(event.getServletContext().getInitParameter("resources"));
 		initLog4j();
 		logger.info("-----------------自定义初始化项---------------");
 	}
 	private void initLog4j() {
 		String logPath = System.getProperty("log.path");
 		String tomcatBase = System.getProperty("catalina.base");
 		
 		if(System.getProperty("os.name").toLowerCase().indexOf("window") == -1) { //linux/unix
 			tomcatBase = tomcatBase.substring(1).replaceAll("/", "-");
 			logPath = logPath.replace("#TEMP_DIR#", tomcatBase);
 		}else { //window
 			logPath = tomcatBase + "/logs";
 		}
 		System.setProperty("log.path",logPath);
 		
 		String fileName = "log4j.xml";
 		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
 		DOMConfigurator.configure(path + fileName);// 加载.xml文件
 	}
 	
 	private void initProperties(String resource) {
 		
 	}
}
