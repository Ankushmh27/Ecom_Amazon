package com.amazon.utilities;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ReportUtils
{
	public static ExtentReports report;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentTest test;

	public static void reportInit() throws IOException
	{
		report = new ExtentReports();
		htmlReporter = new ExtentSparkReporter("E:\\EclipseProject\\MyCucumberFramework\\Ecom_Amazon\\Report\\ExtentReport.html")
				.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
		report.attachReporter(htmlReporter);// connection between spark and report
		htmlReporter.config().setCss("css-string");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Automation Report");
		report.setSystemInfo("Tester Name ", "Ankush");
		report.setSystemInfo( "Environment ","QA");
		report.setSystemInfo("Build No ", "1.0");
		htmlReporter.config().setTimelineEnabled(true);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setJs("js-string");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setTheme(Theme.DARK);	
	}
}

