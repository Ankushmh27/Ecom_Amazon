package com.amazon.utilities;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listener extends ReportUtils implements ITestListener
{
	public void onTestStart(ITestResult result)
	{
		test=report.createTest(result.getName());
		test.getStatus();
	}

	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + " Test Case Passed", ExtentColor.GREEN));	
	}

	public void onTestFailure(ITestResult result) 
	{
		test.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " Test Case Failed", ExtentColor.RED));	
		test.fail(result.getThrowable());
		test.fail(String.valueOf(result.getClass()));
		try
		{
			String path=Utility.getScreenShotPath(result.getName());
			test.addScreenCaptureFromPath(path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result)
	{
		test.log(Status.SKIP,
				MarkupHelper.createLabel(result.getName() + " Test Case Skipped", ExtentColor.GREY));	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		test.log(Status.INFO,
				MarkupHelper.createLabel(result.getName() + " Test Case Failed", ExtentColor.PINK));	
	}

	public void onStart(ITestContext context) 
	{
		try {
			reportInit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context)
	{
		report.flush();
	}		
}





