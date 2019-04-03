package com.demo.utilities;

import com.demo.base.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeleniumUtils  extends TestLibrary {


    /**
     * TAKE SCREENSHOT
     * @param message  string value
     */
    public static void logFailWithScreenshot(String message) {

        String screenshotPath = null;
        try {


              if(!(driver instanceof HtmlUnitDriver)) {
                  File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                  String screenshname = Helper.generateRandomStringUUID() + ".png";

                  screenshotPath = GlobalConstants.screenshotPath + "/" + screenshname;

                  File screeshotFile = new File(screenshotPath);

                  FileUtils.copyFile(scrnsht,
                          screeshotFile);

                  logScreenshot(message, screenshname);
                  logFail(message);
                  System.out.println("screesnshot taken");
              }else{
                  logFail(message);
              }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Launch URL
     * @param strURL application url
     * @return boolean
     */
    public static Boolean launchURL(String strURL) {
        try {

            driver.get(strURL);

            return true;
        } catch (Exception objException) {
            logFailWithScreenshot("Not Able to Launch the URL:"+strURL+" Exception : "+objException.getMessage());
            //logFail("Not Able to Launch the URL:"+strURL+" Exception : "+objException.getMessage());
            return false;
        }
    }



    /**
     * Get Current Browser's URL
     * @return boolean
     */
    public static String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception objException) {
           logFail("Exception while getting current Browser url. "+objException.getMessage());
           return null;
        }
    }


    /**
     * Wait For Page Loaded
     */
    public static void waitPageLoaded()
    {

        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        Wait<WebDriver> wait = new WebDriverWait(driver,60);
        try
        {
            wait.until(expectation);
        }
        catch(Throwable error)
        {
            logFailWithScreenshot("Timeout waiting for Page Load Request to complete.");
        }
    }



    /**
     * CLICK DYNAMIC OBJECT With Multiple Attempts.
     * @param noOfAttempts integer value to represent reattempt time
     * @param objWebElement webElement
     * @param valueToUseInObject String value to replace in object string
     * @param strObjectName object Name
     * @return boolean
     */
    public static Boolean clickObject(Integer noOfAttempts,String objWebElement, String valueToUseInObject,String strObjectName)  {


        try {

            objWebElement = objWebElement.replace("XXX",valueToUseInObject.trim());

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(objWebElement))).click();
            logPass(strObjectName+" has been successfully clicked.");
            return true;
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                clickObject (noOfAttempts,objWebElement, valueToUseInObject,strObjectName);

            }else {

                logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());
                return false;
            }

        }

        return false;
    }


    /**
     * CLICK OBJECT WITH REQUESTED ATTEMPTS
     * @param noOfAttempts No of Attempts if Action failed.
     * @param objWebElement Web Element Object
     * @param strObjectName Web Element Name
     */
    public static void clickObject(int noOfAttempts,WebElement objWebElement, String strObjectName)  {


        try {

            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(objWebElement));
            objWebElement.click();
            logPass(strObjectName+" has been successfully clicked.");
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                System.out.println("attempts:"+noOfAttempts);
                clickObject((noOfAttempts-1),objWebElement, strObjectName);
            }else{
                logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());

            }

        }
    }

    /**
     * TYPE VALUE IN OBJECT
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @param strInputValue text to be entered
     * @return boolean
     */

    public static Boolean typeValue(WebElement objWebElement, String strObjectName, String strInputValue)  {
        try {

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(objWebElement));

            objWebElement.sendKeys(strInputValue);

            logPass(strObjectName+" value has been successfully entered.");

            return true;
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());
            return false;
        }
    }

    /**
     * TYPE VALUE IN OBJECT
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @param strInputValue text to be entered
     * @return boolean
     */

    public static Boolean uploadFile(WebElement objWebElement, String strObjectName, String strInputValue)  {
        try {

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(objWebElement));

            objWebElement.sendKeys(strInputValue);

            logPass(strObjectName+" file successfully uploaded.");

            return true;
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" file not  uploaded. Exception : "+objException.getMessage());
            return false;
        }
    }



    /**
     * Verify Element is visible.
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @return boolean value
     */
    public static Boolean verifyElementVisible(WebElement objWebElement, String strObjectName) {
        try {

            (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(objWebElement));
            logPass(strObjectName+" element is visible.");
            return true;
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" element is not visible. Exception : "+objException.getMessage());
            return false;
        }
    }

    /**
     * Verify Element is visible and Check Text Value
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @param expectedValue String value
     * @return boolean value
     */
    public static Boolean verifyTextInWebElement(WebElement objWebElement, String strObjectName,String expectedValue) {

        String actualValue = null;

        try {

            actualValue = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(objWebElement)).getText();

            if(StringUtils.contains(actualValue,expectedValue)){
                logPass(strObjectName+" is displayed.");
                return true;
            }else{
                logFailWithScreenshot(strObjectName+" is not displayed.");
                return false;
            }

        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" element is not visible. Exception : "+objException.getMessage());
            return false;
        }
    }

    /**
     * Verify Element is visible and Check Text Value
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @param attributeValue attributeValues
     * @return String value
     */
    public static String getTextInWebElement(WebElement objWebElement, String strObjectName,String attributeValue) {

        String actualValue = null;

        try {

            actualValue = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(objWebElement)).getAttribute(attributeValue);

            return actualValue;

        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" element is not visible. Exception : "+objException.getMessage());
            return actualValue;
        }
    }


    /**
     * Objective - Open New BrowserTab
     * @return boolean value
     */
    public static Boolean openNewBrowerTab() {

        ArrayList<String> tabList = null;

        try{

            driver.findElement(new By.ByCssSelector("Body")).sendKeys(Keys.CONTROL + "t");

            return true;

        }catch (Exception objException) {
            logFailWithScreenshot("Error on opening new Browser Tab "+objException.getMessage());
            return false;
        }


    }


    /**
     * Objective - Switch to Tab based on Tab Numbers active in Driver
     * @param tabNumber ,which tab number to switch
     * @param strTabName tab name
     * @return boolean value
     */
    public static Boolean switchTab(int tabNumber,String strTabName) {

        ArrayList<String> tabList = null;

        try{

            tabList = new ArrayList<String>(driver.getWindowHandles());

            if((tabList !=null) && (tabList.size() > 0)){

                if(!(tabNumber > (tabList.size()))) {
                    driver.switchTo().window(tabList.get(tabNumber));
                    logPass("Switched to Tab:"+strTabName+" successfully.");
                }else {
                    logFailWithScreenshot("Total Size of Tab is less than requested TabSize:"+tabNumber);
                }
            }

            return true;

        }catch (Exception objException) {
            logFailWithScreenshot(strTabName+" tab is not found : "+objException.getMessage());
            return false;
        }


    }



}
