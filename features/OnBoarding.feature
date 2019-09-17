Feature: NBA App Installation and test the Onboarding features - Test Rail Test Case ID - From C114906 to C114926 

Scenario: Verify NBA Loading screen, External links available and Accept button 
	Then Verify "NbaLoadingScreenID" is displayed 
    Then Wait for Page To Load
    And Wait for Page To Load
    And Wait for Page To Load		
    	Then Verify "PrivacyAndTermfOfService" is displayed
	When Click on "TermsOfServiceID"
	Then Wait for Page To Load	
	Then Verify "TermsOfServiceWebPage" is displayed
	And Click on "DoneButtonID"  
    Then Wait for Page To Load	
    When Click on "PrivacyPolicyID"
	Then Wait for Page To Load
	Then Verify "PrivacyPolicyWebPage" is displayed
	And Click on "DoneButtonID"  
    Then Wait for Page To Load	
	Then Click on "AcceptButtonID" 
	Then Verify "NBALeaguePassLandingScreen" is displayed
	

Scenario: Test
    Then Verify "NBALeaguePassLandingScreen" is displayed 
	When Click on "ViewPackages_ID"
	Then Verify "NBASalesSheetID" is displayed
    And Click on "CloseButton_ID" 
    When Click on "AlreadyPurchasedID" 
    Then Verify "AlreadyPurchaseScreen" is displayed
    And Click on "NBA.comID"
    And Wait for Page To Load
    Then Verify "NBASignInViewID" is displayed
    Then Verify "HelpButton_ID" is displayed
    And Click on "HelpButton_ID"
    And Wait for Page To Load
    Then Verify "NBAWebViewID" is displayed
    And Click on "BackButtonInOnboardingWV"
    And Verify "PrivacyPolicyID" is displayed
    When Click on "PrivacyPolicyID"
    And Wait for Page To Load
    Then Verify "PrivacyPolicyWebPage" is displayed
    And Click on "BackButtonInOnboardingWV"
    And Wait for Page To Load
    When Click on "TermsOfUse_ID"
    And Wait for Page To Load
    Then Verify "TermsOfUseWebPage" is displayed
    And Click on "BackButtonInOnboardingWV"
    And Wait for Page To Load
    #When Click on "CreateAccountBtn_ID"
    #And Wait for Page To Load
	#And Verify "CreateAccountPage" is displayed
	#Then Create Account in onboarding by entering username password and postal code
	#And Verify "FollowYourTeamsScreen" is displayed
	#When Click on "BackID" 
    #And Wait for Page To Load
    #When Click on "AlreadyPurchasedID" 
    #Then Verify "AlreadyPurchaseScreen" is displayed
    #And Click on "NBA.comID"
    And Wait for Page To Load
    Then sign in the NBA sign in Page
    And Wait for Page To Load  
    And Verify "FollowYourTeamsScreen" is displayed
    	
	
Scenario: Verify Follow Your Teams Screen 
	When User is on "FollowYourTeamsScreen"
	Then Click on "AtlantaHawksID"
    Then Click on "ChicagoBullsID"
	And Click on "NextButtonnID"  
	
		
Scenario: All Notifications Page - TR (C115061-C115066) 
	When User is on "AllNotificationsPage"  
	Then Verify "NBABreakingNewsID" is displayed
	Then Verify "NBABreakingNewsToggleID" toggle action
	Then Wait for Page To Load
	Then Verify "EditorsPickNotificationID" is displayed
	Then Verify "EditorsPickToggleID" toggle action
	Then Wait for Page To Load
	Then Verify "CloseGameAlertID" is displayed
	Then Verify "CloseGameAlertToggleID" toggle action
	Then Wait for Page To Load
	Then Verify "Selected_AtlantaHawksTeam" is displayed
	And Verify the toggle actions for the team "ATLANTA HAWKS"	
    And Click on "DoneButtonID"  
	
	
Scenario: Verify Navigation Bar - TR C114927 
	When User is on "NavigationBar" 
	Then Verify "HomePageID" is displayed
	Then Verify "ScoresButtonID" is displayed
	Then Verify "VideoButtonID" is displayed
	Then Verify "MoreButtonID" is displayed
	Then Click on "MoreButtonID"  
	And Wait for Element
	Then Verify "StandingsID" is displayed
	
	
	
	
	
	