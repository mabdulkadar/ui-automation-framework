package com.demo.controllers;

import com.demo.controllers.controller.HomePageController;
import com.demo.controllers.controller.RegistrationPageController;
import com.demo.controllers.controller.WelcomePageController;
import com.demo.controllers.controller.TempMailPageController;
import org.openqa.selenium.WebDriver;

public class ApplicationController {

    WebDriver driver;

    public ApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    private TempMailPageController tempMailPageController = null;
    public TempMailPageController tempMailPageController()  {
        if (tempMailPageController == null) {
            tempMailPageController = new TempMailPageController(driver);
        }
        return tempMailPageController;
    }

    private RegistrationPageController registrationPageController = null;
    public RegistrationPageController registrationPageController()  {
        if (registrationPageController == null) {
            registrationPageController = new RegistrationPageController(driver);
        }
        return registrationPageController;
    }

    private WelcomePageController welcomePageController = null;
    public WelcomePageController welcomePageController()  {
        if (welcomePageController == null) {
            welcomePageController = new WelcomePageController(driver);
        }
        return welcomePageController;
    }

    private HomePageController homePageController = null;
    public HomePageController homePageController()  {
        if (homePageController == null) {
            homePageController = new HomePageController(driver);
        }
        return homePageController;
    }
}
