package com.nba.ios.seleniumgluecode;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;
import com.nba.ios.api.helper.AesCrypt;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.utilities.ConfigPropertyReader;
import com.nba.ios.utilities.HelperUtils;
import com.nba.ios.utilities.StandingsElements;



public class TopStoriesFunctions {
	
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	
	public static void verifyVideoOrArcticle(String topElement) {
		
		boolean isLivePresent = CommonMethods.isDisplayed(topElement);
		
		if(isLivePresent) {
			if(CommonMethods.getText("TopElementInTopStories").equalsIgnoreCase("LIVE")) {
				Reporter.addStepLog("Top Element in Top Stories Page is Live Video");
			}
		} else {
			Reporter.addStepLog("Top Element in Top Stories Page is Live Video");
		}
	}
	
	
}
