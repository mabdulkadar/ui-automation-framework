package com.demo.testreport;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.demo.utilities.SeleniumUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import static com.aventstack.extentreports.MediaEntityBuilder.*;

public class ExtentReportUtils {

    public static Log log = LogFactory.getLog(SeleniumUtils.class);

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

    public static void logScreenshot(String message,String imagePath) throws IOException {

        ExtentReportITestListener.test.get().fail(message).addScreenCaptureFromPath(imagePath);



    }


}
