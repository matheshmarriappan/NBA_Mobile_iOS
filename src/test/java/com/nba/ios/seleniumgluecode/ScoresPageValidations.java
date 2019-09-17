package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.api.helper.JsonScoreBoardValidation;
import com.nba.ios.utilities.HelperUtils;
import com.nba.ios.api.helper.JsonTeamsValidation;
import com.nba.ios.utilities.LocatorsDataProvier;
import com.nba.ios.utilities.ScorePageElements;

public class ScoresPageValidations {
	
	public static String gameStatus = null;
	public static boolean isTileClicked = false;		
	public static String awayTeamNameOnScorePage = null;
	public static String viewTeamNameOnScorePage = null;
	public static String awayScoreOnScoresPage = null;
	public static String viewScoreOnScorePage = null;
	public static String expGameText="There are 0 games on this day";
	
	
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	
	public static void navigateToCalenderMonth(String month, String date, String gameStatus) throws InterruptedException {
		String getCurrentMonth=null;
		//if (gameStatus.equalsIgnoreCase("Future")) {
			System.out.println("entering 2....");
			getCurrentMonth=CommonMethods.getText("CalenderMonth");
			System.out.println("Month :"+month);
				
			while(!getCurrentMonth.contains(month)) {
				System.out.println("Entering while");
				CommonMethods.clickBtn("PrevoiusBtnInCalID", "Clicking Previous Month in Calender View", "Unable to click the Previous Month button");
			  CommonMethods.waitForElement();
			  getCurrentMonth=CommonMethods.getText("CalenderMonth");
			 }
			
			clickOnCalDate(date);
						
	}
	
	public static void clickOnCalDate(String date) throws InterruptedException {
		
		System.out.println("Waiting fa some time before getting the dates");
		CommonMethods.waitForElement();
		List<WebElement> dates = ScorePageElements.totalDates();
		System.out.println("Total no of date size is:"+dates.size());
		for(int i=0;i<dates.size();i++) {
			String getDate=CommonMethods.getAttributeText(dates.get(i), "value");
		  System.out.println("get date in text" + getDate);
		  
		  if(!getDate.contains(expGameText) && getDate.contains(date)) {
			  System.out.println("Inside if loop no zero games");
			  dates.get(i).click();
			  CommonMethods.waitForElement();
			  break;			  
		  }
		
		}
	}

