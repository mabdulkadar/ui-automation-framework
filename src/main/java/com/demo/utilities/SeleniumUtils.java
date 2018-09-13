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
     * CLICK DYNAMIC OBJECT With Multiple Attempts.
     * @param noOfAttempts integer value to represent reattempt time
     * @param objWebElement webElement
     * @param valueToUseInObject string value to replace in object String vale
     * @param strObjectName object Name
     * @return boolean
     */
    public static Boolean clickRegularObject(Integer noOfAttempts,String objWebElement, String valueToUseInObject,String strObjectName)  {


        try {

            objWebElement = objWebElement.replace("XXX",valueToUseInObject.trim());

            driver.findElement(By.cssSelector(objWebElement)).click();
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
     * CLICK OBJECT WITH REQUESTED ATTEMPTS
     * @param noOfAttempts No of Attempts if Action failed.
     * @param objWebElement string element of objecg
     * @param strObjectName Web Element Name
     */
    public static void javaScriptClick(int noOfAttempts,String objWebElement, String strObjectName)  {


        try {

            objJSExecutor.executeScript("document.querySelector('"+objWebElement+"').click()");
            logPass(strObjectName+" has been successfully clicked.");
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                System.out.println("attempts:"+noOfAttempts);
                javaScriptClick((noOfAttempts-1),objWebElement, strObjectName);
            }else{
                logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());

            }

        }
    }


    /**
     * CLICK OBJECT WITH REQUESTED ATTEMPTS
     * @param noOfAttempts No of Attempts if Action failed.
     * @param objWebElement string element of objecg
     * @param strObjectName Web Element Name
     */
    public static void javaScriptClick(int noOfAttempts,WebElement objWebElement, String strObjectName)  {


        try {

            objJSExecutor.executeScript("arguments[0].click()",objWebElement);
            logPass(strObjectName+" has been successfully clicked.");
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                System.out.println("attempts:"+noOfAttempts);
                javaScriptClick((noOfAttempts-1),objWebElement, strObjectName);
            }else{
                logFailWithScreenshot(strObjectName+" Object is not clicked. Exception : "+objException.getMessage());

            }

        }
    }





    /**
     * CHECK OBJECT is Not Clikable
     * @param noOfAttempts No of Attempts if Action failed.
     * @param objWebElement Web Element Object
     * @param strObjectName Web Element Name
     */
    public static void verifyObjectNotClickable(int noOfAttempts,WebElement objWebElement, String strObjectName)  {


        try {

            (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(objWebElement));
            objWebElement.click();
            logFailWithScreenshot(strObjectName+" has been successfully clicked.");
        } catch (Exception objException) {

            logPass(strObjectName+" Object is not clickable. Actual Message : "+objException.getMessage());


        }
    }

    /**
     * CHECK OBJECT is Not Clickable
     * @param noOfAttempts No of Attempts if Action failed.
     * @param objWebElement Web Element Object
     * @param strObjectName Web Element Name
     * @return boolean
     */
    public static boolean waitforElementClickable(int noOfAttempts,WebElement objWebElement, String strObjectName)  {


        try {

            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(objWebElement));
            return true;
        } catch (Exception objException) {


            if(0 < noOfAttempts) {
                waitforElementClickable((noOfAttempts-1),objWebElement,  strObjectName);

            }

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
     * CLEAR VALUE FROM THE OBJECT
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @return boolean
     */

    public static Boolean clearValue(WebElement objWebElement, String strObjectName)  {
        try {

            (new WebDriverWait(driver, 45)).until(ExpectedConditions.elementToBeClickable(objWebElement));

            objWebElement.clear();

            logPass(strObjectName+" value has been successfully clear.");

            return true;
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" value clear action failed. Exception : "+objException.getMessage());
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
     * Verify Element is Mot Visible.
     * @param objWebElement web element
     * @param strObjectName object name
     */
    public static void verifyElementDisplayed(WebElement objWebElement, String strObjectName) {

        try {

            Boolean actualStatus = objWebElement.isDisplayed();

            if(actualStatus){
                logPass(strObjectName+" element is displayed.");


            }else{
                logFailWithScreenshot(strObjectName+" element is not displayed.");
            }
        } catch (Exception objException) {
            logFailWithScreenshot(strObjectName+" element is display through exception. Exception : "+objException.getMessage());
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
     * Get Text From Webelement
     * @param objWebElement webElement
     * @param strObjectName objectName
     * @return String value
     */
    public static String getTextFromWebElement(WebElement objWebElement, String strObjectName) {

        String actualValue = null;

        try {

            actualValue = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(objWebElement)).getText();

            return actualValue;

        } catch (Exception objException) {
            logFailWithScreenshot("Not Able to Get Text from "+strObjectName+" . Exception : "+objException.getMessage());
            return actualValue;
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
     * @param noOfAttempts number of attempts
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
     * Objective - Select a WebElement from multiple WebElement Lists
     * @param noOfAttempts no of attempts
     * @param strObjectName name of WebElement object
     * @param elementValue actual element value to be selected
     * @return boolean value
     */

    public static Boolean selectWebElement(int noOfAttempts,String strObjectName,String elementValue) {

        List<WebElement> webElementLists = null;
        Iterator<WebElement> itr = null;
        WebElement link = null;



        try {

            if(StringUtils.isNotEmpty(elementValue)){
                strObjectName = strObjectName.replace("XXX",elementValue);
            }


            webElementLists = driver.findElements(By.cssSelector(strObjectName));

            itr = webElementLists.iterator();

            while (itr.hasNext()){

                link = itr.next();




                if(link.getText().contains(elementValue)){

                    (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(link));
                    link.click();

                    logPass(strObjectName+" WebElement has been successfully clicked.");

                    break;
                }

            }

            return true;
        } catch (Exception objException) {

            if(0 < noOfAttempts) {
                selectWebElement((noOfAttempts-1),elementValue,  strObjectName);

            }else {

                logFailWithScreenshot(strObjectName + " WebElement is not selected : " + objException.getMessage());
                return false;
            }
        }

        return false;
    }

    /**
     * SELECT A FROM DROP DOWN BY VALUE
     * @param objWebElement Web Element Object
     * @param strObjectName Web Element Name
     * @param valueToBeSelectedStr String Value
     */
    public static void selectByValueFromDropDown(WebElement objWebElement, String strObjectName, String valueToBeSelectedStr)  {


        try {

            Select select = new Select(objWebElement);
            select.selectByValue(valueToBeSelectedStr.trim());
            logPass(strObjectName+" "+valueToBeSelectedStr+" has been successfully selected.");
        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" "+valueToBeSelectedStr+" has not been successfully selected. Exception : "+objException.getMessage());

        }
    }

    /**
     * SELECT A FROM DROP DOWN BY VISIBLE TEXT
     * @param objWebElement Web Element Object
     * @param strObjectName Web Element Name
     * @param valueToBeSelectedStr String Value
     */
    public static void selectByVisibleTextFromDropDown(WebElement objWebElement, String strObjectName, String valueToBeSelectedStr)  {


        try {

            Select select = new Select(objWebElement);
            select.selectByVisibleText(valueToBeSelectedStr.trim());
            logPass(strObjectName+" "+valueToBeSelectedStr+" has been successfully selected.");
        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" "+valueToBeSelectedStr+" has not been successfully selected. Exception : "+objException.getMessage());

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

    /**
     * Objective - Get Number of tabs active
     * @return Integer value
     */
    public static Integer getNumberOfTabs() {

        ArrayList<String> tabList = null;

        try{

            tabList = new ArrayList<String>(driver.getWindowHandles());

            return tabList.size();

        }catch (Exception objException) {
            logFail("Error while getting no of active tabs.");
            return 0;
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

    /**
     * Objective - Scroll WebElement X's Position
     * @param objWebElement name of Label
     * @return boolean value
     */

    public static Boolean scrollElementXPosition(WebElement objWebElement) {

        try {

            objJSExecutor.executeScript("window.scrollTo(0,"+objWebElement.getLocation().x+")");
            return true;
        } catch (Exception objException) {

            return false;
        }
    }

    /**
     * Objective - Scroll WebElement Y's Position
     * @param objWebElement name of Label
     * @return boolean value
     */

    public static Boolean scrollElementYPosition(WebElement objWebElement) {

        try {

            objJSExecutor.executeScript("window.scrollTo(0,"+objWebElement.getLocation().y+")");
            return true;
        } catch (Exception objException) {

            return false;
        }
    }

    /**
     * MOUSE HOVER AND CLICK OBJECT WITH REQUESTED ATTEMPTS
     * @param objWebElement WebElement element of objecg
     * @param strObjectName Web Element Name
     */
    public static void mouseHoverAndClick(WebElement objWebElement, String strObjectName)  {


        try {

            Actions builder = new Actions(driver);
            builder.moveToElement(objWebElement,0,0).click(objWebElement).perform();

        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" MouseHover and Click not performed. Exception : "+objException.getMessage());

        }
    }


    /**
     * Double Click
     * @param objWebElement WebElement element of objecg
     * @param strObjectName Web Element Name
     */
    public static void doubleClick(WebElement objWebElement, String strObjectName)  {


        try {

            Actions builder = new Actions(driver);
            builder.moveToElement(objWebElement,0,0).doubleClick(objWebElement).perform();

        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" Double Click not performed. Exception : "+objException.getMessage());

        }
    }

    /**
     * MOUSE HOVER ON  OBJECT
     * @param objWebElement String  of object
     * @param strObjectName Web Element Name
     */
    public static void mouseHover(String objWebElement, String strObjectName)  {


        try {

            Actions builderMouseHover = new Actions(driver);
            builderMouseHover.moveToElement(driver.findElement(By.cssSelector(objWebElement)));
            Action mouseHoverAction = builderMouseHover.build();
            mouseHoverAction.perform();


        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" MouseHover performed. Exception : "+objException.getMessage());

        }
    }

    /**
     * Javascript MOUSE HOVER ON  OBJECT
     * @param objWebElement String  of object
     * @param strObjectName Web Element Name
     * @return  boolean value
     */
    public static boolean jsMouseHoverClick(WebElement objWebElement, String strObjectName)  {

        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";

        try {

            objJSExecutor.executeScript(javaScript,objWebElement);
            objJSExecutor.executeScript("arguments[0].click()",objWebElement);
            logPass("Mouse Over Click success.");

            return true;

        } catch (Exception objException) {

            logFailWithScreenshot(strObjectName+" MouseHover not performed. Exception : "+objException.getMessage());
            return false;

        }
    }


    /**
     * Check Two Strings contains matching values
     * @param message String value to print in the report
     * @param expectedValue Expected string value
     * @param actualValue Actual string value
     * @return boolean
     */
    public static Boolean verifyStringContainsMatchingValue(String message,Object expectedValue,Object actualValue) {

        try {

            if ((expectedValue instanceof String) && (actualValue instanceof String)){

                if(StringUtils.contains((String)expectedValue,(String)actualValue)){
                    logPass(message+" is matching.");
                    return true;
                }else{
                    logFail(message+" is not matching. Expected Value:"+(String)expectedValue+" exists in "+(String)actualValue);
                    return false;
                }
            }else{
                logFail("Values are not String Object.");
                return false;
            }

        } catch (Exception objException) {
            logFail("Exception while comparing values "+objException.getMessage());
            return false;
        }
    }

}
