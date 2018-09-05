package com.demo.pages.liveintent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConversionTrackersPage {

    @FindBy(how= How.CSS,using=".button--create")
    public WebElement CreateConverstionTracker_Button;


    @FindBy(how= How.CSS,using=".searchable-select-single div input")
    public WebElement Advertiser_SearchField;

   /* @FindBy(how= How.CSS,using="div[class^='searchable-select-single'] div[class='dropdown'] div[class^='dropdown--container'] span")
    public WebElement _Advertiser_Lists;*/
    //public String _Advertiser_Lists="class*=\"dropdown--container\"";

    public String _Advertiser_Lists="div[class^='searchable-select-single'] div[class='dropdown'] div[class^='dropdown--container'] span";

    @FindBy(how= How.CSS,using="input[name='name']")
    public WebElement Tracker_Name;

    @FindBy(how= How.CSS,using=".button--primary")
    public WebElement CreateTracker_Button;

    //@FindBy(how= How.CSS,using=".grid .row div button[type='button']")
    public String CreateConversionTrackerWindow_CloseCrossButton=".grid .row div button";


}
