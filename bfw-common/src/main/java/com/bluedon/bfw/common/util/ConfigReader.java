package com.bluedon.bfw.common.util;

import java.io.File;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.log4j.Logger;

/**
 * 配置文件读取
 * 
 * @author jason
 *
 */
public class ConfigReader {
	
	private static final Logger log = Logger.getLogger(ConfigReader.class);
	
	public static CombinedConfiguration config;
	
	public static String filePath = "/config.xml";
	
	static {
		log.info("读取配置文件：/config.xml");
		DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
		builder.setFile(new File(filePath));
		try {
			config = builder.getConfiguration(true);
		} catch (ConfigurationException e) {
			log.error("读取配置文件失败", e);
		}
	}
	
	public static String getProperty(String configName, String key,
			String defaultValue) {
		return config.getConfiguration(configName).getString(key, defaultValue);
	}

	public static String getProperty(String configName, String key) {
		return config.getConfiguration(configName).getString(key);
	}
	
	public static int getInt(String configName, String key) {
		return config.getConfiguration(configName).getInt(key);
	}
	
	public static int getInt(String configName, String key,int defaultValue) {
		if(config.getConfiguration(configName)==null){
			return defaultValue;
		}else{
			return config.getConfiguration(configName).getInt(key);
		}
	}

	public static boolean getBoolean(String configName, String key) {
		return config.getConfiguration(configName).getBoolean(key);
	}
	
	public static boolean getBoolean(String configName, String key,boolean defaultValue) {
		if(config.getConfiguration(configName)==null){
			return defaultValue;
		}else{
			return config.getConfiguration(configName).getBoolean(key);
		}
	}

	public static String[] getPropertyAsArray(String configName, String key) {
		return config.getConfiguration(configName).getStringArray(key);
	}

}
