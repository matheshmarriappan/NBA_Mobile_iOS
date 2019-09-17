Feature: Settings Page Validations TR C228797 - TR C228813
@RunTest
Scenario: Verify App Settings Scenarios - Notifications , Local Time App Settings and Auto Play Settings verifications
	When User is on "HomePageID"
	When Click on "MoreButtonID"
	Then Wait for Page To Load
	Then Verify "MorePage" is displayed
	Then Click on "SettingsIcon_ID"
	And Wait for Page To Load
	Then Verify "SignIn/CreateNBAAccountButton_ID" is displayed
	And Click on "SignIn/CreateNBAAccountButton_ID"
	And Wait for Page To Load
    Then Verify "NBASignInView" is displayed
    When Click on "CreateAccountBtn_ID"
    And Wait for Page To Load
	And Verify "CreateAccountPage" is displayed
	Then Create Account by entering username password and postal code
	Then Verify "FollowTeams_ID" is displayed
	When Click on "FollowTeams_ID"
	And Wait for Page To Load
	Then Verify "FollowYourTeamsScreen" is displayed
	And Click on "BrooklynNetsID"
    And Click on "BostonCelticsID"
	And Click on "DoneButtonID"
	Then Verify "SectionAppSettings_ID" is displayed
	And Verify "NotificationsID" is displayed
	When Click on "NotificationsID"
	And Wait for Page To Load 
	Then Verify "NotificationPageTitle" is displayed
	And Click on "DoneButtonID"
	Then Wait for Page To Load
	Then Verify "LocalTimeAppSettings" is displayed and Validate "Local Time Toggle"
	Then Verify "AutoPlayAppSettings" is displayed and Validate "Auto Play"
	Then Click on "BackArrowID"
	And Wait for Page To Load
	When Click on "MoreButtonID"
	And Wait for Page To Load
	Then Click on "SettingsIcon_ID"
	And Wait for Page To Load
	Then Verify "HideScoresSettings" is displayed and Validate "Hide Scores"
