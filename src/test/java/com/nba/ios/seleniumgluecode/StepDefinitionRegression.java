package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.api.helper.JsonHomePageValidation;
import com.nba.ios.utilities.ConfigPropertyReader;
import com.nba.ios.utilities.HelperUtils;
import com.nba.ios.utilities.LocatorsDataProvier;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StepDefinitionRegression {
	

	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String COLOR_CLOSE = "</font>";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String UI_Text_Displayed;
	public static String Text_Displayed_Deeplink;
	public static String JSON_RESPONSE;
	public static String Attribute_Text; 
	public static int SWIPE_COUNT_HOME_PAGE;
	
	
	@Given("^User is on \"([^\"]*)\"$")
	public void user_is_on_Page(String arg1) throws Throwable {
		
		
		WebDriver driver = AppiumApp.getCurrentDriver();
		System.out.println("Now in "+arg1 + " page.");
			
		try {		
			CommonMethods.isDisplayed(arg1, arg1 + " is present.", arg1 +" is not present");
		} catch (Exception e) {
			System.out.println("Exception occured in User is on "+arg1 + " statement "+e.getMessage());
			Reporter.addStepLog("Exception occured in User is on "+arg1 + " statement "+e.getMessage());
			HelperUtils.captureScreen();
		}				
	}
	
	@Given("^User is on App Landing - \"([^\"]*)\"$")
	public void user_is_on_landingPage(String locatorPath) throws Throwable {
		
		// wait until the page is loaded. To do this, I am trying to get the name of first section text
		CommonMethods.waitUntilAppHomeScreenLoads();
		System.out.println("Now in "+locatorPath + " page.");
		try {
			CommonMethods.isDisplayed(locatorPath, locatorPath + " is present.", locatorPath +" is not present");
		} catch (Exception e) {
			System.out.println("Exception occured in User is on "+locatorPath + " statement "+e.getMessage());
			Reporter.addStepLog(RED_OPEN+"NBA App Landing Page is not loaded. Hence unable to perform any actions"+COLOR_CLOSE);
			HelperUtils.captureScreen();
			Assert.fail();
		}
		
	}
	
	@When("^Click on \"([^\"]*)\"$")
	public void Info_TabID(String arg1) throws Throwable {
	    
		System.out.println("Now in element "+arg1);
		try {
		    CommonMethods.clickBtn(arg1, arg1 + " is clicked", arg1 +" is not clicked");
		} catch (Exception e) {
			System.out.println("Exception occured in click on element"+ arg1 + " statement "+e.getMessage());
			Reporter.addStepLog("Exception occured in click on element "+ arg1 + " statement "+e.getMessage());
			HelperUtils.captureScreen();
		}
	} 
	
	@Then("^wait for \"([^\"]*)\"$")
	public void wait_for(String arg1) throws Throwable {
		System.out.println("Now in page "+arg1);
		try {
		    CommonMethods.waitForElement(arg1, arg1 + " is Present", arg1 +" is not present");
		} catch (Exception e) {
			System.out.println("Exception occured in wait For element"+ arg1 + " statement "+e.getMessage());
			Reporter.addStepLog("Exception occured in wait For element "+ arg1 + " statement "+e.getMessage());
			HelperUtils.captureScreen();
		}
	}
	
	@Then("^Verify \"([^\"]*)\" is displayed$")
	public void verify_is_displayed(String arg1) throws Throwable {
		
		System.out.println("arg1 "+arg1);
		try {
			CommonMethods.isDisplayed(arg1, arg1 + " is displayed", arg1 +" is not displayed");
		} catch (Exception e) {
			System.out.println("Exception occured in verify is displayed method for "+ arg1 + " statement "+e.getMessage());
			Reporter.addStepLog("Exception occured in verify is displayed method for "+ arg1 + " statement "+e.getMessage());
			HelperUtils.captureScreen();
		}
				
	}
	

	@Then("^Verify the sign in button displayed$")
	public void verify_the_sign_button_displayed() throws Throwable {

		try {

			LeaguePassFunctions.verifySignInButton();

		} catch (Exception e) {
			Assert.fail("Sign in button is not displayed. The user might be already signed in ");
			HelperUtils.captureScreen();
		}

	}
	
	@Then("^Verify if the user is Signed out$")
	public void verify_the_user_Signed() throws Throwable {

		try {

			LeaguePassFunctions.verifySignOut();

		} catch (Exception e) {
			Assert.fail("Sign in button is not displayed. The user might be already signed in ");
			HelperUtils.captureScreen();
		}

	}
	
	@Then("^Verify element \"([^\"]*)\" is displayed$")
	public void verify_element_is_displayed(String arg1) throws Throwable {
		
		System.out.println("arg1 "+arg1);
		try {
			DeepLinkingFunctions.isElementDisplayed(arg1, arg1 + " is displayed", arg1 +" is not displayed");
		} catch (Exception e) {
			System.out.println("Exception occured in verify is displayed method for "+ arg1 + " statement "+e.getMessage());
			Reporter.addStepLog("Exception occured in verify is displayed method for "+ arg1 + " statement "+e.getMessage());
			//HelperUtils.captureScreen();
		}
				
	}
	@Then("^Get \"([^\"]*)\" JSON Response Data$")
	public void get_JSON_ResponseData(String name) throws Throwable {
		if(name.equals("Home"))
		{
			JSON_RESPONSE = JsonHomePageValidation.getHomesJsonResponse();
		} else {
			System.out.println("Name is not matching to get the json response ");
		}
		
	}

	@Then("^Verify \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verify_and_is(String teamRankLocator, String teamWinLocator, String teamLossLocator, String arg4) throws Throwable {
	    
		String teamRank = null;
		teamRank = CommonMethods.getText(teamRankLocator);
		System.out.println("Team Rank "+teamRank);
		
		String teamWin = null;
		teamWin = CommonMethods.getText(teamWinLocator);
		System.out.println("Team Win "+teamWin);
		
		String teamLoss = null;
		teamLoss = CommonMethods.getText(teamLossLocator);
		System.out.println("Team Loss "+teamLoss);
		
		if(teamRank.equalsIgnoreCase("-") && teamWin.equalsIgnoreCase("-") && teamLoss.equalsIgnoreCase("-")) {
			System.out.println("Scored are hidden as the hide scores button is clicked");
			Reporter.addStepLog("Scores are hidden and displayed as "+COLOR_OPEN +"Team Rank:"+teamRank+" Team Win Score:"+teamWin+" Team loss Score:"+teamLoss+ COLOR_CLOSE);
		} else if(!teamRank.equalsIgnoreCase("-") && !teamWin.equalsIgnoreCase("-") && !teamLoss.equalsIgnoreCase("-")) {
			System.out.println("Scores are enabled and displayed as " +"Team Rank:"+teamRank+" Team Win Score:"+teamWin+" Team loss Score:"+teamLoss);
			Reporter.addStepLog("Scores are enabled in My Teams section and displayed as "+COLOR_OPEN +"Team Rank:"+teamRank+" Team Win Score:"+teamWin+" Team loss Score:"+teamLoss+ COLOR_CLOSE);
		}
		
	}
	
	@And("^Verify \"([^\"]*)\" with Json Data$")
	public void verify_jsonDate(String uiText) throws Throwable {
		String jsonName = null;
		System.out.println("UI text to verify "+uiText);
		if(JSON_RESPONSE!=null)
		{ 
			if(uiText.equalsIgnoreCase ("T1VideoorArticleText") || uiText.equalsIgnoreCase("T2VideoorArticleText") || uiText.equalsIgnoreCase("T3VideoorArticleText")){
				jsonName = JsonHomePageValidation.getSpotLightNames(JSON_RESPONSE, uiText);
			} else if(uiText.equalsIgnoreCase("HeadlineSection1VideoOrArcticle") || uiText.equalsIgnoreCase("HeadlineSection2VideoOrArcticle")) {
			    System.out.println("entering headline comparison");
				jsonName = JsonHomePageValidation.getHeadlineNames(JSON_RESPONSE, uiText);                                                                   
			}
			Reporter.addStepLog("JSON Name "+COLOR_OPEN+ jsonName+COLOR_CLOSE);
			CommonMethods.verifyJsonAndUIText(UI_Text_Displayed, jsonName, uiText);
		} else {
			System.out.println("Json Data is null **** ");
			Assert.fail("JSON Response is not available");
		 }
		}
		
	
/*	@And("^Verify \"([^\"]*)\" with Json Data$")
	public void verify_jsonData(String uiText) throws Throwable {
		System.out.println("UI text to verify "+uiText);
		if(JSON_RESPONSE!=null)
		{
			String jsonName = JsonHomePageValidation.getSpotLightNames(JSON_RESPONSE, uiText);
			Reporter.addStepLog("JSON Name "+COLOR_OPEN+ jsonName+COLOR_CLOSE);
			CommonMethods.verifyJsonAndUIText(UI_Text_Displayed, jsonName, uiText);
		} else {
			System.out.println("Json Data is null **** ");
			Assert.fail("JSON Response is not available");
		}
		
	}*/
	
	/*@Then("^Swipe up to few pixels$")
	public void swipe_up_to_few_pixels() throws Throwable {
		WebDriver driver = AppiumApp.getCurrentDriver();
		HelperUtils.swipeVerticallyToPixels(driver);
	}*/
	
	@Then("^Swipe up to \"([^\"]*)\" and \"([^\"]*)\" pixels$")
	public void swipe_up_to_and_pixels(String xCord, String yCord) throws Throwable {
		WebDriver driver = AppiumApp.getCurrentDriver();
		HelperUtils.swipeVerticallyToPixels(driver, xCord, yCord);
	}
	
	@Then("^Swipe vertically Until \"([^\"]*)\" is found$")
	public void vertical_swipe(String elementName) throws Throwable {
		System.out.println(" Swipe to "+elementName);
		
		try {
			boolean elementFound = CommonMethods.isDisplayed(elementName);
			
			if(!elementFound)
			{
				boolean findElement = CommonMethods.swipeUntilElementFound(elementName, 5);
				
				if(!findElement)
				{
					System.out.println("Unable to find the element "+elementName + " after 3 swipes");
					Reporter.addStepLog("Unable to find the element "+elementName + " after 3 swipes");
					//HelperUtils.captureScreen();
				}
			} else {
				System.out.println(elementName + " is found right first time. No need of swiping");
			}
			
		} catch (Exception e) {
			System.out.println("Exception occured in vertical_swipe action "+e.getMessage());
			Reporter.addStepLog("Exception occured in vertical_swipe action "+e.getMessage());
		}
	}
	
	@Then("^Verify \"([^\"]*)\" is \"([^\"]*)\" Click on \"([^\"]*)\" or \"([^\"]*)\"$")
	public void article_or_video(String videoName, String value, String playerContainer, String ElementBlock) throws Throwable {
		System.out.println("A or v "+videoName);
		
		String spilt[] = value.split("Or");
		
		try {
			boolean video = CommonMethods.isDisplayed(videoName, spilt[0] + " is present.", spilt[1] + " is displayed.");
			
			if(video)
			{
				System.out.println("It is a Video Session");
				CommonMethods.clickBtn(ElementBlock, ElementBlock + " is clicked", ElementBlock + " is not clicked");
				CommonMethods.waitForElementToVisible();
				CommonMethods.waitForElementToVisible();
				CommonMethods.waitForElementToVisible();

				//CommonMethods.waitUntilVideoScreenLoads();
				CommonMethods.verifyVideoActions(playerContainer);
				if(CommonMethods.isDisplayed("BackArrowID")) {
					CommonMethods.clickBtn("BackArrowID", "Back Arrow is clicked to navigate from the video page", "Unable to click the back arrow");
				} else {
					CommonMethods.clickBtn(playerContainer,"Clicked the Player container", "Unable to click the player container");
					CommonMethods.clickBtn("BackArrowID", "Back Arrow is clicked to navigate from the video page", "Unable to click the back arrow");

				}

			} else {
				System.out.println("It is a Article section");
				CommonMethods.clickBtn(ElementBlock, ElementBlock + " is clicked.", ElementBlock + " is not clicked.");
				CommonMethods.waitForElementToVisible();	
				
				boolean isWebViewDisplayed = CommonMethods.isElementPresent("NBAWebViewID");
				if(isWebViewDisplayed) {
					Reporter.addStepLog("WebView Content is displayed");
					CommonMethods.clickBtn("DoneButtonID", "Done Button is clicked.", "Close Button is not clicked.");		
				} else {
					CommonMethods.isDisplayed("ArticleTSHeading", COLOR_OPEN+ CommonMethods.getText("ArticleTSHeading")+COLOR_CLOSE+" is displayed as Arcticle Headline", "Article Page is not displayed");
					if(CommonMethods.isDisplayed("CloseButtonID")) {
						CommonMethods.clickBtn("CloseButtonID", "Close Button is clicked.", "Close Button is not clicked.");		
					}
				}
				 
			}
			
		} catch (Exception e) {
			System.out.println("Exception occured in article_or_video "+e.getMessage());
			Reporter.addStepLog("Exception occured in article_or_video "+e.getMessage());
		}
	}
	
	@Then("^Validate Classic Games functions$")
	public void validate_Classic_Games_functions() throws Throwable {
	  boolean isWatchPresent = CommonMethods.isDisplayed("WatchButtonID");
	  if(isWatchPresent) {
		String watchText =  CommonMethods.getText("WatchText");
		  Reporter.addStepLog("User is not signed to LP. Watch button is displayed along with the text "+COLOR_OPEN+ watchText +COLOR_CLOSE);
		  CommonMethods.clickBtn("WatchButtonID", "Watch button is clicked", "Unable to click the watch button");
		 boolean isNbaLeaguePass = CommonMethods.isDisplayed("NBASalesSheetID");
		 if(isNbaLeaguePass) {
			 Reporter.addStepLog("NBA League Pass page is navigated on clicking the watch button");
			 LeaguePassFunctions.signInWithLP();
			 CommonMethods.clickBtn("CloseButton_ID", "Close button is clicked", "Unable to click the close button");
			 CommonMethods.waitForElementToVisible();
			 CommonMethods.waitForElementToVisible();
			 CommonMethods.waitForElement();
			 boolean nowPlayingText = CommonMethods.isDisplayed("NowPlayingID");		
			 System.out.println("nowPlayingText" + nowPlayingText);
			 HelperUtils.waitForElement();
			 boolean isProgressBarDisplayed =  CommonMethods.isDisplayed("InProgress_ID");
			 System.out.println("Waited for 60 seconds isProgressBarDisplayed "+isProgressBarDisplayed);
				if(!nowPlayingText && !isProgressBarDisplayed )
				{
					Reporter.addStepLog(COLOR_OPEN +"Video is not played in classic games"+COLOR_CLOSE);
				} else {
					Reporter.addStepLog(COLOR_OPEN+"Video is played in classic games after signing in "+COLOR_CLOSE);
				}
		 } else {
			 Reporter.addStepLog("NBA League pass page is not navigated on clicking the Watch button");
		 }
	  } else {
		  Reporter.addStepLog("Watch Button is not displayed. User is already signed in");
	  }  
	 //Sign out the user signed in  to league pass
	  CommonMethods.clickBtn("BackArrowID");
	  CommonMethods.waitForElement();
	  CommonMethods.clickBtn("MoreButtonID");
	  CommonMethods.waitForElement();
	  CommonMethods.clickBtn("SettingsIcon_ID"); 
	 boolean isSignOut = CommonMethods.isDisplayed("SignOutID");
	 if(isSignOut) {
		 CommonMethods.clickBtn("SignOutID");
		  CommonMethods.waitForElement();
	      CommonMethods.tapControlSingle(275, 407, "Signing out the user");

	 } else {
		 Reporter.addStepLog("Sign out button is not displayed");
	 }

	}
	
	
	@Then("^wait for some time if advertisement is present$")
	public void wait_for_some_time_if_advertisement_is_present() throws Throwable {
		CommonMethods.verifyAdvertisement("AdverstiserID");
	}
	
	@Then("^Verify the vod player validations$")
	public void verify_the_vod_player_validations() throws Throwable {
	   
	}
	
	@Then("^Wait for Page To Load$")
	public void wait_for_pageLoad() throws Throwable {
		System.out.println("Wait for page load");
		CommonMethods.waitForElement();
		System.out.println("Wait done");
	}
	
	@And("^Wait for Element$") 
	public void wait_for_element() throws Throwable {
		CommonMethods.waitForElementToVisible();
		}

	@Then("^Verify selected \"([^\"]*)\" - \"([^\"]*)\" with \"([^\"]*)\" - \"([^\"]*)\" is displayed$")
	public void selected_team_names(String teamNameLocator , String cityName, String nicknameLocator , String nickname) throws IOException
	{
		System.out.println("teamNameLocator "+teamNameLocator + " city name "+cityName + "nicknameLocator "+nicknameLocator + " nickname "+nickname);

		CommonMethods.verifyTeamNames(CommonMethods.getText(teamNameLocator), cityName);
		CommonMethods.verifyTeamNames(CommonMethods.getText(nicknameLocator), nickname);
		
	}
	
	@Then("^Click on \"([^\"]*)\" using coordinates \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_on_using_coordinates_and(String clickElement, String xCoord, String yCoord) throws Throwable {
	     CommonMethods.tapControlSingle(Integer.parseInt(xCoord), Integer.parseInt(yCoord), clickElement);
	}
	
	@Then("^Select \"([^\"]*)\" of the teams$")
	public void select_few_of_the_teams(String arg1) throws Throwable {
	    try {
	    	  CommonMethods.isPresentAndClick(arg1, arg1+" is present and clicked", arg1 +" is not present and unable to click it");
	    } catch (Exception e) {
	    	    System.out.println("Exception occured in verify is selecting the team "+ arg1 + " statement "+e.getMessage());
			Reporter.addStepLog("Exception occured in verify is selecting the team "+ arg1 + " statement "+e.getMessage());
			HelperUtils.captureScreen();
	    }
		
	}
	
	@Then("^Get \"([^\"]*)\"$")
	public void get_text(String locatorPath) throws Throwable {
		System.out.println("Locator Path "+locatorPath);
		
		try {
			UI_Text_Displayed = CommonMethods.getText(locatorPath);
			System.out.println("textDisplayed "+UI_Text_Displayed);
			Reporter.addStepLog(locatorPath + " is "+COLOR_OPEN + UI_Text_Displayed+COLOR_CLOSE);
		} catch (Exception e) {
			System.out.println("Exception occured while getting the text for " +locatorPath +e.getMessage());
			Reporter.addStepLog("Exception occured while getting the text for " +locatorPath +e.getMessage());
		}	
	}
	
	@Then("^Get text \"([^\"]*)\"$")
	public void get_element_text(String locatorPath) throws Throwable {
		System.out.println("Locator Path "+locatorPath);
		
		try {
			Text_Displayed_Deeplink = DeepLinkingFunctions.getText(locatorPath);
			System.out.println("textDisplayed "+Text_Displayed_Deeplink);
			Reporter.addStepLog(locatorPath + " is "+COLOR_OPEN + Text_Displayed_Deeplink+COLOR_CLOSE);
		} catch (Exception e) {
			System.out.println("Exception occured while getting the text for " +locatorPath +e.getMessage());
			Reporter.addStepLog("Exception occured while getting the text for " +locatorPath +e.getMessage());
		}	
	}
	
	@Then("^Get \"([^\"]*)\" using attribute \"([^\"]*)\"$")
	public void get_using_attribute(String element, String attribute) throws Throwable {
	   
		try {
			Attribute_Text = CommonMethods.getAttributeText(element, attribute);
			System.out.println("attribute text displayed "+Attribute_Text);
			Reporter.addStepLog(element + " is "+COLOR_OPEN + Attribute_Text+COLOR_CLOSE);
		} catch (Exception e) {
			System.out.println("Exception occured while getting the text for " +element +e.getMessage());
		}
	}
	
	@Then("^Verify Calendar validation - Go back dates, advance dates and check if games are displayed$")
	public void verify_the_games_displayed_in_Previous_date() throws Throwable {
		
		CommonMethods.verifyCalendarDates();
		
	}
	

	@Then("^Score Tiles Validation$")
	public void score_tile_validation() throws Throwable {
		ScoresPageValidations.tileValiations();
	}
	
	@Then("^Verify text \"([^\"]*)\" and \"([^\"]*)\"$")
	public void verify_text_and(String PlayingVideoName, String FirstVideoInCollection) throws Throwable {
	    
	String videoPlayedText =	CommonMethods.getText(PlayingVideoName);
	String firstVideoNameInCollection = CommonMethods.getText(FirstVideoInCollection);
	
	if(videoPlayedText.equalsIgnoreCase(firstVideoNameInCollection)) {
		Reporter.addStepLog(PlayingVideoName +" and " + FirstVideoInCollection +" are same");
	} else {
		Reporter.addStepLog(PlayingVideoName +" and " + FirstVideoInCollection +" are not same");
	}
		
	}

	@Then("^Verify Team Name and Player Profile redirection for the \"([^\"]*)\" data$")
	public void verify_Team_Name_and_Player_Profile_redirection_for_the_data(String conf) throws Throwable {
	   StandingsPage.teamAndPlayerRedirection(conf);
	}
	
	@Then("^Verify \"([^\\\"]*)\" App data with JSON Data$")
	public void verify_App_data_with_JSON_Data(String conference) throws Throwable {
		if(conference.equalsIgnoreCase("Eastern Conference")) {
		    StandingsPage.validateEasternConfData();
		} else {
			if(conference.equalsIgnoreCase("Western Conference")) {
			    StandingsPage.validateWesternConfData();
			}
		}
	}
	

     @Then("^Verify sorting of \"([^\"]*)\" data$")
     public void verify_sorting_of_data(String conference) throws Throwable {
      StandingsPage.validateSorting(conference);
      }
	
	@Then("^Verify teams under the division \"([^\"]*)\" for the \"([^\"]*)\"$")
	public void verify_teams_under_division_the_for_the(String div, String conf) throws Throwable {
		   StandingsPage.verifyDivisions(div,conf);

	}
	
	@Then ("^Get \"([^\"]*)\" details \"([^\"]*)\"$")
	public void getList_TeamTabs(String tabName , String textViewLocator) throws Throwable {
		TeamList.getTabDeatils(tabName, textViewLocator);
	}
	
	
	@Then("^Swipe and check \"([^\"]*)\" is displayed with the Buttons \"([^\"]*)\"$")
	public void swipe_and_check_is_displayed_with_the_Buttons(String gameType, String btnNames) throws Throwable {
		CommonMethods.verifyGameType(gameType, btnNames);

	}
	
	@Then("^Verify \"([^\"]*)\" is displayed for \"([^\"]*)\"$")
	public void verify_is_displayed_for(String featureLocator, String gameTypeLocator) throws Throwable {
		
			String gameStateName = null;
			boolean gameStateDisplay = false;
			String s = featureLocator;
			String spilt[] = s.split("_");
			String locName = spilt[1];
			if(gameTypeLocator.contains("Final"))
			{
				gameStateName = "Final";
				gameStateDisplay = CommonMethods.isDisplayed(gameTypeLocator, COLOR_OPEN+ gameStateName +COLOR_CLOSE+" Game is displayed", gameStateName+" Game is not displayed. Need to swipe and check");
				if(gameStateDisplay)
				{	
					boolean result = CommonMethods.isDisplayed(featureLocator, COLOR_OPEN+locName +COLOR_CLOSE+ " is displayed.", locName + " is not displayed");
					if(result)
					{
						Reporter.addStepLog("Text Displayed for "+COLOR_OPEN+ locName +COLOR_CLOSE + " is "+COLOR_OPEN+CommonMethods.getText(featureLocator)+COLOR_CLOSE);
					} else {
						Reporter.addStepLog(RED_OPEN+"Unable to fetch the text for "+locName +COLOR_CLOSE);
					}
				}
			} else {
				gameStateName = "Future";
				gameStateDisplay = CommonMethods.isDisplayed(gameTypeLocator, COLOR_OPEN+gameStateName +COLOR_CLOSE +" Game is displayed", gameStateName+" Game is not displayed. Need to swipe and check");
				if(gameStateDisplay)
				{	
					boolean result = CommonMethods.isDisplayed(featureLocator, COLOR_OPEN+locName +COLOR_CLOSE+ " is displayed.", locName + " is not displayed");
					if(result && !featureLocator.contains("BellIcon"))
					{
						Reporter.addStepLog("Text Displayed for "+ COLOR_OPEN+ locName +COLOR_CLOSE +" is "+ COLOR_OPEN+CommonMethods.getText(featureLocator) +COLOR_CLOSE);
					} else if(!result && !featureLocator.contains("BellIcon")) {
						Reporter.addStepLog(RED_OPEN+"Unable to fetch the text for "+locName +COLOR_CLOSE);
					} else {
					Reporter.addStepLog("No Match for feature in Score Tile");
					}
				}
			}
	}
	
	
	
	@Then("^Navigate to \"([^\"]*)\" month with date \"([^\"]*)\" for \"([^\"]*)\" game Tile$")
	public void navigate_to_month_with_date_for_game_Tile(String month, String date, String gameStatus) throws Throwable {
	    ScoresPageValidations.navigateToCalenderMonth(month, date, gameStatus);

	}
	
	@Then("^Verify Subscriptions present in NBA League Pass Page$")
	public void verify_subscriptions_leaguePass() throws Throwable {
		LeaguePassFunctions.verifyLeaguePassSubscriptions();
	}
	
	@Then("^Verify the BlackOut Section$")
	public void verify_the_BlackOut_Section() throws Throwable {
		LeaguePassFunctions.verifyBlackOutSession();
	}
     
	@Then("^sign in the NBA sign in Page$")
	public void sign_in_the_NBA_sign_in_Page() throws Throwable {
		LeaguePassFunctions.signIn();
	}
	
	@Then("^Verify Navigation Bar with \"([^\"]*)\" json data$")
    public void navigation_menu_bar(String navJsonName) throws Throwable {
        CommonMethods.verifyNavigationBar(navJsonName);
    }
	
	@Then("^Swipe \"([^\"]*)\" vertically Until \"([^\"]*)\" is found$")
    public void vertical_swipe(String noOfTimesToSwipe , String elementName) throws Throwable {
        System.out.println(" Swipe to "+elementName + "noOfTimesToSwipe "+noOfTimesToSwipe);
        int times = Integer.parseInt(noOfTimesToSwipe);
        WebDriver driver = AppiumApp.getCurrentDriver();
        try {
            boolean elementFound = CommonMethods.isElementPresent(elementName);
            boolean findElement = false;
            if(!elementFound)
            {
                while (times >= 1) {
                    HelperUtils.swipeVertically(driver);
                    times--;
                    SWIPE_COUNT_HOME_PAGE++;
                    boolean isElementPresent = CommonMethods.isElementPresent(elementName);
                    System.out.println("isElementPresent " + isElementPresent);
                    if (isElementPresent) {
                        System.out.println("Breaking loop since element is found");
                        findElement = true;
                        break;
                    }
                    System.out.println("I value " + times);
                }
                                
                if(!findElement)
                {
                    System.out.println("Unable to find the element "+elementName + " after " +times + " swipes");
                    Reporter.addStepLog("Unable to find the element "+elementName +" after " +times + " swipes");
                    HelperUtils.captureScreen();
                }
            } else {
                Reporter.addStepLog(elementName + " is found right first time");
                System.out.println(elementName + " is found right first time. No need of swiping");
            }
            
        } catch (Exception e) {
            System.out.println("Exception occured in vertical_swipe action "+e.getMessage());
            Reporter.addStepLog("Exception occured in vertical_swipe action "+e.getMessage());
        }
        System.out.println("No of times siped "+SWIPE_COUNT_HOME_PAGE);
    }
	
	@Then("^Swipe Reverse$")
    public void swipe_reverse() throws Throwable {
        WebDriver driver = AppiumApp.getCurrentDriver();
        System.out.println("SWIPE_COUNT_HOME_PAGE "+SWIPE_COUNT_HOME_PAGE);
        if(SWIPE_COUNT_HOME_PAGE !=0)
        {
            HelperUtils.swipeReverse(driver, SWIPE_COUNT_HOME_PAGE);
        } else {
            System.out.println("Reverse Swipe Action is not needed here ");
        }
        SWIPE_COUNT_HOME_PAGE = 0;
        System.out.println("Reverse swipe done ");
    }
	
	@And("^Get the \"([^\"]*)\" displayed$")
    public void getDisplayed(String sections) throws Throwable {
		//List<WebElement> elements = CommonMethods.getListofWebElemnts(sections);
		List<WebElement> elements = CommonMethods.getListofText(sections);
		for(int i = 0; i<4; i++) {
			String MoreSection = elements.get(i).getAttribute("name");
			Reporter.addStepLog("The Section displayed under More from NBA is :");
			Reporter.addStepLog(COLOR_OPEN+ MoreSection + COLOR_CLOSE);
		}
	}

	@Then("^Verify \"([^\"]*)\" toggle action$")
	public void verify_toggle_actions_allnotifications(String toggleLocator) throws Throwable {
		CommonMethods.verifyToggleActions_Notification(toggleLocator);
	}
	
	@And("^Verify the toggle actions for the team \"([^\"]*)\"$")
	public void verify_toggle_actions_for_team(String toggleLocator) throws Throwable {
		CommonMethods.verifyToggleActions_Team(toggleLocator);
	}
	
	@Then("^Enter \"([^\"]*)\" in \"([^\"]*)\" and click \"([^\"]*)\"$")
	public void enter_select(String textToEnter, String enterTextFieldLocator, String searchPickLocator) throws Throwable {
		PlayersList.pickUpFromSearchLis(textToEnter, enterTextFieldLocator, searchPickLocator);
	}
	
	@Then("^Verify Previous (\\d+) Games Section$")
	public void verify_Previous_Games_Section(int arg1) throws Throwable {
	   // PlayersList.verifyPreviousGamesSection();
	}
	

	@Then("^Verify All Notification ON OFF Mode$")
	public void all_notification_on_off_mode() throws Throwable {
		CommonMethods.verifyOnOffMode_AllNotifications();
	}
	
	@And("^Verify advertisement if present$")
	public void verify_advertisement() throws Throwable {
	    PlayersList.verifyAdvertisement();
	}
	
	@Then("^Click by coordinates \"([^\"]*)\" and \"([^\"]*)\" for \"([^\"]*)\"$")
	public void click_by_coordinates_and_for(String x, String y, String name) throws Throwable {
	  int xCoord = Integer.parseInt(x);
	  int yCoord = Integer.parseInt(y);
      CommonMethods.tapControlSingle(xCoord, yCoord, name);
	}
	
	@Then("^Get \"([^\"]*)\" and Click on \"([^\"]*)\" team$")
	public void get_and_Click_on_team(String teamNamesLocator, String teamNo) throws Throwable {
	   CommonMethods.verifyTeamsInAllNotification(teamNamesLocator, teamNo);
	}
	
	@Then("^Verify Record Summary with Json Data$")
	public void verify_Record_Summary_with_Json_Data() throws Throwable {
	    PlayersList.verifyStatsJsonData();
	}
	
	@Then("^Swipe from Right To Left in \"([^\"]*)\"$")
	public void swipe_left_to_right(String pageName) throws Throwable {
		HelperUtils.swipeFromRightToLeft(pageName);
	}
	
	@When("^verify the Deeplinking Feature$")
	public void verify_the_Deeplinking_Feature() throws Throwable {
	    DeepLinkingFunctions.verifyDeeplinks();
	}

	@Then("^Verify \"([^\"]*)\" is video or arcticle$")
	public void verify_the_video_or_arcticle(String topElement) throws Throwable {
	   TopStoriesFunctions.verifyVideoOrArcticle(topElement);
	}

	@Then("^Create Account in onboarding  by entering username password and postal code$")
	public void verify_create_account_Onboarding() throws Throwable {
		CommonMethods.createAccountInOnboarding();
	}
	
	@Then("^Create Account by entering username password and postal code$")
	public void verify_create_account() throws Throwable {
		CommonMethods.createAccount();
	}
	
	
	@Then("^Verify \"([^\"]*)\" is displayed and Validate \"([^\"]*)\"$")
	public void validate_appSettings_Page(String locator, String name) throws Throwable {
		if(name.contains("Hide Scores"))
		{
			System.out.println("Validate Hide Scores");
			NotificationsAppSettings.validateHideScoresAppSettings(locator, name);
		} if(name.contains("Local"))
		{
			System.out.println("Validate Local Time Toggle ");
			NotificationsAppSettings.localTimeValidation(locator);
		} if(name.contains("Play"))
		{
			System.out.println("Validate Auto Play Mode");
			NotificationsAppSettings.validateAutoPlay(locator);
		}
	}
	
	@When("^launch the appium$")
	public void launch_the_appium() throws Throwable {
		DeepLinkingFunctions.setCapabilitiesNotePad();
	}
	
	@Given("^launch the deeplink \"([^\"]*)\"$")
	public void launch_the_deeplink(String arg1) throws Throwable {
		DeepLinkingFunctions.launchDeepLink(arg1);
	}
	
	
	
}
