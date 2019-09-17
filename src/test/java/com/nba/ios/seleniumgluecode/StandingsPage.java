package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.JsonStandingsResponse;
import com.nba.ios.api.helper.JsonTeamsValidation;
import com.nba.ios.utilities.StandingsElements;

public class StandingsPage {
	

	static String easternTeamName = null;
    static String westernTeamName = null;
    public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";

	/**
     * Validate Eastern Conference data with JSON Data
     * @throws InterruptedException 
     * @throws IOException 
     * 
     */
	public static void validateEasternConfData() throws InterruptedException, IOException {
		String getTeamsJsonResponse = JsonTeamsValidation.getTeamsJsonResponse();
		String standingsResponse = JsonStandingsResponse.getJsonResponseString();
		
		for(int i = 3; i<=7; i++)
		{
			
			if (i%2!=0) {
				
				System.out.println(" Entering loop 1 since i value is "+ i);
				
				easternTeamName = CommonMethods.getText(StandingsElements.teamName("Eastern Conference", i), 5);
				System.out.println("teamName "+easternTeamName );

				if(easternTeamName.equalsIgnoreCase("﹡-")) {
					easternTeamName = CommonMethods.getText(StandingsElements.teamNameWith("Eastern Conference", i),5);
					System.out.println("Updated team Name "+ easternTeamName);
					
				}
					
				//teamAndPlayerRedirection(easternTeamName,"Eastern Conference",i);
            } else if (i%2==0) {
				System.out.println(" Entering loop 2 since i value is "+ i);

				String win = CommonMethods.getText(StandingsElements.winUI("Eastern Conference",i),5);
				System.out.println("UI WIn Data "+win);
				
				String loss = CommonMethods.getText(StandingsElements.lossUI("Eastern Conference",i), 5);
				System.out.println("UI loss Data "+loss);
				
				String winPct = CommonMethods.getText(StandingsElements.winPercent("Eastern Conference",i), 5);
				System.out.println("winPct "+winPct);
				
				String gb = CommonMethods.getText(StandingsElements.gamesBehind("Eastern Conference",i), 4);
				System.out.println("GB "+gb);
                
				String confUI = CommonMethods.getText(StandingsElements.conf("Eastern Conference",i), 4);
				System.out.println("confUI "+confUI);
				
				String divUI = CommonMethods.getText(StandingsElements.div("Eastern Conference",i), 4);
				System.out.println("div "+divUI);
				
				String homeUI = CommonMethods.getText(StandingsElements.home("Eastern Conference",i), 4);
				System.out.println("homeui "+homeUI);
				

				String roadUI = CommonMethods.getText(StandingsElements.road("Eastern Conference",i), 4);
				System.out.println("roadUI "+roadUI);
				
				String LTen = CommonMethods.getText(StandingsElements.lTen("Eastern Conference",i), 4);
				System.out.println("L10 "+LTen);
				
				String streak = CommonMethods.getText(StandingsElements.streak("Eastern Conference",i), 4);
				System.out.println("Streak "+streak);
				
				/*CommonMethods.swipeRightToLeftUntilElementFound(StandingsElements.raodHeadline(), 5);
                System.out.println("Swiped left to right first time");*/
            
				String getTeamId = JsonTeamsValidation.queryCityName(getTeamsJsonResponse, easternTeamName);
				System.out.println("getTeamId for i value "+ i + getTeamId);
				
					
				JsonStandingsResponse.getStandingsData(standingsResponse, getTeamId, win, loss, winPct, gb, confUI,divUI, homeUI, roadUI, LTen, streak);
				
            }

	   }
	
	}

