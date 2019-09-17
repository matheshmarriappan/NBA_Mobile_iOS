Feature: NBA App Navigation Bar Validation - C228591

	
@RunTest	
Scenario: Verify Navigation Bar - TR C114927 
	When User is on App Landing - "HomePageID" 
    Then Verify "NavigationBar" is displayed
    Then Verify Navigation Bar with "Menus" json data
	
	