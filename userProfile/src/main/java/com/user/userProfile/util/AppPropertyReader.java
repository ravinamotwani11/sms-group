//package com.user.userProfile.util;
//
//import java.io.InputStream;
//import java.util.Enumeration;
//import java.util.Properties;
//
//public class AppPropertyReader {
//
//	private Properties properties;
//
//	private static AppPropertyReader self = new AppPropertyReader();
//
//	private static boolean retvalue = false;
//
//	private AppPropertyReader() {
//		InputStream inputStream = null;
//		try {
//			properties = new Properties();
//			inputStream = this.getClass().getClassLoader().getResourceAsStream("error.properties");
//			properties.load(inputStream);
//		} catch (Exception ex) {
//
//		}
//
//		if (inputStream != null) {
//			try {
//				inputStream.close();
//			} catch (Exception e) {
//			}
//		}
//	}
//
//	public static AppPropertyReader getInstance() {
//		return self;
//	}
//
//	public String getProperty(String key) {
//		return properties.getProperty(key);
//	}
//
//	public Enumeration<Object> getPropertyKey() {
//		return properties.keys();
//	}
//
//	public boolean containProperty(String key) {
//		retvalue = false;
//		if (properties.containsKey(key)) {
//			retvalue = true;
//		}
//		return retvalue;
//	}
//
//	public Enumeration<Object> getAllAppProperties() {
//		Enumeration<Object> keys = properties.keys();
//		return keys;
//	}
//}
