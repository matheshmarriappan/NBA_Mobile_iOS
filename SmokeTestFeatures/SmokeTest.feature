Feature: NBA App - Smoke Test Scenarios 

@runtest	
Scenario: NBA App Installation and Successful Launch 
	When User is on App Landing - "HomePageID"
	
		
Scenario: App Home Screen General Display Validation 
	When User is on "HomePageID" 
    Then Verify "T1VideoorArticle" is displayed 
    And Get "T1VideoorArticleText" 
    	And Verify "T1VideoorArticleText" with Json Data
    Then Verify "T2VideoorArticle" is displayed 
	And  Get "T2VideoorArticleText" 
	And Verify "T2VideoorArticleText" with Json Data 
	Then Verify "T3VideoorArticle" is displayed 
	And Get "T3VideoorArticleText" 
	And Verify "T3VideoorArticleText" with Json Data
	Then Swipe "12" vertically Until "MyTeamsID" is found
	And Verify "MyTeamsID" is displayed
	Then Swipe Reverse
	Then Swipe "12" vertically Until "HeadlinesID" is found
	And Verify "HeadlinesID" is displayed
	Then Swipe Reverse
	Then Swipe "12" vertically Until "MoreID" is found
	And Verify "MoreID" is displayed
	Then Swipe Reverse
	Then Swipe "12" vertically Until "EditorPicksID" is found
	And Verify "EditorPicksID" is displayed
	

Scenario: Scores Page General Display Validation 
	When Click on "ScoresButtonID"
	And Wait for Page To Load
	Then Verify "AllGamesID" is displayed
	Then Verify "MyGamesID" is displayed
	Then Verify Calendar validation - Go back dates, advance dates and check if games are displayed
    When Click on "CurrentCalenderDateID"
	Then Verify "CalenderView" is displayed 
	Then Click on "CancelButtonID"
	
	
Scenario: Videos Page General Display Validation 
	When Click on "MoreButtonID"
	Then Wait for Page To Load
	And Click on "VideoButtonID"
	Then Wait for Page To Load
	Then Wait for Page To Load
	Then Verify "VideoPageTitle" is displayed
	Then Click on "VideoTile1"
	Then Wait for Page To Load
    And Wait for Element
	And Get "VideoTitle" using attribute "name" 
    Then Get "FirstVideoInCollection"  
    And wait for some time if advertisement is present  	     	
	Then Get "PlayingVideoName"
	Then Verify text "PlayingVideoName" and "FirstVideoInCollection"
	Then Verify "NowPlayingID" is displayed
	And Click on "BackArrowID"
	Then Swipe "12" vertically Until "ClassicGamesID" is found
	Then Swipe up to "0.70" and "0.30" pixels  
	And Click on "ClassicGamesID"
    And Wait for Page To Load   
    Then Get "VideoTitle" using attribute "name" 
    And Click on "BackArrowID"
	Then Click on "BackBtn"
	
	
Scenario: Standings Page General Display Validation 
	When Click on "MoreButtonID"
	And Wait for Page To Load
	When Click on "StandingsID"
	Then Verify "StandingsPageTitle" is displayed
	Then Verify "ConferenceID" is displayed
	Then Verify "DivID" is displayed
	And Wait for Page To Load
	Then Verify "EasternConferernceID" is displayed
	And Swipe vertically Until "WesternConferernceID" is found
	Then Verify "WesternConferernceID" is displayed
	Then Click on "BackBtn"
	Then Wait for Page To Load
	
@runtest	
Scenario: NBA League Pass Sign in Validation 
	When Click on "MoreButtonID"
	And Wait for Page To Load
	Then Verify "SubscribeToNBALeaguePassID" is displayed
	And Click on "SubscribeToNBALeaguePassID"
	Then Wait for Page To Load
	Then Verify "NBASalesSheetID" is displayed	
	And Verify the sign in button displayed
	When Click on "SIgnInID"
	And Wait for Page To Load
    Then Verify "AlreadyPurchaseScreen" is displayed
    And Click on "NBA.comID"
    And Wait for Page To Load
    Then Verify "NBASignInViewID" is displayed
    Then sign in the NBA sign in Page
    	And Wait for Page To Load
    	And Wait for Page To Load
    	Then Verify "NBASalesSheetID" is displayed
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
    

Scenario: NBA TV Page - general Display Validation
	When Click on "NBATV_ID"
	Then Wait for Page To Load
	Then Wait for Page To Load
	And Verify "NBATVLandingPage" is displayed
	And Verify "NBATV_WatchButtonID" is displayed
	Then Verify "SignIn_ID" is displayed
	And Swipe up to "0.70" and "0.30" pixels  
	Then Verify "ComingUpNextSection_ID" is displayed
	Then Verify "ComingUpNext_SeeFullSchedule_ID" is displayed
	Then Swipe up to "0.70" and "0.30" pixels  
	And Verify "NBATV_OnDemand_ID" is displayed
	Then Verify "NBATVOnDemand_SeeAllShows" is displayed
	Then Swipe up to "0.70" and "0.30" pixels  
	Then Swipe up to "0.70" and "0.30" pixels  	
	And Wait for Page To Load	
	Then Verify "LearnMoreAboutNBATVLink_ID" is displayed
	When Click on "LearnMoreAboutNBATVLink_ID"
	And Wait for Page To Load
	Then Verify "LearnMoreAboutNBATVPageTitle" is displayed
	Then Click on "BackButton_NBATV_ID"
	And Wait for Page To Load
	
   
Scenario: Top Stories Page - general Display Validation
  When Click on "MoreButtonID"
  And Wait for Page To Load
  Then Click on "TopStores_BtnID"
  And Wait for Page To Load
  Then Verify "TopStoriesPage" is displayed
  And Verify "TopElement" is displayed 
  Then Verify "TopElementInTopStories" is video or arcticle
  Then Get "TopElementName" 
  Then Swipe up to "0.70" and "0.30" pixels  
  And Verify "ArcticleElement" is displayed
  Then Get "ArcticleElementName"	
  When Click on "ArcticleElement"
  And Wait for Page To Load
  And Wait for Page To Load
  Then Get "ArcticlePageNavigated"
