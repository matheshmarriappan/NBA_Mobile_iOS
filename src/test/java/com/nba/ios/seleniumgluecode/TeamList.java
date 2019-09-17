package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;

public class TeamList {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	
	public static void getTabDeatils(String tabName, String textViewLocator) throws IOException {
		List<WebElement> textList = CommonMethods.getListofText(textViewLocator);
		if(textList.size()>0)
		{
			Reporter.addStepLog(" Text displayed for "+ COLOR_OPEN+ tabName +COLOR_CLOSE );
			for(WebElement we: textList)
			{
				Reporter.addStepLog(COLOR_OPEN+we.getText() +COLOR_CLOSE);
			}
		} else {
			Reporter.addStepLog(RED_OPEN+ "Nothing is displayed under tab "+tabName);
		}
		
		
	}
	
	public static void clickOnTeamName(String teamNameLocator, String teamName) {
		CommonMethods.teamNameReplaceAndClick(teamNameLocator, teamName, "Clicked on "+teamName, "Unable to click on "+teamName);
		System.out.println("Done");
	}
}
