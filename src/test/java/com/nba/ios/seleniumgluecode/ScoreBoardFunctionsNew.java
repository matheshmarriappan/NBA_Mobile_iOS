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

public class ScoreBoardFunctionsNew {
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";

	public static void calendarDateValidation(String getDefaultDate, String dateToCompare, String validateDate) {

		String[] spiltDate = null;

		spiltDate = getDefaultDate.split(". ");
		String todaysDate = null;
		if (spiltDate[2] != null) {
			todaysDate = spiltDate[2];
		} else {
			System.out.println("Today's date is null");
		}

		spiltDate = dateToCompare.split(". ");
		String compareDate = null;
		if (spiltDate[2] != null) {
			compareDate = spiltDate[2];
		} else {
			System.out.println("Yesterday date is null");
		}

		int todayDate = Integer.parseInt(todaysDate);
		int compDate = Integer.parseInt(compareDate);

		if (validateDate.equalsIgnoreCase("PreviousDate")) {
			if (todayDate - 1 == compDate) {
				System.out.println("Previous Date is displayed as expected");
				Reporter.addStepLog(GREEN_OPEN + "Previous Date is displayed as expected" + COLOR_CLOSE);
			} else {
				System.out.println("Previous Date is not displayed as expected");
				Reporter.addStepLog(RED_OPEN + "Previous Date is not displayed as expected" + COLOR_CLOSE);
			}
		} else if (validateDate.equalsIgnoreCase("FutureDate")) {
			if (todayDate + 1 == compDate) {
				System.out.println("Next Date is displayed as expected");
				Reporter.addStepLog(GREEN_OPEN + "Next Date is displayed as expected" + COLOR_CLOSE);
			} else {
				System.out.println("Next Date is not displayed as expected");
				Reporter.addStepLog(RED_OPEN + "Next Date is not displayed as expected" + COLOR_CLOSE);
			}
		} else {
			System.out.println("No Date Validation Selection made");
			Reporter.addStepLog(RED_OPEN + "Unable to do the Date Validation" + COLOR_CLOSE);
		}

	}

	public static String getDisplayedDate() {
		String dateDisplayed = null;
		dateDisplayed = CommonMethods.getText("CurrentCalenderDateID");
		System.out.println("dateDisplayed " + dateDisplayed);
		return dateDisplayed;
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

	private static void validateTiles(String dateDisplayed) throws IOException, InterruptedException {
		System.out.println("Tiles are displayed for validation");

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
		Reporter.addStepLog("No.of Games displayed is " + gamesCount);
		int i = 1;
		if (gamesCount == 2) {
			
			String gameStatus = null;
			boolean isTileClicked = false;

			// only two tiles
			// first tile
			String awayTeamNameOnScorePage = null;
			String viewTeamNameOnScorePage = null;
			String awayScoreOnScoresPage = null;
			String viewScoreOnScorePage = null;


			awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
			viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

			System.out.println("away tean detailks" + awayTeamNameOnScorePage);
			System.out.println("view tean detailks" + viewTeamNameOnScorePage);

			gameStatus = getGameStatus("gameStatusTile1");

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
				awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
				viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

				System.out.println("away tean detailks" + awayTeamNameOnScorePage);
				System.out.println("view tean detailks" + viewTeamNameOnScorePage);

				gameStatus = getGameStatus("gameStatusTile1");

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

			isTileClicked = CommonMethods.clickOnTiles("Clicked on Tile with Away team name " + awayTeamNameOnScorePage
					+ " and Home Team Name " + viewTeamNameOnScorePage, "Unable to click on the tile ");
			tileValidationAfterClick(isTileClicked, teamsJsonResponse, scoreBoardResponseJson, awayTeamNameOnScorePage,
					viewTeamNameOnScorePage, gameStatus, awayScoreOnScoresPage, viewScoreOnScorePage);
			Reporter.addStepLog(COLOR_OPEN + "****** Game 1 Validation is done ********** " + COLOR_CLOSE);

			// second tile validation
			
			System.out.println("********** Second tile validation waiting fa some time***********");
			
			CommonMethods.waitForElement();
		
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
		} else {
			
			while (i <= gamesCount) {
				doScoreTileSwipeValidation(teamsJsonResponse, scoreBoardResponseJson, gamesCount);
				Reporter.addStepLog(
						COLOR_OPEN + i + " ***************** Tile Validation Done *********************" + COLOR_CLOSE);
				i = i + 1;
				System.out.println("Tiles Validated count after adding with i " + i);

			}
		}
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
			statusOfGame = "Non-Final";
		} else {
			statusOfGame = "Final";
			Reporter.addStepLog("Status of Game is  "+ COLOR_OPEN+ gameStatus+ COLOR_CLOSE);

		}

		return statusOfGame;
	}

