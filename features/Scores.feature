Feature: Scores page 
    
@RunTest
Scenario: Verify Tiles displayed on Score Page
	When User is on "HomePageID"
	Then Click on "ScoresButtonID"
	Then Wait for Page To Load
	Then Verify "ScoresPageNBALogoID" is displayed 
	Then Verify "AllGamesID" is displayed
	Then Verify "MyGamesID" is displayed
	Then Verify Calendar validation - Go back dates, advance dates and check if games are displayed
	Then Score Tiles Validation
	#When Click on "CurrentCalenderDateID"
	#Then Verify "CalenderView" is displayed
	#Then Navigate to "January" month with date "13" for "Future" game Tile
    # And Click on "CancelButtonID"
    #Then Score Tiles Validation
    When Click on "CurrentCalenderDateID"
	Then Verify "CalenderView" is displayed 
   	Then Navigate to "January" month with date "17" for "Final" game Tile
    Then Score Tiles Validation
   
@RunTest	
Scenario: Verify My Games Page
    Then Verify "MyGamesID" is displayed
    And Click on "MyGamesID"
	Then Wait for Page To Load
	Then Verify Calendar validation - Go back dates, advance dates and check if games are displayed
    When Click on "CurrentCalenderDateID"
	Then Verify "CalenderView" is displayed
    And Click on "CancelButtonID"
