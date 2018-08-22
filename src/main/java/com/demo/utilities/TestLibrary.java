package com.demo.utilities;

import com.demo.testreport.ExtentReportUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestLibrary extends ExtentReportUtils {

    protected static WebDriver driver;

    JavascriptExecutor objJSExecutor = null;

    public TestLibrary(){

    }



    /**
     * CONSTRUCTOR FOR COMPONENT REUSABLE FUNCTIONS
     * @param objTempWebDriver webdriver
     */
    public TestLibrary(WebDriver objTempWebDriver)  {

        driver = objTempWebDriver;
        objJSExecutor = (JavascriptExecutor) driver;
    }
}
