Feature: NBA League Pass Sign in Validation


@RunTest
Scenario: Verify League Pass Feature from More
	When User is on App Landing - "HomePageID" 
	Then Click on "MoreButtonID"
	Then Wait for Page To Load
	Then Verify "MorePage" is displayed 
	Then Verify "SubscribeToNBALeaguePassID" is displayed
	Then Click on "SubscribeToNBALeaguePassID"
	Then Wait for Page To Load
	Then Verify "NBASalesSheetID" is displayed
	#Then Verify Subscriptions present in NBA League Pass Page
	Then Verify the BlackOut Section
    And Verify the sign in button displayed
	When Click on "SIgnInID"
	And Wait for Page To Load
    Then Verify "AlreadyPurchaseScreen" is displayed
    And Click on "NBA.comID"
    And Wait for Page To Load
    Then Verify "NBASignInViewID" is displayed
    Then sign in the NBA sign in Page
    And Wait for Element
    	Then Verify "NBASalesSheetID" is displayed
    Then Verify "Subscriber_AgreementHyperlinkID" is displayed
    And Click on "Subscriber_AgreementHyperlinkID"
    And Wait for Element
    Then Verify "NBAWebViewID" is displayed
    And Verify "NBALeaguePassTermsAndConditionsPage" is displayed
    And Click on "BackButtonInWebView"
    And Wait for Page To Load
    Then Verify "Subscription_DetailsHyperlink" is displayed
    And Click on "Subscription_DetailsHyperlink"
    And Verify "SubscriptionDetailsSection" is displayed	
	And Click on "BackButtonInWebView"
	And Click on "CloseButton_ID"
    	When User is on "MorePage"
	Then Verify "SettingsIcon_ID" is displayed
	And Click on "SettingsIcon_ID"
    Then Wait for Page To Load
    And Verify "SettingsPage" is displayed
    	And Get "User-Signed" using attribute "name" 
    Then Verify "SignOutID" is displayed
    	And Click on "SignOutID"
    	Then Wait for Page To Load 	  
	Then Click by coordinates "275" and "407" for "Sign out Ok button"
    And Wait for Page To Load
    And Verify if the user is Signed out 
    And Click on "BackBtn"
	
	


	  
