package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.sql.Driver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.JsonScoreBoardValidation;
import com.nba.ios.api.helper.JsonTeamsValidation;
import com.nba.ios.testrunner.TestRunner;
import com.nba.ios.utilities.HelperUtils;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;

public class AppHomeScreenFunctions {
	

	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String COLOR_CLOSE = "</font>";
	
	public static String RED_OPEN = "<font color=\"red\">";
	public static String RED_CLOSE = "</font>";
	
	
	
	
	@FindBy(id="com.nbadigital.gametimelite:id/calendar_module_date_text")
	WebElement dateElement;
	
	@FindBy(id="NBA Home Screen")
	WebElement HomeButton;
	
	static By homeScreen = By.id("NBA Home Screen");
	
	static By videoElement1By = By.xpath("//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]");
	
	@FindBy(xpath="//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]")
	WebElement videoElement1;
	
	@FindBy(xpath="//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[2]")
	WebElement videoElement1Text;

	@FindBy(xpath="//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[2]")
	WebElement videoElement2;
	
	@FindBy(xpath="//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[2]//XCUIElementTypeStaticText")
	WebElement videoElement2Text;
	
	@FindBy(xpath="//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[3]")
	WebElement videoElement3;
	
	@FindBy(xpath="//XCUIElementTypeNavigationBar[@name='Home']/..//XCUIElementTypeCollectionView/XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[3]//XCUIElementTypeStaticText")
	WebElement videoElement3Text;

	@FindBy(xpath="//XCUIElementTypeApplication[@name='NBA']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]")
	WebElement containerElement1;
	
	@FindBy(xpath ="//XCUIElementTypeApplication[@name='NBA']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]")
	WebElement containerElement1Text;
	
	@FindBy(xpath ="//XCUIElementTypeApplication[@name='NBA']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[2]")
	WebElement containerElement2;
	
	@FindBy(xpath ="//XCUIElementTypeApplication[@name='NBA']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[2]/XCUIElementTypeStaticText")
	WebElement containerElement2Text;
	
	@FindBy(xpath ="//XCUIElementTypeApplication[@name='NBA']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[3]")
	WebElement containerElement3;
	
	@FindBy(xpath ="//XCUIElementTypeApplication[@name='NBA']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[3]/XCUIElementTypeStaticText")
	WebElement containerElement3Text;
	
	@FindBy(id ="More from the NBA")
	WebElement MoreElement;
	
	static By MoreElem = By.id("More from the NBA");

	@FindBy(xpath="//XCUIElementTypeStaticText[@name='WATCH']")
	WebElement WatchButton;
	
	static By myTeams = By.id("MY TEAMS");

	@FindBy(id="MY TEAMS")
	WebElement myTeamsElem;
	
	static By editorialPicks = By.id("EDITOR'S PICKS");
	
	@FindBy(id ="EDITOR'S PICKS")
	WebElement editorialPicksElem;

	static By headlineElem = By.id("HEADLINES");

	@FindBy(id ="HEADLINES")
	WebElement HeadlineElement;
	
	

	
	public static WebDriver driver;

	
	public AppHomeScreenFunctions(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	

	public void isScreenPresent(String arg1) {
		WebElement element = null;
		 if(arg1.equalsIgnoreCase("NBA Home Screen Logo")) {
			element = HomeButton;
		} else if(arg1.equalsIgnoreCase("More from the NBA")) {
			element = MoreElement;
		} else if(arg1.equalsIgnoreCase("Watch Live")) {
			element = WatchButton;
		} else if(arg1.equalsIgnoreCase("Headlines")) 
		{
		    element = HeadlineElement;
		} else if(arg1.equalsIgnoreCase("MyTeams")) {
			element = myTeamsElem;
			System.out.println("My teams element"+element);
		}
		if(element.isDisplayed()) {
			Reporter.addStepLog(arg1 +"  is displayed ");
			System.out.println(arg1 +"  is displayed");
		} else {
			Reporter.addStepLog(arg1 +"  is not displayed ");
			System.out.println(arg1 +"screen is not displayed");
		}
	}
	

   /*public void swipeVertically() throws InterruptedException {
	   
	  try {
		System.out.println("Driver instance in swipe"+ driver.getClass());
		  org.openqa.selenium.Dimension size = driver.manage().window().getSize();
		   System.out.println("Dimension " + size);

		  int starty = (int) (size.height * 0.70);
		  int endy = (int) (size.height * 0.30);
			 // int startx = (int) (size.width * 0.40);
		  int startx = (int)(size.width * 0.80);
			           // int startx = size.width / 2;
		 System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
		 MobileDriver mobileDriver = ((MobileDriver) driver);
		 TouchAction action = new TouchAction((PerformsTouchActions) driver);
		 new TouchAction(((MobileDriver)driver)).press(startx,starty).waitAction(Duration.ofMillis(1000)).moveTo(startx,endy).release().perform();
		 HelperUtils.waitForElementToVisible();
		 System.out.println("Scroll done");
	} catch (Exception e) {
		e.printStackTrace();
	}

	 }*/
     


public void swipeToElement(String arg1) throws InterruptedException, IOException {
	By element = null;
	if(arg1.equalsIgnoreCase("Headlines")) {
		element = headlineElem;
		System.out.println("Printing element while swiping"+element);
	} else if(arg1.equalsIgnoreCase("More from the NBA")) {
		element = MoreElem;
	} else if(arg1.equalsIgnoreCase("MyTeams")) {
		element = myTeams;
		System.out.println("Print My Team elem "+element);
	}
	
	boolean presenceOfElement = false;
    for(int i = 0; i<= 3; i++)
    {
        presenceOfElement = HelperUtils.isElementPresent(driver, element, 10 );
        System.out.println(arg1+" visisble ? "+presenceOfElement);
        if(presenceOfElement)
        {
            System.out.println(arg1+" is visisble ? "+presenceOfElement);
            break;
        }
        System.out.println(arg1+" is not viisble");
       // swipeVertically();
		Reporter.addStepLog("Swiped until the element "+arg1+ " is found");
		
    }	
}

public boolean isHomeScreeenPresent(String screen) {
	

    WebDriverWait wait = new WebDriverWait(driver, 60);
    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeScreen));
		Reporter.addStepLog(screen+" is displayed");
        return true;
    } catch (Exception e) {
		Reporter.addStepLog(RED_OPEN+screen+" is not displayed"+RED_CLOSE);
        return false;

    }
}




