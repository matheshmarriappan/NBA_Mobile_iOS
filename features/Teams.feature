Feature: Test Teams List C228771 to C228780
@RunTest
Scenario: Verify Teams List displayed
	When User is on "HomePageID"
	Then Click on "MoreButtonID"
	Then Wait for Page To Load
	Then Click on "TeamsPageID"
	Then Wait for Page To Load
	#Then Get Team Name List
	Then Get "TeamName"
	Then Click on "TeamName"
	Then Wait for Page To Load
	Then Verify "TeamLogo" is displayed
    Then Verify "TeamNickName" is displayed
    And Get "TeamNickName"
	Then Verify "Team_TicketsBtnID" is displayed
	Then Click on "Team_TicketsBtnID"
	Then Wait for Page To Load
	Then Verify "Team_TicketsPage" is displayed
	And Click on "DoneButtonID"	
	Then Verify "Team_FollowBtn" is displayed
	And Get "Team_FollowBtn" using attribute "name" 
	Then Click on "Team_FollowBtn"
	And Get "Team_FollowBtn" using attribute "name" 
	Then Verify "Horizontal_Bar_Scroller_Team" is displayed
	Then Verify "Team_WebSiteID" is displayed
	Then Verify "Team_AppID" is displayed
	Then Verify "Team_RosterTabID" is displayed
	Then Click on "Team_RosterTabID"
	Then Wait for Page To Load
	And Verify "RosterPlayerDetails" is displayed
	Then Get "PlayerDisplayedFirstInRosterTab"
	Then Verify "Team_CoachesTabID" is displayed
	Then Click on "Team_CoachesTabID"
	Then Wait for Page To Load
	Then Verify "HeadCoachSection" is displayed
	Then Verify "AssistantCoachSection" is displayed
	Then Verify "Team_ScheduleTabID" is displayed
	Then Click on "Team_ScheduleTabID"
	Then Wait for Page To Load
	Then Verify "SearchTeamsFieldID" is displayed
	Then Verify "ScheduleSectionDetails" is displayed
	Then Click on "BackArrowID"
	Then Wait for Page To Load
	Then Click on "BackBtn"
	Then Wait for Page To Load
	
	
	
	
	