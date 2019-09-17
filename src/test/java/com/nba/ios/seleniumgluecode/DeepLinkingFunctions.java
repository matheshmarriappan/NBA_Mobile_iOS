package com.nba.ios.seleniumgluecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.api.helper.JsonPlayersProfileResponse;
import com.nba.ios.api.helper.JsonPlayersResponse;
import com.nba.ios.api.helper.JsonStandingsResponse;
import com.nba.ios.api.helper.JsonTeamsValidation;
import com.nba.ios.utilities.ConfigPropertyReader;
import com.nba.ios.utilities.HelperUtils;
import com.nba.ios.utilities.LocatorsDataProvier;
import com.nba.ios.utilities.ScorePageElements;
import com.nba.ios.utilities.StandingsElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DeepLinkingFunctions {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	public static WebDriver driver = null;
	public static DesiredCapabilities caps = new DesiredCapabilities();
	
	public static void verifyDeeplinks() throws InterruptedException, IOException {
		try {
			HashMap<String, String> retrivedMap = setTestData();
			System.out.println("Retrieved Map"+ retrivedMap);
			for (Map.Entry<String, String> map : retrivedMap.entrySet()) {
				driver = launchNotePad();
				// Navigating to Notes
				
				driver.findElement(By.id("New note")).click();
				Thread.sleep(2000);
				System.out.println("Printing key for reference " + map.getKey());
				driver.findElement(By.id("note")).sendKeys(map.getKey());
				driver.findElement(By.id("Done")).click();

				System.out.println("Waiting before");
				Thread.sleep(3000);
						
				//Click(driver, map.getKey(),map.getKey() +" deeplink is clicked"," Unable to click the deeplink "+ map.getKey());
				//driver.findElement(By.id(map.getKey())).click();
				/*HelperUtils.waitForElementToVisible();
				HelperUtils.waitForElementToVisible();
				HelperUtils.waitForElementToVisible();*/
				System.out.println("Waiting to launch and test NBA APp");
				isDeeplinkPresent(driver, map.getValue(), 120);
				//isDeepLinkDisplayed(map.getValue(), driver);
				//driver.quit();
				
			 }
		} catch (FileNotFoundException e) {

			System.out.println("Exception occured while reading the test data file " + e.getMessage());
		}

	}
	
	public static void click(WebDriver driver, String btnLocator, String passComment, String failComment) {
        boolean isDisplay = false;
        try {
        	
            String locator = LocatorsDataProvier.getDataMap(btnLocator);
            By by = null;
            if(btnLocator.contains("ID")) {
                by = AppElements.locateID(locator);
            } else {
            	locator = "//XCUIElementTypeLink[@name='"+locator+"']";
                by = AppElements.locateXPATH(locator);
            }
            driver.findElement(by).click();
            isDisplay = true;
            Reporter.addStepLog(passComment);
        } catch (Exception e) {
            System.out.println("Exception occured when clicking "+e.getMessage());
            isDisplay = false;
            Reporter.addStepLog(failComment);
        }
    }
	

	/**
	 * Fetch the deeplinks from the excel sheet
	 * 
	 * @return
	 * @throws IOException
	 */

	public static HashMap<String,String> setTestData() throws IOException {
		HashMap<String, String> hmap = new HashMap<String, String>();
		String testDataPath = System.getProperty("user.dir") + "//resources//DeeplinkData.xlsx";
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(testDataPath);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = null;
			
			sheet = wb.getSheet("DeepLinks");
			int lastRow = sheet.getPhysicalNumberOfRows();
			System.out.println("lastrow in excel"+ lastRow);
			for(int i = 0; i<=lastRow-1; i++)
			{
				// to exclude header part
				if(i == 0)
				{
					continue;
				}
				Row row = sheet.getRow(i);
				Cell valueCell = row.getCell(1);
				Cell keyCell = row.getCell(0);
				
				String value = valueCell.getStringCellValue().trim();
				String key = keyCell.getStringCellValue().trim();
				hmap.put(key, value);
			}
			System.out.println("printing hmap"+ hmap);
		} catch (FileNotFoundException e) {
			
			System.out.println("Exception occured while reading the test data file "+ e.getMessage());
		}
		
		fis.close();
		return hmap;
	}
	
	public static String getText(String arg1) {
		
		String locator = null;
		String text = null;
		try {
			locator = LocatorsDataProvier.getDataMap(arg1);
			// locate the Path and return it in By element
			By by = null;
			if(arg1.contains("ID"))
			{
				by = AppElements.locateID(locator);
				
			} else {
				by = AppElements.locateXPATH(locator);
			}
			
			text = driver.findElement(by).getText();
			System.out.println("Locator for "+ arg1 + " is "+locator);
			System.out.println(" Text for "+arg1 + " is "+text);
		} catch (IOException e) {
			System.out.println("Exception occured while getting the text for "+arg1 + e.getMessage());
			Reporter.addStepLog("Exception occured while getting the text for "+arg1 + e.getMessage());
			HelperUtils.captureScreen();
		}
		return text;
		
	}
	
	public static boolean isDeepLinkDisplayed(String value, WebDriver driver) {
		boolean isDisplay = false;
		String [] locator = value.split(":");
		System.out.println("locator [0]"+ locator[0]);

		try {
			System.out.println("Locator is "+ locator[1]);
			By by = null;
            if(value.contains("ID"))
            {
            	
                by = AppElements.locateID(locator[1]);
                
            } else {
                by = AppElements.locateXPATH(locator[1]);
            }
			 isDisplay = driver.findElement(by).isDisplayed();
			System.out.println("isDisplay"+ isDisplay);
			System.out.println(locator[0] + " page is displayed on navigating the deeplink");
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			isDisplay = false;
			System.out.println(locator[0] + " page is not displayed on navigating the deeplink");
             HelperUtils.captureScreen();
            
		}
		
		return isDisplay;
	}
	
