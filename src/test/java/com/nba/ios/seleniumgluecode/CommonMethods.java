package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AesCrypt;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.api.helper.JsonMenuResponse;
import com.nba.ios.utilities.ConfigPropertyReader;
import com.nba.ios.utilities.HelperUtils;
import com.nba.ios.utilities.Locators;
import com.nba.ios.utilities.LocatorsDataProvier;
import com.nba.ios.utilities.StandingsElements;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import net.bytebuddy.description.annotation.AnnotationValue.Loaded;


public class CommonMethods {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	public CommonMethods(){
	
	}
	
	public static void clickBtn(String btnLocator, String passComment, String failComment) {
        WebDriver driver = AppiumApp.getCurrentDriver();
        boolean isDisplay = false;
        try {
            String locator = LocatorsDataProvier.getDataMap(btnLocator);
            System.out.println("Locator for "+ btnLocator + " is "+locator);
            By by = null;
            if(btnLocator.contains("ID")) {
                by = AppElements.locateID(locator);
            } else {
                 by = AppElements.locateXPATH(locator);
            }
            driver.findElement(by).click();
            isDisplay = true;
            Reporter.addStepLog(passComment);
        } catch (Exception e) {
            System.out.println("Exception occured when clicking "+e.getMessage());
            isDisplay = false;
            Reporter.addStepLog(failComment);
           // HelperUtils.captureScreen();
            System.out.println("Captured Screen Shot **** ");
        }
    }
	
