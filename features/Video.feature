Feature: Test Video Feature after Scores Feature - TR C69011 - C105984 - Total 17 Test cases

@RunTest
Scenario: Verify Videos Session on Top
	When User is on "HomePageID"
    When Click on "VideoButtonID"  
    And Wait for Page To Load    
    Then Verify "VideoPageTitle" is displayed 
    #Then Verify "VideoElement1" is "VideoOrArcticle" Click on "VideoPlayerContainer" or "VideoTile2"
    Then Click on "VideoTile2"    
    And Wait for Page To Load   
    And Get "VideoTitle" using attribute "name" 
    Then Get "FirstVideoInCollection"  
    And wait for some time if advertisement is present  	     	
	Then Get "PlayingVideoName"
	Then Verify text "PlayingVideoName" and "FirstVideoInCollection"
	Then Verify "NowPlayingID" is displayed
	And Click on "BackArrowID"
		
@RunTest	
Scenario: Verify Classic Games session	
    Then Swipe "12" vertically Until "ClassicGamesID" is found
	Then Swipe up to "0.70" and "0.30" pixels  
	And Click on "ClassicGamesID"
    And Wait for Page To Load   
    Then Get "VideoTitle" using attribute "name"
    And Validate Classic Games functions
	
	
	
	
	