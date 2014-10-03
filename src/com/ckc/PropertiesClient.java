package com.ckc;

import java.io.IOException;
import java.util.Properties;

public class PropertiesClient {
	static Properties  p = new Properties();
	
	static{
		try {
			p.load(PropertiesClient.class.getClassLoader().getResourceAsStream("config/Tank.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		};	
	public static String getProperty(String Key){
		return p.getProperty(Key);
	}
}
/*package com.ckc;

import java.io.IOException;
import java.util.Properties;

public class PropertiesClient {
	static Properties props = new Properties();
	
	static {
		try {
			props.load(PropertiesClient.class.getClassLoader().getResourceAsStream("config/Tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//private PropertyMgr() {};
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}*/
