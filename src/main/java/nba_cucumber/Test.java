package nba_cucumber;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class Test {

	public static AppiumDriver<WebElement> driver;
	public static void main(String[] args) throws IOException
	
	{
		
		try
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("platformVersion", "12.1");
	        capabilities.setCapability("platformName", "iOS");
	        capabilities.setCapability("udid", "c310d13218dfedb7252883646b6a2d6c361ae726");
	        capabilities.setCapability("deviceName", "iPhone 7 plus ");
	        capabilities.setCapability("automationName", "XCUITest");                
	        capabilities.setCapability("bundleId", "com.turner.nba.enterprise");
	         
	        driver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
		

}
