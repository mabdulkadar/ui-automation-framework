package com.demo.testreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


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

		//Get test case name
		Object[] testCaseParameters = result.getParameters();
		String testCaseName = result.getMethod().getMethodName();


		for (Object testParameter : testCaseParameters) {

			if (testParameter instanceof TestCaseId) {
				testCaseName = getTestCaseName(testCaseName,testParameter);
				break;
			}else if(testParameter instanceof Object[]){

				for(Object testParameter2 : (Object[]) testParameter){
					if (testParameter2 instanceof TestCaseId) {
						testCaseName = getTestCaseName(testCaseName,testParameter2);
						break;
					}
				}
			}

		}

		ExtentTest test = extent.createTest(testCaseName);
		ExtentReportITestListener.test.set(test);
		for (String group : result.getMethod().getGroups()) {
			test.assignCategory(group);
		}
	}

	public synchronized String getTestCaseName(String testCaseName,Object testParameter){

		return (testCaseName = testCaseName + "_" + ((TestCaseId) testParameter).getTestName());

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