	/**
	 * this method will modify the date and month names format to numbers
	 * 
	 * @param dateDisplayed
	 * @return
	 * @throws ParseException
	 */
	private static String modifyDateFormat(String dateDisplayed) throws ParseException {

		String finalDateAndMonth = "";

		String date = dateDisplayed;
		String[] spiltDate;
		if (date.contains(", ")) {
			// seperate week day names and month-date value
			spiltDate = date.split(", ");

			System.out.println(spiltDate[0] + " and " + spiltDate[1]);

			// take the second part - ex - Dec. 25
			String dateAndMonth = spiltDate[1];
			System.out.println("dateAndMonth " + dateAndMonth);

			if (dateAndMonth.contains(". ") || dateAndMonth.contains(" ")) {
				// spilt the dot and space
				String[] month = null;
				if (dateAndMonth.contains(". ")) {
					month = dateAndMonth.split(". ");

				} else if (dateAndMonth.contains(" ")) {
					month = dateAndMonth.split(" ");

				} else {
					System.out.println("No Format match");
				}

				System.out.println(month[0].trim() + " " + month[1].trim());

				// take month name and change it to month number
				Date d = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month[0].trim());
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				// month number starts from zero so adding +1
				int m = (cal.get(Calendar.MONTH)) + 1;
				System.out.println(m);
				// check for month if it comes with Single digit then need to
				// add zero infront
				String mn;
				String cc = String.valueOf(m);
				System.out.println(cc + " cc ");
				if (cc.length() == 1) {
					mn = "0" + cc;
					System.out.println(mn + "mn");
				} else {
					mn = cc;
				}

				/*
				 * // here adding 2018 , m will have month number and month[1] will have the
				 * date finalDateAndMonth = "2018"+m+month[1].trim();
				 */

				System.out.println("final in int Month " + m);

				// check for date
				String ds;
				if (month[1].trim().length() == 1) {
					System.out.println("Date is in single digit ");
					ds = "0" + month[1].trim();
					System.out.println(ds);
				} else {
					ds = month[1].trim();
				}

				String getAPKPath = System.getProperty("InstallAPK");
				System.out.println("getAPKPath " + getAPKPath);
				
				if (getAPKPath.equalsIgnoreCase("QA_APP")) {
					finalDateAndMonth = "2018" + mn + ds;
				} else if(getAPKPath.equalsIgnoreCase("PROD_APP")) {				
					finalDateAndMonth = "2019" + mn + ds;
				}

			} else {
				System.out.println("second spilt part doesnt contains dot space.");
			}
			// System.out.println(month == Calendar.DECEMBER);
		} else {
			System.out.println("date doesnt contain commas to spilt");
		}
		return finalDateAndMonth;
	}

	/**
	 * create json URL
	 */
	/**
	 * this method will create the json url with the provided date and month data
	 * 
	 * @param dateDisplayed
	 * @return
	 * @throws IOException
	 */
	private static String jsonUrl(String dateDisplayed) throws IOException {
		String getAPKPath = System.getProperty("InstallAPK");
		System.out.println("getAPKPath " + getAPKPath);
		
		String finalURL = "";
		String u1Prod = "http://data.nba.net/prod/v2/";
		String u1Dev = "https://data.sim.nba.net/sweetpotato/v2/";
		//https://data.sim.nba.net/sweetpotato/v2/20180116/scoreboard.json
		String u2 = "/scoreboard.json";
		try {
			String monthAndDate = modifyDateFormat(dateDisplayed);
			System.out.println("Appedned " + monthAndDate);
			if (getAPKPath.equalsIgnoreCase("QA_APP")) {
				finalURL = u1Dev + monthAndDate + u2;
			} else if(getAPKPath.equalsIgnoreCase("PROD_APP")) {				
				finalURL = u1Prod + monthAndDate + u2;
			}
			System.out.println("Final JSON URL for Scoreboard " + finalURL);
			Reporter.addStepLog("JSON URL for Scoreboard is " + COLOR_OPEN + finalURL + COLOR_CLOSE);
		} catch (ParseException e) {
			System.out.println("Parser Exception happened during date format modification.");
		}

		return finalURL;
	}

	public static String getGameStatus(String game) {
		String gameStatus = CommonMethods.getText(game);
		String statusOfGame = null;
		System.out.println("Game Status " + gameStatus);
		if (gameStatus.contains(":")) {
			if(CommonMethods.isDisplayed("ScoresPage_BellIconID")) {
				Reporter.addStepLog("Status of Game is Future. Game Notification icon is displayed. along with the scheduled time "+ COLOR_OPEN + gameStatus + COLOR_CLOSE);
			} else {
				System.out.println("Unable to locate the Bell Icon");
			}
			System.out.println("Its a future game");
			statusOfGame = "Future";
		} else if(gameStatus.contains("Live")) {
			Reporter.addStepLog("Status of Game is Live "+ COLOR_OPEN + gameStatus + COLOR_CLOSE);
			statusOfGame = "Live";
		} else {
			statusOfGame = "Final";
			Reporter.addStepLog("Status of Game is  "+ COLOR_OPEN+ gameStatus+ COLOR_CLOSE);

		}

		return statusOfGame;
	}

	public static void tileValidationAfterClick(boolean isTileClicked, String teamsResponse, String scoreBoardResponse,
			String awayTeamNameOnScorePage, String homeTeamNameOnScorePage, String statusOfGame,
			String awayScoreOnScorePage, String viewScoreOnScorePage) throws IOException, InterruptedException {
	
		if (isTileClicked) {
			validateHomeAndAwayTeamDetails(teamsResponse, scoreBoardResponse, awayTeamNameOnScorePage,
					homeTeamNameOnScorePage, statusOfGame, awayScoreOnScorePage, viewScoreOnScorePage );

		} else {
			System.out.println("Unable to click on tile hence moving a little. May be add is present.");
			HelperUtils.swipeOnScoresPage();
			
			isTileClicked = CommonMethods.clickOnTiles("Clicked on Tile with Away team name " + awayTeamNameOnScorePage
					+ " and Home Team Name " + homeTeamNameOnScorePage, "Unable to click on the tile ");
			if (isTileClicked) {
				validateHomeAndAwayTeamDetails(teamsResponse, scoreBoardResponse, awayTeamNameOnScorePage,
						homeTeamNameOnScorePage, statusOfGame, awayScoreOnScorePage, viewScoreOnScorePage);
			} else {
				System.out.println("Unable to validate even after second swipe");
				Reporter.addStepLog(RED_OPEN + "Unable to validate even after second swipe" + COLOR_CLOSE);
			}

		}
	}

	private static void verfiyScore(String scoreBaordJsonResponse, String getTeamID, String type, String uiScore)
			throws IOException {
		String jsonScore = JsonScoreBoardValidation.validateScore(scoreBaordJsonResponse, getTeamID, type);
		
		if (uiScore.trim().equalsIgnoreCase(jsonScore)) {
			Reporter.addStepLog(COLOR_OPEN + type + COLOR_CLOSE + " Score " + COLOR_OPEN + uiScore + COLOR_CLOSE
					+ " is validated with the ScoreBoard Json data " + COLOR_OPEN + jsonScore + COLOR_CLOSE
					+ " Both are same.");
		} else {
			Reporter.addStepLog(RED_OPEN + type + COLOR_CLOSE + " Score " + RED_OPEN + uiScore + COLOR_CLOSE
					+ " is validated with the ScoreBoard Json data " + RED_OPEN + jsonScore + COLOR_CLOSE
					+ " Both are not same.");
		}

	}
	private static void validateHomeAndAwayTeamDetails(String teamsResponse, String sbResponse,
			String awayTeamNameScorePage, String homeTeamNameScorePage, String statusOfGame, String awayScoreOnScoresPage, String viewScoreOnScorePage)
			throws IOException, InterruptedException {
		
		WebDriver driver = AppiumApp.getCurrentDriver();
		String watchLocator = LocatorsDataProvier.getDataMap("watchButton_ScoresID");
		HelperUtils.waitUntilElementVisible(driver, By.xpath(watchLocator), 10);
		System.out.println("Waited for 30 seconds");
		
		CommonMethods.isDisplayed("watchButton_ScoresID", "Watch Button is present.", "Watch button is not present");
		
		String awayTeamName = CommonMethods.getText("awayTeamName_GamePage");
		if(awayTeamNameScorePage.contains(awayTeamName))
		{
			awayTeamName = awayTeamNameScorePage;
			System.out.println("awayTeamName **** "+awayTeamName);
		} else {
			Reporter.addStepLog("Away Team is not same as before clicking");
		}
		String getAwayTeamID = JsonTeamsValidation.verifyTeamName(teamsResponse, awayTeamName, "Away");
		if(statusOfGame.equals("Final") || statusOfGame.equals("Live"))
		{			
			if (getAwayTeamID != null && awayScoreOnScoresPage !=null) {
				verfiyScore(sbResponse, getAwayTeamID, "Away", awayScoreOnScoresPage);

			} else {
				if(getAwayTeamID == null || getAwayTeamID.isEmpty())
				{
					Reporter.addStepLog(RED_OPEN+ "Unable to get the Team ID from Teams.Json data. Hence cant validate json score." +COLOR_CLOSE);
					
				} else if(awayScoreOnScoresPage == null || awayScoreOnScoresPage.isEmpty() || awayScoreOnScoresPage.equals(""))
				{
					Reporter.addStepLog(RED_OPEN+ "Unable to get the Away Score. Hence cant validate json score." +COLOR_CLOSE);
				}
				
			
			}
			
		} else {
			Reporter.addStepLog("Score Validation for Away Score is not done since the game is not in Final Status.");
		}
		
		
		String viewTeamName = CommonMethods.getText("viewTeamName_GamePage");
		if(homeTeamNameScorePage.contains(viewTeamName))
		{
			viewTeamName = homeTeamNameScorePage;
			System.out.println("viewTeamName **** "+viewTeamName);
		} else {
			Reporter.addStepLog("Home Team is not same as before clicking");
		}
		String getHomeTeamID = JsonTeamsValidation.verifyTeamName(teamsResponse, viewTeamName, "Home");
		if(statusOfGame.equals("Final") || statusOfGame.equals("Live"))
		{
			if (getHomeTeamID != null && viewScoreOnScorePage!=null) {
				verfiyScore(sbResponse, getHomeTeamID, "Away", viewScoreOnScorePage);

			} else {
				if(getHomeTeamID == null || getHomeTeamID.equals("")|| getHomeTeamID.isEmpty())
				{
					Reporter.addStepLog(RED_OPEN+ "Unable to get the Team ID from Teams.Json data. Hence cant validate json score." +COLOR_CLOSE);
				} else {
					Reporter.addStepLog(RED_OPEN+ "Unable to get the Home Score from Scores.Json data. Hence cant validate json score." +COLOR_CLOSE);
				}
				
			}
		} else {
			Reporter.addStepLog("Score Validation for View Score is not done since the game is not in Final Status.");
		}
		
		// validate Game Details Page features
		// 1. Check which Game type it is. Either Final or Upcoming games
		
		if(statusOfGame.equalsIgnoreCase("Final")) {
			
			Reporter.addStepLog("Game State is "+COLOR_OPEN+ "Final "+ COLOR_CLOSE);
			// do validation for final state games
			generalValidation();
			tabValidation("Final");
			
		} else if(statusOfGame.equalsIgnoreCase("Future")){
			// do validation for non final state games
			generalValidation();		
			CommonMethods.isDisplayed("ListenButtonID", "Listen Button is displayed.", "Listen Button is not displayed.");
			CommonMethods.isDisplayed("GamesPage_BellIconID", "Bell Icon is displayed.", "Bell Icon is not displayed.");
			tabValidation("Future");
						
		} else if(statusOfGame.equalsIgnoreCase("Live")) {
			//Do validation for Live Games
			generalValidation();
			CommonMethods.isDisplayed("ListenButtonID", "Listen Button is displayed.", "Listen Button is not displayed.");
			CommonMethods.isDisplayed("GamesPage_BellIconID", "Bell Icon is displayed.", "Bell Icon is not displayed.");
			tabValidation("Live");
		}
		
		CommonMethods.clickBtn("DoneButtonID", "Clicked on Done Button", "Unable to click on Done Button");
		HelperUtils.waitForElement();
		//HelperUtils.swipeOnScoresPage();
		

	}
	
