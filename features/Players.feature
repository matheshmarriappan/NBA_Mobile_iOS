Feature: Test Players List C228781 to C228796
@RunTest	
Scenario: Verify Teams List displayed
	When User is on App Landing - "HomePageID" 
	When User is on "HomePageID"
	Then Click on "MoreButtonID"
	Then Wait for Page To Load
	Then Click on "PlayersID"
	Then Wait for Page To Load
	Then Verify "PlayersPageTitle" is displayed
    #And Verify advertisement if present
	Then Verify "SearchIcon_ID" is displayed
	Then Verify "PlayerName_ColumnHeadingID" is displayed
	Then Verify "Team_ColumnHeadingID" is displayed
	#Then Get "PlayerNames" details "PlayerNames_List"
	Then Click by coordinates "57" and "318" for "Player_Adebayo,Bam"
	#When Click on "AdebayoBam_Player"
	And Wait for Page To Load
	Then Verify "PlayersDetailPage" is displayed
	#Then Click on "SearchIcon_ID"
	#Then Wait for Page To Load
	#Then Verify "Search_TextField" is displayed
	#Then Enter "Barnes" in "Search_TextField" and click "PickFromSearchList"
	#Then Wait for Page To Load
	Then Verify "PlayerImage" is displayed
	Then Verify "TeamLogo" is displayed	
	Then Get "PlayerFirstName" 
	Then Get "PlayerLastName"
	Then Verify "Players_FollowBtn" is displayed
	And Get "Players_FollowBtn" using attribute "name" 
	Then Click on "Players_FollowBtn"
	And Get "Players_FollowBtn" using attribute "name"	
	Then Verify "RecordsSlider" is displayed
	And Verify Record Summary with Json Data
	And Verify advertisement if present
	Then Verify "Stats_TabID" is displayed
	Then Click on "Stats_TabID"
	Then Wait for Page To Load
	Then Get "StatsColumnNames" details "StatsDetails"
	Then Click on "Info_TabID"
	Then Wait for Page To Load
    Then Swipe up to "0.70" and "0.30" pixels  
	Then Verify "Previous3Games_Section" is displayed
	Then Get "Previous 3 Games Date" details "DateDisplayedForPrevious3Games"
    Then Get "Previous 3 Games Opponent" details "OpponentDisplayedForPreviousGames"	
	Then Click on "BackArrowID"
	Then Wait for Page To Load
	Then Click on "BackBtn"
	Then Wait for Page To Load