	public static void clickBtn(String btnLocator) {
        WebDriver driver = AppiumApp.getCurrentDriver();
        boolean isDisplay = false;
        try {
            String locator = LocatorsDataProvier.getDataMap(btnLocator);
            System.out.println("Locator for "+ btnLocator + " is "+locator);
            By by = null;
            if(btnLocator.contains("ID")) {
                by = AppElements.locateID(locator);
            } else {
                 by = AppElements.locateXPATH(locator);
            }
            driver.findElement(by).click();
            isDisplay = true;
        } catch (Exception e) {
            System.out.println("Exception occured when clicking "+e.getMessage());
            isDisplay = false;
           // HelperUtils.captureScreen();
            System.out.println("Captured Screen Shot **** ");
        }
    }
	public static boolean enter_Text(String textToEnter, String arg1, String passComment, String failComment) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isEntered = false;
		try {
			String locator = LocatorsDataProvier.getDataMap(arg1);
			// locate the Path and return it in By element
			By by = null;
			if (arg1.contains("ID")) {
				by = AppElements.locateID(locator);

			} else {
				by = AppElements.locateXPATH(locator);
			}

			driver.findElement(by).sendKeys(textToEnter);
			isEntered = true;
			Reporter.addStepLog(passComment);
		} catch (IOException e) {
			System.out.println("Error while entering the text "+e.getMessage());
			isEntered = false;
			Reporter.addStepLog(failComment);
		}
		return isEntered;
	}
	public static void clickBtn(By btnLocator, String passComment, String failComment) {
        WebDriver driver = AppiumApp.getCurrentDriver();
        boolean isDisplay = false;
        try {
            
            driver.findElement(btnLocator).click();
            isDisplay = true;
            Reporter.addStepLog(passComment);
        } catch (Exception e) {
            System.out.println("Exception occured when clicking "+e.getMessage());
            isDisplay = false;
            Reporter.addStepLog(failComment);
            HelperUtils.captureScreen();
            System.out.println("Captured Screen Shot **** ");
        }
    }
	
	/*
	 * wait until Home screen page is loaded. To do this, I am trying to get the name of first section text
	 * */
	public static void waitUntilAppHomeScreenLoads() throws InterruptedException {
		String text = null;
		int i = 0;
		while(i<=7)
		{
			try {
				text = CommonMethods.getTextWithoutReportPrintStatement("T1VideoorArticleText");
				if(text!=null && !text.isEmpty() && !(text == ""))
				{
					System.out.println("Text is found . breaking the wait "+text);
					break;
				}
				
			} catch (Exception e) {
				System.out.println("Text is not found ");
				HelperUtils.waitForElementToVisible();
				System.out.println("Waited "+i+ " time for the NBA App Page to load");
			}
			System.out.println("Text "+text);			
			i++;
		}
		
	}
	
	
	
	public static void waitUntilVideoScreenLoads() throws InterruptedException {
		String text = null;
		int i = 0;
		while(i<=7)
		{
			
				boolean isVideoPresent = CommonMethods.isElementPresent("PauseButtonID");
				if(isVideoPresent)
				{
					System.out.println("Video is Loaded. Breaking the loop");
					CommonMethods.clickBtn("PauseButtonID", "Pause button is clicked. Pausing the video", "Unable to Pause the video");
					break;
				} else {
					System.out.println("Element is not found ");
					HelperUtils.waitForElementToVisible();
					System.out.println("Waited "+i+ " time for the Video to load");
				}
				
			i++;
		}
		
	}
	public static String getTextWithoutReportPrintStatement(String arg1) {

		WebDriver driver = AppiumApp.getCurrentDriver();
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
			//Reporter.addStepLog("Exception occured while getting the text for "+arg1 + e.getMessage());
			HelperUtils.captureScreen();
		}
		return text;
		
	
		
	}
     public static void isPresentAndClick(String locator, String passComment, String failComment) {
		
    	 WebDriver driver = AppiumApp.getCurrentDriver();
         boolean isDisplay = false;
         try {
             //String locator = LocatorsDataProvier.getDataMap(arg1);
            // System.out.pri
               By by = AppElements.locateID(locator);    
 			isDisplay = driver.findElement(by).isDisplayed();
             if(isDisplay) {  
                 driver.findElement(by).click();
             } 
             Reporter.addStepLog(passComment);
         } catch (Exception e) {
             System.out.println("Exception occured when clicking "+e.getMessage());
             isDisplay = false;
             Reporter.addStepLog(failComment);
             HelperUtils.captureScreen();
             System.out.println("Captured Screen Shot **** ");
         }
	  }
     
     
     public static void isPresentAndClick(WebElement locator, String passComment, String failComment) {
 		
    	 WebDriver driver = AppiumApp.getCurrentDriver();
         boolean isDisplay = false;
         try {
               
 			isDisplay = locator.isDisplayed();
             if(isDisplay) {  
            	 locator.click();
             } 
             Reporter.addStepLog(passComment);
         } catch (Exception e) {
             System.out.println("Exception occured when clicking "+e.getMessage());
             isDisplay = false;
             Reporter.addStepLog(failComment);
             HelperUtils.captureScreen();
             System.out.println("Captured Screen Shot **** ");
         }
	  }
     
     public static void testElements(String path, String text) {
 		
 		try {
 			WebDriver driver = AppiumApp.getCurrentDriver();
 			System.out.println(text + " -- " +driver.findElement(By.xpath(path)).isDisplayed());
 			System.out.println(driver.findElements(By.xpath(path)).size());
 			
 			if(driver.findElements(By.xpath(path)).size() > 0)
 			{
 				System.out.println("Size is greater than 0 for "+text);
 			} else {
 				System.out.println("Size is 0");
 			}
 		} catch (Exception e) {
 			System.out.println(text + " is not displayed ");
 		}
 		
 	}
     
	public static void moveToVidep(String elem) {

		WebDriver driver = AppiumApp.getCurrentDriver();

		try {
			String locator = LocatorsDataProvier.getDataMap(elem);
			System.out.println("Locator for " + elem + " is " + locator);
			By by = null;
			if (elem.contains("ID")) {
				by = AppElements.locateID(locator);

			} else {
				by = AppElements.locateXPATH(locator);
			}
			WebElement element = driver.findElement(by);
			System.out.println("Going to move the video slider");
			Actions action = new Actions(driver);
			action.dragAndDropBy(element, 100, 0);
			
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			HelperUtils.captureScreen();
			System.out.println("Captured Screen Shot **** ");
		}
	}
	public static boolean isDisplayed(String arg1, String passComment, String failComment) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isDisplay = false;
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
			if(failComment.contains("Article"))
            {
                Reporter.addStepLog(RED_OPEN+failComment +COLOR_CLOSE);
            } else {
                Reporter.addStepLog(RED_OPEN+failComment +COLOR_CLOSE);
                HelperUtils.captureScreen();
            }
		}
		
		return isDisplay;
	}
	
	public static boolean isDisplayed(String arg1) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isDisplay = false;
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
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			isDisplay = false;
			HelperUtils.captureScreen();
			System.out.println("Captured Screen Shot **** ");
		}
		
		return isDisplay;
	}
	
	public static boolean isDisplayed(String arg1, WebDriver driver) {
		//WebDriver driver = AppiumApp.getCurrentDriver();
		
		System.out.println("Printing driver"+ driver.getClass());
		boolean isDisplay = false;
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
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			isDisplay = false;
			HelperUtils.captureScreen();
			System.out.println("Captured Screen Shot **** ");
		}
		
		return isDisplay;
	}
	public static boolean isDisplayed(By element) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isDisplay = false;
		try {
  
			driver.findElement(element).isDisplayed();
			isDisplay = true;
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			isDisplay = false;
			HelperUtils.captureScreen();
			System.out.println("Captured Screen Shot **** ");
		}
		
		return isDisplay;
	}
	public static boolean verifyJsonAndUIText(String uiText , String jsonText, String elementName)
	{
		boolean result = false;
		if(uiText.equalsIgnoreCase(jsonText))
		{
			Reporter.addStepLog("JSON Data is verified for "+GREEN_OPEN+ elementName +COLOR_CLOSE+ ". Both are same");
			result = true;
		} else {
			Reporter.addStepLog("JSON Data is verified for "+RED_OPEN+ elementName +COLOR_CLOSE+ ". Both are not same");
			result = false;
		}
		return result;
	}
	
	public static void waitForElement() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public static void waitForElementToVisible() throws InterruptedException {
		Thread.sleep(10000);
	}
	
	 public static boolean tapControlSingle(int x,int y, String comment){
			boolean isDisplay = false;

	        WebDriver driver = AppiumApp.getCurrentDriver();
	        try {
	        	System.out.println("x value"+x);
	        	System.out.println("y value"+y);

				new TouchAction((PerformsTouchActions)driver).tap(PointOption.point(x, y)).release().perform();
				isDisplay = true;
				Reporter.addStepLog(comment +" is clicked");

			} catch (Exception e) {
				isDisplay = false;
				Assert.fail(comment +"is not clicked"+ e.getMessage());
				//Reporter.addStepLog(comment +"is not clicked"+e.getMessage());
				e.printStackTrace();
			}
			return isDisplay;        
			
	    }
	
	 public static void tapControlDouble(int x,int y, String comment){
	        WebDriver driver = AppiumApp.getCurrentDriver();
            System.out.println("Double tapping on the element");
	        try {
	          	System.out.println("x value"+x);
	        	    System.out.println("y value"+y);
				new TouchAction((PerformsTouchActions)driver).tap(PointOption.point(x, y)).release().perform().tap(PointOption.point(x, y)).release().perform();
			} catch (Exception e) {
				Reporter.addStepLog(comment +"is not clicked"+e.getMessage());
				e.printStackTrace();
			}
	    }
	 
	 public static void validateHideShowScores(String locator) {
			//String text = getText(locator);
			
			String teamRank = null;
			teamRank = getText("teamRanking");
			System.out.println("Team Rank "+teamRank);
			
			String teamWin = null;
			teamWin = getText("teamWins");
			System.out.println("Team Win "+teamWin);
			
			String teamLose = null;
			teamLose = getText("teamLosses");
			System.out.println("Team Loss "+teamLose);
			
			if(teamRank.equalsIgnoreCase("—") && teamWin.equalsIgnoreCase("—") && teamLose.equalsIgnoreCase("—"))
			{
				Reporter.addStepLog("Scores are hided.");
				System.out.println("Scores are hided click on Show Score");
				CommonMethods.clickBtn("HomePage_TeamsSettings", "Clicked on Home Page Team Settings button", "Unable to click on Home Page Team Settings button");
				System.out.println("Going to click on Hide scores");	
				CommonMethods.clickBtn(locator, "Enabled Show Scores", "Unable to Enable Show Scores");
				teamRank = getText("teamRanking");
				System.out.println("Team Rank after clicking on Hide Score "+teamRank);
				teamWin =  getText("teamWins");
				System.out.println("Team Win after clicking on Hide Score "+teamWin);
				teamLose = getText("teamLosses");
				System.out.println("Team Lose after clicking on Hide Score "+teamLose);
				
				if(teamRank != null && teamWin !=null && teamLose != null)
				{
					Reporter.addStepLog("Team Rank "+COLOR_OPEN+teamRank + COLOR_CLOSE+ " Team Win "+COLOR_OPEN+teamWin +COLOR_CLOSE+  " Team Lose " +COLOR_OPEN+teamLose +COLOR_CLOSE);
				} else {
					Reporter.addStepLog(RED_OPEN+"Unable to enable the Scores from Team options"+COLOR_CLOSE);
					HelperUtils.captureScreen();
				}
				
			} else if(teamRank != null && teamWin !=null && teamLose != null)
			{
				System.out.println("Scores are enabled");
				Reporter.addStepLog("Scores are enabled.");
				
				CommonMethods.clickBtn("HomePage_TeamsSettings", "Clicked on Home Page Team Settings button", "Unable to click on Home Page Team Settings button");
				System.out.println("Going to click on Hide scores");	
				CommonMethods.clickBtn(locator, "Enabled Hide Scores", "Unable to Enable Hide Scores");
				teamRank = getText("teamRanking");
				System.out.println("Team Rank after clicking on Hide Score "+teamRank);
				teamWin =  getText("teamWins");
				System.out.println("Team Win after clicking on Hide Score "+teamWin);
				teamLose = getText("teamLosses");
				System.out.println("Team Lose after clicking on Hide Score "+teamLose);
				
				if(teamRank.equalsIgnoreCase("—") && teamWin.equalsIgnoreCase("—") && teamLose.equalsIgnoreCase("—"))
				{
					Reporter.addStepLog("Hided the Scores. Team Rank "+COLOR_OPEN +teamRank +COLOR_CLOSE+ " Team Win "+COLOR_OPEN+teamWin +COLOR_CLOSE + " Team Lose "+COLOR_OPEN+teamLose+COLOR_CLOSE);
				} else {
					Reporter.addStepLog(RED_OPEN+"Unable to Hide the Scores from Team options"+COLOR_CLOSE);
					HelperUtils.captureScreen();
				}
			}
			
		}
	
	public static void verifyVideoActions(String playerContainer) throws IOException, InterruptedException {

		
        	 
        	 Reporter.addStepLog("Video is loaded to play and the video player container is displayed");
        	 
          	// Check if advertisement is present
        	 
     		CommonMethods.verifyAdvertisement("AdverstiserID");

     		// Checking for the Pause Button
     		boolean isPauseButtonPresent = CommonMethods.isElementPresent("PauseButtonID");

     		if (!isPauseButtonPresent) {
     			System.out.println("Pause button is not located in the video played so clicking player container...........");
     			CommonMethods.clickBtn(playerContainer, playerContainer + " is clicked ",
     					playerContainer + " is not clicked");
    		     }

     		CommonMethods.clickBtn("PauseButtonID", "Pause button is clicked to pause the video",
     				"Unable to click the pause button");

     		CommonMethods.isDisplayed("VideoSlider", "Video Scrub Bar is displayed", "Unable to loacte the video Scrub bar in the video played");
     		
     		boolean isAirPlayPresent = CommonMethods.isDisplayed("AirPlayID");
     		if(isAirPlayPresent) {
     			Reporter.addStepLog("Air Play button is displayed in the video Played");
     		} else {
     			Reporter.addStepLog(RED_OPEN +  " Air Play button is not displayed in the video Played " +COLOR_CLOSE);
     		}
     		
     		// Sliding video element

     		/*
     		 * WebElement sliderElement = getWebElement("VideoSlider"); int width =
     		 * sliderElement.getSize().getWidth(); System.out.println("Width...."+width);
     		 * int moveToMiddle = (width - (width / 2));
     		 * System.out.println("moveToMiddle... " + moveToMiddle);
     		 * 
     		 * dragAndDropInPixel(sliderElement,width/10,
     		 * 0,"Able to move the slider to initial position",
     		 * "Unable to move the slider to initial Position");
     		 */
            String videoPlayedTime = CommonMethods.getText("VideoPlayedTime");
            Reporter.addStepLog("The time video played is " +COLOR_OPEN+ videoPlayedTime + COLOR_CLOSE);
            
            String totalVideoTime = CommonMethods.getText("VideoTotalTime");
            Reporter.addStepLog("The total duration of the video is " +COLOR_OPEN+ totalVideoTime + COLOR_CLOSE);
            
     		CommonMethods.isDisplayed("RewindButtonID", "RewindBtn is present in the video played",
     				"RewindBtn  is not present");
     		CommonMethods.isDisplayed("FastForwardButtonID", "FastForwardBtn is present for the played video",
     				"FastForwardBtn  is not present");
     		CommonMethods.isDisplayed("ClosedCaptionID", "ClosedCaption is present for the video played",
     				"ClosedCaption  is not present");

     		CommonMethods.validateFullScreenInVideo("FullScreenID", playerContainer);	
	}
	 
	private static void validateFullScreenInVideo(String element, String playerElement) throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();

		try {
			ScreenOrientation screenRotationBefore =((Rotatable) driver).getOrientation();
			Reporter.addStepLog("Current screen rotation before clicking the full screen buttion is "+ COLOR_OPEN + screenRotationBefore + COLOR_CLOSE);
			boolean isFullScreenBtn = CommonMethods.isElementPresent(element);
			if (!isFullScreenBtn) {
				System.out.println("Full Screen button is not present and so clicking the player container...........");
				CommonMethods.clickBtn(playerElement, playerElement + " is clicked ",
						playerElement + " is not clicked");			
			}
			
			if(CommonMethods.isDisplayed(element)) {
				Reporter.addStepLog("Full Screen button is displayed on the Video Player Page after clicking on the Player container");
				 CommonMethods.clickBtn(element, "Full Screen button is clicked", "Unable to click the full screen element");
		            Thread.sleep(3000);
					ScreenOrientation screenRotationAfter = ((Rotatable) driver).getOrientation();
					
					if(!screenRotationBefore.equals(screenRotationAfter)) {
						Reporter.addStepLog("Full Screen view is displayed. Screen mode after clicking the full screen button is "+ COLOR_OPEN + screenRotationAfter + COLOR_CLOSE);
					} else {
						Reporter.addStepLog("Full Screen view is not dispalyed");
						System.out.println("Full screen view is not displayed");
					}
					
					CommonMethods.tapControlSingle(65, 5, "Back Button");
					Thread.sleep(2000);

					CommonMethods.tapControlSingle(65, 5, "Back Button");
					Thread.sleep(3000);
			} else {
				Reporter.addStepLog(RED_OPEN +"Full Screen button is not displayed on the Video Player Page after clicking on the Player container" +COLOR_CLOSE);
				CommonMethods.tapControlSingle(65, 64, "Back Button");
				Thread.sleep(2000);

				CommonMethods.tapControlSingle(65, 64, "Back Button");
				Thread.sleep(3000);
			}         
			
		} catch (Exception e) {
			System.out.println("Exception occured in full screenValidation " + e.getMessage());
		}
	}

	public static WebElement getWebElement(String element) throws IOException {
		 
		 WebDriver driver = AppiumApp.getCurrentDriver();

			WebElement elementReturned = null;
			try {
				String locator = LocatorsDataProvier.getDataMap(element);
				System.out.println("Locator for element " + locator);
				By by = null;
				if ("VideoSlider".contains("ID")) {
					by = AppElements.locateID(locator);

				} else {
					by = AppElements.locateXPATH(locator);
				}
				elementReturned = driver.findElement(by);
			} catch (Exception e) {
				System.out.println("Exception occured in fetching the element "+ e.getMessage());
			}
			return elementReturned;
		 
	 }
	 
  
	
	public static String getText(By locator, int timeOut) {
		WebDriver driver = AppiumApp.getCurrentDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
        } catch (Exception e) {
            	System.out.println(".<br> The error message is " + e.getMessage());
            Reporter.addStepLog(".<br> The error message is " + e.getMessage());
            return null;
        }
    }
    
	public static String getText(String arg1) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		
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
	
	public static int getCalenderDate(String completeDate) {
		
		System.out.println("currentDateWithDay "+completeDate);
		   String [] arr =completeDate.split(",");
		   System.out.println("date and month is "+arr[1]);
		   String [] dateStr = arr[1].split(" ");
		   Integer date = Integer.valueOf(dateStr[2]);
		   return date;	
	}
	
	public static void dragAndDropInPixel(final WebElement elem, final int x, final int y, final String pc, final String fc) {
        try {
   		 WebDriver driver = AppiumApp.getCurrentDriver();
            Actions act = new Actions(driver);
            System.out.println("ELement in action class" +elem);
            act.moveToElement(elem).build().perform();
            System.out.println("entering actions class....");
            act.dragAndDropBy(elem, x, y).build();
            act.perform();
            System.out.println("complete action class function");
            Reporter.addScenarioLog(pc);
        } catch (Exception e) {
         Reporter.addStepLog(fc);       
           }
         }
	public static void verifyGamesIfPresent(String date, String dateValue) {
		
	boolean noGames = CommonMethods.isDisplayed("NoGamesAvailableText"); 
	if(noGames) {
		String noText = CommonMethods.getText("NoGamesAvailableText");
		Reporter.addStepLog("No Games are available for the " +  date + COLOR_OPEN +  dateValue+ COLOR_CLOSE+ " instead it displayes the text " +RED_OPEN+ noText+COLOR_CLOSE);
	} else {
		boolean isGamePresent = CommonMethods.isDisplayed("FirstGameTile");
		if(isGamePresent && !noGames) {
			Reporter.addStepLog("Games are displayed for the " +  date + COLOR_OPEN +  dateValue+ COLOR_CLOSE);
		}
	 }
	}
	
	public static boolean checkNoGamesDisplayed(String arg1, String date) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isDisplay = false;
		try {
			String locator = LocatorsDataProvier.getDataMap(arg1);
			System.out.println("Locator for "+ arg1 + " is "+locator);
			
			// locate the Path and return it in By element
			By by = null;
			if(arg1.contains("ID"))
			{
				by = AppElements.locateID(locator);
				
			} else {
				by = AppElements.locateXPATH(locator);
			}
			
			driver.findElement(by).isDisplayed();
			isDisplay = false;
			Reporter.addStepLog(RED_OPEN +"No Games are displayed for the date "+date +COLOR_CLOSE);
		} catch (Exception e) {
			
			System.out.println("Games are displayed for the date "+date );
			Reporter.addStepLog("Games are displayed for the date "+date);
			isDisplay = true;
			
		}
		
		return isDisplay;
	}
	
