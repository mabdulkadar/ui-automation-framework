package com.demo.controllers.hellofresh;

import org.openqa.selenium.WebDriver;

public class HelloFreshApplicationController {

    WebDriver driver;

    public HelloFreshApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    private LogInController loginPageController = null;
    public LogInController loginPageController()  {
        if (loginPageController == null) {
            loginPageController = new LogInController(driver);
        }
        return loginPageController;
    }

    private AccountController accountController = null;
    public AccountController accountController()  {
        if (accountController == null) {
            accountController = new AccountController(driver);
        }
        return accountController;
    }

    private CheckOutController checkOutController = null;
    public CheckOutController checkOutController()  {
        if (checkOutController == null) {
            checkOutController = new CheckOutController(driver);
        }
        return checkOutController;
    }
}