public static void generalValidation() {
		
	/*if (CommonMethods.isElementPresent("GamesPage_SeriesID")) {
			Reporter.addStepLog("Series button is displayed in Game Detailed Page" );
		} else {
			Reporter.addStepLog(RED_OPEN + "Series button is not displayed in Game Detailed Page." + COLOR_CLOSE);
		}*/
		
		CommonMethods.isDisplayed("watchButton_ScoresID", "Watch Button is displayed in Game Detailed Page", "Watch Button is not displayed");

	}
	
private static void validateMatchUp_GamePage_Final() throws InterruptedException {
	
	WebDriver driver = AppiumApp.getCurrentDriver();

		CommonMethods.isDisplayed("GamePage_MatchUpGameDate", "Match Up Game Date is displayed "+COLOR_OPEN + CommonMethods.getText("GamePage_MatchUpGameDate")+ COLOR_CLOSE, "Match Up Game Date is not displayed");
		CommonMethods.isDisplayed("GamePage_MatchUpGameLocation", "Match Up Game Location is displayed "+COLOR_OPEN + CommonMethods.getText("GamePage_MatchUpGameLocation")+ COLOR_CLOSE, "Match Up Game Location is not displayed");
		CommonMethods.isDisplayed("GamePage_MatchUpQuaterContent", "Match Up Quarter Content is displayed", "Match Up Quarter Content is not displayed");
		CommonMethods.isDisplayed("GamePage_LeadTrackerID", "Lead Tracker is displayed", "Lead Tracker is not displayed");
		
		HelperUtils.swipeVerticallyToPixels(driver, "70", "30");
		
		CommonMethods.isDisplayed("Leaders_Section", COLOR_OPEN+ CommonMethods.getText("Leaders_Section") + COLOR_CLOSE +" section is displayed in Matchup tab for Final Game", "Team Stats is not displayed");

		CommonMethods.isDisplayed("TeamStats_Section", COLOR_OPEN+ CommonMethods.getText("TeamStats_Section") + COLOR_CLOSE +" section is displayed in Matchup tab for Final Game", "Team Stats is not displayed");
		
	}
	private static void validateRECAP_GamePage() throws InterruptedException {
		if(CommonMethods.isElementPresent("GamePage_RECAP_Title"))
		{
			Reporter.addStepLog(COLOR_OPEN+CommonMethods.getText("GamePage_RECAP_Title") +COLOR_CLOSE);
			Reporter.addStepLog(COLOR_OPEN+CommonMethods.getText("GamePage_RECAPAuthorTitle")+COLOR_CLOSE);
			//Reporter.addStepLog(COLOR_OPEN+CommonMethods.getText("GamePage_PublicationDate")+COLOR_CLOSE);
		} else {
			Reporter.addStepLog(RED_OPEN+"RECAP title is not present."+COLOR_CLOSE);
		}
		
	}
	
	private static void validatePlayByPlay_SubNav() {
		if (CommonMethods.isElementPresent("GamePage_SubNav_PlayByPlay")) {
			try {
				List<WebElement> elements = CommonMethods.getListofText("GamePage_SubNav_PlayByPlay");
				for (WebElement we : elements) {
					Reporter.addStepLog(COLOR_OPEN + we.getText() + COLOR_CLOSE);
				}
			} catch (IOException e) {
				Reporter.addStepLog(
						"Exception while getting sub navigation elements in Play By Play Page " + e.getMessage());
			}
		}

	}

	private static void validateMatchUp_FutureGames() throws InterruptedException {	

		WebDriver driver = AppiumApp.getCurrentDriver();

		CommonMethods.isDisplayed("GamePage_Matchup_BuyTicketsID", "Buy Tickets Option is available for Future Games",
				"Buy Tickets Option is not available");
		CommonMethods.isDisplayed("GamePage_MatchUpGameDate",
				"Match Up Game Date is displayed " + CommonMethods.getText("GamePage_MatchUpGameDate"),
				"Match Up Game Date is not displayed");
		/*CommonMethods.isDisplayed("GamePage_MatchUpGameLocation",
				"Match Up Game Location is displayed " + CommonMethods.getText("GamePage_MatchUpGameLocation"),
				"Match Up Game Location is not displayed");*/
				
		CommonMethods.isDisplayed("LatestRecords&Stats_Section", COLOR_OPEN+ CommonMethods.getText("LatestRecords&Stats_Section") + COLOR_CLOSE +" section is displayed in Matchup tab for Future Game", "Team Stats is not displayed");

	}
	

	private static void validateMatchUp_Live() throws InterruptedException {
		
		WebDriver driver = AppiumApp.getCurrentDriver();
		
		CommonMethods.isDisplayed("GamePage_MatchUpGameDate",
				"Match Up Game Date is displayed " + CommonMethods.getText("GamePage_MatchUpGameDate"),
				"Match Up Game Date is not displayed");
		CommonMethods.isDisplayed("GamePage_MatchUpGameLocation",
				"Match Up Game Location is displayed " + CommonMethods.getText("GamePage_MatchUpGameLocation"),
				"Match Up Game Location is not displayed");
		
		if(CommonMethods.isDisplayed("GamePage_MatchUp_NationalTvInfo")) {
			Reporter.addStepLog("Matchup Local TV info " + COLOR_OPEN + CommonMethods.getText("GamePage_MatchUp_NationalTvInfo") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog("Matchup Local Tv info is not available");
		}
		
		if(CommonMethods.isDisplayed("GamePage_MatchUp_LocalTvInfo")) {
			Reporter.addStepLog("Matchup Radio info " + COLOR_OPEN + CommonMethods.getText("GamePage_MatchUp_LocalTvInfo") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog("Matchup Radio info is not available");
		}
		
		HelperUtils.swipeVerticallyToPixels(driver, "70", "30");
		
		CommonMethods.isDisplayed("Leaders_Section", COLOR_OPEN+ CommonMethods.getText("Leaders_Section") + COLOR_CLOSE +" section is displayed in Matchup tab for Live Game", "Team Stats is not displayed");

		CommonMethods.isDisplayed("TeamStats_Section", COLOR_OPEN+ CommonMethods.getText("TeamStats_Section") + COLOR_CLOSE +" section is displayed in Matchup tab for Live Game", "Team Stats is not displayed");
	}
	

	private static void tabValidation(String gameType) throws IOException, InterruptedException {
					
			List<WebElement> tabs = CommonMethods.getListofText("gamePage_tabDetails");
			boolean recap = false;
			if(tabs.size() > 0)
			{
				System.out.println("Displayed tabs ");
				
				for(int i = 0; i<tabs.size(); i++)
				{
					
					String name = tabs.get(i).getAttribute("name");
					Reporter.addStepLog("Tab Name : "+COLOR_OPEN+ name + COLOR_CLOSE);
					if(name.equalsIgnoreCase("Video"))
					{
						CommonMethods.clickBtn("GamePage_VideoTabID", "Video Tab is clicked", "Unable to click on Video Tab");
						HelperUtils.waitForElement();
						boolean videoPresent = CommonMethods.isDisplayed("ThumbNail_Video", "Video is present", "Video is not present");
						if(videoPresent)
						{
							Reporter.addStepLog("Video Title "+CommonMethods.getText("Video_RECAPTitle"));
							
						} else {
							Reporter.addStepLog(RED_OPEN+"Video is not present to validate."+COLOR_CLOSE);
						}
						
					} else if(name.equalsIgnoreCase("BOX SCORE"))
					{
						CommonMethods.clickBtn("GamePage_BoxScoreTabID", "Box Score is Clicked", "Unable to click on Box Scores");
						HelperUtils.waitForElement();
						boolean bScore = CommonMethods.isDisplayed("GamePage_BoxScoreWrapper", "Box Score Wrapper is present", "Box Score Wrapper is not present");
						if(bScore)
						{
							List<WebElement> names = CommonMethods.getListofText("GamePage_BSRadioBtns");
							if(names.size() > 0)
							{
								Reporter.addStepLog("Box Score Table names ");
								for(WebElement s: names)
								{
									Reporter.addStepLog(COLOR_OPEN+ s.getAttribute("name") +COLOR_CLOSE);
								}
							} else {
								Reporter.addStepLog(RED_OPEN+" Box Score Table names are not displayed"+COLOR_CLOSE);
							}
						} else {
							Reporter.addStepLog(RED_OPEN+ " Box Score wrapper is not present."+COLOR_CLOSE);
						}
					} else if(name.equalsIgnoreCase("MATCHUP"))
					{
						CommonMethods.clickBtn("GamePage_MatchUpTabID", "Match up Tab is Clicked", "Unable to click on Match Up Tab");
						if(gameType.equalsIgnoreCase("Final"))
						{
							validateMatchUp_GamePage_Final();
						} else if(gameType.equalsIgnoreCase("Live")){
							validateMatchUp_Live();
						} else if(gameType.equalsIgnoreCase("Future")) {
							validateMatchUp_FutureGames();
						}
						
						
					} else if(name.equalsIgnoreCase("PLAY-BY-PLAY"))
					{
						CommonMethods.clickBtn("GamePage_PLAY-BY-PLAYTabID", "Play By Play Tab is Clicked", "Unable to click on Play By Play Tab");
						//validatePlayByPlay_SubNav();
					} else if(name.equalsIgnoreCase("RECAP"))
					{
						recap = true;
						CommonMethods.clickBtn("GamePage_RECAPTabID", "Clicked on RECAP Button", "Unable to click on RECAP Button");
						Thread.sleep(5000);
						validateRECAP_GamePage();
					} else if(name.equalsIgnoreCase("Preview")) {
						
						CommonMethods.clickBtn("PreviewID", "Clicked on Preview tab", "Unable to click on Preview Button");
						
					}
				}
				
			}
			
		}


	

	/**
	 * Validation for each and every tile
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void tileValiations() throws IOException, InterruptedException {

		String dateDisplayed = null;
		boolean tilesDisplayed = false;
		
		System.out.println("Waiting fa some time to different tile ");
		Thread.sleep(8000);
		
		int i = 0;
		while (i < 3) {
			dateDisplayed = getDisplayedDate();
			System.out.println("isDateDisplayed " + tilesDisplayed);
			tilesDisplayed = CommonMethods.checkNoGamesDisplayed("NoGamesAvailableText", dateDisplayed);
			if (tilesDisplayed) {
				
				
			validateTiles(dateDisplayed);
				break;
			} else {
				CommonMethods.clickBtn("LeftCalNavigation", "Clicked on Left Side Calendar Navigation",
						"Unable to Click on Left Side Calendar Navigation");
				HelperUtils.waitForElement();
				i++;
			}
		}

	}
	
	public static String getDisplayedDate() {
		String dateDisplayed = null;
		dateDisplayed = CommonMethods.getText("CurrentCalenderDateID");
		System.out.println("dateDisplayed " + dateDisplayed);
		return dateDisplayed;
	}

	public static void validateTiles(String dateDisplayed) throws IOException, InterruptedException {
		
		System.out.println("Tiles are displayed for validation");

		//String dateDisplayed = getDisplayedDate();

		// get Teams json response. Teams json is used to validate the Team names and to
		// get the team Id for score verification
		String teamsJsonResponse = JsonTeamsValidation.getTeamsJsonResponse();

		// get score board json URL as per date displayed
		String scoreBoardJSONURL = jsonUrl(dateDisplayed);
		System.out.println("scoreBoardJSONURL " + scoreBoardJSONURL);

		String scoreBoardResponseJson = JsonScoreBoardValidation.getJsonResponseString(scoreBoardJSONURL);

		// get No.of Games available
		int gamesCount = JsonScoreBoardValidation.getNoOfGames(scoreBoardResponseJson);
		System.out.println("gamesCount " + gamesCount);
		Reporter.addStepLog("No.of Games displayed on the page is " + gamesCount);

		// Get Game Status
		gameStatus = getGameStatus("gameStatusTile1");
		
		

		// first tile
		

		if (gameStatus.equalsIgnoreCase("Final") | gameStatus.equalsIgnoreCase("Future") | gameStatus.equalsIgnoreCase("Live") ) {

			System.out.println(
					"The status of the game available is Final or Future or live Hence Proceeding with the Validations of first tile available");
		
			validateFirstTile(gameStatus, teamsJsonResponse, scoreBoardResponseJson);

		} else {

			// second tile validation

			System.out.println(
					"********** The game status of first tile is not Final or Future.  Second tile validation Starts waiting fa some time***********"
							+ gameStatus);

			CommonMethods.waitForElement();

			validateSecondTile(gameStatus, teamsJsonResponse, scoreBoardResponseJson);

		}

	}
/**
 * This method is to validate the second tile available in Scores Page
 * 
 * @param gameStatus2
 * @param teamsJsonResponse
 * @param scoreBoardResponseJson
 * @throws InterruptedException 
 * @throws IOException 
 */
	private static void validateSecondTile(String gameStatus2, String teamsJsonResponse,
			String scoreBoardResponseJson) throws IOException, InterruptedException {
		
		awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile2", "value");
			viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile2", "value");

			System.out.println("away tean detailks" + awayTeamNameOnScorePage);
			System.out.println("view tean detailks" + viewTeamNameOnScorePage);

			gameStatus = getGameStatus("gameStatusTile2");

			if (gameStatus != null && awayTeamNameOnScorePage != null && viewTeamNameOnScorePage != null) {

				System.out.println("game status " + gameStatus);
				if (gameStatus.equalsIgnoreCase("Final")) {
					String[] awayDetailScore = awayTeamNameOnScorePage.split(":");
					awayDetailScore[1].trim();
					String[] awayScore = awayDetailScore[1].split("\\.");
					System.out.println("away score final " + awayScore[0]);
					awayScoreOnScoresPage = awayScore[0];

					String[] viewDetailScore = viewTeamNameOnScorePage.split(":");
					viewDetailScore[1].trim();
					String[] viewScore = viewDetailScore[1].split("\\.");
					System.out.println("view score final " + viewScore[0]);
					viewScoreOnScorePage = viewScore[0];
				}
				
				System.out.println("away team name initial " + awayTeamNameOnScorePage);
				String[] awayTeamDetail = awayTeamNameOnScorePage.split("\\.");
				System.out.println("away team Name on Scores " + awayTeamDetail[0]);
				awayTeamNameOnScorePage = awayTeamDetail[0];

				String[] viewTeamDetail = viewTeamNameOnScorePage.split("\\.");
				System.out.println("viewTeamNameOnScorePage " + viewTeamDetail[0]);
				viewTeamNameOnScorePage = viewTeamDetail[0];

			} else {
				
				System.out.println("Game status fetched is not valid in scores page may be ad is present" + gameStatus
						+ awayTeamNameOnScorePage + viewTeamNameOnScorePage + " so swiping a little ");
				HelperUtils.swipeOnScoresPage();
				awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile2", "value");
				viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile2", "value");

				System.out.println("away tean detailks" + awayTeamNameOnScorePage);
				System.out.println("view tean detailks" + viewTeamNameOnScorePage);

				gameStatus = getGameStatus("gameStatusTile2");

				if (gameStatus != null && awayTeamNameOnScorePage != null && viewTeamNameOnScorePage != null) {

					System.out.println("game status " + gameStatus);
					if (gameStatus.equalsIgnoreCase("Final")) {
						String[] awayDetailScore = awayTeamNameOnScorePage.split(":");
						awayDetailScore[1].trim();
						String[] awayScore = awayDetailScore[1].split("\\.");
						System.out.println("away score final " + awayScore[0]);
						awayScoreOnScoresPage = awayScore[0];

						String[] viewDetailScore = viewTeamNameOnScorePage.split(":");
						viewDetailScore[1].trim();
						String[] viewScore = viewDetailScore[1].split("\\.");
						System.out.println("view score final " + viewScore[0]);
						viewScoreOnScorePage = viewScore[0];
					}
					
					System.out.println("away team name initial " + awayTeamNameOnScorePage);
					String[] awayTeamDetail = awayTeamNameOnScorePage.split("\\.");
					System.out.println("away team Name on Scores " + awayTeamDetail[0]);
					awayTeamNameOnScorePage = awayTeamDetail[0];

					String[] viewTeamDetail = viewTeamNameOnScorePage.split("\\.");
					System.out.println("viewTeamNameOnScorePage " + viewTeamDetail[0]);
					viewTeamNameOnScorePage = viewTeamDetail[0];
				}
			}
			
			gameStatus = getGameStatus("gameStatusTile2");
			isTileClicked = CommonMethods.clickOnSecondTile("Clicked on Tile with Away team name "
					+ awayTeamNameOnScorePage + " and Home Team Name " + viewTeamNameOnScorePage,
					"Unable to click on the tile ");
			 tileValidationAfterClick(isTileClicked, teamsJsonResponse, scoreBoardResponseJson, awayTeamNameOnScorePage, viewTeamNameOnScorePage, gameStatus, awayScoreOnScoresPage, viewScoreOnScorePage );
			Reporter.addStepLog(COLOR_OPEN + "****** Game 2 Validation is done ********** " + COLOR_CLOSE);
	
	}

	/**
	 * This method is to validate first Game Tile available in Scores Page
	 * @param gameStatus
	 * @param teamJsonResponse
	 * @param scoreBoardResponseJson
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void validateFirstTile(String gameStatus, String teamJsonResponse, String scoreBoardResponseJson) throws IOException, InterruptedException {
		//Wait before getting the team names
		CommonMethods.waitForElement();
		
		awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamName_Section", "label");
		viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamName_Section", "label");

		System.out.println("away tean detailks" + awayTeamNameOnScorePage);
		System.out.println("view tean detailks" + viewTeamNameOnScorePage);

		

		if (gameStatus != null && awayTeamNameOnScorePage != null && viewTeamNameOnScorePage != null) {
			Reporter.addStepLog("The away Team Details for the " + gameStatus+" game tile in Scores Page:" + awayTeamNameOnScorePage);
			Reporter.addStepLog("The view Team Details for the " + gameStatus+" game tile in Scores Page:" + viewTeamNameOnScorePage);

			System.out.println("game status " + gameStatus);
			if (gameStatus.equalsIgnoreCase("Final") | gameStatus.equalsIgnoreCase("Live")) {
				String[] awayDetailScore = awayTeamNameOnScorePage.split(":");
				awayDetailScore[1].trim();
				String[] awayScore = awayDetailScore[1].split("\\.");
				System.out.println("away score final " + awayScore[0]);
				awayScoreOnScoresPage = awayScore[0];

				String[] viewDetailScore = viewTeamNameOnScorePage.split(":");
				viewDetailScore[1].trim();
				String[] viewScore = viewDetailScore[1].split("\\.");
				System.out.println("view score final " + viewScore[0]);
				viewScoreOnScorePage = viewScore[0];
			}
			
			System.out.println("away team name initial " + awayTeamNameOnScorePage);
			String[] awayTeamDetail = awayTeamNameOnScorePage.split("\\.");
			System.out.println("away team Name on Scores " + awayTeamDetail[0]);
			awayTeamNameOnScorePage = awayTeamDetail[0];

			String[] viewTeamDetail = viewTeamNameOnScorePage.split("\\.");
			System.out.println("viewTeamNameOnScorePage " + viewTeamDetail[0]);
			viewTeamNameOnScorePage = viewTeamDetail[0];

		} else {
			System.out.println("The status of the game , away and View Team name feteched for the tile are not valid");
		}
		
		//Validate the ScorePage elements for first Tile 
		
		if(gameStatus.equalsIgnoreCase("Final")) {
			validateBtnsDisplayed("WATCH,VIDEO,BOX SCORE", gameStatus);
          } else if(gameStatus.equalsIgnoreCase("Future")) {
  			validateBtnsDisplayed("WATCH,LISTEN", gameStatus);
          } else if(gameStatus.equalsIgnoreCase("Live")) {
    			validateBtnsDisplayed("WATCH,LISTEN,VIDEO", gameStatus);
          }

		isTileClicked = CommonMethods.clickOnTiles("Clicked on Tile with Away team name " + awayTeamNameOnScorePage
				+ " and Home Team Name " + viewTeamNameOnScorePage, "Unable to click on the tile ");
		tileValidationAfterClick(isTileClicked, teamJsonResponse, scoreBoardResponseJson, awayTeamNameOnScorePage,
				viewTeamNameOnScorePage, gameStatus, awayScoreOnScoresPage, viewScoreOnScorePage);
		//Reporter.addStepLog(COLOR_OPEN + "****** Game 1 Validation is done ********** " + COLOR_CLOSE);

	 }
	
	public static void validateBtnsDisplayed(String buttons, String gameType) throws IOException {
		System.out.println("Elements to validate " + buttons);
		List<WebElement> btns = null;
		String getBroadCastInfo = null;
		String spilt[] = buttons.split(",");
			getBroadCastInfo = CommonMethods.getAttributeText("GameTile1BroadCastInfo","value");
			btns = CommonMethods.getListofText("ScoresPage_Buttons");
         System.out.println("buttos size final" + btns.size());
		
		Reporter.addStepLog("This Game is broad casted in " + getBroadCastInfo);
		String name = null;
		for (int i = 0; i < btns.size(); i++) {
			name = btns.get(i).getAttribute("name");
			System.out.println("UI Elements"+ name + i);
			System.out.println(" Expec Elems "+ spilt[i] + i);
			if (spilt[i].equalsIgnoreCase(name)) {
				Reporter.addStepLog(
						 "Button Name " +COLOR_OPEN + name + COLOR_CLOSE+ " is displayed as expected for the " +COLOR_OPEN+ gameType + COLOR_CLOSE +" game");
			} else {
				Reporter.addStepLog(RED_OPEN + "Button Name " + name + " is not displayed as expected for " + gameType
						+ COLOR_CLOSE);
			}
		}

	  }
	

	/**
	 * This method is to validate the first game tile present in the My games section
	 * Checks if the advertisement is present in the page
	 * 
	 */
	private static void validateTileInMyGames() {

		String awayTeamNameOnScorePage = null;
		String viewTeamNameOnScorePage = null;
		String awayScoreOnScoresPage = null;
		String viewScoreOnScorePage = null;
        String gameStatus = null;

		awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
		viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

		System.out.println("away tean detailks" + awayTeamNameOnScorePage);
		System.out.println("view tean detailks" + viewTeamNameOnScorePage);

		gameStatus = getGameStatus("gameStatusTile1");

        String broadCastInfo =  CommonMethods.getAttributeText("GameTile1BroadCastInfo", "value"); 
        Reporter.addStepLog("The broadcaster info displayed for the first Game Tile  is " + COLOR_OPEN+  broadCastInfo + COLOR_CLOSE);
        
		if (gameStatus != null && awayTeamNameOnScorePage != null && viewTeamNameOnScorePage != null) {

			System.out.println("game status " + gameStatus);
			if (gameStatus.equalsIgnoreCase("Final")) {
				String[] awayDetailScore = awayTeamNameOnScorePage.split(":");
				awayDetailScore[1].trim();
				String[] awayScore = awayDetailScore[1].split("\\.");
				System.out.println("away score final " + awayScore[0]);
				awayScoreOnScoresPage = awayScore[0];
				Reporter.addStepLog("The Away Team Score displayed on the game displayed in My Games Page "+ COLOR_OPEN+ awayScoreOnScoresPage + COLOR_CLOSE );

				String[] viewDetailScore = viewTeamNameOnScorePage.split(":");
				viewDetailScore[1].trim();
				String[] viewScore = viewDetailScore[1].split("\\.");
				System.out.println("view score final " + viewScore[0]);
				viewScoreOnScorePage = viewScore[0];
				Reporter.addStepLog("The View Team Score displayed on the game displayed in My Games Page "+ COLOR_OPEN+ viewScoreOnScorePage + COLOR_CLOSE);

			}
			
			System.out.println("away team name initial " + awayTeamNameOnScorePage);
			String[] awayTeamDetail = awayTeamNameOnScorePage.split("\\.");
			System.out.println("away team Name on Scores " + awayTeamDetail[0]);
			awayTeamNameOnScorePage = awayTeamDetail[0];
			Reporter.addStepLog("The Away Team Name displayed on the game displayed in My Games Page "+ COLOR_OPEN+ awayTeamNameOnScorePage + COLOR_CLOSE);

			String[] viewTeamDetail = viewTeamNameOnScorePage.split("\\.");
			System.out.println("viewTeamNameOnScorePage " + viewTeamDetail[0]);
			viewTeamNameOnScorePage = viewTeamDetail[0];
			Reporter.addStepLog("The View Team Name displayed on the game displayed in My Games Page "+ COLOR_OPEN+ viewTeamNameOnScorePage + COLOR_CLOSE);

		} else {
			Reporter.addStepLog("No Data's are fetched for the game present in My Games. ");
		}
		
		//Validate Advertisement if Present
		
		if(CommonMethods.isDisplayed(ScorePageElements.advertisement())) {
			Reporter.addStepLog("Adevertisement is displayed in the My Games Page");
			System.out.println("Advertisement is displayed");
		} else {
			Reporter.addStepLog("Adevertisement is not displayed in the My Games Page");
		}
		
	}
	}

