package com.demo.controllers.controller;

import com.demo.base.GlobalConstants;
import com.demo.controllers.pages.RegistrationPage;
import com.demo.controllers.pages.SignUpPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.URI;
import java.net.URISyntaxException;

import static com.demo.utilities.SeleniumUtils.*;
import static org.testng.AssertJUnit.assertEquals;

public class RegistrationPageController extends TestLibrary {

    RegistrationPage registrationPage = null;
    SignUpPage signUpPage = null;

    public RegistrationPageController(WebDriver driver)  {
        super(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        signUpPage = PageFactory.initElements(driver, SignUpPage.class);

    }

    public void fillRegistrationForm(String name,String emailId,String password){

        logmsg("Fill Registration Form with all fields with valid values.");
       // openNewBrowerTab();
        launchURL(GlobalConstants.applicationURL);
        waitPageLoaded();
        clickObject(2, registrationPage.joinMeetupLink,"Join Meetup");
        waitPageLoaded();
        clickObject(2, registrationPage.signUpWithEmailLink,"Sign Up with Email");
        typeValue(signUpPage.yourNameTextField,"Name",name);
        typeValue(signUpPage.emailAddressTextField,"Email",emailId);
        typeValue(signUpPage.passwordTextField,"Password",password);
        clickObject(2, signUpPage.confirmButton,"Sign Up");


    }

    public void fillRegistrationFormWithooutMandatoryFields(){

        logmsg("Fill Registration Form without mandatory  fields.");
        openNewBrowerTab();
        launchURL(GlobalConstants.applicationURL);
        waitPageLoaded();
        clickObject(2, registrationPage.joinMeetupLink,"Join Meetup");
        waitPageLoaded();
        clickObject(2, registrationPage.signUpWithEmailLink,"Sign Up with Email");
        clickObject(2, signUpPage.confirmButton,"Sign Up");

    }

    public void verifyRegistratioPageUrlIsActive(String expectedUrlPath){
        URI myURI = null;
        String actualUrlPath = null;
        String urlStr = getCurrentUrl();
        try {
            myURI = new URI(urlStr);
            actualUrlPath = myURI.getPath();
            assertEquals(expectedUrlPath,actualUrlPath);

        } catch (URISyntaxException e1) {
            logFail("Failure in verifying Registration page url. Exception :"+e1.getMessage());
        }catch (Exception e) {
            logFail("Failure in verifying Registration page url. Exception :"+e.getMessage());
        }

    }

    public void verifyErrorInformationForAllMandatoryFields(){

        verifyElementVisible(registrationPage.nameFieldError,"Name Field Error message");
        verifyElementVisible(registrationPage.emailFieldError,"Email Field Error message");
        verifyElementVisible(registrationPage.paswordFieldError,"Password Field Error message");

    }

}
