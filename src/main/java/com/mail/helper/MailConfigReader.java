package com.mail.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailConfigReader {


	public static Properties prop = new Properties();
	
	public static void readMailConfigValue() throws IOException {
		
		File file = new File("config.prop");
	
		try {
			prop.load(new FileInputStream(file));
			System.out.println("Loaded the prop file");
		} catch (IOException e) {
			System.out.println("IO Exception occured while reading");
		}
	}
	
	 public static String getPropertyValue(final String key) {
		    return prop.getProperty(key);
		  }


}
