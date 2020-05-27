package com.nba.ios.testrunner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.nba.ios.api.helper.AppiumApp;
import com.nba.ios.api.helper.AppiumServer;
import com.nba.ios.seleniumgluecode.CommonMethods;
import com.nba.ios.seleniumgluecode.DeepLinkingFunctions;
import com.nba.ios.utilities.ConfigPropertyReader;
import com.nba.ios.utilities.LocatorsDataProvier;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
//features = {"features//Players.feature","features//Settings.feature"},
features = {"features//Home.feature"},
glue= {"com/nba/ios/seleniumgluecode"},
monochrome = true,
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"},
tags={"@RunTest"}
)


public class TestRunner {
	
	public static ExtentReports report;
	public static ExtentTest test;
	public static String reportPath;
	public static String reportName;
	
	@BeforeClass
	public static void startTest() throws IOException, InterruptedException {
		String getAPKPath = System.getProperty("InstallAPK");
		System.out.println("getAPKPath " + getAPKPath);
		
		File reportConfig = new File("./extent-config.xml");
		String dateTime = new SimpleDateFormat("yyyyMMdd_hhmm").format(new Date());
		System.out.println("dateTime "+dateTime);
		reportName = "NBA_Report.html";
		String reportFolder = "Report_"+dateTime;
		System.out.println("Report Name "+reportName);
		reportPath = System.getProperty("user.dir") +"//custom_report//" + reportFolder + "//" +reportName;
		System.out.println("reportPath "+reportPath);
		
		ExtentProperties extentProp = ExtentProperties.INSTANCE;
		extentProp.setReportPath(reportPath);
		report = new ExtentReports(reportPath, true);
		report.loadConfig(reportConfig);
		report.setTestRunnerOutput("Smoke Scripts for NBA iOS App");
		report.addSystemInfo("user", System.getProperty("user.name"));
		report.addSystemInfo("os", "iOS");
		test = report.startTest("NBA App Testing", "NBA Smoke Scenarios");
		
		// write the report path to the reportLocation.txt file
		String reportLocation = "./reportLocation.txt";
		FileWriter fw = new FileWriter(new File(reportLocation), false);
		fw.write(reportPath);
		fw.close();
		
		// start appium
		if(getAPKPath.equalsIgnoreCase("DeepLinks")) {
			DeepLinkingFunctions.setCapabilitiesNotePad();
		} else {
			System.out.println("Launching NBA app");
			AppiumApp.getAppiumApp();
		}
		
		//Check for app crash after launching 
		//CommonMethods.checkCrash();
				
		// load locators from Test Data sheet
		LocatorsDataProvier.setTestData();
		System.out.println("** Before class works are done **** ");
	}
	
	@Before
	public void beforeMethodTest() {
		
		System.out.println("Before method is never called ");
	}
	
	@After
	public void afterMethod() {
		System.out.println("I am in after method");
		System.out.println("I am in after class ***");
		System.out.println("Loaded the config file now ending the test");
			
	}
	
	@AfterClass
    public static void writeExtentReport() {
		String getAPKPath = System.getProperty("InstallAPK");
		report.endTest(test);
		report.flush();
		report.close();
		System.out.println("Closed reports");
		LocatorsDataProvier.endThreadLocal();
		if(getAPKPath.equalsIgnoreCase("DeepLinks")) {
			DeepLinkingFunctions.closeDrivers();
		} else {
			//Closing the appium drivers
			AppiumApp.endAppiumAppDriver();
		}
      //Stopping the appium server
		AppiumServer.appium_stop();
		System.out.println("Done");
    }
	
}
