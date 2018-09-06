package com.demo.test.liveintent;

import com.demo.base.BaseScript;
import com.demo.utilities.Helper;
import org.testng.annotations.Test;

public class ConversionTrackerTest extends BaseScript {

    @Test(testName="Conversion Tracker",groups = { "Conversion Tracker","Regression" },
            description="Create New Conversion Tracker without Advertiser .")
    public void createNewConversionWithoutAdvertiser() throws Exception {

        logmsg("Test Case : Create New Conversion for an Advertiser.");
        liveIntent().LoginPageController().login("provide email id","provide assword");
        liveIntent().conversionTrackerController().createNewConversionTrackerWithoutAdvertiser("QATracker_"+ Helper.generateUniqueValue());
    }



    @Test(testName="Conversion Tracker",groups = { "Conversion Tracker","Regression" },
            description="Create New Conversion Tracker without TrackerNAme .")
    public void createNewConversionWithoutTrackerName() throws Exception {

        logmsg("Test Case : Create New Conversion for an Advertiser.");
        liveIntent().conversionTrackerController().createNewConversionTrackerWithoutTrackerName("testAutomationAdvertiser");


    }

    @Test(testName="Conversion Tracker",groups = { "Conversion Tracker","Regression" },
            description="Create New Conversion Tracker for an Advertiser.")
    public void createNewConversion() throws Exception {

        logmsg("Test Case : Create New Conversion for an Advertiser.");
        liveIntent().conversionTrackerController().createNewConversionTracker("testAutomationAdvertiser",
                "QATracker_"+ Helper.generateUniqueValue());

    }





}