	private static void doScoreTileSwipeValidation(String teamsResponse, String scoreBoardResponse, int gamesCount)
			throws IOException, InterruptedException {
	
		String gameStatus = null;
		boolean isTileClicked = false;
		if (gamesCount == 2) {
			// only two tiles
			// first tile
			
			// only two tiles
						// first tile
						String awayTeamNameOnScorePage = null;
						String viewTeamNameOnScorePage = null;
						String awayScoreOnScoresPage = null;
						String viewScoreOnScorePage = null;


						awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
						viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

						System.out.println("away tean detailks" + awayTeamNameOnScorePage);
						System.out.println("view tean detailks" + viewTeamNameOnScorePage);

						gameStatus = getGameStatus("gameStatusTile1");
						

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
							awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
							viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

							System.out.println("away tean detailks" + awayTeamNameOnScorePage);
							System.out.println("view tean detailks" + viewTeamNameOnScorePage);

							gameStatus = getGameStatus("gameStatusTile1");

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

						isTileClicked = CommonMethods.clickOnTiles("Clicked on Tile with Away team name " + awayTeamNameOnScorePage
								+ " and Home Team Name " + viewTeamNameOnScorePage, "Unable to click on the tile ");
						tileValidationAfterClick(isTileClicked, teamsResponse, scoreBoardResponse, awayTeamNameOnScorePage,
								viewTeamNameOnScorePage, gameStatus, awayScoreOnScoresPage, viewScoreOnScorePage);
						Reporter.addStepLog(COLOR_OPEN + "****** Game 1 Validation is done ********** " + COLOR_CLOSE);

						// second tile validation
						
						System.out.println("********** Second tile validation waiting fa some time***********");
						
						CommonMethods.waitForElement();
					
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
							Thread.sleep(3000);
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
						 tileValidationAfterClick(isTileClicked, teamsResponse, scoreBoardResponse, awayTeamNameOnScorePage, viewTeamNameOnScorePage, gameStatus, awayScoreOnScoresPage, viewScoreOnScorePage );
						Reporter.addStepLog(COLOR_OPEN + "****** Game 2 Validation is done ********** " + COLOR_CLOSE);

		} else {
			moreThanTwoGamesValidation(teamsResponse, scoreBoardResponse);
		}

	}

