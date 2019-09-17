package com.nba.ios.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertyReader {
	
	public static Properties prop = new Properties();
	
public static void main(String[] args) throws IOException {
        
       // readConfigValue();
		readXMLPathConfigValue();
        String appPath = getPropertyValue("T1VideoorArticle");
        System.out.println("appPath "+appPath);
        

    }
	
	public static void readConfigValue() throws IOException {
		
		File file = new File("./config.prop");
	
		try {
			prop.load(new FileInputStream(file));
			System.out.println("Loaded the prop file");
		} catch (IOException e) {
			System.out.println("IO Exception occured while reading");
		}
	}

	public static void readXMLPathConfigValue() throws IOException {
		
		File file = new File("./xmlPathReader.prop");
	
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
