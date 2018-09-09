package com.demo.controllers.hellofresh;

import com.demo.pages.hellofresh.AuthenticationPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.demo.utilities.SeleniumUtils.*;

public class AccountController extends TestLibrary {

    AuthenticationPage authenticationPage = null;

    public AccountController(WebDriver driver)  {
        super(driver);
        authenticationPage = PageFactory.initElements(driver, AuthenticationPage.class);

    }

    public void createNewAccount(String firstNameStr,String lastNameStr,String passwordStr,
                                 String dayStr,String monthStr,String yearStr){

        clickObject(1,authenticationPage.mrs_RadioButton,"Mrs Radio Button");

        typeValue(authenticationPage.firstName_TextField,"First Name",firstNameStr);
        typeValue(authenticationPage.lastName_TextField,"Last Name",lastNameStr);
        typeValue(authenticationPage.password_TextField,"Password",passwordStr);

        selectByValueFromDropDown(authenticationPage.dateOfBirth_days_Dropdown,"Date of Birth Day",dayStr);
        selectByValueFromDropDown(authenticationPage.dateOfBirth_months_Dropdown,"Date of Birth Month",monthStr);
        selectByValueFromDropDown(authenticationPage.dateOfBirth_years_Dropdown,"Date of Birth Year",yearStr);

        typeValue(authenticationPage.company_TextField,"Company","Company");
        typeValue(authenticationPage.address_TextField,"Address","Qwerty, 123");
        typeValue(authenticationPage.address_line_2_TextField,"Address Line 2","zxcvb");
        typeValue(authenticationPage.city_TextField,"City","Qwerty");
        selectByVisibleTextFromDropDown(authenticationPage.state_DropDown,"State","Colorado");
        typeValue(authenticationPage.zip_postal_code_TextField,"Zip/Postal Code","12345");

        typeValue(authenticationPage.additional_information_TextField,"Additional Information","Qwerty");
        typeValue(authenticationPage.homePhone_TextField,"Home Phone","12345123123");
        typeValue(authenticationPage.mobilePhone_TextField,"Mobile Phone","22345123123");
        typeValue(authenticationPage.assign_an_address_alias_for_future_reference_TextField,"Assign an address alias for future reference",
                "hf");
        clickObject(1,authenticationPage.register_Button,"Register Button");

    }

    public void verifyAccountDetails(String accountUserName){

        verifyTextInWebElement(authenticationPage.myAccount_Heading,"MY ACCOUNT in HEADER",
                "MY ACCOUNT");
        verifyTextInWebElement(authenticationPage.accountName_Link,"Account NAME in HEADER .",
                accountUserName);

        String accountWelcomeMessageStr = getTextFromWebElement(authenticationPage.welcomeMessage_Header,"Welcome Message.");
        verifyStringContainsMatchingValue("Check Welcome Message Contains : ",
                accountWelcomeMessageStr,"Welcome to your account.");

        verifyElementDisplayed(authenticationPage.signOut_Link,"Sign Out Link");

        String currentURLStr = getCurrentUrl();
        verifyStringContainsMatchingValue("Check current url query parameter contains value",
                currentURLStr,"controller=my-account");



    }
}