	public static void validateWesternConfData() throws IOException {
		
		String getTeamsJsonResponse = JsonTeamsValidation.getTeamsJsonResponse();
		String standingsResponse = JsonStandingsResponse.getJsonResponseString();
		
		for(int i = 3; i<=7; i++)
		{
			
			if (i%2!=0) {
				
				System.out.println(" Entering loop 1 since i value is "+ i +"with locator" + StandingsElements.teamName("Western Conference",i));
				westernTeamName = CommonMethods.getText(StandingsElements.teamName("Western Conference",i), 5);
				
				if(westernTeamName.equalsIgnoreCase("﹡-")) {
					westernTeamName = CommonMethods.getText(StandingsElements.teamNameWith("Western Conference", i),5);
					System.out.println("Updated western team Name "+ westernTeamName);					
				}
				//teamAndPlayerRedirection(westernTeamName,"Western Conference",i);
			
            } else if (i%2==0) {
				System.out.println(" Entering loop 2 since i value is "+ i);

				String win = CommonMethods.getText(StandingsElements.winUI("Western Conference",i),5);
				System.out.println("UI WIn Data "+win);
				
				String loss = CommonMethods.getText(StandingsElements.lossUI("Western Conference",i), 5);
				System.out.println("UI loss Data "+loss);
				
				String winPct = CommonMethods.getText(StandingsElements.winPercent("Western Conference",i), 5);
				System.out.println("winPct "+winPct);
				
				String gb = CommonMethods.getText(StandingsElements.gamesBehind("Western Conference",i), 4);
				System.out.println("GB "+gb);
                
				String confUI = CommonMethods.getText(StandingsElements.conf("Western Conference",i), 4);
				System.out.println("confUI "+confUI);
				
				String divUI = CommonMethods.getText(StandingsElements.div("Western Conference",i), 4);
				System.out.println("div "+divUI);
				
				String homeUI = CommonMethods.getText(StandingsElements.home("Western Conference",i), 4);
				System.out.println("homeui "+homeUI);
				

				String roadUI = CommonMethods.getText(StandingsElements.road("Western Conference",i), 4);
				System.out.println("roadUI "+roadUI);
				
				String LTen = CommonMethods.getText(StandingsElements.lTen("Western Conference",i), 4);
				System.out.println("L10 "+LTen);
				
				String streak = CommonMethods.getText(StandingsElements.streak("Western Conference",i), 4);
				System.out.println("Streak "+streak);
				
            
				String getTeamId = JsonTeamsValidation.queryCityName(getTeamsJsonResponse, westernTeamName);
				System.out.println("getTeamId for i value "+ i + getTeamId);
				
					
				JsonStandingsResponse.getStandingsData(standingsResponse, getTeamId, win, loss, winPct, gb, confUI,divUI, homeUI, roadUI, LTen, streak);
				
            }

	   }
	
	}

