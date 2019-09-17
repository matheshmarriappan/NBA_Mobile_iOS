package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.api.helper.JsonPlayersProfileResponse;
import com.nba.ios.api.helper.JsonPlayersResponse;
import com.nba.ios.api.helper.JsonStandingsResponse;
import com.nba.ios.api.helper.JsonTeamsValidation;
import com.nba.ios.utilities.HelperUtils;
import com.nba.ios.utilities.ScorePageElements;
import com.nba.ios.utilities.StandingsElements;

public class PlayersList {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	static LinkedHashSet<String> lhSet = new LinkedHashSet<String>();
	
	
	
	public static void atoz_slider() throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		String getLetter = CommonMethods.getText("Side_Letter");
		System.out.println("Get Letter "+getLetter);
		Reporter.addStepLog("Letter Displayed is "+getLetter);
		
		for(int i = 1; i<26; i++)
		{
			int c = i+65;
			//System.out.println((char)c);
			char h = (char)c;
			System.out.println("Going to search for "+h);
			String k = "//android.widget.TextView[contains(@resource-id,'id/players_list_player_name')]";
			List<WebElement> welement = driver.findElements(By.xpath(k));
			addTeamNames(welement);
			String eleXPath = "//android.widget.TextView[contains(@resource-id,'id/side_letter') and @text='"+h+"']";
			boolean isLetterFound = swipeUntilElementFound(eleXPath, 7, h);
			System.out.println("*** isLetterFound **** "+isLetterFound);
			
		}
		
		// print added team names
		if(lhSet.size() > 0)
		{
			System.out.println("Going to print the team names");
			for(String g : lhSet)
			{
				System.out.println(g);
			}
		}
	}
	
	private static void addTeamNames(List<WebElement> welement) {
		for(WebElement we : welement)
		{
			String teamName = we.getText();
			System.out.println("teamName "+teamName);
			lhSet.add(teamName);
		}
		
	}

	public static boolean isLetterPresent(String path) {
		System.out.println("Is Letter "+path);
		WebDriver driver = AppiumApp.getCurrentDriver();
		boolean isPresent = false;
		
		try {
			driver.findElement(By.xpath(path)).isDisplayed();
			isPresent = true;
		} catch (Exception e) {
	//	System.out.println("Slider Error "+e.getMessage());
			System.out.println("Letter not present");
		isPresent = false;
		}
		
		return isPresent;
	}
	
	public static boolean swipeUntilElementFound(String element, int times, char h) throws InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();
		int i = times;
		boolean result = false;
		while (i >= 1) {
			
			HelperUtils.swipeVertically(driver);
			String k = "//android.widget.TextView[contains(@resource-id,'id/players_list_player_name')]";
			List<WebElement> welement = driver.findElements(By.xpath(k));
			addTeamNames(welement);
			i--;
			boolean isElementPresent = isLetterPresent(element);
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

	public static void pickUpFromSearchLis(String textToEnter, String enterTextFieldLocator, String searchPickLocator) {
		
		CommonMethods.enter_Text(textToEnter, enterTextFieldLocator, "Text "+COLOR_OPEN+textToEnter +COLOR_CLOSE+ " is entered in search field", "Unable to enter text in search field "+textToEnter);
		CommonMethods.clickBtn(searchPickLocator, "Clicked on the listed text", "Unable to click on the entered text");
	}

	public static void verifyAdvertisement() throws IOException {
		
		if(CommonMethods.isDisplayed(ScorePageElements.advertisement())) {
			System.out.println("Advertisement is displayed in the Page");		
         Reporter.addStepLog("Advetisement is displayed in the Screen");			
		} else {
			System.out.println("No Advertisement is present on the screen");
	        Reporter.addStepLog("No Advertisement is present on the screen");			
		}
	}

	public static void verifyStatsJsonData() throws IOException, InterruptedException {
		WebDriver driver = AppiumApp.getCurrentDriver();

		String getPlayersJsonResponse = JsonPlayersResponse.getJsonResponseString();
		
		
		String firstName = CommonMethods.getText("PlayerFirstName");
		String lastName = CommonMethods.getText("PlayerLastName");
		String temporaryName = lastName+","+" "+firstName;
		
		String playerID = JsonPlayersResponse.getPlayerID(getPlayersJsonResponse, temporaryName);
		System.out.println("Player Id of the player in json "+ playerID );
		
		String getPlayersProfilesResponse = JsonPlayersProfileResponse.getJsonResponseString(playerID);
		System.out.println("PlayerProfileResponse.."+ getPlayersProfilesResponse);
		String seasonYear = CommonMethods.getText("StatsYear");
		System.out.println("YearUI"+ seasonYear);
		 
		String season  = CommonMethods.getText("Season_ID");
		System.out.println("saesonUI"+ season);

		String careerStats = CommonMethods.getText("CareerStats_ID");
		System.out.println("careerStatsUI"+ careerStats);

		 
		  List<WebElement> stats =  StandingsElements.Stats();
		  
		  System.out.println("total stats data" + stats.size());
		  
		 String ppgSeason = CommonMethods.getText("ppgSeasonValue");
		 String ppgCareer = CommonMethods.getText("ppgCareerStatsValue");
		 String rpgSeason = CommonMethods.getText("rpgSeasonValue");
		 String rpgCareer = CommonMethods.getText("rpgCareerStatsValue");
		 String apgSeason = CommonMethods.getText("apgSeasonValue");
		 String apgCareer = CommonMethods.getText("apgCareerStatsValue");
		 String spgSeason = CommonMethods.getText("spgSeasonValue");
		 String spgCareer = CommonMethods.getText("spgCareerStatsValue");
		 String bpgSeason = CommonMethods.getText("bpgSeasonValue");
		 String bpgCareer = CommonMethods.getText("bpgCareerStatsValue");
		 
		 HelperUtils.swipeRightToLeftHorizontally(driver);
		 
		 String fgPercSeason = CommonMethods.getText("fg%SeasonValue");
		 String fgPercCareer = CommonMethods.getText("fg%CareerStatsValue");
		 String threePercSeason = CommonMethods.getText("3p%SeasonValue");
		 String threePercCareer = CommonMethods.getText("3p%CareerStatsValue");
		 String ftPercSeason = CommonMethods.getText("ft%SeasonValue");
		 String ftPercCareer = CommonMethods.getText("ft%CareerStatsValue");
		 
		 
		JsonPlayersProfileResponse.getLatestStatsData(getPlayersProfilesResponse, seasonYear, season, ppgSeason, rpgSeason, apgSeason, spgSeason, bpgSeason, fgPercSeason, threePercSeason, ftPercSeason );
		JsonPlayersProfileResponse.getLatestStatsData(getPlayersProfilesResponse, seasonYear, careerStats, ppgCareer, rpgCareer, apgCareer, spgCareer, bpgCareer, fgPercCareer, threePercCareer, ftPercCareer );

		 
		
		}

	
}