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

    public ConversionTrackerController(WebDriver driver)  {
        super(driver);
        conversionTrackersPage = PageFactory.initElements(driver, ConversionTrackersPage.class);
        menuPage = PageFactory.initElements(driver, MenuPage.class);

    }


    public void createNewConversionTracker(String advertiserNameStr,String trackerNameStr){
        clickObject(5,menuPage.CampaignManager_GlobalMenu,"CampaignManager Global Menu");
        clickObject(5,menuPage.ConversionTracker_MenuItem,"Conversion Trackers Menu Item");
        waitPageLoaded();
        clickObject(5,conversionTrackersPage.CreateConverstionTracker_Button,
                "Create Conversion Tracker Button");
        clickObject(5,conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        typeValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser",advertiserNameStr);
        clearValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        clickObject(5,conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        typeValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser",advertiserNameStr);
        clickObject(5,conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        /*clickObject(5,conversionTrackersPage._Advertiser_Lists,
                advertiserNameStr);*/
        javaScriptClick(5,conversionTrackersPage._Advertiser_Lists,
                advertiserNameStr);
        typeValue(conversionTrackersPage.Tracker_Name,"Tracker Name",
                trackerNameStr);
        clickObject(2,conversionTrackersPage.CreateTracker_Button,
                "Create Tracker Button");
    }

    public void createNewConversionTrackerWithoutTrackerName(String advertiserNameStr){
        clickObject(5,menuPage.ConversionTracker_MenuItem,"Conversion Trackers Menu Item");
        waitPageLoaded();
        clickObject(5,conversionTrackersPage.CreateConverstionTracker_Button,
                "Create Conversion Tracker Button");
        clickObject(5,conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        typeValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser",advertiserNameStr);
        clearValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        clickObject(5,conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        typeValue(conversionTrackersPage.Advertiser_SearchField,
                "Advertiser",advertiserNameStr);
        clickObject(5,conversionTrackersPage.Advertiser_SearchField,
                "Advertiser");
        /*clickObject(5,conversionTrackersPage._Advertiser_Lists,
                advertiserNameStr);*/
        selectWebElement2(5,conversionTrackersPage._Advertiser_Lists,
                advertiserNameStr);

        verifyObjectNotClickable(2,conversionTrackersPage.CreateTracker_Button,
                "Create Tracker Button");

        javaScriptClick(2,conversionTrackersPage.CreateConversionTrackerWindow_CloseCrossButton,
                "Close the Screen");
    }

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
}