public static List<WebElement> getListofText(String locator) throws IOException{
		
		String locName = LocatorsDataProvier.getDataMap(locator);
		System.out.println("Loc name "+locName);
		
		List<WebElement> listElements = AppElements.locateXPATHList(locName);
		System.out.println("listElements "+listElements.size());
		for(WebElement we: listElements)
		{
			System.out.println(" Name  ********* "+we.getAttribute("name"));
		}
		
		return listElements;
		
	}


public static List<WebElement> getListofWebElemnts(String locator) throws IOException{
	
	String locName = LocatorsDataProvier.getDataMap(locator);
	System.out.println("Loc name "+locName);
	
	List<WebElement> listElements = null;
	try {
		listElements = AppElements.locateXPATHList(locName);
		System.out.println("listElements "+listElements.size());
	} catch (Exception e) {
		System.out.println("Exception occured while getting list of elemnts "+ e.getMessage());
	}
	
	return listElements;
	
}
/**
 * this method is specially written for clicking on the tile in scores page
 * @param playerContainer
 * @param BackBtn
 */
public static boolean clickOnTiles(String passComment, String failComment) {
	WebDriver driver = AppiumApp.getCurrentDriver();
	boolean clickable = false;
	try {
		//By by = AppElements.locateXPATH("//android.support.v7.widget.RecyclerView[contains(@resource-id,'id/score_cards')]//android.widget.FrameLayout[@index='"+tileNo+"']//android.widget.FrameLayout[@index='0']");
		//By by = AppElements.locateXPATH("//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/following-sibling::XCUIElementTypeOther[@name='AwayTeamContainer']");
		By by = AppElements.locateXPATH("(//XCUIElementTypeOther[@name='AwayTeamContainer'])[2]");	
		driver.findElement(by).click();
		clickable = true;
		Reporter.addStepLog(passComment);
	} catch (Exception e) {
		System.out.println("Exception occured while clicking on a tile " +e.getMessage());
		clickable = false;
		Reporter.addStepLog(failComment);
		HelperUtils.captureScreen();
		System.out.println("Captured Screen Shot **** ");
	}
	
	return clickable;
	
}

