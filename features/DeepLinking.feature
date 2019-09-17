Feature: Test Deeplink features 


Scenario: Verify Deeplinking feature
#Given launch the appium 
Given launch the deeplink "Scores_DeeplinkID" 
Then Verify element "ScoresPageID" is displayed
Then Verify element "AllGamesID" is displayed
Then Verify element "MyGamesID" is displayed
#Given launch the deeplink "TopStories_DeeplinkID" 
#Then Verify element "TopStoriesPage" is displayed
#Given launch the deeplink "Videos_DeeplinkID" 
#Then Verify element "VideosPage" is displayed
#Given launch the deeplink "Standings_DeeplinkID" 
#Then Verify element "StandingsPageTitle" is displayed
#Given launch the deeplink "Teams_DeeplinkID"
#Then Verify element "TeamsPage" is displayed
#Given launch the deeplink "TeamAtlanta_DeeplinkID"
#Then Verify element "AtlantaHawksPageID" is displayed
#Given launch the deeplink "TeamWithID"
#Then Verify element "AtlantaHawksPageID" is displayed
#Given launch the deeplink "Players_DeeplinkID"
#Then Verify element "PlayersPageTitle" is displayed
#Given launch the deeplink "PlayerWithID"
#And Verify element "PlayerFirstName" is displayed
#Then Get text "PlayerFirstName"
#Then Get text "PlayerLastName" 
#Given launch the deeplink "Settings_DeeplinkID"
#Then Verify element "SettingsPage" is displayed
#Given launch the deeplink "Stats_DeeplinkID"
#Then Verify element "StatsPage" is displayed
#Given launch the deeplink "SalesSheet_DeeplinkID"	
#And Verify element "NBASalesSheetID" is displayed
#Given launch the deeplink "NBATV_DeeplinkID"
#And Verify element "NBATVLandingPage" is displayed
Given launch the deeplink "NBATVShows_DeeplinkID"
And Verify element "NBAOnDemandPage" is displayed
Given launch the deeplink "NBATVSchedule_DeeplinkID"
And Verify element "NBATVSchedulePage" is displayed