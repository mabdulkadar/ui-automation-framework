package com.demo.controllers.trivago;

import org.openqa.selenium.WebDriver;

public class Trivago_ApplicationController {

    WebDriver driver;

    public Trivago_ApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    private Trivago_HomePageController homePageController = null;
    public Trivago_HomePageController homePageController() throws Exception {
        if (homePageController == null) {
            homePageController = new Trivago_HomePageController(driver);
        }
        return homePageController;
    }

}