public void verifyHomeScreenFunctions(String section) throws InterruptedException, IOException {
	
	boolean homeScreen = HelperUtils.isDisplayed(HomeButton);
	if(homeScreen) {
		System.out.println(homeScreen + "Home screen is present " + " And section name "+section);
		if(section.equalsIgnoreCase("video section")) {
			validateContainerSection();

		} else if(section.equalsIgnoreCase("More from the NBA")) {
			HelperUtils.swipeToElement(driver, MoreElem);
			boolean moreElem = HelperUtils.isDisplayed(MoreElement);
			if(moreElem) {
				System.out.println("more from the NBA setion is present");
				Reporter.addStepLog("More from NBA section is displayed on the App home screen");	
			} else {
				System.out.println("more not present");
				Reporter.addStepLog(RED_OPEN+"More from NBA section is not displayed on the App home screen"+RED_CLOSE);	
			}	
		} else if(section.equalsIgnoreCase("Headlines")) {
			HelperUtils.swipeToElement(driver, headlineElem);
           boolean headlineSection = HelperUtils.isDisplayed(HeadlineElement);
           if(headlineSection) {
        	       System.out.println("headline section present");
				Reporter.addStepLog("Headline section is displayed on the App home screen");	
           } else {
    	       System.out.println("headline not present");
		   Reporter.addStepLog(RED_OPEN+"Headline section is not displayed on the App home screen"+RED_CLOSE);	
           }	
		} else if(section.equalsIgnoreCase("MyTeams")) {
		HelperUtils.swipeToElement(driver, myTeams);
	           boolean myTeamElem = HelperUtils.isDisplayed(myTeamsElem);
	           if(myTeamElem) {
	        	   System.out.println("my teams section present");
					Reporter.addStepLog("MyTeams section is displayed on the App home screen");	
	           } else {
	        	   System.out.println("my teams not present");
					Reporter.addStepLog(RED_OPEN+"My teams section is not displayed on the App home screen"+RED_CLOSE);	
	           }	
		} else if(section.equalsIgnoreCase("EditorialPicks")) {
 		HelperUtils.swipeToElement(driver, editorialPicks);
	       boolean editorialElem = HelperUtils.isDisplayed(editorialPicksElem);
	        if(editorialElem) {
	        	System.out.println("editorial section present");
				Reporter.addStepLog("Editorial Picks section is displayed on the App home screen");	
            } else {
            	System.out.println("Editorial section not present");
				Reporter.addStepLog(RED_OPEN+"Editorial Picks section is not displayed on the App home screen"+RED_CLOSE);	
            }
		}
	} else {
		Reporter.addStepLog(RED_OPEN+"App home screen is not displayed"+RED_CLOSE);	
		Assert.fail("App home screen is not displayed");
	}
	
}

private void validateContainerSection() throws InterruptedException, IOException {
	
	/*int size = containerlist.size();
	System.out.println("container size"+size);
	Thread.sleep(2000);
	int sizeTxt = containerlistText.size();
	System.out.println("textcontainer"+sizeTxt);
	
	
	for(int i=0;i<=sizeTxt-1;i++) {
		String txt = containerlistText.get(i).getAttribute("name");
		System.out.println("text"+i+txt);
	}*/
	
	
	if(HelperUtils.isElementPresent(driver, videoElement1By, 10)) {
		System.out.println("Video element1 is present");
		Reporter.addStepLog("Video element1 is displayed on the App home screen");
		String containerText1 = videoElement1Text.getAttribute("name");
		System.out.println("container1text..."+containerText1);
		Reporter.addStepLog("The title displayed for the videoElement1 is "+ COLOR_OPEN + containerText1 +COLOR_CLOSE);
	} else {
		System.out.println("video 1 Not displayed");
		Reporter.addStepLog(RED_OPEN+"The Video element1 is not displayed on the App home screen"+RED_CLOSE);
	}
	
	if(HelperUtils.isDisplayed(videoElement2)) {
		Reporter.addStepLog("Video element2 is displayed on the App home screen");
		String containerText2 = videoElement2Text.getAttribute("name");
		System.out.println("containertext2..."+containerText2);
		Reporter.addStepLog("The title displayed for the videoElement2 is "+ COLOR_OPEN + containerText2 +COLOR_CLOSE);
	} else {
		System.out.println("video 2 Not displayed");
		Reporter.addStepLog(RED_OPEN+"The Video element2 is not displayed on the App home screen"+RED_CLOSE);
	}
	
	if(HelperUtils.isDisplayed(videoElement3)) {
		Reporter.addStepLog("Video element3 is displayed on the App home screen");
		String containerText3 = videoElement3Text.getAttribute("name");
		System.out.println("containerText3..."+containerText3);
		Reporter.addStepLog("The title displayed for the videoElement3 is "+ COLOR_OPEN + containerText3 +COLOR_CLOSE);
	} else {
		System.out.println("video 3 Not displayed");
		Reporter.addStepLog(RED_OPEN+"The Video element3 is not displayed on the App home screen"+RED_CLOSE);
	}
	
}
	
	

}
