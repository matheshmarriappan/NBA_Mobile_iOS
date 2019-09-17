Feature: NBA League Pass Sign in Validation
@RunTest
Scenario: Verify Standings conference page
	#When User is on App Landing - "HomePageID"
	When User is on "HomePageID"
	When Click on "MoreButtonID"
    And Wait for Page To Load    
	Then Click on "StandingsID"
	And Wait for Page To Load 
	Then Verify "StandingsPageTitle" is displayed 
	And Verify "ConferenceID" is displayed
	And Verify "EasternConferernceID" is displayed
	Then Verify Team Name and Player Profile redirection for the "Eastern Conference" data
	Then Verify "Eastern Conference" App data with JSON Data
	And Verify sorting of "Eastern Conference" data
	And Swipe vertically Until "WesternConferernceID" is found
	Then Swipe up to "0.60" and "0.40" pixels
	And Verify "WesternConferernceID" is displayed 
    Then Verify Team Name and Player Profile redirection for the "Western Conference" data
    And Wait for Page To Load     		
	Then Verify "Western Conference" App data with JSON Data
	And Verify sorting of "Western Conference" data
	
@RunTest		
Scenario: Verify Standings Div page		
	Then Verify "DivID" is displayed
	And Click on "DivID"
    And Wait for Page To Load 
    Then Verify "EasternConferernceID" is displayed
    Then Verify teams under the division "ATLANTIC" for the "Eastern Conference"
    Then Verify teams under the division "CENTRAL" for the "Eastern Conference"
    Then Swipe up to "0.60" and "0.40" pixels
    Then Verify teams under the division "SOUTHEAST" for the "Eastern Conference"
    Then Verify "WesternConferernceID" is displayed  
    Then Swipe up to "0.60" and "0.40" pixels
    Then Verify teams under the division "NORTHWEST" for the "Western Conference"
    Then Swipe up to "0.70" and "0.30" pixels
    Then Verify teams under the division "PACIFIC" for the "Western Conference"
    Then Swipe up to "0.60" and "0.40" pixels
    Then Verify teams under the division "SOUTHWEST" for the "Western Conference"
      
	
