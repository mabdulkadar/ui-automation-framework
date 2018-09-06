package com.demo.controllers.liveintent;

import com.demo.pages.liveintent.ConversionTrackersPage;
import com.demo.pages.liveintent.MenuPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.demo.utilities.SeleniumUtils.*;

public class ConversionTrackerController extends TestLibrary {

    ConversionTrackersPage conversionTrackersPage = null;
    MenuPage menuPage = null;


    /**
     * Objective - Initialize Constructor with Page Objects
     * @param driver Webdriver
     */
    public ConversionTrackerController(WebDriver driver)  {
        super(driver);
        conversionTrackersPage = PageFactory.initElements(driver, ConversionTrackersPage.class);
        menuPage = PageFactory.initElements(driver, MenuPage.class);

    }


    /**
     * Objective - Test Steps Create New Conversion Successfully
     * @param advertiserNameStr String value
     * @param trackerNameStr String value
     */
    public void createNewConversionTracker(String advertiserNameStr,String trackerNameStr){
        clickObject(5,menuPage.CampaignManager_GlobalMenu,"CampaignManager Global Menu");
        clickObject(5,menuPage.ConversionTracker_MenuItem,"Conversion Trackers Menu Item");
        waitPageLoaded();
        clickObject(5,conversionTrackersPage.CreateConverstionTracker_Button,
                "Create Conversion Tracker Button");
        selectAdvertiser(advertiserNameStr);
        typeValue(conversionTrackersPage.Tracker_Name,"Tracker Name",
                trackerNameStr);
        clickCreateTrackerButton();
    }


    /**
     * Objective - Test Steps to Create New Conversion without Tracker Name (Negative Case)
     * @param advertiserNameStr String value
     */
    public void createNewConversionTrackerWithoutTrackerName(String advertiserNameStr){
        clickObject(5,menuPage.CampaignManager_GlobalMenu,"CampaignManager Global Menu");
        clickObject(5,menuPage.ConversionTracker_MenuItem,"Conversion Trackers Menu Item");
        waitPageLoaded();
        clickObject(5,conversionTrackersPage.CreateConverstionTracker_Button,
                "Create Conversion Tracker Button");

       selectAdvertiser(advertiserNameStr);

        verifyObjectNotClickable(2,conversionTrackersPage.CreateTracker_Button,
                "Create Tracker Button");

        javaScriptClick(2,conversionTrackersPage.CreateConversionTrackerWindow_CloseCrossButton,
                "Close the Screen");
    }

    /**
     * Objective - Test Steps to Create New Conversion without Advertiser Name (Negative Case)
     * @param trackerNameStr String value
     */
    public void createNewConversionTrackerWithoutAdvertiser(String trackerNameStr){

        clickObject(5,menuPage.CampaignManager_GlobalMenu,"CampaignManager Global Menu");
        clickObject(5,menuPage.ConversionTracker_MenuItem,"Conversion Trackers Menu Item");
        waitPageLoaded();
        clickObject(5,conversionTrackersPage.CreateConverstionTracker_Button,
                "Create Conversion Tracker Button");

        typeValue(conversionTrackersPage.Tracker_Name,"Tracker Name",
                trackerNameStr);

        verifyObjectNotClickable(1,conversionTrackersPage.CreateTracker_Button,
                "Create Tracker Button");

        javaScriptClick(5,conversionTrackersPage.CreateConversionTrackerWindow_CloseCrossButton,
                "Close the Screen");

    }


    /**
     * Objective - Test Steps to Choose the Advertiser Name from Drop Down, since Drop Down Dynamic, it got re-try mechanism
     * @param advertiserNameStr String value
     */
    public void selectAdvertiser(String advertiserNameStr){


        boolean status = false;
        int counter = 3;

        typeValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser", advertiserNameStr);

        while(counter > 0 && !status) {


            clearValue(conversionTrackersPage.Advertiser_SearchField,
                    "Advertiser");
            clickObject(5, conversionTrackersPage.Advertiser_SearchField,
                    "Advertiser");
            typeValue(conversionTrackersPage.Advertiser_SearchField,
                    "Advertiser", advertiserNameStr);
            clickObject(5, conversionTrackersPage.Advertiser_SearchField,
                    "Advertiser");
            status = waitforElementClickable(1, conversionTrackersPage._Advertiser_Lists,
                    "AdvertiserName");

            counter--;

            logmsg("Attempt "+counter+" check Clickable Advertiser Name in DropDown.");

        }


        if(status){
            mouseHoverAndClick(conversionTrackersPage._Advertiser_Lists,
                        "AdvertiserName");
        }else{
            logFailWithScreenshot("Element not visible after"+counter+" attempted");
        }
    }

    /**
     * Objective - Test Steps to Click Create Tracker Button, Button is always Non-Clickable, it got re-try mechanism
     */
    public void clickCreateTrackerButton(){

        boolean status = false;
        int counter = 5;

        while(counter > 0 && !status) {

            status = waitforElementClickable(1, conversionTrackersPage.CreateTracker_Button,
                    "Create Tracker Button");
            counter--;
            logmsg("Attempt "+counter+" check Clickable Create Tracker Button.");


        }

        if(status){

            clickObject(1, conversionTrackersPage.CreateTracker_Button,
                    "Create Tracker Button");

        }else{
            javaScriptClick(1, conversionTrackersPage.CreateTracker_Button,
                    "Create Tracker Button");
            logWarning("JavaScript Click Trigger as CreateTracker Button is not clickable, after multiple checks.");
        }

    }
}