	public static void teamAndPlayerRedirection(String conference) {
		
		if(CommonMethods.isDisplayed("AdvertisementWebview")) {
			Reporter.addStepLog("Advertisement webview link is displayed in the standings page");
			System.out.println("Advertisement webview link is displayed in the standings page");
		} else {
			Reporter.addStepLog("No advertisement is displayed in the standings page");
			System.out.println("No advertisement is displayed in the standings page");
		}
		for (int i = 3; i <= 5; i++) {
			if (i%2!=0) {
			String teamName = null;
			teamName = CommonMethods.getText(StandingsElements.teamName(conference, i), 5);		
			System.out.println("teamName " + teamName);
			
			if(teamName.equalsIgnoreCase("﹡-")) {
				teamName = CommonMethods.getText(StandingsElements.teamNameWith(conference, i),5);
				System.out.println("Updated team Name "+ teamName);
				CommonMethods.clickBtn(StandingsElements.teamNameWith(conference, i), "Clicking on the team name " +COLOR_OPEN+ teamName +COLOR_CLOSE,
						"Unable to click on the Team Name");
			} else {
				CommonMethods.clickBtn(StandingsElements.teamName(conference, i), "Clicking on the team name " +COLOR_OPEN+ teamName +COLOR_CLOSE,
						"Unable to click on the Team Name");
			}
			
			
			boolean isTeamPage = CommonMethods.isDisplayed("redirectedteamName");
			if (isTeamPage) {
				System.out.println("Team Page is redirected");
				String teamNameRedirected = CommonMethods.getAttributeText("redirectedteamName","name");
				if (teamName.equalsIgnoreCase(teamNameRedirected)) {
					Reporter.addStepLog("Team Page is redirected correctly on clicking the team name " + teamName);
					boolean isPlayerPresent = CommonMethods.isDisplayed("playerName");
					if (isPlayerPresent) {
						String playerName = CommonMethods.getText("playerName");
						System.out.println("Player name " + playerName);
						CommonMethods.clickBtn("playerName",
								"Clicking on the player name " + COLOR_OPEN+ playerName +COLOR_CLOSE+ " displayed on the team page",
								"Unable to click on the Player name");

						String firstName = CommonMethods.getText("redirectedPlayerFirstName");
						String lastName = CommonMethods.getText("redirectedPlayerLastName");

						String playerNameInPlayerPage = firstName + " " + lastName;
						System.out.println("playerNameInPlayerPage...." + playerNameInPlayerPage);
						if (playerName.equalsIgnoreCase(playerNameInPlayerPage)) {
							System.out.println("Player is navigated");
							Reporter.addStepLog("Player Profile page for the player " + playerName + " is displayed");
							CommonMethods.clickBtn("BackArrowID",
									"Back button is clicked to navigate back to team page",
									"Unable to click the back button");
						} else {
							Reporter.addStepLog("Player page is not navigaated correctly");
						}

					} else {
						Reporter.addStepLog("Player name is not displayed in Team page ");
					}

					CommonMethods.clickBtn("BackArrowID", "Back button is clicked to navigate back to Standings page",
							"Unable to click the back button");

				} else {
					Reporter.addStepLog("Team page is not navigated correctly on clicking the team name");
					System.out.println("Team page is not navigated correctly");

				}
			}
		  }
		}
	}
	public static void teamAndPlayerRedirection(String teamName, String conference, int i) {

		CommonMethods.clickBtn(StandingsElements.teamName(conference, i), "Clicking on the team name " + teamName,
				"Unable to click on the Team Name");
		System.out.println("teamName " + teamName);
		boolean isTeamPage = CommonMethods.isDisplayed("redirectedteamName");
		if (isTeamPage) {
			System.out.println("Team Page is redirected");
			String teamNameRedirected = CommonMethods.getText("redirectedteamName");
			if (teamName.equalsIgnoreCase(teamNameRedirected)) {
				Reporter.addStepLog("Team Page is redirected correctly on clicking the team name " + teamName);
				boolean isPlayerPresent = CommonMethods.isDisplayed("playerName");
				if (isPlayerPresent) {
					String playerName = CommonMethods.getText("playerName");
					System.out.println("Player name " + playerName);
					CommonMethods.clickBtn("playerName",
							"Clicking on the player name " + playerName + " displayed on the team page",
							"Unable to click on the Player name");
					
					String firstName = CommonMethods.getText("redirectedPlayerFirstName");
					String lastName = CommonMethods.getText("redirectedPlayerLastName");
					
					String playerNameInPlayerPage = firstName+" "+lastName;
					System.out.println("playerNameInPlayerPage...." + playerNameInPlayerPage);
					if(playerName.equalsIgnoreCase(playerNameInPlayerPage)) {
						System.out.println("Player is navigated");
						Reporter.addStepLog("Player Profile page is redirected on clicking the player "+ playerName + " in Team page");
	                    CommonMethods.clickBtn("BackArrowID", "Back button is clicked to navigate back to team page", "Unable to click the back button");
					} else {
						Reporter.addStepLog("Player page is not navigaated correctly");
					}
					
				} else {
					Reporter.addStepLog("Player name is not displayed in Team page ");
				}
				
                CommonMethods.clickBtn("BackArrowID", "Back button is clicked to navigate back to Standings page", "Unable to click the back button");

			} else {
				Reporter.addStepLog("Team page is not navigated correctly on clicking the team name");
				System.out.println("Team page is not navigated correctly");

			}
		}

	}

	public static void verifyDivisions(String division, String conference) {
		String teamName = null;
		boolean divPresent = CommonMethods.isDisplayed(division);
		if(divPresent) {
			System.out.println(division + " is present for the " + conference);
            for(int i = 1; i <= 11; i ++) {
            	if (i%2==0) {
            			List<WebElement> teamElements = StandingsElements.divName(division, i);
            			 System.out.println("team elements size" + teamElements.size());
            			if(teamElements.size()==2) {
                			 teamName  = CommonMethods.getText(StandingsElements.divisionDoubName(division, i), 5);
            			} else {
               			 teamName  = CommonMethods.getText(StandingsElements.divisionSingleName(division, i), 5);
            			}
        			System.out.println("Team  Name "+ teamName);
                Reporter.addStepLog("The team displayed under the Division " + division+ " is "+ COLOR_OPEN+ teamName + COLOR_CLOSE);
            	}
            }
		}
		
	}

