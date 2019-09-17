Feature: Test Home Feature after OnBoarding Feature - TR C115691 - C115701 

@RunTest
Scenario: Verify Home session
	When User is on App Landing - "HomePageID" 
	Then Get "Home" JSON Response Data
	Then Verify "T1VideoorArticle" is displayed 
    Then Get "T1VideoorArticleText" 
	And Verify "T1VideoorArticleText" with Json Data
    #Then Verify "T1SpotLightID" is "VideoOrArticle" Click on "VideoPlayerContainer" or "T1VideoorArticle"
	Then Verify "T2VideoorArticle" is displayed 
	Then Get "T2VideoorArticleText" 
	And Verify "T2VideoorArticleText" with Json Data 
	#Then Verify "T2SpotLight" is "VideoOrArticle" Click on "VideoPlayerContainer" or "T2VideoorArticle"
	Then Verify "T3VideoorArticle" is displayed 
	Then Get "T3VideoorArticleText" 
	And Verify "T3VideoorArticleText" with Json Data
	#Then Verify "T3SpotLight" is "VideoOrArticle" Click on "VidoePlayerContainer" or "T3VideoorArticle"

@RunTest	
Scenario: Verify My Teams Section
    Then Swipe "12" vertically Until "MyTeamsID" is found
	Then Verify "MyTeamsID" is displayed
	Then Verify selected "TeamName1" - "Atlanta" with "NickName1" - "Hawks" is displayed
    Then Verify selected "TeamName2" - "BOSTON" with "NickName2" - "CELTICS" is displayed
    Then Swipe up to "0.60" and "0.40" pixels
    When Click on "TeamBlockStandings"
    And Wait for Page To Load
    Then Verify "StandingsScreen" is displayed
    	And Click on "DoneButtonID"	
    When Click on "TeamBlockScores"
    And Wait for Page To Load
    Then Verify "GameDetailScreen" is displayed
    	And Click on "DoneButtonID"
    When Click on "HomePage_TeamsSettingsID"
    	And Wait for Page To Load   	
    Then Click on "ManageTeams" using coordinates "203" and "631"
	And Wait for Page To Load
	Then Verify "FollowYourTeamsScreen" is displayed
	And Click on "DoneButtonID"	
	When Click on "HomePage_TeamsSettingsID"
	And Wait for Page To Load
	Then Click on "HideOrShowScoresButton" using coordinates "203" and "576" 	
	Then Verify "TeamRank" "TeamWin" and "TeamLoss" is "DisplayedOrHidden"
	When Click on "HomePage_TeamsSettingsID"
	And Wait for Page To Load
	Then Click on "HideOrShowScoresButton" using coordinates "203" and "576" 	
	Then Verify "TeamRank" "TeamWin" and "TeamLoss" is "DisplayedOrHidden"
	Then Swipe Reverse
	
@RunTest       	 
Scenario: Verify Headlines Section
     Then Get "Home" JSON Response Data   
     Then Swipe "12" vertically Until "HeadlinesID" is found 
     Then Verify "HeadlinesID" is displayed
     Then Swipe up to "0.60" and "0.40" pixels  
     Then Verify "HeadlineSection1VideoOrArcticle" is displayed  
     Then Get "HeadlineSection1VideoOrArcticleText"  
     And Verify "HeadlineSection1VideoOrArcticle" with Json Data   
     Then Verify "HeadlineSection2VideoOrArcticle" is displayed  
     Then Get "HeadlineSection2VideoOrArcticleText"  
     And Verify "HeadlineSection2VideoOrArcticle" with Json Data
     Then Verify "HeadlineSection1" is "VideoOrArcticle" Click on "VideoPlayerContainer" or "HeadlineSection1VideoOrArcticle"
     Then Verify "HeadlineSection2" is "VideoOrArcticle" Click on "VideoPlayerContainer" or "HeadlineSection2VideoOrArcticle"
     Then Click on "HeadlinesArrowID"
     And Wait for Page To Load   	
     Then Verify "HeadlinesScreen" is displayed
     And Click on "DoneButtonID"
     Then Swipe Reverse
     
@RunTest    
Scenario: Verify More section 	
     Then Swipe "12" vertically Until "MoreID" is found 
     Then Verify "MoreID" is displayed
     And Get the "MoreFromNBASections" displayed
     Then Swipe Reverse

@RunTest     
Scenario: Verify Editors Picks section 
     Then Swipe "12" vertically Until "EditorPicksID" is found 
     Then Verify "EditorPicksID" is displayed

     
Scenario: Verify the Watch Live Section 
     Then Swipe "12" vertically Until "WatchElementID" is found   
     Then Swipe up to "0.70" and "0.30" pixels
     Then Verify "WatchElementID" is displayed
     Then Verify "WatchLiveElement1" is displayed

     

     
  
     
     
     
    
