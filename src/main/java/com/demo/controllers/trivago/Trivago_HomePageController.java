package com.demo.controllers.trivago;

import com.demo.base.AppConstants;
import com.demo.pages.trivago.ContactPage;
import com.demo.pages.trivago.HomePage;
import com.demo.utilities.SeleniumUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class Trivago_HomePageController extends SeleniumUtils {

    HomePage homePage = null;
    ContactPage contactPage = null;

    public Trivago_HomePageController(WebDriver driver)  {
        super(driver);
        homePage = PageFactory.initElements(driver, HomePage.class);
        contactPage = PageFactory.initElements(driver, ContactPage.class);
    }

    public void search(String searchStrValue) throws Exception{

        launchURL(AppConstants.applicationURL);
        clickObject(homePage.searchIcon,"Search Icon");
        clickObject(homePage.searchInputField,"Search Input Field");
        typeValue(homePage.searchInputField,"search Input Field",searchStrValue);
        enterReturnKey(homePage.searchInputField,"search Input Field");
        clickObject(homePage.searchCloseIcon,"Search Close Icon");

    }

    public void navigateDestination() throws Exception{

        scrollUp("Scroll up to access Menu Icon.");
        clickObject(homePage.topLeftMenuIcon,"Top Left Menu Icon");
        clickFirstLinkCarouselMenuCard(homePage._destinationCarouselMenuCards,0);
    }

    public void subscribeNewsLetter(String emailIdstr) {

        launchURL(AppConstants.applicationURL);
        waitPageLoaded();
        scrollDownStillElementClickable(homePage.newsLetterCheckBox,"Newsletter Confirmation Check Box");
        clickObject(2,homePage.newsLetterCheckBox,"Newsletter Confirmation Check Box");
        typeValue(homePage.newsLetterEmailTextField,"Newsletter Email Text Field",emailIdstr);
        clickObject(homePage.newsLetterSubmitButton,"Newsletter Submit Button");
        verifyElementVisible(homePage.newsLetterSubmittedConfirmation,"Newsletter Subscription submitted Message.");


    }

    public void subscribeNewsLetterForSameCookie(String emailIdstr){

        launchURL(AppConstants.applicationURL);
        waitPageLoaded();
        verifyElementNotDisplayed(homePage.newsLetterCheckBox,"Newsletter Confirmation Check Box");
        verifyElementNotDisplayed(homePage.newsLetterEmailTextField,"Newsletter Email Text Field");
        verifyElementNotDisplayed(homePage.newsLetterSubmitButton,"Newsletter Submit Button");

    }

    public void nagivateToContactPage() throws Exception{

        launchURL(AppConstants.applicationURL);
        scrollDownToBottom("Contact Link");
        clickLinkUsingHref(homePage._contactLink,"Contact Link");

    }

    public void enterContactInformation(String yourMessage,String fullName,String emailId) throws Exception{

        boolean tabSwitchStatus = switchTab(1,"Contact Tab");
        if(tabSwitchStatus) {
            waitPageLoaded();
            typeValue(contactPage.yourMessageField, "Your Message Field.", yourMessage);
            typeValue(contactPage.fullNameField, "Full Name Field", fullName);
            typeValue(contactPage.yourEmailField, "Your Email Field", emailId);
            clickObject(contactPage.confirmCheckBox, "Confirmation CheckBox");
            clickObject(contactPage.submitButton, "Submit Button");
        }else{
            logFail("Contact Tab is opened.");
        }
    }

    public void enterContactInformationWithoutConfirmation(String yourMessage,String fullName,String emailId) throws Exception{

        String currentBrowserUrl = getCurrentUrl();

        if(StringUtils.isNotEmpty(currentBrowserUrl)) {

            launchURL(currentBrowserUrl);
            waitPageLoaded();
            typeValue(contactPage.yourMessageField, "Your Message Field.", yourMessage);
            typeValue(contactPage.fullNameField, "Full Name Field", fullName);
            typeValue(contactPage.yourEmailField, "Your Email Field", emailId);
            clickObject(1,contactPage.submitButton, "Submit Button");
            verifyElementVisible(contactPage.submitFailMessage, "Submit Fail.");
        }else{
            logFail("Current Brower url is empty.");
        }

    }
}