	private static void moreThanTwoGamesValidation(String teamsResponse, String scoreBoardResponse)
			throws IOException, InterruptedException {
		// get team names
		
		String awayTeamNameOnScorePage = null;
		String viewTeamNameOnScorePage = null;
		String gameStatus = null;
		String awayScoreOnScoresPage = null;
	    String viewScoreOnScorePage = null;
	    
		awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
		viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

		System.out.println("away tean detailks" + awayTeamNameOnScorePage);
		System.out.println("view tean detailks" + viewTeamNameOnScorePage);

		gameStatus = getGameStatus("gameStatusTile1");
		System.out.println("game status displayed" + gameStatus);

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
			Thread.sleep(3000);
			awayTeamNameOnScorePage = CommonMethods.getAttributeText("awayTeamDetailsTile1", "value");
			viewTeamNameOnScorePage = CommonMethods.getAttributeText("viewTeamDetailsTile1", "value");

			System.out.println("away tean detailks" + awayTeamNameOnScorePage);
			System.out.println("view tean detailks" + viewTeamNameOnScorePage);

			gameStatus = getGameStatus("gameStatusTile1");

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
		
		CommonMethods.waitForElement();
		boolean isTileClicked = CommonMethods.clickOnTiles("Clicked on Tile with Away team name "
				+ awayTeamNameOnScorePage + " and Home Team Name " + viewTeamNameOnScorePage,
				"Unable to click on the tile ");
		tileValidationAfterClick(isTileClicked, teamsResponse, scoreBoardResponse,awayTeamNameOnScorePage, viewTeamNameOnScorePage, gameStatus, awayScoreOnScoresPage, viewScoreOnScorePage );

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
		if(statusOfGame.equals("Final"))
		{
			//String awayScore = CommonMethods.getText("awayTeamScore_GamePage");
			
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
		if(statusOfGame.equals("Final"))
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
			
		} else {
			// do validation for non final state games
			generalValidation();
			/*if(CommonMethods.isElementPresent("GamesPage_Time"))
			{
				Reporter.addStepLog("Time displayed is "+ COLOR_OPEN+CommonMethods.getText("GamesPage_Time")+COLOR_CLOSE);
			} else {
				Reporter.addStepLog(RED_OPEN+"Time is not displayed"+COLOR_CLOSE);
			}
		
			if(CommonMethods.isElementPresent("GamesPage_TimePeriod"))
			{
				Reporter.addStepLog("Time Period displayed is "+ COLOR_OPEN+CommonMethods.getText("GamesPage_TimePeriod")+COLOR_CLOSE);
			} else {
				Reporter.addStepLog(RED_OPEN+"Time Period is not displayed"+COLOR_CLOSE);
			}
			
			if(CommonMethods.isElementPresent("GamesPage_TimeZone"))
			{
				Reporter.addStepLog("Time Zone displayed is "+ COLOR_OPEN+CommonMethods.getText("GamesPage_TimeZone")+COLOR_CLOSE);
			} else {
				Reporter.addStepLog(RED_OPEN+"Time Zone is not displayed"+COLOR_CLOSE);
			}*/
			CommonMethods.isDisplayed("ListenButtonID", "Listen Button is displayed.", "Listen Button is not displayed.");
			CommonMethods.isDisplayed("GamesPage_BellIconID", "Bell Icon is displayed.", "Bell Icon is not displayed.");
			tabValidation("Non-Final");
			
		}
		
		//Reporter.addStepLog("Tab Names "+tabNames);
		CommonMethods.clickBtn("DoneButtonID", "Clicked on Done Button", "Unable to click on Done Button");
		HelperUtils.waitForElement();
		HelperUtils.swipeOnScoresPage();
		HelperUtils.waitForElement();

	}

