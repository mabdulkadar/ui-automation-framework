package com.demo.controllers.controller;

import com.demo.controllers.pages.WelcomePage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.demo.utilities.SeleniumUtils.*;


public class WelcomePageController extends TestLibrary {

    WelcomePage welcomePage = null;

    public WelcomePageController(WebDriver driver)  {
        super(driver);
        welcomePage = PageFactory.initElements(driver, WelcomePage.class);

    }


    public void uploadProfileLogo(String filepath){
        logmsg("Upload the profile logo picture.");
        switchTab(1,"Meetup Welcome Page");
        waitPageLoaded();
        uploadFile(welcomePage.uplLoadAPhotoLink, "Upload Photo Link", filepath);
        clickObject(2, welcomePage.useThisPhotonButton,"Use This Photon Button");

    }
    public void provideProfileInformation(){
        logmsg("Select User's Goal for his Registration");
        clickObject(2, welcomePage.developASkillLink,"Develop A Skill");

    }

}
