Feature: NBA TV Landing Page
@RunTest
Scenario: Verify NBA TV Landing feature for non subscribed users
	When Click on "MoreButtonID"
	And Wait for Page To Load
	Then Click on "NBATvButton_ID"
	And Wait for Page To Load
	Then Verify "NBATVLandingPage" is displayed
	And Verify "NBATV_WatchButtonID" is displayed
	Then Click on "NBATV_WatchButtonID"
	And Wait for Page To Load
	Then Verify "NBATV_UpsellSheetID" is displayed
	Then Click on "CancelButtonID"
	And Wait for Page To Load
    Then Swipe up to "0.70" and "0.30" pixels  
	Then Verify "ComingUpNextSection_ID" is displayed	
	And Get "NBAGameTime_ComingUpNextSection" details "GameTime_ComingupNext"
	When Swipe from Right To Left in "ComingUpNext ShowSlider"
	And Get "NBAGameTime_ComingUpNextSection" details "GameTime_ComingupNext"
	Then Verify "ComingUpNext_SeeFullSchedule_ID" is displayed
	When Click on "ComingUpNext_SeeFullSchedule_ID"
	And Wait for Page To Load
	Then Verify "NBATVSchedule" is displayed
	And Get "NBATVSchedle_Date" using attribute "name" 
	Then Click on "BackButton_NBATV_ID"
	Then Wait for Page To Load
    Then Swipe up to "0.70" and "0.30" pixels  
	And Verify "NBATV_FeaturedShows_ID" is displayed
	And Get "NBAGameTime_ComingUpNextSection" details "GameTime_FeaturedShows"
	When Swipe from Right To Left in "NBATVFeatured ShowSlider"
	And Get "NBAGameTime_ComingUpNextSection" details "GameTime_FeaturedShows"	
	Then Verify "NBATVFeatured_SeeAllLink" is displayed
	And Click on "NBATVFeatured_SeeAllLink"
	And Wait for Page To Load
	Then Verify "FeaturedEpisodesPage" is displayed
	When Click on "BackArrowID"
	And Wait for Page To Load
	Then Swipe up to "0.80" and "0.20" pixels  	
	Then Verify "LearnMoreAboutNBATVLink_ID" is displayed
	When Click on "LearnMoreAboutNBATVLink_ID"
	And Wait for Page To Load
	Then Verify "LearnMoreAboutNBATVPageTitle" is displayed
	Then Click on "BackButton_NBATV_ID"
	Then Click on "BackBtn"
	And Wait for Page To Load
	
	