	private static void tabValidation(String gameType) throws IOException, InterruptedException {

		List<WebElement> tabs = CommonMethods.getListofText("gamePage_tabDetails");
		boolean recap = false;
		if (tabs.size() > 0) {
			System.out.println("Displayed tabs ");

			for (int i = 0; i <= tabs.size(); i++) {
				String name = tabs.get(i).getText();
				Reporter.addStepLog("Tab Name " + name);
				if (name.equalsIgnoreCase("Video")) {
					CommonMethods.clickBtn("GamePage_VideoTab", "Video Tab is clicked", "Unable to click on Video Tab");
					HelperUtils.waitForElement();
					boolean videoPresent = CommonMethods.isDisplayed("ThumbNail_Video", "Video is present",
							"Video is not present");
					if (videoPresent) {
						Reporter.addStepLog("Video Title " + CommonMethods.getText("Video_RECAPTitle"));
						/*
						 * CommonMethods.clickBtn("ThumbNail_Video", "Clicked on Video Container",
						 * "Unable to click on Video Container"); HelperUtils.waitForElement();
						 * HelperUtils.waitForElement();
						 * CommonMethods.verifyVideoActions("VidoePlayerContainer", "NavigateBackBtn");
						 * HelperUtils.waitForElement();
						 */

					} else {
						Reporter.addStepLog(RED_OPEN + "Video is not present to validate." + COLOR_CLOSE);
					}

				} else if (name.equalsIgnoreCase("BOX SCORE")) {
					CommonMethods.clickBtn("GamePage_BoxScoreTab", "Box Score is Clicked",
							"Unable to click on Box Scores");
					HelperUtils.waitForElement();
					boolean bScore = CommonMethods.isDisplayed("GamePage_BoxScoreWrapper",
							"Box Score Wrapper is present", "Box Score Wrapper is not present");
					if (bScore) {
						List<WebElement> names = CommonMethods.getListofText("GamePage_BSRadioBtns");
						if (names.size() > 0) {
							Reporter.addStepLog("Box Score Table names ");
							for (WebElement s : names) {
								Reporter.addStepLog(COLOR_OPEN + s.getText() + COLOR_CLOSE);
							}
						} else {
							Reporter.addStepLog(RED_OPEN + "Away and Home Team names are not displayed" + COLOR_CLOSE);
						}
					} else {
						Reporter.addStepLog(RED_OPEN + " Box Score wrapper is not present." + COLOR_CLOSE);
					}
				} else if (name.equalsIgnoreCase("MATCHUP")) {
					CommonMethods.clickBtn("GamePage_MatchUpTab", "Match up Tab is Clicked",
							"Unable to click on Match Up Tab");
					if (gameType.equalsIgnoreCase("Final")) {
						validateMatchUp_GamePage();
					} else {
						validateMatchUp_FutureGames();
					}

				} else if (name.equalsIgnoreCase("PLAY-BY-PLAY")) {
					CommonMethods.clickBtn("GamePage_PLAY-BY-PLAYTabID", "Play By Play Tab is Clicked",
							"Unable to click on Play By Play Tab");
					validatePlayByPlay_SubNav();
				} else if (name.equalsIgnoreCase("RECAP")) {
					recap = true;
					CommonMethods.clickBtn("GamePage_RECAPTab", "Clicked on RECAP Button",
							"Unable to click on RECAP Button");
					validateRECAP_GamePage();
				}
			}

			// if RECAP is not in the list , then check if RECAP is present at the end and
			// do the validation
			if (CommonMethods.isElementPresent("GamePage_RECAPTab") && !recap) {
				CommonMethods.clickBtn("GamePage_RECAPTab", "Clicked on RECAP Button",
						"Unable to click on RECAP Button");
				validateRECAP_GamePage();
			} else {
				System.out.println("Recap is not present at the end");
			}
		}

	}

	private static void validateMatchUp_FutureGames() {
		CommonMethods.isDisplayed("GamePage_Matchup_BuyTicketsID", "Buy Tickets Option is available for Future Games",
				"Buy Tickets Option is not available for Future Games");
		CommonMethods.isDisplayed("GamePage_MatchUpGameDate",
				"Match Up Game Date is displayed " + CommonMethods.getText("GamePage_MatchUpGameDate"),
				"Match Up Game Date is not displayed");
		CommonMethods.isDisplayed("GamePage_MatchUpGameLocation",
				"Match Up Game Location is displayed " + CommonMethods.getText("GamePage_MatchUpGameLocation"),
				"Match Up Game Location is not displayed");
		CommonMethods.isDisplayed("GamePage_Mup_TV_Info",
				"Broadcast Info " + COLOR_OPEN + CommonMethods.getText("GamePage_Mup_TV_Info") + COLOR_CLOSE,
				"Broadcast Information is not present");
	}

	private static void validateMatchUp_GamePage() {

		CommonMethods.isDisplayed("GamePage_MatchUpGameDate",
				"Match Up Game Date is displayed " + CommonMethods.getText("GamePage_MatchUpGameDate"),
				"Match Up Game Date is not displayed");
		CommonMethods.isDisplayed("GamePage_MatchUpGameLocation",
				"Match Up Game Location is displayed " + CommonMethods.getText("GamePage_MatchUpGameLocation"),
				"Match Up Game Location is not displayed");
		CommonMethods.isDisplayed("GamePage_MatchUpQuaterContent", "Match Up Quarter Content is displayed",
				"Match Up Quarter Content is not displayed");
		CommonMethods.isDisplayed("GamePage_LeadTracker", "Lead Tracker is displayed", "Lead Tracker is not displayed");

	}
	
