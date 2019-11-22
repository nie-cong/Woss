package com.briup.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//日志
public class LogImpl implements Log{
	//实现init方法，获取propertires内的所需的值
	String filePath=null;
	private static Logger logger;
	public void init(Properties properties) throws Exception {
		filePath=(String) properties.get("log4j-properties");
		//获取rootLigger
		logger=Logger.getRootLogger();
		//加载properties文件
		PropertyConfigurator.configure(filePath);
		
	}
	public void debug(String message) {
		logger.debug(message);
	}
	public void info(String message) {
		logger.info(message);
	}
	public void warn(String message) {
		logger.warn(message);
	}
	public void error(String message) {
		logger.error(message);
	}
	public void fatal(String message) {
		logger.fatal(message);
	}
	public void trace(String message) {
		logger.trace(message);
	}
}
