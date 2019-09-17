package com.nba.ios.seleniumgluecode;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.utilities.HelperUtils;


public class NotificationsAppSettings {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String COLOR_CLOSE = "</font>";
	
	public static String RED_OPEN = "<font color=\"red\">";
	public static String RED_CLOSE = "</font>";
	static WebDriver driver;
	
	public static void validateHideScoresAppSettings(String locator, String name) throws InterruptedException {
		String getSwitchMode = CommonMethods.getAttributeText(locator, "value");
		System.out.println("Get Text "+getSwitchMode);
		if(getSwitchMode.equalsIgnoreCase("0"))
		{
			Reporter.addStepLog("Hide Scores are turned OFF. Turing it ON");
			CommonMethods.clickBtn(locator, "Hide Scores is turned ON", "Unable to turn ON Hide Scores");
			
				System.out.println("Small Dialog appeared");
			     HelperUtils.waitForElement();
			     CommonMethods.tapControlSingle(209, 413, "Ok Button in Dialog Box");
			     HelperUtils.waitForElement();
			     getSwitchMode = CommonMethods.getAttributeText(locator, "value");
			     
			     if(getSwitchMode.equalsIgnoreCase("1"))
				{
					CommonMethods.clickBtn("BackBtn", "Clicked on Settings Close Btn", "Unable to click on Settings Close Btn");
					Reporter.addStepLog("Hide Scores are now turned ON");
					validateHideScores();
					
					//Turning off Hide Scores after validating 
					
					CommonMethods.clickBtn("MoreButtonID","Clicking on the More Button","Unable to click the More Button");
					CommonMethods.isDisplayed("MorePage");
					CommonMethods.clickBtn("SettingsIcon_ID", "Setting Icon is clicked", "Unable to click the settings icon");
				     HelperUtils.waitForElement();
				     HelperUtils.waitForElement();
					CommonMethods.clickBtn(locator, "Hide Scores is turned OFF", "Unable to turn OFF Hide Scores");
					// Validate Scores after turning it ON
					validateHideScores();
				}
			
			
		} else if (getSwitchMode.equalsIgnoreCase("1"))
		{
			CommonMethods.clickBtn("BackBtn", "Clicked on Settings Close Btn", "Unable to click on Settings Close Btn");
			Reporter.addStepLog("Hide Scores is turned ON");
			validateHideScores();
			CommonMethods.clickBtn("MoreButtonID","Clicking on the More Button","Unable to click the More Button");
			CommonMethods.isDisplayed("MorePage");
			CommonMethods.clickBtn("SettingsIcon_ID", "Setting Icon is clicked", "Unable to click the settings icon");
			CommonMethods.clickBtn(locator, "Hide Scores is turned OFF", "Unable to turn OFF Hide Scores");
		}
		
	}

	
   /*
	 * validate scores are hided in App Home Page
	 * @throws InterruptedException 
	 **/
	private static void validateHideScores() throws InterruptedException {
		
		CommonMethods.clickBtn("HomePageID", "Clicked on Home Menu", "Unable to click on Home Menu");
		HelperUtils.waitForElement();
        boolean myTeamsFound = CommonMethods.isElementPresent("MyTeamsID");
		//boolean myTeamsFound = CommonMethods.swipeUntilElementFound("MyTeamsID", 10);
		if(myTeamsFound)
		{
			//HelperUtils.swipeVerticallyToPixels(driver, "0.60", "0.40");
			
			String teamRank = null;
			teamRank = CommonMethods.getText("TeamRank");
			System.out.println("Team Rank "+teamRank);
			
			String teamWin = null;
			teamWin = CommonMethods.getText("TeamWin");
			System.out.println("Team Win "+teamWin);
			
			String teamLose = null;
			teamLose = CommonMethods.getText("TeamLoss");
			System.out.println("Team Lose "+teamLose);
			if(teamRank.equalsIgnoreCase("-") && teamWin.equalsIgnoreCase("-") && teamLose.equalsIgnoreCase("-"))
			{
				Reporter.addStepLog(COLOR_OPEN+"Scores are hided in App Home Screen - MyTeams section"+COLOR_CLOSE);
			} else {
				Reporter.addStepLog(COLOR_OPEN+"Scores are not hided in App Home Screen - MyTeams section"+COLOR_CLOSE);
			}
		} else {
			System.out.println("*** My Teams section not found ***");
			Reporter.addStepLog("My Teams section not found");
		}
	}

