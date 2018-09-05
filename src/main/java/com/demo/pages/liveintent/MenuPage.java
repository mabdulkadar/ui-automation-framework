package com.demo.pages.liveintent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MenuPage {

    @FindBy(how= How.CSS,using="global-navigation.fixed nav ul li:nth-of-type(2) ul li a span")
    public WebElement CampaignManager_GlobalMenu;

    @FindBy(how= How.CSS,using=".campaign-manager aside nav ul li:nth-of-type(3) a span")
    public WebElement ConversionTracker_MenuItem;
}
