package com.nba.ios.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nba.ios.seleniumgluecode.CommonMethods;

public class ScorePageElements {

	
	
	/*public static By awayTeamNameOnScorePage(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "(//XCUIElementTypeOther[@name='AwayTeamContainer'])["+i+"]");
	}
	
	public static By viewTeamNameOnScorePage(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "(//XCUIElementTypeOther[@name='ViewTeamSegment'])["+i+"]");
	}*/
	
	public static By awayTeamNameOnScorePage(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/following-sibling::XCUIElementTypeOther[@name='AwayTeamContainer']");
	}
	
	public static By viewTeamNameOnScorePage(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/following-sibling::XCUIElementTypeOther[@name='ViewTeamSegment']");
	}
	
	/*public static By gameStatus(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "(//XCUIElementTypeOther[@name='ViewStateSegment'])["+i+"]//XCUIElementTypeStaticText[1]");
	}*/
	
	public static By gameStatus(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeOther//XCUIElementTypeOther[@name='ViewStateSegment']");
	}
	
	public static By broadCastInfo(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[@name='ViewBroadcastSegment']");
	}
	
	
	public static By advertisement() {
        return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeOther[@name='3rd party ad content']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeLink/XCUIElementTypeLink | //XCUIElementTypeImage[@name='Advertisement'] | //XCUIElementTypeApplication[@name='NBA']//XCUIElementTypeWebView");
        
    }

	public static By gameTile(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeNavigationBar[@name='Scores']/..//XCUIElementTypeCollectionView//XCUIElementTypeCell["+i+"]");
	}
	
	public static By watchButton() {
		return CommonMethods.locatorValue(Locators.ID, "Watch");
	}
	
	public static List<WebElement> totalDates() {
		return CommonMethods.locateElements(Locators.XPATH, "//XCUIElementTypeButton[@name='Previous Month']/..//following-sibling::XCUIElementTypeOther//XCUIElementTypeCollectionView/XCUIElementTypeCell[contains(@value,'games')]");
	}

	//XCUIElementTypeNavigationBar[@name='NBA.GameDetailView']/..//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]//XCUIElementTypeStaticText


	
	//(//XCUIElementTypeOther[@name='ViewStateSegment'])[1]//XCUIElementTypeStaticText[1]

	//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[2]

}
