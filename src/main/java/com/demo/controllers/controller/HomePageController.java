package com.demo.controllers.controller;

import com.demo.base.GlobalConstants;
import com.demo.controllers.pages.HomePage;
import com.demo.controllers.pages.ProfilePage;
import com.demo.utilities.Helper;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.demo.utilities.SeleniumUtils.*;
import static com.sun.webkit.network.URLs.newURL;

public class HomePageController extends TestLibrary {

    HomePage homePage = null;
    ProfilePage profilePage = null;

    public HomePageController(WebDriver driver)  {
        super(driver);
        homePage = PageFactory.initElements(driver, HomePage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);

    }

    public void openWebsiteHomePageInNewTab(){
        logmsg("Open the Website in New Tab of Active Browser.");
        openNewBrowerTab();
        launchURL(GlobalConstants.applicationURL);
    }

    public void navigateProfilePage(){
        logmsg("Nagivate to Profile Page from Home Page.");
        clickObject(2, homePage.profileButton,"Profile Icon");
        clickObject(2, homePage.profileLink,"Profile Icon");
    }


    public void verifyUserInformation(String name){
        logmsg("Verify User Information like Name in Profile Page.");
        verifyTextInWebElement(profilePage.nameHeader,"Name in Profile Page",name);
    }

    public void verifyLogoPictureInPorfilePage(String expectedLogoFilePath){
        logmsg("Verify Uploaded logo pictures is visible.");

        String imageLinkStr = "";
        BufferedImage actualProfileImage= null;
        BufferedImage expectedProfileImage= null;
        boolean resultFlag = false;

        try{

            imageLinkStr = getTextInWebElement(profilePage.profileLogoPicture,"Profile Picture","src");
            actualProfileImage= ImageIO.read(newURL(imageLinkStr));;
            expectedProfileImage= ImageIO.read(new File(expectedLogoFilePath));

            resultFlag = Helper.compareImages(expectedProfileImage,actualProfileImage);

            if(resultFlag){
                logPass("Logo Pictures is matching");
            }else{
                logFail("Logo Pictures not is matching");
            }

        }catch(Exception e){
            logFail("Exception while comparing the logo image. Exception : "+e.getMessage());
        }

    }

}
