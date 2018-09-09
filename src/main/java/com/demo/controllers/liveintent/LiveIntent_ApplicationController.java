package com.demo.controllers.liveintent;

import org.openqa.selenium.WebDriver;

public class LiveIntent_ApplicationController {

    WebDriver driver;

    public LiveIntent_ApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    private LoginPageController LoginPageController = null;
    public LoginPageController LoginPageController() throws Exception {
        if (LoginPageController == null) {
            LoginPageController = new LoginPageController(driver);
        }
        return LoginPageController;
    }

    private ConversionTrackerController conversionTrackerController = null;
    public ConversionTrackerController conversionTrackerController() throws Exception {
        if (conversionTrackerController == null) {
            conversionTrackerController = new ConversionTrackerController(driver);
        }
        return conversionTrackerController;
    }


}
