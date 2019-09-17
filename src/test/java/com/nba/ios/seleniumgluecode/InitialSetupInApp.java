package com.nba.ios.seleniumgluecode;
/*package seleniumgluecode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;

import utilities.HelperUtils;

public class InitialSetupInApp {
	
	WebDriver driver;
	
	@FindBy(id = "com.nbadigital.gametimelite:id/remote_logo")
	WebElement remoteInitalLogo;
	
	@FindBy(id = "com.nbadigital.gametimelite:id/remote_logo")
	static By remoteInitalLogoByEle;
	
	@FindBy(id = "android:id/button1")
	static WebElement AcceptButton;
	
	@FindBy(id = "com.nbadigital.gametimelite.dev:id/nba_lp_logo")
	static WebElement nbaLogo;
	
	@FindBy(id="com.nbadigital.gametimelite.dev:id/gateway_options_button")
	static WebElement viewOptionsBtn;
	
	@FindBy(id="com.nbadigital.gametimelite.dev:id/gateway_purchased_button")
	static WebElement purchaseBtn;
	
	@FindBy(id ="com.nbadigital.gametimelite.dev:id/gateway_maybe_later_button")
	static WebElement mayBeLaterBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@index= '1']")
	static WebElement pageTitle;
	
	@FindBy(id="com.nbadigital.gametimelite.dev:id/btn_next")
	static WebElement nextBtn;
	
	@FindBy(id = "com.nbadigital.gametimelite.dev:id/done_button")
	static WebElement donebtn;

	public InitialSetupInApp(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void remoteLogo() throws InterruptedException {
		HelperUtils.waitForElementToVisible();
		HelperUtils.waitForElementToVisible();
		
		try {
			if(HelperUtils.isDisplayed(remoteInitalLogo))
			{
				System.out.println("Remote Logo is displayed");
				Reporter.addStepLog("Remote Log is displayed");
			}
		} catch (Exception e) {
			System.out.println("Remote Logo is not displayed ");
		}
		
		boolean logoDisappear = HelperUtils.waitForElementToBeInvisible(driver, remoteInitalLogoByEle);
		if(logoDisappear)
		{
			System.out.println("Logo Disappeared");
		} else {
			System.out.println("Logo still exists or crossed");
		}
	}

	public static void handleAcceptBtn() throws InterruptedException {
		System.out.println("In Handle Accept Button");
		try {
			if(AcceptButton.isDisplayed())
			{
				System.out.println("Accept Button is displayed");
				AcceptButton.click();
				System.out.println("Clicked the accept button");
				Reporter.addStepLog("Accept Button is clicked.");
			} else {
				System.out.println("accept Button is not displayed");
			}
		} catch (Exception e) {
			System.out.println("Inital buttons are not displayed");
			Reporter.addStepLog("Initial Buttons are not displayed . May be due to app already configured");
		}
		
		
	}
	
	public void clickOnBtns(String buttonName) throws InterruptedException {
		System.out.println("Inside click on btns method ***");
		if(buttonName.equalsIgnoreCase("MayBeLater"))
		{
			System.out.println("In may be later condition");
			handleMayBeLaterBtn();
		} else if(buttonName.equalsIgnoreCase("Next"))
		{
			System.out.println("In next btn condition");
			nextBtn();
		} else if(buttonName.equalsIgnoreCase("Done"))
		{
			System.out.println("In done btn condition");
			doneBtn();
		} else if(buttonName.equalsIgnoreCase("Accept"))
		{
			System.out.println("In Accept button condition");
			handleAcceptBtn();
		}
		
	}
	
	public static void handleMayBeLaterBtn() {
		
		try {
			boolean isNBALogoDisplayed =HelperUtils.isDisplayed(nbaLogo);
			if(isNBALogoDisplayed)
			{
				System.out.println("Logo is displayed ");
				Reporter.addStepLog("NBA Logo is displayed after clicking on Accept Button.");
			} else {
				System.out.println("Logo is not displayed");
			}
			
			boolean isviewOptionsBtnDisplayed = HelperUtils.isDisplayed(viewOptionsBtn);
			
			if(isviewOptionsBtnDisplayed)
			{
				System.out.println("View Your options is displayed ");
				Reporter.addStepLog("View Your Options is displayed ");
			} else {
				System.out.println("View Your options is not displayed ");
				Reporter.addStepLog("View Your Options is not displayed ");
			}
			
			boolean ispurchaseBtnDisplayed = HelperUtils.isDisplayed(purchaseBtn);
			
			if(ispurchaseBtnDisplayed)
			{
				System.out.println("Already Purchased is displayed ");
				Reporter.addStepLog("Already Purchased is displayed ");
			} else {
				System.out.println("Already Purchased is not displayed ");
				Reporter.addStepLog("Already Purchased is not displayed ");
			}
			
		
		boolean mayBeLaterDisplayed = HelperUtils.isDisplayed(mayBeLaterBtn);
		if(mayBeLaterDisplayed)
		{
			Reporter.addStepLog("May Be Later is displayed");
			HelperUtils.click(mayBeLaterBtn, "May be Later");
			
			System.out.println("Clicked on May be later");
		} else {
			System.out.println("Unable to click on may be later");
		}
		
		} catch (Exception e) {
			System.out.println("Ended with error "+e.getMessage());
		}
		
	
		
	}
	
	public static void nextBtn() {
		System.out.println("inside next btn ");

		if(HelperUtils.isDisplayed(nextBtn))
		{
			Reporter.addStepLog(" Title of Next Button page is "+pageTitle.getText());
			HelperUtils.click(nextBtn, "Next Button");
			System.out.println("Clicked on Next Button " +pageTitle.getText());
		} else {
			System.out.println("Unable to find next btn");
		}
		
		
	}
	
	public static void doneBtn() {
		
		String getTitle = HelperUtils.getText(pageTitle );
		System.out.println("Done btn title "+getTitle);
		
		if(HelperUtils.isDisplayed(donebtn))
		{
			System.out.println("done is displayed ");
			HelperUtils.click(donebtn, "Done Button");
			Reporter.addStepLog("Clicked on Done button and title of the page is "+getTitle);
		} else {
			System.out.println("Unable to click on done button");
			Reporter.addStepLog("Unable to click on Done button");
		}
	}

}
*/