	public static void validateSorting(String conference) throws IOException, InterruptedException {

		ArrayList<String> sortData = new ArrayList<String>();
        sortData.add("winUI");
        sortData.add("lossUI");
       // sortData.add("winPerct");
        System.out.println("sortData...."+sortData);
        for(String list : sortData)
        {
            By headline = null;
            String columnName = list;
            System.out.println("Each Column name "+columnName);
            if(columnName.equalsIgnoreCase("winUI")) {
                System.out.println("Entering win UI Validation");
                headline = StandingsElements.winHeadline();
            } else if(columnName.equalsIgnoreCase("lossUI")) {
                headline =  StandingsElements.lossHeadline(); 
            }  else if(columnName.equalsIgnoreCase("winPerct")) {
                headline = StandingsElements.winPerHeadline();   
            }
            validateSorting(columnName, headline, conference);
            
        }
	
	}
	
	
	private static void validateSorting(String columnName, By headline, String conference) throws IOException, InterruptedException {
	    // Fetching the UI data before sorting
	        
	        if (columnName == "winPerct") {
	            
	            try {
	                validateWinPercentageSort(columnName,headline, conference);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }            

	        } else {
	            ArrayList<Integer> winDataBeforeSort = fetchingEasternData(columnName, conference);
	            System.out.println("DataBeforeSort...." + winDataBeforeSort);

	            // Sorting the data in descending order (Manipulation)
	            Collections.sort(winDataBeforeSort, Collections.reverseOrder());
	            System.out.println("DataArrayList after sorting in descending order(manipulation).." + winDataBeforeSort);

	            // Clicking the UI Headline to sort in descending order

	            CommonMethods.clickBtn(headline, "Clicking on the " +columnName+ " headline to sort in descending order",
	                    "Unable to click the "+columnName+ " headline");
	           System.out.println(headline+" Printing element headline");
	           Thread.sleep(2000);
	            // Fetching the UI data after sort in descending order
	            ArrayList<Integer> winDataAfterSortingUIDesc = fetchingEasternData(columnName, conference);
	            System.out.println("winDataArrayListAfterSortingUI in descending...." + winDataAfterSortingUIDesc);

	            if (winDataBeforeSort.equals(winDataAfterSortingUIDesc)) {
	                Reporter.addStepLog("The " +columnName+ " data is sorting in descending order.." + winDataAfterSortingUIDesc );

	                System.out.println(
	                        "The " +columnName+ " data is sorted in descending order.." + winDataAfterSortingUIDesc + winDataBeforeSort);
	            } else {
	               Reporter.addStepLog("The " +columnName+ " data is not sorted in descending order" );
	            }

	            // Sorting the data in ascending order (Manipulation)
	            Collections.sort(winDataBeforeSort);
	            System.out.println("winDataArrayList after sorting in ascending order.." + winDataBeforeSort);

	            // Clicking the UI Headline again to sort in ascending order
	            CommonMethods.clickBtn(headline,"Clicking on the " +columnName+ " headline again to sort in ascending order",
	                    "Unable to click the "+columnName+ " headline");

	            // Fetching the UI data after sort
	            ArrayList<Integer> winDataAfterSortUIAsc = fetchingEasternData(columnName, conference);

	            System.out.println("dataArrayListAfterSortingUI in Ascending order...." + winDataAfterSortUIAsc);

	            if (winDataBeforeSort.equals(winDataAfterSortUIAsc)) {
	               Reporter.addStepLog( "The " +columnName+ " data is sorting in ascending order" + winDataAfterSortUIAsc );
	            } else {
	              Reporter.addStepLog( "The " +columnName+ " data is is not sorting in ascending order" );
	            }
	        
	            //CLicking the UI headline to sort it back to default
	            CommonMethods.clickBtn(headline, "Clicking on the " +columnName+ " sort it back to default order",
	                    "Unable to click the "+columnName+ " headline");
	        }
	    }
	
