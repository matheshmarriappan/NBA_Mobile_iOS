package com.nba.ios.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.seleniumgluecode.CommonMethods;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
//import junit.framework.Assert;

public class HelperUtils {

	public static void waitForElementToVisible() throws InterruptedException {
		Thread.sleep(10000);
	}

	public static void waitForElement() throws InterruptedException {
		Thread.sleep(5000);
	}

	public static boolean printText(String comments, String text) throws IOException {
		boolean result = false;
		if (text != null && !text.isEmpty()) {
			System.out.println(comments + text);
			// Reporter.addStepLog(comments + text);
			result = true;
		} else {

			System.out.println(comments + "Null or Empty String is returned for " + text);
			// Reporter.addStepLog(comments +"Null or Empty String is returned
			// for " +text);
			result = false;
		}

		return result;

	}

	/**
	 * This function is used to verify whether the element is present in the web
	 * Page
	 * 
	 * @param locator
	 *            -By
	 * @param timeouts
	 *            -Integer
	 * @return -boolean
	 * @throws IOException
	 */
	public static boolean isElementPresent(WebDriver driver, By locator, int timeouts, String pc, String fc)
			throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		try {
			WebElement element = null;
			// WebElement element =
			// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			element.isDisplayed();
			Reporter.addStepLog(pc);
			return true;

		} catch (Exception e) {
			// UMReporter.log(Status.FAIL, fc);
			Reporter.addStepLog(fc);
			return false;
		}
	}

	public static boolean waitForVisibility(String locator, int timeOutSeconds) throws InterruptedException, IOException {
		
	    WebElement element =	CommonMethods.getWebElement(locator);
		System.out.println("** Inside waitForVisibility ***");
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean result = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,timeOutSeconds);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement((By) element)));
			System.out.println("Element is found");
			result = true;
		} catch (Exception e) {
			System.out.println("Waited for the visibilty "+e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	public static boolean isElementPresent(WebDriver driver,By locator,int timeouts) throws IOException {
		
		boolean isDisplayed = false;	
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if(element.isDisplayed()) {
				isDisplayed = true;
			}
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	/**
	 * This function is used check whether the specified element is present and performs click action 
	 * @param locator
	 * 		-By
	 * @param timeout
	 * @throws IOException
	 */
	public static void iselementpresentandclick(By locator,int timeout, String comments, WebDriver driver) throws IOException     
	{
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try{
			System.out.println("iselementpresentandclick ");
			if(wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed())
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
				System.out.println(comments +" is present and clicked.");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("iselementpresentandclick not present --> " +comments);
			System.out.println("The message is "+e.getMessage());
		}
	}

	public static boolean isDisplayed(WebElement element, String comment) {

		boolean isDisplayed = false;
		try {
			if (element.isDisplayed()) {
				isDisplayed = true;
				Reporter.addStepLog(comment + " is displayed");
				System.out.println(comment + " is displayed");
			}
		} catch (Exception e) {
			System.out.println(comment + " is not present");
			isDisplayed = false;
		}

		return isDisplayed;
	}

	public static boolean isDisplayed(WebElement element) {

		boolean isDisplayed = false;
		try {
			if (element.isDisplayed()) {
				isDisplayed = true;
			}
		} catch (Exception e) {
			isDisplayed = false;
		}

		return isDisplayed;
	}

	public static void click(WebElement clickElement, String comments) {
		try {
			clickElement.click();
			System.out.println("Clicked on the element " + comments);
			Reporter.addStepLog("Clicked on the " + comments);
		} catch (Exception e) {
			System.out.println("Unable to click on element ");
			Reporter.addStepLog("Unable to click on an element " + comments + e.getMessage());
		}

	}

	public static String getTextFromAttribute(WebDriver driver, By locator, String attr, int timeOut) {
		
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        String value = null;
        try {
        	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            value = element.getAttribute(attr);
        } catch (Exception e) {
        	System.out.println("Failed to retrieve tagname" + " The error message is " + e.getMessage());
        }
        return value;
    }
	
	public static boolean enterText(WebElement element, String text, String comments) {
		boolean entered = false;
		try {
			element.sendKeys(text);
			entered = true;
			System.out.println("Entered");
			Reporter.addStepLog("Entered " + comments);
		} catch (Exception e) {
			entered = false;
			System.out.println("Unable to enter " + comments);
		}

		return entered;
	}

	public static String getText(WebElement element) {

		String text = null;
		try {
			text = element.getText();
		} catch (Exception e) {
			text = "";
			System.out.println("Unable to get the text ");
		}
		return text;
	}
	
	public static boolean waitForElementToBeInvisible(WebDriver driver, By element) {
		 WebDriverWait wait = new WebDriverWait(driver, 60);
		 boolean elementInvisible = false;
		 try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			elementInvisible = true;
		} catch (Exception e) {
			System.out.println("Execption occured during wait for element ");
		}
		 return elementInvisible;
	}
	
	public static boolean waitUntilElementVisible(WebDriver driver, By element, int timeoutInSeconds)
	{
		boolean result = false;
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			result = true;
		} catch (Exception e) {
			System.out.println("Wait until element visible");
		}
		return result;
	}
	
	/**
	 * capture screen.
	 * 
	 * @return String string
	 */
	public static String captureScreen() {
		WebDriver driver = AppiumApp.getCurrentDriver();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dest = reportPathLocation() + src.getName();
		//String dest = "./custom_report/"+src.getName();
		System.out.println("Dest "+dest);
		File targetPath = new File(dest);
		try {
			FileUtils.copyFile(src, targetPath);
			System.out.println("Target Path "+targetPath.getAbsolutePath());
		
			Reporter.addScreenCaptureFromPath(targetPath.getAbsolutePath());
			System.out.println("Attached the screen shot *** ");
		} catch (IOException e) {
			System.out.println("Exception occured while copying the file "+e.getMessage());
		}
		
		return targetPath.getAbsolutePath();
		
	}
	
	public static String reportPathLocation() {
        String reportPath = null;
        try {
            reportPath = new String(Files.readAllBytes(Paths.get("./reportLocation.txt")));
            System.out.println("readLoc "+reportPath);
        } catch (IOException e) {
            System.out.println("Exception occured while reading the path from Report Location File to send email *** "+e.getMessage());
        }
        
        return reportPath;
    }
	
	public static void swipeVertically(WebDriver driver) throws InterruptedException {
		   
		  try {
			System.out.println("Driver instance in swipe"+ driver.getClass());
			  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			   System.out.println("Dimension " + size);

			  int starty = (int) (size.height * 0.70);
			  int endy = (int) (size.height * 0.30);
				 // int startx = (int) (size.width * 0.40);
			  int startx = (int)(size.width * 0.70);
				           // int startx = size.width / 2;
			 System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
			 MobileDriver mobileDriver = ((MobileDriver) driver);
			 TouchAction action = new TouchAction((PerformsTouchActions) driver);
			 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, endy)).release().perform();
			 Thread.sleep(3000);
			 System.out.println("Scroll done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void swipeRightToLeftHorizontally(WebDriver driver) throws InterruptedException {
		   
		  try {
			System.out.println("Driver instance in swipe"+ driver.getClass());
			  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			   System.out.println("Dimension " + size);

			    int startx = 318;
				int starty =295;
				int endx = 57;
				int endy = 305;
			
			  MobileDriver mobileDriver = ((MobileDriver) driver);
				TouchAction action = new TouchAction((PerformsTouchActions) driver);
				// new
				// TouchAction(((MobileDriver)driver)).press(startx,endy).waitAction(Duration.ofMillis(20000)).moveTo(startx,starty).release().perform();
				 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endx, endy)).release().perform();
			 Thread.sleep(3000);
			 System.out.println("Scroll done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void swipeFromRightToLeft(String pageName) {

		WebDriver driver = AppiumApp.getCurrentDriver();
		org.openqa.selenium.Dimension size = driver.manage().window().getSize();
		System.out.println("Dimension " + size);
		System.out.println("Size height " + size.height);
		System.out.println("Size width " + size.width);
		
		int startx = 0;
		int endx = 0;
		int starty = 0;
		int endy = 0;
		
		if(pageName.equals("PlayersPage"))
		{
			startx = (int) (size.width - 69);
			starty = (int) (size.height - 1060);
			endx = (int) (size.width - 980);
			endy = (int) (size.height - 1028);
			
		} else if(pageName.equals("ComingUpNext ShowSlider") || pageName.equals("NBATVFeatured ShowSlider"))
		{
			
			startx = 395;
			starty = 585;
			endx =  45;
			endy = 586;
			
		} else if(pageName.equalsIgnoreCase("LeaguePass"))
		{
			startx = 318;
			starty =295;
			endx = 57;
			endy = 305;
		}
		
		System.out.println("Start x "+startx);
		System.out.println("Start y "+starty);
		
		System.out.println("End x "+endx);
		System.out.println("Endy "+endy);
		
		
		MobileDriver mobileDriver = ((MobileDriver) driver);
		TouchAction action = new TouchAction((PerformsTouchActions) driver);
		// new
		// TouchAction(((MobileDriver)driver)).press(startx,endy).waitAction(Duration.ofMillis(20000)).moveTo(startx,starty).release().perform();
		 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endx, endy)).release().perform();

		try {
			HelperUtils.waitForElement();
			System.out.println("Swiped from right to left side **** ");
			Reporter.addStepLog("Swiped from Right to Left in "+pageName);
		} catch (InterruptedException e) {
			System.out.println("Unable to wait for some element while swiping in "+pageName);
			Reporter.addStepLog("Unable to swipe from Right to Left on "+pageName);
			HelperUtils.captureScreen();
		}
		System.out.println("Scroll done");
	
	}

	public static void swipeVerticallyToPixels(WebDriver driver, String x, String y) throws InterruptedException {
		   
		  try {
			System.out.println("Driver instance in swipe"+ driver.getClass());
			  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			   System.out.println("Dimension " + size);
			   
			   Double startyInDoub = Double.parseDouble(x);
			   Double endyInDoub = Double.parseDouble(y);
			  int starty = (int) (size.height * startyInDoub);
			  int endy = (int) (size.height * endyInDoub);
				 // int startx = (int) (size.width * 0.40);
			  int startx = (int)(size.width * 0.80);
				           // int startx = size.width / 2;
			 System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
			 MobileDriver mobileDriver = ((MobileDriver) driver);
			 TouchAction action = new TouchAction((PerformsTouchActions) driver);
			 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, endy)).release().perform();
			 Thread.sleep(3000);
			 System.out.println("Scroll done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void swipeBottomToTopInPixels(WebDriver driver, String x, String y) throws InterruptedException {
		   
		  try {
			System.out.println("Driver instance in swipe"+ driver.getClass());
			  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			   System.out.println("Dimension " + size);
			   
			   Double startyInDoub = Double.parseDouble(x);
			   Double endyInDoub = Double.parseDouble(y);
			  int starty = (int) (size.height * startyInDoub);
			  int endy = (int) (size.height * endyInDoub);
				 // int startx = (int) (size.width * 0.40);
			  int startx = (int)(size.width * 0.80);
				           // int startx = size.width / 2;
			 System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
			 MobileDriver mobileDriver = ((MobileDriver) driver);
			 TouchAction action = new TouchAction((PerformsTouchActions) driver);
			 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,endy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, starty)).release().perform();
			 Thread.sleep(3000);
			 System.out.println("Scroll done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void swipeBottomToTop(WebDriver driver) throws InterruptedException {
		   
		  try {
			System.out.println("Driver instance in swipe"+ driver.getClass());
			  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			   System.out.println("Dimension " + size);
			   
			  int starty = (int) (size.height * 0.70);
			  int endy = (int) (size.height * 0.30);
				 // int startx = (int) (size.width * 0.40);
			  int startx = (int)(size.width * 0.70);
				           // int startx = size.width / 2;
			 System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
			 MobileDriver mobileDriver = ((MobileDriver) driver);
			 TouchAction action = new TouchAction((PerformsTouchActions) driver);
			 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,endy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, starty)).release().perform();
			 Thread.sleep(3000);
			 System.out.println("Scroll done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean swipeReverse(WebDriver driver, int times) throws InterruptedException {
        int i = times;
        boolean result = false;
        while (i >= 1) {
            swipeBottomToTop(driver);
            i--;
            System.out.println("I value " + i);
        }

        return result;
    }
	 public static void swipeToElement(WebDriver driver, By byElement) throws InterruptedException, IOException {
			
			boolean presenceOfElement = false;
		    for(int i = 0; i<=4; i++)
		    {
		        presenceOfElement = HelperUtils.isElementPresent(driver, byElement, 5 );
		        System.out.println(" isElement visible ? "+presenceOfElement);
		        if(presenceOfElement)
		        {
		            System.out.println("isElement visible ? "+presenceOfElement);
		            break;
		        }
		        System.out.println("Element is not visible");
		        HelperUtils.swipeVertically(driver);
		        System.out.println("Swiped until the element "+byElement+ " is found");			
		    }	
		}

	 
	public static void swipeOnScoresPage() {
		WebDriver driver = AppiumApp.getCurrentDriver();
		  try {
			System.out.println("Driver instance in swipe"+ driver.getClass());
			  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			   System.out.println("Dimension " + size);

			  int starty = (int) (size.height * 0.60);
			  int endy = (int) (size.height * 0.40);
				 // int startx = (int) (size.width * 0.40);
			  int startx = (int)(size.width * 0.60);
				           // int startx = size.width / 2;
			 System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
			 MobileDriver mobileDriver = ((MobileDriver) driver);
			 TouchAction action = new TouchAction((PerformsTouchActions) driver);
			 new TouchAction(((MobileDriver)driver)).press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, endy)).release().perform();
			 Thread.sleep(3000);
			 System.out.println("Scroll done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	
}
