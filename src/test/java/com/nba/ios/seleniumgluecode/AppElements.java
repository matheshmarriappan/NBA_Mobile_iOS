package com.nba.ios.seleniumgluecode;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nba.ios.utilities.Locators;

public class AppElements {
	
	public static By locateID(String path) {
		return CommonMethods.locatorValue(Locators.ID,path);
	}
	
	public static By locateXPATH(String path) {
		return CommonMethods.locatorValue(Locators.XPATH,path);
	}

	public static List<WebElement> locateXPATHList(String path) {
		return CommonMethods.locateElements(Locators.XPATH, path);
	}
	
	public static By locateXPATHDynamicValue(String path, String teamName) {
		return CommonMethods.locatorValue(Locators.XPATH,path);
	}
}
