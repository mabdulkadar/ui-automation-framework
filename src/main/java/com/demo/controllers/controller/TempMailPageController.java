package com.demo.controllers.controller;

import com.demo.base.GlobalConstants;
import com.demo.controllers.pages.TempMailPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.demo.utilities.SeleniumUtils.*;

public class TempMailPageController extends TestLibrary {

    TempMailPage tempMailPage = null;
    String tempEmailId=null;


    public TempMailPageController(WebDriver driver)  {
        super(driver);
        tempMailPage = PageFactory.initElements(driver, TempMailPage.class);

    }


    public String getTemporaryEmailId(){
        logmsg("Generate the temporary fake email.");
        launchURL(GlobalConstants.tempMailURL);
        waitPageLoaded();
        tempEmailId = getTextInWebElement(tempMailPage.eamilAddressField, "Temp Email Address Field","value");
        return tempEmailId;
    }

    public void verifyYourAccountConfirmationInEmail(){
        logmsg("Verify the Website's Registration by Activating in the confirmation email.");
       // switchTab(0,"Temp Email Tab");
        launchURL(GlobalConstants.tempMailURL);
        scrollDownToElement(tempMailPage.emailView,"Email View");
        clickObject(5, tempMailPage.verificationEmailLink,"Verification Email Link");
        scrollDownToElement(tempMailPage.emailView,"Email View");
        clickObject(5, tempMailPage.verifyYourAccountLinkInEmail,"Verify Your Account Link");

    }

}
