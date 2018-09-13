package com.demo.testreport;

import com.demo.utilities.SeleniumUtils;
import com.demo.utilities.TestLibrary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;


public class ExtentReportUtils {

    public static Log log = LogFactory.getLog(TestLibrary.class);

    public static void logmsg(String msg) {
        log.info(msg);

    }

    public static void logerror(String msg) {

        log.error(msg);

    }

    public static void logFail(String msg) {

        ExtentReportITestListener.test.get().fail(msg);
    }

    public static void logPass(String msg) {

        ExtentReportITestListener.test.get().pass(msg);
    }

    public static void logWarning(String msg) {

        ExtentReportITestListener.test.get().warning(msg);
    }

    public static void logScreenshot(String message,String imagePath) throws IOException {

        ExtentReportITestListener.test.get().fail(message).addScreenCaptureFromPath(imagePath);



    }


}
