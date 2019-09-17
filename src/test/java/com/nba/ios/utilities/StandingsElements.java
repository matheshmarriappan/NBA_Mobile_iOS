package com.nba.ios.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nba.ios.seleniumgluecode.CommonMethods;

public class StandingsElements {

	
	
	public static By teamName(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/../following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText");
	}
	
	public static By teamNameWith(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/../following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[2]");
	}
	public static By divisionDoubName(String div, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+div+"']/../../following-sibling::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[2]");
	}
	
	public static By divisionSingleName(String div, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+div+"']/../../following-sibling::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText");
	}
	
	public static List<WebElement> divName(String div, int i) {
		return CommonMethods.locateElements(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+div+"']/../../following-sibling::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText");
	}
	
	public static By toggleAction(String team, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeOther[@name='"+team+"']/following-sibling::XCUIElementTypeCell["+i+"]//XCUIElementTypeSwitch");
	}
	
	public static By winUI(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/../following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[1]");
	}
	
	public static By lossUI(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[2]");
	}
	
	public static By winPercent(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[3]");
	}
	
	public static By gamesBehind(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[4]");
	}
	
	public static By conf(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[5]");
	}
	public static By div(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH,"//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[6]");
	}
	
	public static By home(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[7]");
	}
	
	public static By road(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[8]");
	}
	
	public static By lTen(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH,"//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[9]");
	}
	
	public static By streak(String conf, int i) {
		return CommonMethods.locatorValue(Locators.XPATH,"//XCUIElementTypeStaticText[@name='"+conf+"']/following::XCUIElementTypeCell["+i+"]//XCUIElementTypeStaticText[10]");
	}
	
	
	public static By redirectedteamName() {
		return CommonMethods.locatorValue(Locators.XPATH,"//XCUIElementTypeStaticText[@name='Teams']/following::XCUIElementTypeOther[2]//XCUIElementTypeStaticText[1]");
	}
	
	//XCUIElementTypeButton[@name='Roster']/../../following-sibling::XCUIElementTypeOther//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText

	public static By raodHeadline() {
		return CommonMethods.locatorValue(Locators.ID, "ROAD");
	}
	
	public static By westernConferenceTitle() {
		return CommonMethods.locatorValue(Locators.ID, "Western Conference");
	}
	
	public static By winHeadline() {
		return CommonMethods.locatorValue(Locators.ID, "W");
	}

	public static By lossHeadline() {
		return CommonMethods.locatorValue(Locators.ID, "L");
	}

	public static By winPerHeadline() {
		return CommonMethods.locatorValue(Locators.ID, "WIN%");
	}
	public static By streakHeadline() {
		return CommonMethods.locatorValue(Locators.ID, "ROAD");
	}
	
	public static By exploreFeatures(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "(//XCUIElementTypeButton[@name='Explore Features'])["+i+"]");
	}
	
	public static By subscribedBtutton(int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "(//XCUIElementTypeButton[@name='SUBSCRIBED'])["+i+"]");
	}
	
//Player Stats page elements 
	
	public static List<WebElement> Stats() {
		return CommonMethods.locateElements(Locators.XPATH, "//XCUIElementTypeStaticText[@name='SEASON']/../following-sibling::XCUIElementTypeCollectionView/XCUIElementTypeCell");
	}
	
	public static WebElement statsHeadline (int i) {
		return CommonMethods.locateElement(Locators.XPATH, "//XCUIElementTypeStaticText[@name='SEASON']/../following-sibling::XCUIElementTypeCollectionView/XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]");
	}

	public static By seasonData (int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='SEASON']/../following-sibling::XCUIElementTypeCollectionView/XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	}
	
	public static By careerStatsData (int i) {
		return CommonMethods.locatorValue(Locators.XPATH, "//XCUIElementTypeStaticText[@name='SEASON']/../following-sibling::XCUIElementTypeCollectionView/XCUIElementTypeCell["+i+"]/XCUIElementTypeOther/XCUIElementTypeStaticText[3]");
	}
	

}
