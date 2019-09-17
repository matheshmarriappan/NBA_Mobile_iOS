package com.nba.ios.api.helper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cucumber.listener.Reporter;
import com.nba.ios.seleniumgluecode.CommonMethods;
import com.nba.ios.utilities.ConfigPropertyReader;
import com.nba.ios.utilities.HelperUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumApp {

	/**
	 * ThreadLocal currentDriver.
	 */
	private static ThreadLocal<WebDriver> currentDriver = new ThreadLocal<WebDriver>();
	
	

	/**
	 * . To get the current WebDriver object
	 * 
	 * @return WebDriver WebDriver
	 */
	public static WebDriver getCurrentDriver() {
		WebDriver driver = currentDriver.get();
		if (driver != null) {
			System.out.println("Driver instance is not null *** ");
			return driver;
		} else {
			System.out.println("Driver not initialized");
			return null;
		}
	}

	public static void getAppiumApp() throws InterruptedException {
		currentDriver.set(launchAppiumApp());
		//WebDriver driver = launchAppiumApp();
	}

	
	
	public static WebDriver launchAppiumApp() throws InterruptedException {

		WebDriver appDriver = null;

		String getAPKPath = System.getProperty("InstallAPK");
		System.out.println("getAPKPath " + getAPKPath);


		// read app path from config file
		boolean configReader = false;
		String appPath = null;
		String finalAppPath = null;
		try {
			ConfigPropertyReader.readConfigValue();
			configReader = true;
		} catch (IOException e1) {
			System.out.println("Error while reading the config property reader class");
		}
		if (configReader) {
			if (getAPKPath.equalsIgnoreCase("QA_APP")) {
				appPath = ConfigPropertyReader.getPropertyValue("iPhone_QA_App_PATH");

			} else if (getAPKPath.equalsIgnoreCase("PROD_APP")) {
				appPath = ConfigPropertyReader.getPropertyValue("iPhone_PROD_App_PATH");
			}

			System.out.println("App path from Config property " + appPath);
			File appFile = new File(appPath);
			finalAppPath = appFile.getAbsolutePath();
			System.out.println("finalPath " + finalAppPath);
		} else {
			System.out.println("Config reader has some problem");
		}

		String deviceId = ConfigPropertyReader.getPropertyValue("iPhone_UDID");
		System.out.println("deviceId " + deviceId);

		String deviceName = ConfigPropertyReader.getPropertyValue("Device_Name");
		System.out.println("deviceName " + deviceName);

		String platformName = ConfigPropertyReader.getPropertyValue("Platform_Name");
		System.out.println("platformName " + platformName);

		String noRest = ConfigPropertyReader.getPropertyValue("No_Reset");
		System.out.println("noRest " + noRest);
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		
		//Install build based on app bundle ID
		
		if (configReader) {
			if (getAPKPath.equalsIgnoreCase("QA_APP")) {
				caps.setCapability(MobileCapabilityType.APP, "com.turner.nba.enterprise");

			} else if (getAPKPath.equalsIgnoreCase("PROD_APP")) {
				caps.setCapability(MobileCapabilityType.APP, "com.turner.nba.enterprise");
			}
		
		} else {
			System.out.println("Config reader has some problem");
		}
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);		
		caps.setCapability(MobileCapabilityType.UDID, deviceId);
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
		caps.setCapability("useNewWDA", true);
		caps.setCapability("noReset", noRest);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

		System.out.println("**************"+appPath);
 
		// Instantiate Appium Driver
		try {
			 appDriver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			
			System.out.println("Launched the app");
			//Reporter.addStepLog("Installed the NBA App and Launched.");
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}

		return appDriver;
	}

	public static void main(String[] args) throws InterruptedException {

		//AppiumServer.appium_Start();
		// System.out.println("Started Appium ");
		// driver = launchAppiumApp();
		System.out.println("launched the app");
		AppiumServer.appium_stop();
		System.out.println("Stopped the appium");

	}
	
	public static void endAppiumAppDriver() {
		currentDriver.get().quit();
		currentDriver.remove();
		System.out.println("Removed current driver instance");
	}

}