public static boolean clickOnTiles(By ele, String passComment, String failComment) {
	WebDriver driver = AppiumApp.getCurrentDriver();
	boolean clickable = false;
	try {
		driver.findElement(ele).click();
		clickable = true;
		Reporter.addStepLog(passComment);
	} catch (Exception e) {
		System.out.println("Exception occured while clicking on a tile " +e.getMessage());
		clickable = false;
		Reporter.addStepLog(failComment);
		HelperUtils.captureScreen();
		System.out.println("Captured Screen Shot **** ");
	}
	
	return clickable;
	
}
/**
 * specially written to click on second tile
 * @param passComment
 * @param failComment
 * @return
 */
public static boolean clickOnSecondTile(String passComment, String failComment) {
	WebDriver driver = AppiumApp.getCurrentDriver();
	boolean clickable = false;
	try {
		//By by = AppElements.locateXPATH("//android.support.v7.widget.RecyclerView[contains(@resource-id,'id/score_cards')]//android.widget.FrameLayout[@index='"+tileNo+"']//android.widget.FrameLayout[@index='0']");
		By by = AppElements.locateXPATH("//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/following-sibling::XCUIElementTypeOther[@name='AwayTeamContainer']");
		driver.findElement(by).click();
		clickable = true;
		Reporter.addStepLog(passComment);
	} catch (Exception e) {
		System.out.println("Exception occured while clicking on a tile " +e.getMessage());
		clickable = false;
		Reporter.addStepLog(failComment);
		HelperUtils.captureScreen();
		System.out.println("Captured Screen Shot **** ");
	}
	
	return clickable;
	
}


public static boolean isElementPresent(String arg1) {
	WebDriver driver = AppiumApp.getCurrentDriver();
	boolean isDisplay = false;
	try {
		String locator = LocatorsDataProvier.getDataMap(arg1);
		System.out.println("Locator for "+ arg1 + " is "+locator);
		
		// locate the Path and return it in By element
		By by = null;
		if(arg1.contains("ID"))
		{
			by = AppElements.locateID(locator);
			
		} else {
			by = AppElements.locateXPATH(locator);
		}
		
		driver.findElement(by).isDisplayed();
		isDisplay = true;
	} catch (Exception e) {
		
		isDisplay = false;
		
	}
	
	return isDisplay;
}


	public static void checkCrash() {
		try {
			System.out.println("Entering block to check crash");
			WebDriver driver = AppiumApp.getCurrentDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Map<String, Object> params = new HashMap<>();
			params.put("bundleId", "com.turner.nba.enterprise");
			final Long state = (Long)js.executeScript("mobile: queryAppState", params);
			System.out.println("Application state code is :"+state);

			if (state==1) {
				Reporter.addStepLog(RED_OPEN+" Application is crashed"+COLOR_CLOSE);
				
				driver.close();
				driver.quit();
			}

		} catch (Exception e){
			System.out.println("Application state error :" +e);

		}
	}
	
	public static String getAttributeText(String ele, String attr) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		String locator = null;
		String text = null;
		try {
			locator = LocatorsDataProvier.getDataMap(ele);
			// locate the Path and return it in By element
			By by = null;
			if(ele.contains("ID"))
			{
				by = AppElements.locateID(locator);
				
			} else {
				by = AppElements.locateXPATH(locator);
			}
			
			text = driver.findElement(by).getAttribute(attr);
			System.out.println("Locator for "+ ele + " is "+locator);
			System.out.println(" Attribute text for "+ele + " is "+text);
		} catch (IOException e) {
			System.out.println("Exception occured while getting the text for "+ele + e.getMessage());
			Reporter.addStepLog("Exception occured while getting the text for "+ele + e.getMessage());
			HelperUtils.captureScreen();
		}
		return text;
		
	}
	
	public static String getAttributeText(By ele, String attr) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		String text = null;
		try {	
			text = driver.findElement(ele).getAttribute(attr);
			System.out.println("Locator for " + " is "+ ele);
			System.out.println(" Attribute text for "+ele + " is "+text);
		} catch (Exception e) {
			System.out.println("Exception occured while getting the text for "+ele + e.getMessage());
			//Reporter.addStepLog("Exception occured while getting the text for "+ele + e.getMessage());
			HelperUtils.captureScreen();
		}
		return text;
		
	}
	
	public static String getAttributeText(WebElement ele, String attr) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		String text = null;
		try {	
			
			text = ele.getAttribute(attr);
			//System.out.println("Locator for " + " is "+ ele);
			System.out.println(" Attribute text for " + " is "+text);
		} catch (Exception e) {
			System.out.println("Exception occured while getting the text for "+ele + e.getMessage());
			//Reporter.addStepLog("Exception occured while getting the text for "+ele + e.getMessage());
			HelperUtils.captureScreen();
		}
		return text;
		
	}
	
	public static boolean swipeUntilElementFound(String element, int times) throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		int i = times;
		boolean result = false;
		while (i >= 1) {
			HelperUtils.swipeVertically(driver);
			i--;
			boolean isElementPresent = CommonMethods.isElementPresent(element);
			System.out.println("isElementPresent " + isElementPresent);
			if (isElementPresent) {
				result = true;
				System.out.println("Breaking loop since element is found");
				break;
			}
			System.out.println("I value " + i);
		}

		return result;
	}
	
	public static boolean swipeLeftToRightUntilElementFound(String element, int times) throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		int i = times;
		boolean result = false;
		while (i >= 1) {
			HelperUtils.swipeRightToLeftHorizontally(driver);
			i--;
			boolean isElementPresent = CommonMethods.isDisplayed(element);
			System.out.println("isElementPresent " + isElementPresent);
			if (isElementPresent) {
				result = true;
				System.out.println("Breaking loop since element is found");
				break;
			}
			System.out.println("I value " + i);
		}

		return result;
	}
	public static boolean swipeUntilElementFound(By element, int times) throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		int i = times;
		boolean result = false;
		while (i >= 1) {
			HelperUtils.swipeVertically(driver);
			i--;
			boolean isElementPresent = CommonMethods.isDisplayed(element);
			System.out.println("isElementPresent " + isElementPresent);
			if (isElementPresent) {
				result = true;
				System.out.println("Breaking loop since element is found");
				break;
			}
			System.out.println("I value " + i);
		}

		return result;
	}
	public static boolean swipeRightToLeftUntilElementFound(By element, int times) throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		int i = times;
		boolean result = false;
		while (i >= 1) {
			HelperUtils.swipeRightToLeftHorizontally(driver);
			i--;
			try {
				if(driver.findElement(element).isDisplayed()) {
					result = true;
					System.out.println("Breaking loop since element is found");
					break;
				} else {
					result = false;
				}
			} catch (Exception e) {
				result = false;
				System.out.println("Element not found " +e.getMessage());
			}
			System.out.println("I value " + i);
		}

		return result;
	}

	public static void waitForElement(String arg1, String passComment, String failComment) throws IOException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isDisplayed = false;	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locator = LocatorsDataProvier.getDataMap(arg1);
		System.out.println("Locator for "+ arg1 + " is "+locator);
		By by = null;
		if(arg1.contains("ID")) {
			by = AppElements.locateID(locator);
		} else {
            by = AppElements.locateXPATH(locator);
		}
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			if(element.isDisplayed()) {
				isDisplayed = true;
				Reporter.addStepLog(passComment);
			}
		} catch (Exception e) {
			System.out.println("Exception occured when finding is Displayed "+e.getMessage());
			isDisplayed = false;
			Reporter.addStepLog(failComment);
			HelperUtils.captureScreen();
			System.out.println("Captured Screen Shot **** ");
		}
	}
	
	public static By locatorValue(Locators locatorTpye, final String value) {
		By by = null;
		switch (locatorTpye) {
		case ID:
			by = By.id(value);
			break;
		case NAME:
			by = By.name(value);
			break;
		case XPATH:
			by = By.xpath(value);
			break;
		case CSS:
			by = By.cssSelector(value);
			break;
		case LINKTEXT:
			by = By.linkText(value);
			break;
		case PARTIAL_LINKTEXT:
			by = By.partialLinkText(value);
			break;
		case TAG_NAME:
			by = By.tagName(value);
			break;
		case CLASS_NAME:
			by = By.className(value);
			break;
		case NA:
			break;
		default:
			break;
		}
		return by;
	}
	
	public static synchronized WebElement locateElement( Locators locatorType, final String value) {
		By by = null;
		WebDriver driverLoc = AppiumApp.getCurrentDriver();
		System.out.println("Driver instance in locate element " +driverLoc.getClass());
		switch (locatorType) {
		case ID:
			by = By.id(value);
			break;
		case NAME:
			by = By.name(value);
			break;
		case XPATH:
			by = By.xpath(value);
			break;
		case CSS:
			by = By.cssSelector(value);
			break;
		case LINKTEXT:
			by = By.linkText(value);
			break;
		case PARTIAL_LINKTEXT:
			by = By.partialLinkText(value);
			break;
		case TAG_NAME:
			by = By.tagName(value);
			break;
		case CLASS_NAME:
			by = By.className(value);
			break;
		case NA:
			break;
		default:
			break;
		}
		try {
			return driverLoc.findElement(by);
		} catch (Exception e) {
			return null;
		}
	}

	
