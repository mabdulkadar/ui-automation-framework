package com.demo.utilities;

import com.demo.base.AppConstants;
import com.demo.testreport.ExtentReportUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeleniumUtils  extends ExtentReportUtils {

    protected static WebDriver driver;

    JavascriptExecutor objJSExecutor = null;

    public SeleniumUtils(){

    }



    /**
     * CONSTRUCTOR FOR COMPONENT REUSABLE FUNCTIONS
     * @param objTempWebDriver webdriver
     */
    public SeleniumUtils(WebDriver objTempWebDriver)  {

        driver = objTempWebDriver;
        objJSExecutor = (JavascriptExecutor) driver;
    }

    /**
     * TAKE SCREENSHOT
     */
    String screenshname = null;
    public static void logFailWithScreenshot(String message) {

        String screenshotPath = null;
        try {


              if(!(driver instanceof HtmlUnitDriver)) {
                  File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                  String screenshname = Helper.generateRandomStringUUID() + ".png";

                  screenshotPath = AppConstants.screenshotPath + "/" + screenshname;

                  File screeshotFile = new File(screenshotPath);

                  FileUtils.copyFile(scrnsht,
                          screeshotFile);

                  logScreenshot(message, screenshname);
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

        /*    if(driver.findElement(By.className("Cookie__button")).isEnabled()){
                driver.findElement(By.className("Cookie__button")).click();
            }*/

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
     * Close Current Active Browser
     * @return boolean
     */
    public static Boolean closeActiveBroser() {
        try {
            driver.close();
            logPass("Current Active Browser Closed.");
            return true;
        } catch (Exception objException) {
            logFailWithScreenshot("Exception while closing the current active browser "+objException.getMessage());
            return false;
        }
    }

    /**
     * Navigate back to previous page in session browser
     * @return boolean
     */
    public static Boolean navigatePreviousPage() {
        try {
            driver.navigate().back();
            logPass("Navigate back to previous page");
            return true;
        } catch (Exception objException) {
            logFailWithScreenshot("Exception while cNavigate back to previous page "+objException.getMessage());
            return false;
        }
    }

    /**
     * Refresh the page and wait for load
     */
    public static void refreshAndWaitForPageLoaded()
    {

        driver.navigate().refresh();

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
     * CLICK OBJECT
     * @param objWebElement webElement
     * @param strObjectName object name
     * @return boolean
     */
    public static Boolean clickObject(WebElement objWebElement, String strObjectName)  {

        int attempt =1;

        try {

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(objWebElement));
            objWebElement.click();
            logPass(strObjectName+" has been successfully clicked.");
            return true;
        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());
            return false;
        }
    }

    /**
     * CLICK OBJECT With Multiple Attempts.
     * @param noOfAttempts integer value to represent reattempt time
     * @param objWebElement webElement
     * @param strObjectName object Name
     * @return boolean
     */
    public static Boolean clickObject(Integer noOfAttempts,WebElement objWebElement, String strObjectName)  {


        try {

            System.out.println("To check recursive call. "+noOfAttempts);

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(objWebElement));
            objWebElement.click();
            logPass(strObjectName+" has been successfully clicked.");
            return true;
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                clickObject (noOfAttempts--,objWebElement, strObjectName);

            }else {

                logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());
                return false;
            }

        }

        return false;
    }

    /**
     * CLICK ON First Link of Carousel Menu Card
     * @param selector string
     * @param cardNumber which number of the card to click
     */
    public static void clickFirstLinkCarouselMenuCard(String selector, Integer cardNumber)  {

        List<WebElement>  webElements   = null;

        try {

            webElements   = driver.findElements(By.cssSelector(selector));

            if(webElements != null && (webElements.size() >= cardNumber)){

                webElements.get(cardNumber).click();
                logPass(selector+" Menu Carousel menucar no: "+cardNumber);

            }else{
                logFailWithScreenshot(selector+" Menu Carousel Not Found. ");
            }

        } catch (Exception objException) {
            logFailWithScreenshot(" Click Failed for Menu carousel. Exception : "+objException.getMessage());

        }
    }

    /**
     * CLICK OBJECT WITH REQUESTED ATTEMPTS
     * @param noOfAttempts No of Attempts if Action failed.
     * @param objWebElement Web Element Object
     * @param strObjectName Web Element Name
     */
    public static void clickObject(int noOfAttempts,WebElement objWebElement, String strObjectName)  {


        try {

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(objWebElement));
            objWebElement.click();
            logPass(strObjectName+" has been successfully clicked.");
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                System.out.println("attempts:"+noOfAttempts);
                clickObject(noOfAttempts--,objWebElement, strObjectName);
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
     * Verify Element is visible.
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @return boolean value
     */
    public static Boolean verifyElementVisible(WebElement objWebElement, String strObjectName) {
        try {

            (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOf(objWebElement));
            logPass(strObjectName+" element is visible.");
            return true;
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" element is not visible. Exception : "+objException.getMessage());
            return false;
        }
    }


    /**
     * Verify Element is Mot Visible.
     * @param objWebElement web element
     * @param strObjectName object name
     */
    public static void verifyElementNotDisplayed(WebElement objWebElement, String strObjectName) {

        try {

            Boolean actualStatus = objWebElement.isDisplayed();

            if(actualStatus){
                logFailWithScreenshot(strObjectName+" element is visible");

            }else{
                logPass(strObjectName+" element is not visible ");
            }
        } catch (Exception objException) {
            logPass(strObjectName+" element is not visible ");
        }


    }


    /**
     * ENTER RETURN/ENTER Key
     * @param objWebElement  webElement
     * @param strObjectName objectName
     * @return boolen
     */
    public static Boolean enterReturnKey(WebElement objWebElement, String strObjectName)  {
        try {

            (new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(objWebElement));

            objWebElement.sendKeys(Keys.RETURN);

            logPass(strObjectName+" Performed Return key Action.");

            return true;
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+"  Return key Action failed. : "+objException.getMessage());
            return false;
        }
    }

    /**
     * Objective - Click the Link using Href value
     * @param hrefValue is actual link i.e href value
     * @param strObjectName name of Label
     * @return boolean value
     */

    public static Boolean clickLinkUsingHref(int noOfAttempts,String hrefValue, String strObjectName) {

        List<WebElement> links = null;
        Iterator<WebElement> itr = null;
        WebElement link = null;



        try {


            links = driver.findElements(By.tagName("a"));

            itr = links.iterator();

            while (itr.hasNext()){

                link = itr.next();

                if(link.getAttribute("href").contains(hrefValue)){

                    (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(link));
                    link.click();

                    logPass(strObjectName+" Link has been successfully clicked.");

                    break;
                }

            }

            return true;
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                 clickLinkUsingHref((noOfAttempts-1),hrefValue,  strObjectName);

            }else {

                logFailWithScreenshot(strObjectName + " link is not clicked : " + objException.getMessage());
                return false;
            }
        }

        return false;
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

    /**
     * Objective - Scroll Down page, still Link displayed
     * @param hrefValue is actual link i.e href value
     * @param strObjectName name of Label
     * @return boolean value
     */

    public static Boolean scrollDownStillLink(String hrefValue, String strObjectName) {


        JavascriptExecutor js = null;

        List<WebElement> links = null;
        Iterator<WebElement> itr = null;
        WebElement link = null;

        boolean successAction = false;

        try {


            links = driver.findElements(By.tagName("a"));

            itr = links.iterator();

            while (itr.hasNext()){

                link = itr.next();

                if(link.getAttribute("href").contains(hrefValue)){
                    js = (JavascriptExecutor)driver;;
                    js.executeScript("arguments[0].scrollIntoView();", link);
                    logPass("Scrolled Still Link "+strObjectName+" visible.");
                    successAction =true;
                    break;
                }

            }

            if (!(successAction)) logFailWithScreenshot("Scroll still Link "+strObjectName+" visible failed.");

            return true;
        } catch (Exception objException) {
            logFailWithScreenshot("Scroll still"+strObjectName+" link visible failed. Exception : "+objException.getMessage());
            return false;
        }
    }

    /**
     * Objective - Scroll Down page, still Element displayed and Clickable
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @return boolean value
     */

    public static Boolean scrollDownStillElementClickable(WebElement objWebElement, String strObjectName) {


        JavascriptExecutor js = null;

        try {

            js = (JavascriptExecutor)driver;;
            js.executeScript("arguments[0].scrollIntoView();", objWebElement);
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(objWebElement));

            logPass("Scrolled Still Element "+strObjectName+" visible and clickable.");
            return true;
        } catch (Exception objException) {
            objException.printStackTrace();
            logFailWithScreenshot("Scroll still"+strObjectName+" link visible failed. Exception : "+objException.getMessage());
            return false;
        }
    }


    /**
     * Objective - Scroll Down page to Bottom of page
     * @param strObjectName name of Label
     * @return boolean value
     */

    public static Boolean scrollDownToBottom(String strObjectName) {


        JavascriptExecutor js = null;

        try {
            js = (JavascriptExecutor)driver;;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            logPass("Scrolled Down to Bottom of Page");
            return true;
        } catch (Exception objException) {
            objException.printStackTrace();
            logFailWithScreenshot("Scrolled Down to Bottom of Page is failed");
            return false;
        }
    }

    /**
     * Objective - Scroll Up
     * @param strObjectName name of Label
     * @return boolean value
     */

    public static Boolean scrollUp(String strObjectName) {


        JavascriptExecutor js = null;

        try {
            js = (JavascriptExecutor)driver;;
            js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
            logPass("Scrolled Down to Bottom of Page");
            return true;
        } catch (Exception objException) {
            objException.printStackTrace();
            logFailWithScreenshot("Scrolled Down to Bottom of Page is failed");
            return false;
        }
    }

    /**
     * Objective - Scroll Down page to Bottom of page
     * @return boolean value
     */

    public static Boolean scrollMiddleOfPage() {


        JavascriptExecutor js = null;

        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        try {

            js = (JavascriptExecutor)driver;;
            js.executeScript(scrollElementIntoMiddle);
            logPass("Scrolled Down to Bottom of Page");
            return true;
        } catch (Exception objException) {
            objException.printStackTrace();
            logFailWithScreenshot("Scrolled Down to Bottom of Page is failed");
            return false;
        }
    }

}