	/**
	 * vaidate Local Time Toggle
	 * @param locator
	 */
	public static void localTimeValidation(String locator) {
		String getLocalTimeMode = CommonMethods.getAttributeText(locator, "value");
		System.out.println("getLocalTimeMode "+getLocalTimeMode);
		
		if(getLocalTimeMode.equalsIgnoreCase("1"))
		{
			Reporter.addStepLog(COLOR_OPEN +"Local Time is Enabled"+COLOR_CLOSE);		
		} else if(getLocalTimeMode.contains("0"))
		{
			Reporter.addStepLog(COLOR_OPEN+"Local Time is Disabled in App Settings"+COLOR_CLOSE);
		}
		
		CommonMethods.clickBtn(locator, "Clicked on Local Time", "Unable to click on Local Time");
		 getLocalTimeMode = CommonMethods.getAttributeText(locator, "value");
		 if(getLocalTimeMode.equalsIgnoreCase("0")) {
				Reporter.addStepLog("Local time mode is disabled on clicking the toggle switch");
		 } else if(getLocalTimeMode.equalsIgnoreCase("1")) {
			 
			Reporter.addStepLog("Local time mode is enabled on clicking the toggle switch");
		 }
		
	}

	/**
	 * Validate Auto Play options
	 * Disable auto play and verify in videos page
	 * @param locator
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void validateAutoPlay(String locator) throws InterruptedException, IOException {
		
		String autoPlayText = CommonMethods.getAttributeText(locator, "value");

		System.out.println("Auto Play Text "+autoPlayText);
		if(autoPlayText.equalsIgnoreCase("1"))
		{
			CommonMethods.clickBtn(locator, "Auto Play is disabled", "Unable to disable Auto Play option");
			autoPlayText = CommonMethods.getText(locator);
			if(autoPlayText.equalsIgnoreCase("0"))
			{
				
				CommonMethods.clickBtn("BackBtn", "Clicked on Settings Close Btn", "Unable to click on Settings Close Btn");
				validateVideoPage();
				CommonMethods.clickBtn("BackArrowID");
				CommonMethods.clickBtn("MoreButtonID", "More Button is clicked", "Unable to click the More Button");
				CommonMethods.isDisplayed("MorePage");
				CommonMethods.clickBtn("SettingsIcon_ID", "Clicking on Settings button", "Unable to click the settings icon");
			     HelperUtils.waitForElement();
				CommonMethods.clickBtn(locator, "Auto Play is Enabled", "Unable to disable Auto Play option");
				
				CommonMethods.clickBtn("BackBtn", "Clicked on Settings Close Btn", "Unable to click on Settings Close Btn");
				validateVideoPage();
				
			} else {
				Reporter.addStepLog(RED_OPEN+"Unable to disable Auto Play and Verify"+COLOR_CLOSE);
			}

		} else {
			Reporter.addStepLog("Auto Play is OFF. Verifing Videos Section");
			CommonMethods.clickBtn("BackBtn", "Clicked on Settings Close Btn", "Unable to click on Settings Close Btn");
			validateVideoPage();
		}
	}

	/**
	 * this method will validate in video page
	 * after switching off the auto play mode
	 * in settings page
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	private static void validateVideoPage() throws InterruptedException, IOException {
		driver = AppiumApp.getCurrentDriver();
		HelperUtils.waitForElement();
		CommonMethods.clickBtn("VideoButtonID", "Clicked on Video Option", "Unable to click on Video Option");
		HelperUtils.waitForElement();
		HelperUtils.waitForElement();
		CommonMethods.isDisplayed("VideoPageTitle", "Video Page is displayed", "Video Page is not displayed");
		CommonMethods.clickBtn("VideoTile2", "Clicked on Video Top Plays", "Unable to click on Top Plays");
		HelperUtils.waitForElement();
		if(CommonMethods.isElementPresent("AdverstiserID"))
		{
			System.out.println("Ad is present ***");
			Reporter.addStepLog(RED_OPEN+"Ad is being played when Auto Play is Off" +COLOR_CLOSE);
			HelperUtils.waitForElement();
			HelperUtils.waitForElement();
			
		} else {
			System.out.println("Ad is not present ****");
			Reporter.addStepLog("No Ad is displayed");
		}
		boolean nowPlayingText = CommonMethods.isDisplayed("NowPlayingID");
		
	    System.out.println("nowPlayingText" + nowPlayingText);
		HelperUtils.waitForElement();

	   boolean isProgressBarDisplayed =  CommonMethods.isDisplayed("InProgress_ID");
		//boolean isProgressBarDisplayed = HelperUtils.waitForVisibility("InProgress_ID", 180);
		System.out.println("Waited for 60 seconds isProgressBarDisplayed "+isProgressBarDisplayed);
		if(!nowPlayingText && !isProgressBarDisplayed )
		{
			Reporter.addStepLog(COLOR_OPEN +"Video is not autoplayed "+COLOR_CLOSE);
		} else {
			Reporter.addStepLog(COLOR_OPEN+"Video is autoplayed "+COLOR_CLOSE);
		}
		
	}
}