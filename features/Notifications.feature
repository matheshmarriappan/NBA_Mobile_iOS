Feature: Notification Test Cases TR : C228586 to TR C228590

@RunTest
Scenario: Verify Videos Session on Top
	When User is on "HomePageID"
	Then Click on "MoreButtonID"
	Then Wait for Page To Load
	Then Verify "MorePage" is displayed 
	Then Verify "SettingsIcon_ID" is displayed
	And Click on "SettingsIcon_ID"
    Then Wait for Page To Load
    And Verify "SettingsPage" is displayed
	Then Wait for Page To Load
	And Verify "NotificationsID" is displayed
    And Click on "NotificationsID"
    Then Wait for Page To Load
	Then Verify "NotificationPageTitle" is displayed
	Then Verify "Notifications" is displayed
	Then Verify All Notification ON OFF Mode
	Then Wait for Page To Load
	Then Verify "NBABreakingNewsID" is displayed
	Then Verify "NBABreakingNewsToggleID" toggle action
	Then Wait for Page To Load
	Then Verify "EditorsPickNotificationID" is displayed
	Then Verify "EditorsPickToggleID" toggle action
	Then Wait for Page To Load
	Then Verify "CloseGameAlertID" is displayed
	Then Verify "CloseGameAlertToggleID" toggle action
	Then Wait for Page To Load
	Then Verify "Notifications_Teams" is displayed
	And Click on "Notifications_Teams"
	Then Wait for Page To Load
	Then Get "Notifications_TeamNamesTile" and Click on "2" team
	Then Click on "BackBtn_InTeamNotificationsID"
	Then Click on "DoneButtonID"
	
	
