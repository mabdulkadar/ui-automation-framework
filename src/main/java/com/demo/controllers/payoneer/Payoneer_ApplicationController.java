package com.demo.controllers.payoneer;

import org.openqa.selenium.WebDriver;

public class Payoneer_ApplicationController {

    WebDriver driver;

    public Payoneer_ApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    private CommonController commonController = null;
    public CommonController commonController() throws Exception {
        if (commonController == null) {
            commonController = new CommonController(driver);
        }
        return commonController;
    }

    private SignUpController signUpController = null;
    public SignUpController signUpController() throws Exception {
        if (signUpController == null) {
            signUpController = new SignUpController(driver);
        }
        return signUpController;
    }




}