public static boolean isDeeplinkPresent(WebDriver driver,String value,int timeouts) throws IOException {
	String [] locator = value.split(":");
	System.out.println("locator [0]"+ locator[0]);

		boolean isDisplayed = false;	
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		try {
			System.out.println("Locator is "+ locator[1]);
			By by = null;
            if(value.contains("ID"))
            {      	
                by = AppElements.locateID(locator[1]);
                
            } else {
                by = AppElements.locateXPATH(locator[1]);
            }
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			if(element.isDisplayed()) {
				isDisplayed = true;
				System.out.println("element is present "+ locator[0]);
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	public static boolean isElementDisplayed(String arg1, String passComment, String failComment) {
		boolean isDisplay = false;
		System.out.println("Printing Driver" + driver.getClass());
		try {
			String locator = LocatorsDataProvier.getDataMap(arg1);
			System.out.println("Locator for " + arg1 + " is " + locator);
			WebDriverWait wait = new WebDriverWait(driver, 120);

			By by = null;
			if (arg1.contains("ID")) {
				by = AppElements.locateID(locator);

			} else {
				by = AppElements.locateXPATH(locator);
			}
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			if (element.isDisplayed()) {
				isDisplay = true;
				System.out.println("element is present " + locator);
				Reporter.addStepLog(passComment);
			} else {
				Reporter.addStepLog(failComment);
			}
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed " + e.getMessage());
			isDisplay = false;
		}

		return isDisplay;
	}

	public static DesiredCapabilities setCapabilitiesNotePad() throws MalformedURLException {
		
		boolean configReader = false;
		try {
			ConfigPropertyReader.readConfigValue();
			configReader = true;
		} catch (IOException e1) {
			System.out.println("Error while reading the config property reader class");
		}
    		if (configReader) {

          	System.out.println("setting capabilities to launch notepad");
          	String platformName = ConfigPropertyReader.getPropertyValue("Platform_Name");
    		    String deviceName = ConfigPropertyReader.getPropertyValue("Device_Name");
    			String deviceId = ConfigPropertyReader.getPropertyValue("iPhone_UDID");    			
    		
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);		
			caps.setCapability(MobileCapabilityType.UDID, deviceId);
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
			caps.setCapability("useNewWDA", true);
			caps.setCapability("noReset", true);
			caps.setCapability(MobileCapabilityType.APP, "com.apple.mobilenotes");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			
    		} else {
    			Reporter.addStepLog("Config Reader has a problem in reading the data");
    		}
		return caps;
		
	}
	public static WebDriver launchNotePad() throws MalformedURLException {

		WebDriver driver = null;
        try {
			DesiredCapabilities caps = new DesiredCapabilities();
			
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhoneX2");		
			caps.setCapability(MobileCapabilityType.UDID, "c310d13218dfedb7252883646b6a2d6c361ae726");
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
			caps.setCapability("useNewWDA", true);
			caps.setCapability("noReset", true);
			caps.setCapability(MobileCapabilityType.APP, "com.apple.mobilenotes");

			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

			
				 driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return driver;
		
	}
	
	public static WebDriver launchNoteApp() throws MalformedURLException {
		WebDriver driver = null;

        try {
			driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return driver;
		
	}

	@SuppressWarnings("unchecked")
	public static void launchDeepLink(String deepLink) throws IOException {		
		try {
			String deepLinkValue = LocatorsDataProvier.getDataMap(deepLink);

				driver = launchNoteApp();
				// Navigating to Notes
				//((AppiumDriver<WebElement>) driver).rotate(ScreenOrientation.LANDSCAPE);
				driver.findElement(By.id("New note")).click();
				Thread.sleep(2000);
				System.out.println("Printing key for reference " + deepLinkValue);
				driver.findElement(By.id("note")).sendKeys(deepLinkValue);
				driver.findElement(By.id("Done")).click();

				System.out.println("Waiting before");
				Thread.sleep(3000);
				
				click(driver, deepLink, "Deeplink "+ deepLinkValue +" is clicked", "Unable to click the deeplink "+ deepLinkValue);
					
				System.out.println("Waiting to launch and test NBA APp");
						
			 
		} catch (Exception e) {

			System.out.println("Exception occured while reading the test data file " + e.getMessage());
		}

	}
	
	public static boolean isDisplayed(String arg1, String passComment, String failComment) {
		boolean isDisplay = false;
		System.out.println("Printing Driver"+ driver.getClass());
		try {
            String locator = LocatorsDataProvier.getDataMap(arg1);
			System.out.println("Locator for "+ arg1 + " is "+locator);
			By by = null;
            if(arg1.contains("ID"))
            {
                by = AppElements.locateID(locator);
                
            } else {
                by = AppElements.locateXPATH(locator);
            }
			driver.findElement(by).isDisplayed();
			isDisplay = true;
			Reporter.addStepLog(passComment);
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			isDisplay = false;			
		}
		
		return isDisplay;
	}

	/**
	 * This method is to close all the drivers
	 */
	public static void closeDrivers() {
		driver.quit();
		System.out.println("Quitting the drivers");
	}	
	
}