	private static void validateRECAP_GamePage() {
		if (CommonMethods.isElementPresent("GamePage_AllFields_RECAP")) {
			Reporter.addStepLog(COLOR_OPEN + CommonMethods.getText("GamePage_AllFields_RECAP") + COLOR_CLOSE);
			Reporter.addStepLog(COLOR_OPEN + CommonMethods.getText("GamePage_AllFields_RECAP") + COLOR_CLOSE);
			Reporter.addStepLog(COLOR_OPEN + CommonMethods.getText("GamePage_RECAPAuthotTitle") + COLOR_CLOSE);
			Reporter.addStepLog(COLOR_OPEN + CommonMethods.getText("GamePage_PublicationDate") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog(RED_OPEN + "RECAP text is not present." + COLOR_CLOSE);
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


	public static void generalValidation() {
		
		/*CommonMethods.isDisplayed("WatchButton", "Watch Button is displayed", "Watch Button is not displayed");
		if (CommonMethods.isElementPresent("GamePage_PlayoffDetails")) {
			Reporter.addStepLog("Play off Conference displayed is " + COLOR_OPEN
					+ CommonMethods.getText("GamePage_PlayoffDetails") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog(RED_OPEN + "Play off Conference is not displayed in Game Detailed Page." + COLOR_CLOSE);
		}*/

		/*if (CommonMethods.isElementPresent("GamesPage_PlayoffSeries")) {
			Reporter.addStepLog("Play off Series displayed is " + COLOR_OPEN
					+ CommonMethods.getText("GamesPage_PlayoffSeries") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog(RED_OPEN + "Play off Series is not displayed in Game Detailed Page." + COLOR_CLOSE);
		}
*/
		/*if (CommonMethods.isElementPresent("GamesPage_SeriesHubButton")) {
			Reporter.addStepLog("Series button displayed is " + COLOR_OPEN
					+ CommonMethods.getText("GamesPage_SeriesHubButton") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog(RED_OPEN + "Series button is not displayed in Game Detailed Page." + COLOR_CLOSE);
		}*/
		
		CommonMethods.isDisplayed("watchButton_ScoresID", "Watch Button is displayed", "Watch Button is not displayed");

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
		String finalURL = "";
		String u1 = "http://data.nba.net/prod/v2/";
		String u2 = "/scoreboard.json";
		try {
			String monthAndDate = modifyDateFormat(dateDisplayed);
			System.out.println("Appedned " + monthAndDate);
			finalURL = u1 + monthAndDate + u2;
			System.out.println("Final JSON URL for Scoreboard " + finalURL);
			Reporter.addStepLog("JSON URL for Scoreboard is " + COLOR_OPEN + finalURL + COLOR_CLOSE);
		} catch (ParseException e) {
			System.out.println("Parser Exception happened during date format modification.");
		}

		return finalURL;
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

				finalDateAndMonth = "2019" + mn + ds;

			} else {
				System.out.println("second spilt part doesnt contains dot space.");
			}
			// System.out.println(month == Calendar.DECEMBER);
		} else {
			System.out.println("date doesnt contain commas to spilt");
		}
		return finalDateAndMonth;
	}

	public static void validateBtnsDisplayed(String buttons, String gameType) throws IOException {
		System.out.println("Elements to validate " + buttons);
		List<WebElement> btns = null;
		String getBroadCastInfo = null;
		String spilt[] = buttons.split(",");
		if (gameType.equalsIgnoreCase("Final")) {
			getBroadCastInfo = CommonMethods.getText("BroadCasterInfo");
			btns = CommonMethods.getListofText("FinalStateBtns");

		} else {
			System.out.println("Future Game");
			getBroadCastInfo = CommonMethods.getText("BroadCasterInfoFurtureGames");
			btns = CommonMethods.getListofText("FutureGamesBtns");
		}
		Reporter.addStepLog("This Game is broad casted in " + getBroadCastInfo);
		String name = null;
		for (int i = 0; i < btns.size(); i++) {
			name = btns.get(i).getText();
			System.out.println(name);
			if (spilt[i].equalsIgnoreCase(name)) {
				Reporter.addStepLog(
						COLOR_OPEN + "Button Name " + name + " is displayed as expected for " + gameType + COLOR_CLOSE);
			} else {
				Reporter.addStepLog(RED_OPEN + "Button Name " + name + " is not displayed as expected for " + gameType
						+ COLOR_CLOSE);
			}
		}

	}

	
}
