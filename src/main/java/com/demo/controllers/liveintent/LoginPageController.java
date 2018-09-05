package com.demo.controllers.liveintent;
import com.demo.base.AppConstants;
import com.demo.pages.liveintent.LoginPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.demo.utilities.SeleniumUtils.*;


public class LoginPageController extends TestLibrary {

    LoginPage loginPage = null;

    public LoginPageController(WebDriver driver)  {
        super(driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

    }




    public void login(String emailAddressStr, String passwordStr){

        launchURL(AppConstants.applicationURL);
        typeValue(loginPage.emailAddress_TextField,"email Address",emailAddressStr);
        typeValue(loginPage.password_TextField,"password",passwordStr);
        clickObject(2,loginPage.login_Button,"Login Button");

    }

}






