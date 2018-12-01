package com.demo.controllers.mercurytour;

import com.demo.base.GlobalConstants;
import com.demo.pages.mercurytour.LoginPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.demo.utilities.SeleniumUtils.*;

public class MercuryLoginController  extends TestLibrary {
    LoginPage loginPage = null;

    /**
     * Objective - Initialize Constructor with Page Objects
     * @param driver Webdriver
     */
    public MercuryLoginController(WebDriver driver)  {
        super(driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

    }

    public void login(String username, String password){

        launchURL(GlobalConstants.applicationURL);
        typeValue(loginPage.userName,"username",username);
        typeValue(loginPage.passWord,"password",password);
        clickObject(2,loginPage.submitButton,"Submit Button");

        waitPageLoaded();

    }
}
