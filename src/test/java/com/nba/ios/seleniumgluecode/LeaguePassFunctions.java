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



public class LeaguePassFunctions {
	
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	/**
	 * Verify League Pass Subscriptions
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void verifyLeaguePassSubscriptions() throws InterruptedException, IOException {
		//int i = 1;
		List<WebElement> tiles = CommonMethods.getListofText("SalesSheet_Tiles");
		System.out.println("SalesSheet_Tiles size: "+tiles.size());
		
		for(int i = 0; i<tiles.size(); i++) {
			String name = tiles.get(i).getText();
			
			System.out.println("tile name "+ i + name);
			if(name.equalsIgnoreCase("NBA Team Pass")) {
				Reporter.addStepLog(COLOR_OPEN+" NBA Team Pass - One Team "+ COLOR_CLOSE +" is displayed");
				if(CommonMethods.isDisplayed(StandingsElements.subscribedBtutton(i+1))) {
					Reporter.addStepLog("User is already Subscribed to NBA Team Pass");
					String teamName = CommonMethods.getText("NBATeamPassTeamName");	
					Reporter.addStepLog("The Team displayed in NBA Team Pass after the user is subscribed is "+COLOR_OPEN + teamName + COLOR_CLOSE);
				} else {
					validateOneTeam(i);
				}
				
						
				//Explore Feature Button Validation
				verifyExploreFeaturesBtn(i);
				
				System.out.println("One Team Pass");
			} else if(name.equalsIgnoreCase("NBA Single Game")) {
				

				if(CommonMethods.isDisplayed(StandingsElements.subscribedBtutton(i+1))) {
					Reporter.addStepLog("User is already Subscribed to NBA Single Game");
				} else {
					if(CommonMethods.isDisplayed("PickTheGameID")) {
						String getPickTheGame = CommonMethods.getText("PickTheGameID");
						Reporter.addStepLog(COLOR_OPEN +" NBA Single Game "+ getPickTheGame +" "+COLOR_CLOSE+ " is present .");
						Reporter.addStepLog( "The Subscription amount displayed in " + name + " is :" + COLOR_OPEN  + CommonMethods.getText("SingleGame_SubsAmt") 
						+ COLOR_CLOSE);								
					} else {
						System.out.println("Pick the game text is not displayed in: "+name);
					}				
				}
				
				//Explore Feature Button Validation
				verifyExploreFeaturesBtn(i);
				
			}  else {
				System.out.println("The above conditions are not satified.");
			}
			
			//Swiping left to right to next tile
			
			System.out.println("trying to swipe after i" + i);
			HelperUtils.swipeFromRightToLeft("LeaguePass");
		}
	}
	/**
	 * This method is to validate the Sales Sheet details for NBA Team Pass One Team
	 * 
	 * @param i
	 * @throws IOException
	 */
	private static void validateOneTeam(int i) throws IOException {
		
		List<WebElement> btns = CommonMethods.getListofText("OneTeamBtns");
		if(btns.size()>1) {
			Reporter.addStepLog("Monthly and Annual Team Buttons are displayed in One Team Pass");
			
		    for(int j=0;j<btns.size();j++) {
					String getBtnName = btns.get(j).getAttribute("name");
					if(getBtnName.equalsIgnoreCase("Monthly")) {
						btns.get(j).click();
						String getAmtDet = CommonMethods.getAttributeText("OneTeam_SubscAmt", "value")+"/month";
						Reporter.addStepLog("Monthly subscription amount displayed for the one team NBA team pass  is : "+ COLOR_OPEN+ getAmtDet + COLOR_CLOSE);
					}else if(getBtnName.equalsIgnoreCase("Annual")) {
						btns.get(j).click();
						String getAmtDet = CommonMethods.getAttributeText("OneTeam_SubscAmt", "value")+"/Annual";
						Reporter.addStepLog("Annual subscription amount displayed for the one team NBA team pass  is : "+ COLOR_OPEN+ getAmtDet + COLOR_CLOSE);
					}else {
						System.out.println("The above two conditios are not satisfied");
					}
		 }
		}else {
			Reporter.addStepLog(RED_OPEN + "Buttons for subscriptions are not displayed as expected :"+COLOR_CLOSE);
		}		
	}

	public static void verifyExploreFeaturesBtn(int i) throws InterruptedException {
		
		if(CommonMethods.isDisplayed(StandingsElements.exploreFeatures(i+1))) {
			System.out.println("Verirfy Explore Feature Button is present");

			String getExplFeaTxt = CommonMethods.getAttributeText(StandingsElements.exploreFeatures(i+1), "name");
			Reporter.addStepLog(COLOR_OPEN+getExplFeaTxt+COLOR_CLOSE+ " is present.");
			CommonMethods.clickBtn(StandingsElements.exploreFeatures(i+1), "ExploreFeaturesBtn is clicked", "Not able to click ExploreFeaturesBtn");
			CommonMethods.waitForElement();
			if(CommonMethods.isDisplayed("ExploreFeaturesPageID")) {
				Reporter.addStepLog(COLOR_OPEN+getExplFeaTxt+COLOR_CLOSE+ " page is navigated successfully.");
				CommonMethods.clickBtn("DoneButtonID", "Done btn is clicked successfully to return back to Leauge pass page", "Done btn is not clicked");
				CommonMethods.waitForElement();
			}else {
				Reporter.addStepLog(RED_OPEN+getExplFeaTxt+COLOR_CLOSE+ " page is not navigated.");
			}
		}else {
			System.out.println("Verirfy Explore Feature Button is not present");
			Reporter.addStepLog(RED_OPEN+"ExploreFeatures "+COLOR_CLOSE+ " button is not presnt.");
		}
		
	}