public static boolean verifyTeamNames(String actualTeam, String textToVerify ) {
	boolean result = false;
	System.out.println("actual team name"+actualTeam);
	System.out.println("Text to verify"+textToVerify);
	if(actualTeam.equalsIgnoreCase(textToVerify))
	{
		System.out.println("is present passed");
		Reporter.addStepLog(GREEN_OPEN+ actualTeam +COLOR_CLOSE+ " is present");
		result = true;
	} else {
		System.out.println("not present failed");
		Reporter.addStepLog(GREEN_OPEN+ actualTeam +COLOR_CLOSE+ " is not present");
		result = false;
	}
	return result;
}

public static void getListOfElement(String teamNameLocator, String textToVerify) throws IOException {
		
		
		String locator = LocatorsDataProvier.getDataMap(teamNameLocator);
		System.out.println("Locator for "+ teamNameLocator + " is "+locator);
		
		List<WebElement> listElements = AppElements.locateXPATHList(locator);
		boolean checkName = false;
		System.out.println("listElements "+listElements.size());
		for(WebElement we : listElements)
		{
			String name = we.getText();
			System.out.println(" We " +name);
			if(textToVerify.equalsIgnoreCase(name))
			{
				checkName = true;
				Reporter.addStepLog(teamNameLocator + " " +GREEN_OPEN+ textToVerify + COLOR_CLOSE+ " is present");
			}
		}
		
		// if none of the list name has the team name or city name then fail the statement
		if(!checkName)
		{
			Reporter.addStepLog(teamNameLocator + " " +textToVerify + " is not present");
		}
	}

	public static List<WebElement> locateElements(WebDriver d, Locators locatorType, final String value) {
		WebDriver driver = d;
		By by = null;
		switch (locatorType) {
		case ID:
			by = By.id(value);
			break;
		case NAME:
			by = By.name(value);
			break;
		case XPATH:
			by = By.xpath(value);
			break;
		case CSS:
			by = By.cssSelector(value);
			break;
		case LINKTEXT:
			by = By.linkText(value);
			break;
		case PARTIAL_LINKTEXT:
			by = By.partialLinkText(value);
			break;
		case TAG_NAME:
			by = By.tagName(value);
			break;
		case CLASS_NAME:
			by = By.className(value);
			break;
		case NA:
			break;
		default:
			break;
		}
		try {
			return driver.findElements(by);
		} catch (Exception e) {
			return null;
		}
	}

	
	public static List<WebElement> locateElements(Locators locatorType, final String value) {
		WebDriver driver = AppiumApp.getCurrentDriver();
		By by = null;
		switch (locatorType) {
		case ID:
			by = By.id(value);
			break;
		case NAME:
			by = By.name(value);
			break;
		case XPATH:
			by = By.xpath(value);
			break;
		case CSS:
			by = By.cssSelector(value);
			break;
		case LINKTEXT:
			by = By.linkText(value);
			break;
		case PARTIAL_LINKTEXT:
			by = By.partialLinkText(value);
			break;
		case TAG_NAME:
			by = By.tagName(value);
			break;
		case CLASS_NAME:
			by = By.className(value);
			break;
		case NA:
			break;
		default:
			break;
		}
		try {
			return driver.findElements(by);
		} catch (Exception e) {
			return null;
		}
	}

	public static void verifyAdvertisement(String loactor) throws InterruptedException {
		
		boolean isAdvPresent = CommonMethods.isElementPresent(loactor);
		if(isAdvPresent) {
			Reporter.addStepLog("Advertisement is present. Waiting for Some Time");
			Thread.sleep(25000);
		} else {
			Reporter.addStepLog("No Advertisement is displayed in video");
		}
		
	}

	public static void verifyCalendarDates() throws InterruptedException {

		   //Fetching current Date
			
		   String currentDateBeforeClciking =  CommonMethods.getText("CurrentCalenderDateID");
		   int currentDate = CommonMethods.getCalenderDate(currentDateBeforeClciking);
		   Reporter.addStepLog("The current date displayed in the calender view is "+COLOR_OPEN+currentDateBeforeClciking+COLOR_CLOSE);
		   System.out.println("Current date "+ currentDate);
		   
		   CommonMethods.verifyGamesIfPresent("Current Date", currentDateBeforeClciking);
		   
		   //Clicking previous arrow in Calender button  
		   CommonMethods.clickBtn("CalenderPreviousArrowID", "Previous Arrow button is clicked in the Calender", "Unable to click the Previous Arrow button");
		   CommonMethods.waitForElement();
		   
		   // Fetching Previous Date
		   String calenderDateOnClickingPrevious =  CommonMethods.getText("CurrentCalenderDateID");
	       int previousDate = CommonMethods.getCalenderDate(calenderDateOnClickingPrevious);
	       System.out.println("Previous Date "+previousDate);
	       
	       if(previousDate == currentDate - 1) {
	    	       System.out.println("Previous date is displayed as Expected on clicking the Previous Arrow in Calender "+COLOR_OPEN+ calenderDateOnClickingPrevious +COLOR_CLOSE);
	       } else {
	    	   System.out.println("Previous date is not displayed as Expected"+ calenderDateOnClickingPrevious);
	       }
	       
		   CommonMethods.verifyGamesIfPresent("Previous Date", calenderDateOnClickingPrevious);

	       //Clicking Next Day Arrow in Calender
	       
	       CommonMethods.clickBtn("CalenderNextDayArrowID", "Clicking the Next Day Arrow in Calender to move to the default current date from Previous date", "Unable to click the next day Arrow in calender");
	       CommonMethods.waitForElement();
	       CommonMethods.clickBtn("CalenderNextDayArrowID", "Clicking the Next Day Arrow in Calender to move to the advance date from current date", "Unable to click the next day Arrow in calender");

	      String futureDate = CommonMethods.getText("CurrentCalenderDateID");
	      int advanceDate = CommonMethods.getCalenderDate(futureDate);
	      System.out.println("Advance dtae"+ advanceDate);
	      
	      if(advanceDate == currentDate+1) {
	    	    Reporter.addStepLog("Advance date is displayed as expected on clicking the Next day Arrow in calender "+ COLOR_OPEN+futureDate +COLOR_CLOSE);
	      } else {
	    	    Reporter.addStepLog("Advance date is not displayed as expected");
	      }
	      
		   CommonMethods.verifyGamesIfPresent("Advance Date", futureDate);
		   
		// Land to default Date for validation
			CommonMethods.clickBtn("CalenderPreviousArrowID", "Clicked on Left Side Calendar Navigation to land back to the default date", "Unable to Click on Left Side Calendar Navigation");
			HelperUtils.waitForElement();
	}
	
	public static void teamNameReplaceAndClick(String teamNameLocator, String teamName, String passComment, String failComment) {

		WebDriver driver = AppiumApp.getCurrentDriver();
		//System.out.println("Driver instance in Common Methods "+driver.getPageSource());
		boolean isDisplay = false;
		try {
			String locator = LocatorsDataProvier.getDataMap(teamNameLocator);
			System.out.println("Locator for "+ teamNameLocator + " is "+locator);
			
			// locate the Path and return it in By element
			By by = null;
			if(teamNameLocator.contains("ID"))
			{
				by = AppElements.locateID(locator);
				
			} else {
				by = AppElements.locateXPATHDynamicValue(locator, teamName);
				System.out.println("By element "+by);
			}
			driver.findElement(by).click();
			isDisplay = true;
			Reporter.addStepLog(passComment);
		} catch (Exception e) {
			System.out.println("Exception occured when clicking "+e.getMessage());
			isDisplay = false;
			Reporter.addStepLog(failComment);
			HelperUtils.captureScreen();
			System.out.println("Captured Screen Shot **** ");
		}
	
	}
    
	public static void verifyGameType(String gameTypeLoc, String buttons) throws IOException, InterruptedException {
		String datedisplayed = getText("CurrentCalenderDateID");
		boolean gamesFoundFlag = false;
		String gameStateName = null;
		if(gameTypeLoc.contains("Final"))
		{
			gameStateName = "Final";
			// check is games are displayed
			if(CommonMethods.checkNoGamesDisplayed("NoGamesDisplayed", datedisplayed))
			{
				System.out.println("Games are found in first try");
				validateGameDetails(gameTypeLoc, buttons, gameStateName);
				gamesFoundFlag = true;
				
			} else {
				System.out.println("Games are not found in first try");
				// navigate back and check final state games
				int j =0;
				while(j<3)
				{
					CommonMethods.clickBtn("LeftCalNavigation", "Clicked on Left Side Calendar Navigation", "Unable to Click on Left Side Calendar Navigation");
					HelperUtils.waitForElement();
					datedisplayed = getText("DisplayedDate");
					
					if(CommonMethods.checkNoGamesDisplayed("NoGamesDisplayed", datedisplayed))
					{
						gamesFoundFlag = true;
						validateGameDetails(gameTypeLoc, buttons, gameStateName);
						break;
					}
					j++;
				}
				
				
			}
			if(!gamesFoundFlag)
			{
				Reporter.addStepLog(RED_OPEN+" Final Games are not found even after clicking on 3 to 4 previous dates" + COLOR_CLOSE);
			} else {
				System.out.println("Game state validations are done");
			}
			
		} else if(gameTypeLoc.contains("Future"))
		{
			gameStateName = "Future";
			CommonMethods.clickBtn("RightCalNavigation", "Clicked on Right Side Cal Navigation to get future games", "Unable to click on Right Side cal Navigation");
			HelperUtils.waitForElement();
			datedisplayed = getText("DisplayedDate");
			// check is games are displayed
			if(CommonMethods.checkNoGamesDisplayed("NoGamesDisplayed", datedisplayed))
			{
				gamesFoundFlag = true;
				// check if time is displayed for upcoming games
				String s = CommonMethods.getText(gameTypeLoc).replaceAll(":", ".");
				System.out.println("s **** "+s);
				if(s.matches("-?\\d+(\\.\\d+)?"))
				{
					System.out.println("String is a number. Hence it is coming date");
					validateGameDetails(gameTypeLoc, buttons,gameStateName );
				} else {
					System.out.println("String is not a number. It is not future game");
					Reporter.addStepLog("Future Game is not found in "+s);
				}
					
				
			} else {
				System.out.println("Future Games are not found in first try");
				// navigate back and check final state games
				int j =0;
				while(j<3)
				{
					CommonMethods.clickBtn("RightCalNavigation", "Clicked on Right Side Calendar Navigation", "Unable to Click on Right Side Calendar Navigation");
					HelperUtils.waitForElement();
					datedisplayed = getText("DisplayedDate");
					if(CommonMethods.checkNoGamesDisplayed("NoGamesDisplayed", datedisplayed))
					{
						gamesFoundFlag = true;
						String s =  CommonMethods.getText(gameTypeLoc).replaceAll(":", ".");
						System.out.println("s **** "+s);
						if(s.matches("-?\\d+(\\.\\d+)?"))
						{
							System.out.println("String is a number. Hence it is coming date");
							validateGameDetails(gameTypeLoc, buttons,gameStateName );
						} else {
							System.out.println("String is not a number. It is not future game");
							Reporter.addStepLog("Future Game is not found in "+s);
						}
						break;
					}
					j++;
				}
			
			}
			if(!gamesFoundFlag)
			{
				Reporter.addStepLog(RED_OPEN+" Final Games are not found even after clicking on 3 to 4 previous dates" + COLOR_CLOSE);
			} else {
				System.out.println("Game state validations are done");
			}
		}
		
	}

	private static void validateGameDetails(String gameTypeLoc, String buttons, String gameStateName) throws IOException, InterruptedException {
		
		boolean gameStateDisplay = CommonMethods.isDisplayed(gameTypeLoc, gameStateName +" Game is displayed", gameStateName+" Game is not displayed. Need to swipe and check");
		if(gameStateDisplay)
		{
			ScoreBoardFunctionsNew.validateBtnsDisplayed(buttons, gameStateName);
		} else {
			swipeAndCheck(gameTypeLoc, buttons, gameStateName);
			
		}
		
	}
	
	private static void swipeAndCheck(String gameTypeLoc, String buttons, String gameStateName) throws IOException, InterruptedException {
		int i = 0;
		boolean gameStateDisplay = false;
		if(gameStateName.contains("Final"))
		{
			CommonMethods.clickBtn("LeftCalNavigation", "Clicked on Left Side Calendar Navigation", "Unable to Click on Left Side Calendar Navigation");
			HelperUtils.waitForElement();
		} else {
			CommonMethods.clickBtn("RightCalNavigation", "Clicked on Right Side Calendar Navigation", "Unable to click on Right Side Claendar Navigation");
			HelperUtils.waitForElement();
		}
		while(i<5)
		{
			HelperUtils.swipeOnScoresPage();
			gameStateDisplay = CommonMethods.isElementPresent(gameTypeLoc);
			if(gameStateDisplay)
			{
				ScoreBoardFunctionsNew.validateBtnsDisplayed(buttons, gameStateName);
				break;
			}
			i++;
			
		}
		
	}

	
		/**
	     * Navigation Bar verification with Menus.json data
	     * @param navJsonName
	     * @throws IOException
	     */
	    public static void verifyNavigationBar(String navJsonName) throws IOException {
	        
	        String menusJsonResponse = JsonMenuResponse.getMenusJsonResponse();
	        
	        ArrayList<String> expectedMenuNames = JsonMenuResponse.getMenuNames(menusJsonResponse);
	        
	        if(expectedMenuNames.size() > 1)
	        {
	            System.out.println("expectedMenuNames size "+expectedMenuNames.size());
	            
	            for(int i = 0; i< expectedMenuNames.size(); i++)
	            {
	                String getJsonMenuName = expectedMenuNames.get(i).trim();
	                System.out.println("getJsonName "+getJsonMenuName);
	                
	                if(getJsonMenuName.equalsIgnoreCase("Home")) {
	                	getJsonMenuName = "NBA Home Screen";
	                } else if(getJsonMenuName.equalsIgnoreCase("Scores")) {
	                	getJsonMenuName = "Games";
	                } else if(getJsonMenuName.equalsIgnoreCase("NBA TV")) {
	                	getJsonMenuName = "TV Provider";
	                } else if(getJsonMenuName.equalsIgnoreCase("")) {
	                	  
	                }
	                boolean isDisplay = CommonMethods.navigationBarDisplay(getJsonMenuName);
	                if(isDisplay)
	                {
	                    Reporter.addStepLog(GREEN_OPEN+" JSON Menu Name : "+ COLOR_OPEN+getJsonMenuName +COLOR_CLOSE+ " is displayed as expected in the NBA App Navigation bar"+COLOR_CLOSE);
	                } else {
	                    Reporter.addStepLog(RED_OPEN+"JSON Menu Name : "+COLOR_OPEN+getJsonMenuName +COLOR_CLOSE+ " is not displayed as expected in the navigation Bar"+COLOR_CLOSE);
	                }
	                
	            }
	        }
	        
	        
	    }

	    private static boolean navigationBarDisplay(String getJsonMenuName) {
	        WebDriver driver = AppiumApp.getCurrentDriver();
	        boolean isDisplay = false;
	        try {        	
	            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='"+getJsonMenuName+"']")).isDisplayed();
	            isDisplay = true;
	        } catch (Exception e) {
	            System.out.println("Exception occured while finding the menu name "+getJsonMenuName + " in NBA App");
	            isDisplay = false;
	        }
	        return isDisplay;
	    }

	    /**
		 * Toggle Mode verification for the options displayed in
		 * Notification Page
		 * @param toggleLocator
		 */
		public static void verifyToggleActions_Notification(String toggleLocator) {
			
			String toggleMode = CommonMethods.getAttributeText(toggleLocator,"value");
			if(toggleMode.equalsIgnoreCase("1"))
			{
				Reporter.addStepLog(toggleLocator + " Mode is enabled.");
				CommonMethods.clickBtn(toggleLocator, "Disabling the toggle mode of "+toggleLocator, "Unable to disable the toggle mode "+toggleLocator);
			} else {
				Reporter.addStepLog( toggleLocator +" Mode is disabled.");
				CommonMethods.clickBtn(toggleLocator, "Enabling the toggle mode of "+toggleLocator, "Unable to Enable the toggle mode of " +toggleLocator);
			}
		}
		
		/**
		 * Toggle mode verifications for Teams notifications
		 * 
		 * @param toggleLocator
		 * @throws IOException 
		 * @throws InterruptedException 
		 */

		public static void verifyToggleActions_Team(String team) throws IOException, InterruptedException {
			
			for(int i=1; i<=6; i++) {
				
			String toggleName =	CommonMethods.getAttributeText(StandingsElements.toggleAction(team, i),"name");
			String toggleMode =	CommonMethods.getAttributeText(StandingsElements.toggleAction(team, i),"value");
			if(toggleMode.equalsIgnoreCase("1"))
			{
				Reporter.addStepLog(toggleName + " Mode is enabled.");
				CommonMethods.clickBtn(StandingsElements.toggleAction(team, i), "Disabling the toggle mode of "+toggleName, "Unable to disable the toggle mode "+toggleName);
			} else {
				Reporter.addStepLog( toggleName +" Mode is disabled.");
				CommonMethods.clickBtn(StandingsElements.toggleAction(team, i), "Enabling the toggle mode of "+toggleName, "Unable to Enable the toggle mode of " +toggleName);
			}
			Thread.sleep(3000);
			}
			
			
		/*	List<WebElement> toggles = CommonMethods.getListofWebElemnts(toggleLocator);
			System.out.println("toggles size "+ toggles.size());
			Reporter.addStepLog("The available toggles under the team "+ toggleLocator +" are "+ toggles.size());
			
			for (int i=0; i<toggles.size(); i++) {
				String toggleName = toggles.get(i).getAttribute("name");
				String toggleMode = toggles.get(i).getAttribute("value");
				if(toggleMode.equalsIgnoreCase("1"))
				{
					Reporter.addStepLog(toggleName + " Mode is enabled.");
					CommonMethods.clickBtn(toggleName, "Disabling the toggle mode of "+toggleName, "Unable to disable the toggle mode "+toggleName);
				} else {
					Reporter.addStepLog( toggleName +" Mode is disabled.");
					CommonMethods.clickBtn(toggleName, "Enabling the toggle mode of "+toggleName, "Unable to Enable the toggle mode of " +toggleName);
				}
				Thread.sleep(3000);
			}*/
		}

		public static void verifyOnOffMode_AllNotifications() throws InterruptedException, IOException {
			/**
			 * Verify On and Off functionality in All Notification option which is displayed
			 * in Notification page
			 * @throws InterruptedException
			 * @throws IOException
			 */
		
				String getMode = CommonMethods.getAttributeText("Notifications_Toggle", "value");
				System.out.println("getMode "+getMode);
				if(getMode.equalsIgnoreCase("1"))
				{
					Reporter.addStepLog("All Notification is ON");
					// switch off and check content is hided
					CommonMethods.clickBtn("Notifications_Toggle", "Disabling All Notifications", "Unable to disable All Notification");
					CommonMethods.waitForElement();
					getMode = CommonMethods.getAttributeText("Notifications_Toggle", "value");
					System.out.println("getMode "+getMode);
					boolean isEmptyList = CommonMethods.isDisplayed("EmptyList_ID");
					if(getMode.equalsIgnoreCase("0") && isEmptyList)
					{
						Reporter.addStepLog(COLOR_OPEN+" Notifications are disabled and no list are displayed"+COLOR_CLOSE);
					} else {
						Reporter.addStepLog(RED_OPEN+ "Unable to disable " +COLOR_CLOSE);
					}
					CommonMethods.clickBtn("Notifications_Toggle", "Enabling Notifications toggle for other actions to perform", "Unable to Enable Notification toggle to perform other actions");
					CommonMethods.waitForElement();
				} else {
					// mode is off . Enable it
					CommonMethods.clickBtn("Notifications_Toggle", "Enabling Notifications since it is disabled", "Unable to Enable All Notification");
					CommonMethods.waitForElement();
				}
				
			}
		
		
		/**
		 * get team names from All Notification teams page and click on the given no
		 * @param teamNamesLocator
		 * @param teamNo
		 * @throws IOException 
		 * @throws InterruptedException 
		 */
		public static void verifyTeamsInAllNotification(String teamNamesLocator, String teamNo) throws IOException, InterruptedException {
			int teamNumber = Integer.parseInt(teamNo);
			List<WebElement> allTeamNames = CommonMethods.getListofWebElemnts(teamNamesLocator);
			if(allTeamNames.size() > 1)
			{
				for(int i =0;i<allTeamNames.size(); i++)
				{
					if(i == teamNumber)
					{
						
						System.out.println("entering for loop for i"+ i);
						String teamName = allTeamNames.get(i).getText();
						System.out.println("allTeamName "+ teamName);

						allTeamNames.get(i).click();
						String teamNameInPage = CommonMethods.getAttributeText("Notifications_TeamPage", "name");
						if(teamName.equalsIgnoreCase(teamNameInPage)) {
							Reporter.addStepLog("The Team " + COLOR_OPEN+  teamNameInPage +COLOR_CLOSE + " is clicked from the Teams Page in Notifications" );
						List<WebElement> toggleButtons = CommonMethods.getListofWebElemnts("TeamPage_Toggles");
						
						for(int j = 0 ; j < toggleButtons.size(); j++) {
							String toggleButton = toggleButtons.get(j).getAttribute("name");
							toggleButtons.get(j).click();
							Reporter.addStepLog("Enabling the toggle switch "+ toggleButton + " for the Team " + teamNameInPage );
						}
						
						CommonMethods.clickBtn("TeamsPageID", "Clicking on the back arrow button to navigae back to the Teams Page", "Unable to click the back arrow button");
												
						
						List<WebElement> teamNamesInMyNotifications = CommonMethods.getListofWebElemnts("TeamName_MyNotifications");
						
						for(int j= 0; j < teamNamesInMyNotifications.size(); j++) {
							String expectedTeamName = teamNamesInMyNotifications.get(j).getText();
							
							if(teamNameInPage.equalsIgnoreCase(expectedTeamName)) {
								Reporter.addStepLog("The Notifications for the Team "+COLOR_OPEN+ expectedTeamName +COLOR_CLOSE+ " is enabled from Notifications Page and displayed under My Notifications");
							     break;
							} else {
							  System.out.println("The Team name "+ teamNameInPage + " is not added to the My Notifications and it is not enabled");
							}
						}

					/*	if(teamNameInPage.equalsIgnoreCase(teanNameInMyNotifications)) {
							Reporter.addStepLog("The Notifications for the Team "+COLOR_OPEN+ teanNameInMyNotifications +COLOR_CLOSE+ " is enabled from Notifications Page");
							
							CommonMethods.clickBtn("TeamName_MyNotifications", "Clicking on the Notifications enabled - Team Name "+ teanNameInMyNotifications + " to disable the toggles" , "Unable to click on the Team Name in My Notifications");
							CommonMethods.waitForElement();
							for(int j = 0 ; j < toggleButtons.size(); j++) {
								String toggleButton = toggleButtons.get(j).getAttribute("name");
								//toggleButtons.get(j).click();
								CommonMethods.isPresentAndClick(toggleButtons.get(j), "Disabling the toggle switch "+ toggleButton + " for the Team " + teamNameInPage, "");
								//Reporter.addStepLog("Disabling the toggle switch "+ toggleButton + " for the Team " + teamNameInPage );
							}
							
							CommonMethods.clickBtn("TeamsPageID", "Clicking on the back arrow button to navigae back to the Teams Page", "Unable to click the back arrow button");

							
						 } else {
							 Reporter.addStepLog("The Team name "+ teanNameInMyNotifications + " is not added to the My Notifications and it is not enabled");
						 }*/
						} else {
							Reporter.addStepLog("Unable to navigate to the Team page "+ teamNameInPage +" from Notifications ");
						}
											
					}
							
				}
			} else {
				Reporter.addStepLog(RED_OPEN + " Team Names are not displayed on the clicking the teams Notications from Notifications Page "+ COLOR_CLOSE);
			}
			
		}

		
		/**
		 * Creates new user account from settings page
		 * @throws InterruptedException
		 */
		public static void createAccount() throws InterruptedException {
			
			//String emailAddress = ConfigPropertyReader.getPropertyValue("emailAddress");
			String currentDate = new SimpleDateFormat("MM-dd-hh:mm:ss").format(new Date());
			String emailAddress = "TestUser_"+currentDate+"@gmail.com";
			String password = ConfigPropertyReader.getPropertyValue("PASSWORD");
			 
		      String decryptPassword = null;
		      try {
		          decryptPassword = AesCrypt.decrypt(password);
		      } catch (Exception e) {
		          System.out.println("Decrypted password exception "+e.getMessage());
		      }
		      
			String postalCode = ConfigPropertyReader.getPropertyValue("postalCode");

			
			CommonMethods.enter_Text(emailAddress, "CreateAccount_EmailAddressField", "Entered Email Address " +COLOR_OPEN+emailAddress+COLOR_CLOSE, "Unable to Enter Email Address");
			//CommonMethods.waitForElement();
			CommonMethods.enter_Text(decryptPassword, "CreateAccount_PasswordField", "Entered Password", "Unable to enter Password");
			CommonMethods.enter_Text(postalCode, "CreateAccount_PostalField", "Entered Postal Code "+COLOR_OPEN+postalCode +COLOR_CLOSE, "Unable to enter postal code");
			CommonMethods.clickBtn("TermsAndConditions_CheckBox", "Clicked on Terms and Conditions Check Box", "Unable to click on Terms and Conditions Check Box");
			CommonMethods.clickBtn("CreateAccountSignupBtn", "Clicked on Create Account Signup Btn", "Unable to click on Create Account Sign Up Btn");

			
			HelperUtils.waitForElementToVisible();
			
			if(CommonMethods.isElementPresent("SignOutID"))
			{
				System.out.println("Sign Out is displayed");
				String createdUser = CommonMethods.getText("CreatedUserName");
				Reporter.addStepLog("Successfully created new user " +COLOR_OPEN+ createdUser +COLOR_CLOSE +" SignOut Button is displayed");
				CommonMethods.clickBtn("SignOutID", "Clicked on the Sign Out Button", "Unable to click on the Sign Out Button");
				HelperUtils.waitForElement();

			      CommonMethods.tapControlSingle(273, 407, "Sign Out Ok Button");

				HelperUtils.waitForElement();
				
			} else {
				System.out.println("Sign Out button is not displayed*** ");
				Reporter.addStepLog(RED_OPEN+"Unable to create new account" +COLOR_CLOSE);
			}
			
		}
		

		/**
		 * Creates new user account from Onboarding page
		 * @throws InterruptedException
		 */
		public static void createAccountInOnboarding() throws InterruptedException {
			
			//String emailAddress = ConfigPropertyReader.getPropertyValue("emailAddress");
			String currentDate = new SimpleDateFormat("MM-dd-hh:mm:ss").format(new Date());
			String emailAddress = "TestUser_"+currentDate+"@gmail.com";
			String password = ConfigPropertyReader.getPropertyValue("password");
			String postalCode = ConfigPropertyReader.getPropertyValue("postalCode");

			
			CommonMethods.enter_Text(emailAddress, "CreateAccount_EmailAddressField", "Entered Email Address " +COLOR_OPEN+emailAddress+COLOR_CLOSE, "Unable to Enter Email Address");
			//CommonMethods.waitForElement();
			CommonMethods.enter_Text(password, "CreateAccount_PasswordField", "Entered Password", "Unable to enter Password");
			CommonMethods.enter_Text(postalCode, "CreateAccount_PostalField", "Entered Postal Code "+COLOR_OPEN+postalCode +COLOR_CLOSE, "Unable to enter postal code");
			CommonMethods.clickBtn("TermsAndConditions_CheckBox", "Clicked on Terms and Conditions Check Box", "Unable to click on Terms and Conditions Check Box");
			CommonMethods.clickBtn("CreateAccountSignupBtn", "Clicked on Create Account Signup Btn", "Unable to click on Create Account Sign Up Btn");
		
			HelperUtils.waitForElementToVisible();
			
		}
}
