Feature: NBA League Pass Sign in Validation

Scenario: Verify Top Stories
	When User is on "More" Page
	Then  Verify "TopStories" is displayed
	And Click on "TopStories"
	Then Wait for pageLoad
	Then Verify pageTitle - "TopStories"
	Then Click on "TopStoriesVideo"
	Then Wait for pageLoad
	And Verify "VideoContent"
	Then Click on "BackBtn"
	Then Click on "TopStoriesArticle"
	Then Wait for pageLoad
	And Verify "ArticleTitle"
	Then Click on "BackBtn"
	Then ScrollTo "LivePresserVideo"
	Then Click on "LivePresserVideo"
	Then Wait for pageLoad
	And Verify "VideoContent"
	Then Click on "BackBtn"
	