	public static void verifyBlackOutSession() {
		if(CommonMethods.isDisplayed("BlackOutID") && CommonMethods.isDisplayed("NoticeID")) {
			Reporter.addStepLog("BlackOut Notice Section is displayed in the NBA League Pass Page");	
			Reporter.addStepLog("Blackout Message displayed is : "+ COLOR_OPEN + CommonMethods.getText("BlackOutMessage") + COLOR_CLOSE);
		} else {
			Reporter.addStepLog("BlackOut Notice Section is not displayed in the NBA League Pass Page");
		}
	}
	
	public static void signIn() throws IOException, InterruptedException {
		
		WebDriver driver = AppiumApp.getCurrentDriver();

	      String username =  ConfigPropertyReader.getPropertyValue("USERNAME");
	      String password =  ConfigPropertyReader.getPropertyValue("PASSWORD");
	      
	      String decryptPassword = null;
	      try {
	          decryptPassword = AesCrypt.decrypt(password);
	      } catch (Exception e) {
	          System.out.println("Decrypted password exception "+e.getMessage());
	      }

			if(CommonMethods.isDisplayed("EmailAddressField")) {
				System.out.println("Email address field is found");
				WebElement emailField = CommonMethods.getWebElement("EmailAddressField");
				emailField.clear();
				emailField.sendKeys(username);
				Reporter.addStepLog("Email address is entered successfully");

			} else {
				System.out.println("Email address field is not found");
				Reporter.addStepLog(RED_OPEN+"Email address field is not found in sign in page"+COLOR_CLOSE);
			}
			
			if(CommonMethods.isDisplayed("PasswordField")) {
				System.out.println("password field is present");
				WebElement passwordField = CommonMethods.getWebElement("PasswordField");
				passwordField.clear();
				Thread.sleep(3000);
				System.out.println("decripted password"+decryptPassword);
				passwordField.sendKeys(decryptPassword);
				Reporter.addStepLog("Password is entered successfully");
				Thread.sleep(3000);

			} else {
				System.out.println("Password field is not present");
				Reporter.addStepLog(RED_OPEN+"Password field is not found in sign in page"+COLOR_CLOSE);
			}
			if(CommonMethods.isDisplayed("SignIn_ID")) {
				System.out.println("sign in button is prsent and clicking on it");
				Thread.sleep(3000);
				CommonMethods.clickBtn("SignIn_ID", "Sign in button is clicked on the NBA Sign in Page", "Unable to click the sign in button");
				Thread.sleep(3000);
				
			} else {
				Assert.fail("sign in button is not present on the NBA sign in view page");	
			}
			
		/*	//Swiping bottom to top in NBA League Pass Page
			System.out.println("Gonna swipe bottom to top ");
			HelperUtils.swipeBottomToTop(driver);
			System.out.println("bottom to top swipe completed");*/

	}
	public static void signInWithLP() throws InterruptedException, IOException {
        WebDriver driver = AppiumApp.getCurrentDriver();

		if(CommonMethods.isDisplayed("SIgnInID")) {
		    CommonMethods.clickBtn("SIgnInID", "SIgnInID" + " is clicked", "SIgnInID" +" is not clicked");
			CommonMethods.waitForElement();
			if(CommonMethods.isDisplayed("AlreadyPurchaseScreen") ) {
				Reporter.addStepLog("Already Purchased Screen is displayed");
				CommonMethods.clickBtn("NBA.comID", "NBA.com is found and clicked on the Already Purchased SCreen", "Unable to click on the NBA.com");
				CommonMethods.waitForElement();
				if(CommonMethods.isDisplayed("NBASignInViewID")) {
					try {
						signIn();
					} catch (Exception e) {
						Reporter.addStepLog("Unable to Sign in with League Pass User "+ e.getMessage());
					}
				} else {
					Reporter.addStepLog(RED_OPEN + " Sign in View is not displayed "+ COLOR_CLOSE);
				}
			} else {
				Reporter.addStepLog(RED_OPEN + " Already Purchased Screen not displayed "+ COLOR_CLOSE);
			}
		} 
		
	}
	
	/**
	 *  Verify if the sign in button is displayed in League Pass Page
	 * 
	 */
	public static void verifySignInButton() {
		
		boolean isSignInPresent = CommonMethods.isDisplayed("SIgnInID");
		if(isSignInPresent) {
			Reporter.addStepLog("Sign in button is displayed in the NBA League Pass Sales Sheet Page");
		} else {
			Assert.fail("Sign in button is not displayed. The user might be already signed in ");
			HelperUtils.captureScreen();
		}
	}
	
	/**
	 * Verify if the signed in league pass user is signed out successfully
	 * 
	 * 
	 */
	public static void verifySignOut() {
		
		boolean signBtn = CommonMethods.isDisplayed("SignIn/CreateNBAAccountButton_ID");
		if(signBtn) {
			Reporter.addStepLog("The user is signed out successfully");
		} else {
			Reporter.addStepLog(RED_OPEN +" The user is not signed out successfully" + COLOR_CLOSE);
		}
	}
	
	
}
