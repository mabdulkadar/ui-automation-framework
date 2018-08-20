package com.demo.testreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.demo.base.AppConstants;
import com.demo.utilities.Helper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

import static com.demo.base.BaseScript.driver;


public class ExtentReportITestListener implements ITestListener {
	private static String FILE_NAME = "Extent-reportnew.html";
	private static ExtentReports extent = null;

	{
		try {
			extent = ExtentManager.createInstance(FILE_NAME);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@Override
	public synchronized void onStart(ITestContext context) {


	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();

	}

	@Override
	public synchronized void onTestStart(ITestResult result) {


		String testCaseName = result.getMethod().getMethodName();

		ExtentTest test = extent.createTest(testCaseName);
		ExtentReportITestListener.test.set(test);
		for (String group : result.getMethod().getGroups()) {
			test.assignCategory(group);
		}
	}


	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {


	    test.get().fail(result.getThrowable());

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