	private static void validateWinPercentageSort(String columnName, By headline, String conference) throws IOException, InterruptedException {
        
	    System.out.println("...............Entering winpercent section............");
	    ArrayList<String> winPercentBeforeSort = fetchEasternPercent(conference);
	    System.out.println("uiColumnList for percentage before sort....." + winPercentBeforeSort);
	    Collections.sort(winPercentBeforeSort, Collections.reverseOrder());
	    System.out
	            .println("DataArrayList after sorting in descending order(manipulation).." + winPercentBeforeSort);

	    // Clicking the UI Headline to sort in descending order
	    System.out.println("headline element...." + headline);
	    CommonMethods.clickBtn(headline,
	            "Clicking on the " + columnName + " headline to sort in descending order",
	            "Unable to click the " + columnName + " headline");
	    Thread.sleep(3000);
	    
	    // Fetching the UI data after sort in descending order
	    ArrayList<String> winPercentageAfterSortingUIDesc = fetchEasternPercent(conference);
	    System.out.println("winPercentageAfterSortingUI in descending...." + winPercentageAfterSortingUIDesc);
	    if (winPercentBeforeSort.equals(winPercentageAfterSortingUIDesc)) {
	        Reporter.addStepLog("The " + columnName + " data is sorting in descending order.."
	                + winPercentageAfterSortingUIDesc);
	    } else {
	        Reporter.addStepLog( "The " + columnName + " data is not sorted in descending order");
	    }

	    // Sorting the winPercent data in ascending order (Manipulation)
	    Collections.sort(winPercentBeforeSort);
	    System.out.println("winPercentArrayList after sorting in ascending order.." + winPercentBeforeSort);

	    // Clicking the UI Headline again to sort in ascending order
	    System.out.println("headline element...." + headline);
	    CommonMethods.clickBtn(headline,
	            "Clicking on the " + columnName + " headline to sort in descending order",
	            "Unable to click the " + columnName + " headline");
	    Thread.sleep(3000);

	    // Fetching the UI data after sort
	    ArrayList<String> winPercentDataAfterSortUIAsc = fetchEasternPercent(conference);

	    System.out.println("winPercentArrayListAfterSortingUI in Ascending order...." + winPercentDataAfterSortUIAsc);

	    if (winPercentBeforeSort.equals(winPercentDataAfterSortUIAsc)) {
	        Reporter.addStepLog( "The " + columnName + " data is sorting in ascending order" + winPercentDataAfterSortUIAsc);
	    } else {
	        Reporter.addStepLog("The " + columnName + " data is is not sorting in ascending order");
	    }

	    // CLicking the UI headline to sort it back to default
	    CommonMethods.clickBtn(headline,
	            "Clicking on the " + columnName + " to sort it back to default order",
	            "Unable to click the " + columnName + " headline");
	    Thread.sleep(3000);

	    System.out.println(".................Closing winpercent section..................");
	    }
	
	private static ArrayList<Integer> fetchingEasternData(String getColumnName, String conf)
			throws IOException, InterruptedException {
		ArrayList<Integer> uiColumnList = new ArrayList<Integer>();
		Integer integerValue;

		for (int i = 4; i <= 32; i++) {
			if (i % 2 == 0) {
				String uiData = null;
				if (getColumnName == "winUI") {
					uiData = CommonMethods.getText(StandingsElements.winUI(conf,i), 4);
					System.out.println("In WIn UI COlumn " + uiData + StandingsElements.winUI(conf,i));
				} else if (getColumnName == "lossUI") {
					uiData = CommonMethods.getText(StandingsElements.lossUI(conf,i),4);
					System.out.println("In loss UI column " + uiData);
				} else if (getColumnName == "winPerct") {
					uiData = CommonMethods.getText(StandingsElements.winPercent(conf,i),4);
					System.out.println("in Win perct column " + uiData);
				} else {
					// nothing matches
				}
				System.out.println("uiData... before converting to integer" + uiData);
				integerValue = Integer.valueOf(uiData);
				System.out.println("integervalue..." + integerValue);
				uiColumnList.add(integerValue);
			}
		}

		return uiColumnList;

	}
	
	private static ArrayList<String> fetchEasternPercent(String conference) throws IOException, InterruptedException {
	    ArrayList<String> uiColumnList = new ArrayList<String>();
	    for(int i=4; i <= 32; i++) {
	    	if (i % 2 == 0) {
	         String uiData =  CommonMethods.getText(StandingsElements.winPercent(conference, i),4);
	         System.out.println("win percent data" + uiData);
	         uiColumnList.add(uiData);
	           
	       }    	
	    }
	    return uiColumnList;	    
	
       }
}