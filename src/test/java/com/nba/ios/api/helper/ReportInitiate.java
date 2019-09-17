package com.nba.ios.api.helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportInitiate {
	
	static HashMap<Object, Object> parentList = new HashMap<Object, Object>();
	private static ExtentReports extentD = new ExtentReports();
	//public static ExtentTest test = ExtentTest();
	
	public static void main(String args[])
	{
		createReport();
		ReportInitiate("MyNBATest" /*+ " <span class='device-name'> "+testName+"</span>"*/, "Regression Test Set", "NBAAutomation");
		initTest("MyNBATest", "NBA", "NBAAutomation");
	}

	private static void createReport() {
		System.out.println("Create report");
		File reportConfig= new File("./reportConfig.xml");
		String css = ".categoryOS,.categoryBrowser,.categoryheightwidth,.categoryEnvironment { background-color: #537851; border-radius: 3px;  color: #fff; font-size: 12px; margin-right: 3px; padding: 2px 4px; font-family: Roboto, Nunito, 'Source Sans Pro', Arial; font-weight: 400; line-height: 1.5;}";
		String path = "./custom_report/";
		String fileName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
		String reportName = "Report_" + fileName;
		new File(path + reportName).mkdirs();
		String reportPath = path + "Report_" + fileName;
		System.out.println("reportPath "+reportPath);
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath + "/NBA_BDD_Report.htm");
		htmlReporter.loadXMLConfig("./config/" + reportConfig);
		
		htmlReporter.config().setCSS(css);
		
		htmlReporter.config().setReportName("NBA Mobile QA App Automation");
		
		extentD.attachReporter(htmlReporter);
		System.out.println("Done");
	}
	

	public static void ReportInitiate(String testName, String description, String suiteName) {
		initParent(testName, description, suiteName);		
	}
	
	public static void initParent(String testName, String description, String suiteName) {

		ExtentTest testParent = extentD.createTest(testName, description);
		parentList.put(testName, testParent);
		System.out.println("initParent");
	}
	
	public static void initTest(String className,String testName,String suiteName) {
		System.out.println("Calling init test");
		String description = "My First Test";
		
		String driver = "AppiumApp";
		testName = testName + " <span class='categoryDriver'>" + driver + "</span>";
		String deviceId = "4d002ff7dcc061df";
		testName = testName + " <span class='categoryDevice'>" + deviceId + "</span>";
		
		ExtentTest parent = (ExtentTest) parentList.get(className);
		//test.set(parent.createNode(testName,description));
		//parent.
		
	}

}
