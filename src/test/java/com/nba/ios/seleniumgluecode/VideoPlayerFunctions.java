package com.nba.ios.seleniumgluecode;
/*package um.testng.test.pom.functions;

import com.relevantcodes.extentreports.LogStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import um.testng.test.drivers.DriverFactory;
import um.testng.test.fs.lib.FsConstants;
import um.testng.test.pom.elements.BrowseAllPage;
import um.testng.test.pom.elements.LoginPageElements;
import um.testng.test.pom.elements.VideoPlayerElements;
import um.testng.test.utilities.framework.UmReporter;
import um.testng.test.utilities.framework.WrapperMethods;

*//**
 * @ author munagala.s.
 *
 *//*
public final class VideoPlayerFunctions {

  *//**
   * constructor.
   *//*
  private VideoPlayerFunctions() {
    // constructor.
  }

  *//**
   * Logger.
   *//*
  private static Logger logger = Logger
      .getLogger(VideoPlayerFunctions.class.getName());

  *//**
   * Play movie at the given position number in Browse All page.
   * 
   * @param positionNumber positionNumber
   *//*
  public static void playMovieAtPosition(int positionNumber) {
    List<WebElement> moviesList = BrowseAllPage.movieNamesList();
    if (moviesList.size() < positionNumber) {
      throw new IllegalArgumentException(
          "Position number is greater than " + "the number of movies in page");
    } else {
      WebElement movieElement = moviesList.get(positionNumber);
      WrapperMethods.scrollIntoWebElement(movieElement);
      String movieName = movieElement.getText();
      UmReporter.log(LogStatus.INFO, "The current validation is for the movie "
          + movieName + " at position number# " + positionNumber);
      WebElement playButtonEle = WrapperMethods.findElementByElement(
          movieElement, By.xpath("ancestor::div/following-sibling::"
              + "div//button[@translate='LABEL.PLAY']"));
      WebDriver domDriver = DriverFactory.getCurrentDriver();
      Actions act = new Actions(domDriver);
      act.moveToElement(playButtonEle).build().perform();
      WrapperMethods.waitAndClick(playButtonEle, FsConstants.FIVE,
          "Able to click on Play button for movie",
          "Failed to clik on Play button for movie");
      WrapperMethods.waitForVisiblity(BrowseAllPage.playerLocator(),
          "Able to see movie player", "Failed to see movie player");
      WrapperMethods.waitSeconds(25);
      String srcPath = WrapperMethods.captureScreen();
      UmReporter.addScreenshotToReport("Screenshot of video player", srcPath);
    }
  }

  public static void validateForwardFunctionInPlayer() {
    WebDriver domDriver = DriverFactory.getCurrentDriver();
    Actions act = new Actions(domDriver);
    int width = VideoPlayerElements.seekbar().getSize().getWidth();
    System.out.println(width);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
        FsConstants.FIVE, "Able to click on Play/Pause Button",
        "Failed to click on Play/Pause Button");
    WrapperMethods.waitSeconds(5);

    WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
        -(width), 0, "Able to drag player seeker to initial position",
        "Failed to drag player seeker to initial position");

    WrapperMethods.waitSeconds(15);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
        FsConstants.FIVE, "Able to click on Play/Pause Button",
        "Failed to click on Play/Pause Button");
    WrapperMethods.waitSeconds(5);

    for (int i = 0; i < 5; i++) {
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
          FsConstants.FIVE, "Able to click on Play/Pause Button",
          "Failed to click on Play/Pause Button");
      WrapperMethods.waitSeconds(5);
      String playBackTime = WrapperMethods
          .getText(VideoPlayerElements.playBackTime());
      UmReporter.log(LogStatus.INFO,
          "The current play back time is # " + playBackTime);

      WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
          (width / 10), 0, "Able to drag forward player seeker",
          "Failed to drag forward player seeker");

      WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
          FsConstants.FIVE, "Able to click on Play/Pause Button",
          "Failed to click on Play/Pause Button");
      if (i < 5) {
        WrapperMethods.waitSeconds(40);
      } else {
        playBackTime = WrapperMethods
            .getText(VideoPlayerElements.playBackTime());
        UmReporter.log(LogStatus.INFO,
            "The current play back time is # " + playBackTime);
      }
    }
  }

  public static void validateBackScrollFunctionInPlayer() {
    WebDriver domDriver = DriverFactory.getCurrentDriver();
    Actions act = new Actions(domDriver);
    int width = VideoPlayerElements.seekbar().getSize().getWidth();
    System.out.println(width);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
        FsConstants.FIVE, "Able to click on Play/Pause Button",
        "Failed to click on Play/Pause Button");
    WrapperMethods.waitSeconds(5);

    WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
        -(width), 0, "Able to drag player seeker to initial position",
        "Failed to drag player seeker to initial position");

    WrapperMethods.waitSeconds(15);

    WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
        width - 20, 0, "Able to drag forward player seeker",
        "Failed to drag forward player seeker");

    WrapperMethods.waitSeconds(10);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
        FsConstants.FIVE, "Able to click on Play/Pause Button",
        "Failed to click on Play/Pause Button");
    WrapperMethods.waitSeconds(5);

    for (int i = 0; i < 5; i++) {
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
          FsConstants.FIVE, "Able to click on Play/Pause Button",
          "Failed to click on Play/Pause Button");
      WrapperMethods.waitSeconds(5);
      String playBackTime = WrapperMethods
          .getText(VideoPlayerElements.playBackTime());
      UmReporter.log(LogStatus.INFO,
          "The current play back time is # " + playBackTime);

      WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
          -(width / 10), 0, "Able to drag backward player seeker",
          "Failed to drag backward player seeker");

      WrapperMethods.waitAndClick(VideoPlayerElements.playPauseBtn(),
          FsConstants.FIVE, "Able to click on Play/Pause Button",
          "Failed to click on Play/Pause Button");
      if (i < 5) {
        WrapperMethods.waitSeconds(40);
      } else {
        playBackTime = WrapperMethods
            .getText(VideoPlayerElements.playBackTime());
        UmReporter.log(LogStatus.INFO,
            "The current play back time is # " + playBackTime);
      }
    }
  }

  public static void playMovieCaption() {
    WrapperMethods.waitSeconds(35);
    WrapperMethods.mouseHover(BrowseAllPage.volumeSlider(),
        "Mouse Over the Volume Slider to get the movie duration",
        "Unable to over the Volume Slider");
    if (!WrapperMethods
        .isElementPresent(VideoPlayerElements.closedCaptionHiddenButton(), 3)) {
      System.out.println("Only in single option");
      WrapperMethods.waitSeconds(2);
      if (WrapperMethods.isDisplayed(VideoPlayerElements.closedCaptionText())) {
        System.out.println("CC Validation");
        videoCaptionONOFFValidation();
        WrapperMethods.waitAndclick(VideoPlayerElements.playerCrossBtn(), 3,
            "Able to close Player", "Failed to close player");
      }
    } else {
      UmReporter.log(LogStatus.INFO,
          "***Video does not contains Closed Caption Option***");

    }
  }

  private static void videoCaptionONOFFValidation() {
    UmReporter.log(LogStatus.INFO, "***video CC Option Validation starts***");
    WrapperMethods.waitAndClick(VideoPlayerElements.closedCaptionButton(), 1,
        "Able to click on CC Button", "Failed to click on CC Button");
    System.out.println("CC Caption is clicked ");
    String captionText = WrapperMethods
        .getText(VideoPlayerElements.closedCaptionText());
    System.out.println("CC text is " + captionText);
    String captionStatus = WrapperMethods
        .getText(VideoPlayerElements.closedcaptionButtonClick());
    System.out.println("CC Status before clicking : " + captionStatus);
    UmReporter.log(LogStatus.INFO,
        "***CC Status before clicking is :" + captionStatus);
    WrapperMethods.waitSeconds(2);
    WrapperMethods.clickJavaScript(
        VideoPlayerElements.closedcaptionButtonClick(),
        "Able to click on Closed caption drop down",
        "Failed to click on Closed caption drop down");
    WrapperMethods.click(VideoPlayerElements.selectCaptionON());
    String captionStatus1 = WrapperMethods
        .getText(VideoPlayerElements.selectCaptionON());
    System.out.println("CC Status After clicking : " + captionStatus1);
    UmReporter.log(LogStatus.INFO,
        "***CC Status After clicking is :" + captionStatus1);
    WrapperMethods.waitSeconds(10);
    UmReporter.log(LogStatus.INFO, "***video CC Option Validation ends***");

  }

  public static void verifyVideoPlayerMiddleOption() {
    WrapperMethods.waitAndClick(BrowseAllPage.allFilter(), FsConstants.TEN,
        "Clicked on All Filter", "Failed to click on All Filter");
    WrapperMethods.waitForVisiblity(BrowseAllPage.browseAllTitle(),
        FsConstants.TWENTY, "Browse All Page is visible",
        "Browse All Page is not visible");

    WrapperMethods.pageScrollDown();
    WrapperMethods.waitSeconds(FsConstants.THREE);
    WrapperMethods.pageScrollDown();
    WrapperMethods.waitSeconds(FsConstants.THREE);
    int maxMoviesCount = BrowseAllPage.learnMoreBtnList().size();

    UmReporter.log(LogStatus.INFO,
        "No.of Movies in BrowseAll Page is " + maxMoviesCount);
    int minMovieCount = 1;
    Random r = new Random();

    int randomNumber = r.nextInt((maxMoviesCount - minMovieCount) + 1)
        + minMovieCount;
    System.out.println("randomNumber " + randomNumber);

    WrapperMethods.waitSeconds(FsConstants.TEN);

    List<WebElement> movieNameList = BrowseAllPage.playMovieName();
    System.out.println("movieNameList " + movieNameList.size());

    List<WebElement> playList = BrowseAllPage.playButtonBrowseAllPage();
    System.out.println("playList " + playList.size());

    String movieName = WrapperMethods.getText(movieNameList.get(randomNumber));
    System.out.println("movieName " + movieName);
    UmReporter.log(LogStatus.INFO, "Selected Random Movie Name " + movieName);
    WrapperMethods.scrollIntoWebElement(playList.get(randomNumber));
    WrapperMethods.mouseHoverAndClick(playList.get(randomNumber),
        playList.get(randomNumber), "Moved to the Play Element and Clicked.",
        "Unable to move to the Play Element");
    WrapperMethods.waitSeconds(FsConstants.THIRTY);
    WrapperMethods.mouseHover(BrowseAllPage.volumeSlider(),
        "Mouse Over the Volume Slider to get the movie duration",
        "Unable to over the Volume Slider");

    String srcPath = WrapperMethods.captureScreen();
    UmReporter.addScreenshotToReport("Added movie player screenshot to report",
        srcPath);

    String movieDuration = WrapperMethods
        .getText(BrowseAllPage.movieDuration());
    System.out.println("movieDuration " + movieDuration);
    UmReporter.log(LogStatus.INFO, "Movie Duration Time - " + movieDuration);

    String movieStartTime = WrapperMethods
        .getText(BrowseAllPage.moviePlayedTime());
    System.out.println("movieStartTime " + movieStartTime);
    UmReporter.log(LogStatus.INFO, "movieStartTime " + movieStartTime);

    int width = VideoPlayerElements.seekbar().getSize().getWidth();
    System.out.println("width --- " + width);

    int moveToMiddle = (width - (width / 2));
    System.out.println("moveToEnd " + moveToMiddle);
    WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
        moveToMiddle, 0, "Able to move the slider to Middile Position",
        "Unable to move the slider to Middle Position");
    System.out.println("Dragged waiting starts ");
    String moveMiddle = WrapperMethods.captureScreen();
    UmReporter.addScreenshotToReport("Added movie player screenshot to report",
        moveMiddle);
    WrapperMethods.waitAndclick(VideoPlayerElements.playerCrossBtn(), 3,
        "Able to close Player", "Failed to close player");
//		WrapperMethods.waitUntilElementnotVisible(BrowseAllPage.videoPlayerBox(), 5*60*1000);
//		System.out.println("Player is closed ");
    WrapperMethods.waitSeconds(3);
//		String backToBrowseAll = WrapperMethods.captureScreen();
//		UmReporter.addScreenshotToReport("Back to Browse All Page.", backToBrowseAll);
    System.out.println("done");

  }

  public static void checkKeyboardShortcuts() throws Exception {
    WebDriver domDriver = DriverFactory.getCurrentDriver();
    Actions act = new Actions(domDriver);
    int width = VideoPlayerElements.seekbar().getSize().getWidth();
    System.out.println(width);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.dragAndDropInPixel(VideoPlayerElements.playBackSeeker(),
        (width/12), 0, "Able to forward player seeker slightly",
        "Failed to forward player seeker slightly");
    WrapperMethods.waitSeconds(15);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.SPACE, "Able to click Space bar on keyboard",
        "Failed to click Space bar on keyboard");
    String strStartTime = getCurrentPlaybackTime(act);
    WrapperMethods.waitSeconds(3);
    
    UmReporter.log(LogStatus.INFO, "Script waiting for 3 seconds");
    
    String strEndTime = getCurrentPlaybackTime(act);
    
    long difference = 
        WrapperMethods.getDifferenceTimeInSeconds(strStartTime, strEndTime);
    
    if(difference == 0) {
      UmReporter.log(LogStatus.PASS, "Pause movie by space bar is "
          + "working fine");
    } else {
      UmReporter.log(LogStatus.PASS, "Pause movie by space bar is "
          + "not working");
    }
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.ARROW_RIGHT, "Able to click Right Arrow on keyboard",
        "Failed to click Right Arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    strEndTime = getCurrentPlaybackTime(act);
    
    difference = 
        WrapperMethods.getDifferenceTimeInSeconds(strStartTime, strEndTime);
    
    if(difference == 5) {
      UmReporter.log(LogStatus.PASS, "Movie forwarded by 5 secs upon clicking"
          + " right arrow key on keyboard");
    } else {
      UmReporter.log(LogStatus.FAIL, "Movie not forwarded by 5 secs upon "
          + "clicking right arrow key on keyboard");
    }
    
    strStartTime = getCurrentPlaybackTime(act);
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendCombinationKeys(VideoPlayerElements.playPauseBtn(),
        Keys.CONTROL, Keys.RIGHT, "Able to click Ctrl+Right Arrow on keyboard",
        "Failed to click Right Arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    strEndTime = getCurrentPlaybackTime(act);
    
    difference = 
        WrapperMethods.getDifferenceTimeInSeconds(strStartTime, strEndTime);
    
    if(difference == 10) {
      UmReporter.log(LogStatus.PASS, "Movie forwarded by 10 secs upon clicking"
          + " Ctrl+Right arrow key on keyboard");
    } else {
      UmReporter.log(LogStatus.FAIL, "Movie not forwarded by 10 secs upon "
          + "clicking Ctrl_+Right arrow key on keyboard");
    }
    
    strStartTime = getCurrentPlaybackTime(act);
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.LEFT, "Able to click Left Arrow on keyboard",
        "Failed to click Left Arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    strEndTime = getCurrentPlaybackTime(act);
    
    difference = 
        WrapperMethods.getDifferenceTimeInSeconds(strEndTime, strStartTime);
    
    if(difference == 5) {
      UmReporter.log(LogStatus.PASS, "Movie seeked by minus 5 secs upon "
          + "clicking left arrow key on keyboard");
    } else {
      UmReporter.log(LogStatus.FAIL, "Movie not seeked by minus 5 secs upon "
          + "clicking left arrow key on keyboard");
    }
    
    strStartTime = getCurrentPlaybackTime(act);
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendCombinationKeys(VideoPlayerElements.playPauseBtn(),
        Keys.CONTROL, Keys.LEFT, "Able to click Ctrl+Left Arrow on keyboard",
        "Failed to click Ctrl+Left Arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    strEndTime = getCurrentPlaybackTime(act);
    
    difference = 
        WrapperMethods.getDifferenceTimeInSeconds(strEndTime, strStartTime);
    
    if(difference == 10) {
      UmReporter.log(LogStatus.PASS, "Movie seeked by minus 10 secs upon "
          + "clicking Ctrl+Left arrow key on keyboard");
    } else {
      UmReporter.log(LogStatus.FAIL, "Movie not seeked by minus 10 secs upon "
          + "clicking Ctrl+Left arrow key on keyboard");
    }
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    String strVolume = WrapperMethods.getAttribute(
        VideoPlayerElements.volumeToggleBtn(), "class");
    if (strVolume.contains("bmpui-unmuted")) {
      UmReporter.log(LogStatus.INFO, "Initially Video is # unMuted");
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      WrapperMethods.sendkeys(VideoPlayerElements.volumeToggleBtn(),
          "m", "Clicked m on the keyboard", "Failed to click m on the keyboard");
      WrapperMethods.waitSeconds(5);
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      strVolume = WrapperMethods.getAttribute(
          VideoPlayerElements.volumeToggleBtn(), "class");
      if (strVolume.contains("bmpui-muted")) {
        UmReporter.log(LogStatus.PASS, "Able to mute the player by clicking"
            + " m on the keyboard");
      } else {
        UmReporter.log(LogStatus.FAIL, "Failed to mute the player by clicking"
            + " m on the keyboard");
      }
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      WrapperMethods.sendkeys(VideoPlayerElements.volumeToggleBtn(),
          "m", "Clicked m on the keyboard", "Failed to click m on the keyboard");
      WrapperMethods.waitSeconds(5);
    } else if(strVolume.contains("bmpui-muted")) {
      UmReporter.log(LogStatus.INFO, "Initially Video is # Muted");
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      WrapperMethods.sendkeys(VideoPlayerElements.volumeToggleBtn(),
          "m", "Clicked m on the keyboard", "Failed to click m on the keyboard");
      WrapperMethods.waitSeconds(5);
      act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
      strVolume = WrapperMethods.getAttribute(
          VideoPlayerElements.volumeToggleBtn(), "class");
      if (strVolume.contains("bmpui-unmuted")) {
        UmReporter.log(LogStatus.PASS, "Able to unmute the player by clicking"
            + " m on the keyboard");
      } else {
        UmReporter.log(LogStatus.FAIL, "Failed to unmute the player by clicking"
            + " m on the keyboard");
      }
    } else {
      UmReporter.log(LogStatus.FAIL, "Failed to check if player is muted"
          + " or not");
    }
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    int initialVolumeLevel = Integer.parseInt(WrapperMethods.getAttribute(
        VideoPlayerElements.volumeToggleBtn(), "data-bmpui-volume-level-tens"));
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.DOWN, "Able to click down arrow on keyboard",
        "Failed to click down arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.DOWN, "Able to click down arrow on keyboard",
        "Failed to click down arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    int laterVolumeLevel = Integer.parseInt(WrapperMethods.getAttribute(
        VideoPlayerElements.volumeToggleBtn(), "data-bmpui-volume-level-tens"));
    
    if (initialVolumeLevel > laterVolumeLevel) {
      UmReporter.log(LogStatus.PASS, "Decreasing volume by down arrow on"
          + " keyboard is working fine Initial Volume = " + initialVolumeLevel
          + " ## Later Volume = " + laterVolumeLevel);
    } else {
      UmReporter.log(LogStatus.FAIL, "Decreasing volume by down arrow on"
          + " keyboard failed Initial Volume = " + initialVolumeLevel 
          + " ## Later Volume = " + laterVolumeLevel);
    }
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    initialVolumeLevel = Integer.parseInt(WrapperMethods.getAttribute(
        VideoPlayerElements.volumeToggleBtn(), "data-bmpui-volume-level-tens"));
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.UP, "Able to click Up arrow on keyboard",
        "Failed to click Up arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.UP, "Able to click Up arrow on keyboard",
        "Failed to click Up arrow on keyboard");
    WrapperMethods.waitSeconds(5);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    laterVolumeLevel = Integer.parseInt(WrapperMethods.getAttribute(
        VideoPlayerElements.volumeToggleBtn(), "data-bmpui-volume-level-tens"));
    
    if (initialVolumeLevel < laterVolumeLevel) {
      UmReporter.log(LogStatus.PASS, "Increasing volume by down arrow on"
          + " keyboard is working fine Initial Volume = " + initialVolumeLevel
          + " ## Later Volume = " + laterVolumeLevel);
    } else {
      UmReporter.log(LogStatus.FAIL, "Increasing volume by down arrow on"
          + " keyboard failed Initial Volume = " + initialVolumeLevel 
          + " ## Later Volume = " + laterVolumeLevel);
    }
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.waitAndClick(VideoPlayerElements.fullScreenToggleBtn(),
        FsConstants.THREE, "Able to click fullscreen button on player",
        "Failed to click fullscreen button on player");
    WrapperMethods.waitSeconds(5);
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    String fullScreenMode = WrapperMethods.getAttribute(
        VideoPlayerElements.fullScreenToggleBtn(), "class");
    if (fullScreenMode.contains("bmpui-on")) {
      UmReporter.log(LogStatus.PASS, "Able to change player to full screen "
          + "mode");
    } else {
      UmReporter.log(LogStatus.FAIL, "Failed to change player to full "
          + "screen mode");
    }
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    WrapperMethods.sendKeys(VideoPlayerElements.playPauseBtn(),
        Keys.ESCAPE, "Able to click escape button on keyboard",
        "Failed to click escape button on keyboard");
    
    WrapperMethods.waitSeconds(5);
    
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    fullScreenMode = WrapperMethods.getAttribute(
        VideoPlayerElements.fullScreenToggleBtn(), "class");
    if (fullScreenMode.contains("bmpui-off")) {
      UmReporter.log(LogStatus.PASS, "Able to exit full screen "
          + "mode using ESC key");
    } else {
      UmReporter.log(LogStatus.FAIL, "Failed to exit full "
          + "screen mode using ESC key");
    }
    
  }


  public static String getCurrentPlaybackTime(Actions act) {
    act.moveToElement(VideoPlayerElements.playPauseBtn()).build().perform();
    String playbackTime = WrapperMethods.getText(BrowseAllPage.moviePlayedTime());
    logger.log(Level.INFO, "Current Playback Time # " + playbackTime);
    UmReporter.log(LogStatus.INFO, "Current Playback Time # " + playbackTime);
    return playbackTime;
  }

  public static void closePlayerAndLogout() {
    WrapperMethods.waitAndClick(VideoPlayerElements.closePlayer(),
        FsConstants.FIVE, "Able to click on close player",
        "Failed to click on close player");
    WrapperMethods.waitSeconds(5);
    WrapperMethods.pageScrollUp();
    WrapperMethods.mouseHover(LoginPageElements.userAccount(),
        "Performed mouse hover over User account link",
        "Mouse hover over User account link failed");
    WrapperMethods.waitAndClick(LoginPageElements.logoutbutton(),
        FsConstants.NINETY, "Clicked on logout link",
        "Failed to click on logout link");
  }
}
*/