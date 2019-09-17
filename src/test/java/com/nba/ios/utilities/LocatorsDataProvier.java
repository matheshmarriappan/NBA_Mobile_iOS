package com.nba.ios.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.appium.java_client.remote.MobileCapabilityType;

public class LocatorsDataProvier {

	 /**
	   * ThreadLocal testDataMap.
	   */
	  private static ThreadLocal<Map<String, String>> testDataMap = new ThreadLocal<Map<String, String>>();
	
	/*public static void main(String[] args) throws IOException {
		
		setTestData();
		getDataMap();
		endThreadLocal();
		
	}*/
	
	public static HashMap<String,String> setTestData() throws IOException {
		HashMap<String, String> hmap = new HashMap<String, String>();
		String testDataPath = System.getProperty("user.dir") + "//resources//TestData.xlsx";
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(testDataPath);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = null;
			String getAPKPath = System.getProperty("InstallAPK");
				if (getAPKPath.equalsIgnoreCase("QA_APP")) {
					 sheet = wb.getSheet("AppElements");

				} else if (getAPKPath.equalsIgnoreCase("PROD_APP")) {
					 sheet = wb.getSheet("AppElementsProd");
				} else if (getAPKPath.equalsIgnoreCase("DeepLinks")) {
					sheet = wb.getSheet("DeepLinks");
				}
					
			//Sheet sheet = wb.getSheet("AppElements");
			int lastRow = sheet.getLastRowNum();
			
			for(int i = 0; i<=lastRow; i++)
			{
				// to leave header part
				if(i == 0)
				{
					continue;
				}
				Row row = sheet.getRow(i);
				Cell valueCell = row.getCell(1);
				Cell keyCell = row.getCell(0);
				
				String value = valueCell.getStringCellValue().trim();
				//System.out.println("Value "+value);
				//System.out.println("i value in test data" + i + value );
				String key = keyCell.getStringCellValue().trim();
				//System.out.println("Key "+key);
				hmap.put(key, value);
			}
			testDataMap.set(hmap);
			System.out.println(hmap);
		} catch (FileNotFoundException e) {
			
			System.out.println("Exception occured while reading the test data file "+ e.getMessage());
		}
		
		fis.close();
		return hmap;
	}
	
	public static String getDataMap(String locatorPath) throws IOException {
		
		String getLocator = testDataMap.get().get(locatorPath);
		System.out.println("getLocator "+getLocator);
		return getLocator;
		
	}
	
	/**
	   * endThreadLocal.
	   */
	  public static void endThreadLocal() {
	    testDataMap.remove();
	    System.out.println("Removed data map");
	  }

}
