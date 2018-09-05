package com.demo.pages.payoneer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpPage {

    //Getting Started Section

    @FindBy(how = How.ID,using="USPSContinue")
    public WebElement notUS_Resident_Continue_Link;

    @FindBy(how = How.CSS,using=".radio")
    public WebElement company_RadioButton;

    @FindBy(how = How.CSS,using="#txtCompanyName")
    public WebElement campanyLegalName_TextField;

    @FindBy(how = How.CSS,using="#ddlBusinessOrganization")
    public WebElement typeOfBusinessLegalEntity_Field;

   /* @FindBy(how = How.CSS,using="#ddlBusinessOrganization option")
    public WebElement typeOfBusinessLegalEntity_Lists;*/

    public String _typeOfBusinessLegalEntity_Lists = "#ddlBusinessOrganization option";

    @FindBy(how = How.CSS,using="#txtCompanyURL")
    public WebElement companyWebSiteURL_TextField;

    @FindBy(how = How.CSS,using="#txtContactPersonFirstName")
    public WebElement firtNameOfContactPerson_TextField;

    @FindBy(how = How.CSS,using="#txtContactPersonLastName")
    public WebElement lastNameOfContactPerson_TextField;

    @FindBy(how = How.CSS,using="#txtEmail")
    public WebElement emailAddress_TextField;

    @FindBy(how = How.CSS,using="#txtRetypeEmail")
    public WebElement re_enterEmailAddress_TextField;

    @FindBy(how = How.ID,using="PersonalDetailsButton")
    public WebElement nextButton_InGettingStarted;

    //Contact Details
    @FindBy(how = How.CSS, using="#ddlHeadquartersCountries")
    public WebElement country_Field;

    @FindBy(how = How.CSS, using="#ddlHeadquartersCountries option")
    public WebElement country_Lists;

    @FindBy(how = How.CSS, using="#ddlState")
    public WebElement state_Field;

    @FindBy(how = How.CSS, using="#ddlState option")
    public WebElement state_Lists;

    @FindBy(how = How.CSS, using="#txtAddress3")
    public WebElement businessAddress_Field1;

    @FindBy(how = How.CSS, using="#txtAddress4")
    public WebElement businessAddress_Field2;

    @FindBy(how = How.CSS, using="#txtCity")
    public WebElement city_Field;

    @FindBy(how = How.CSS, using="#txtZip")
    public WebElement Postal_ZipCode_Field;

    @FindBy(how = How.CSS, using="#ContactPersonPhoneNumber_num")
    public WebElement contactPersonPhoneNumber_Field;

    @FindBy(how = How.CSS, using="#ContactPersonPhoneNumber_num")
    public WebElement nextButton_InContact;

    //Security Details
    @FindBy(how = How.CSS, using="#txtUserName")
    public WebElement userName_Field;

    @FindBy(how = How.CSS, using="#tbPassword")
    public WebElement password_Field;

    @FindBy(how = How.CSS, using="#tbRetypePassword")
    public WebElement re_enterPassword_Field;

    @FindBy(how = How.CSS, using="#ddlSecurityQuestions")
    public WebElement securityQuestion_Field;

    @FindBy(how = How.CSS, using="#ddlSecurityQuestions option")
    public WebElement securityQuestion_List;

    @FindBy(how = How.CSS, using="#tbSecurityAnswer")
    public WebElement securityAnswer_Field;

    @FindBy(how = How.CSS, using="#ddlIssueCountry")
    public WebElement issuingCountryOfId_Field;

    @FindBy(how = How.CSS, using="#txtCollectEIN")
    public WebElement employerIdentificationNumber_Field;

    @FindBy(how = How.CSS, using="#AccountDetailsButton")
    public WebElement nextButton_InSecurityDetails;

    //Almost Done Section
    @FindBy(how = How.CSS, using="#ddlAccountType label:nth-of-type(1)")
    public WebElement pesonalAccount_Radio;

    @FindBy(how = How.CSS, using="#ddlAccountType label:nth-of-type(2)")
    public WebElement companyAccount_Radio;

    @FindBy(how = How.CSS, using="#ddlCountries")
    public WebElement bankCountry_Field;

    @FindBy(how = How.CSS, using="#ddlCountries option")
    public WebElement bankCountry_List;

    @FindBy(how = How.CSS, using="#ddlCurrencies")
    public WebElement currency_Field;

    @FindBy(how = How.CSS, using="#ddlCurrencies option")
    public WebElement currency_List;

    @FindBy(how = How.CSS, using="#iachfield1ddl")
    public WebElement bankName_Field;

    @FindBy(how = How.CSS, using="#iachfield1ddl option")
    public WebElement bankName_List;

    @FindBy(how = How.CSS, using="#iachfield2")
    public WebElement accountName_Field;

    @FindBy(how = How.CSS, using="#iachfield3")
    public WebElement accountNumber_Field;

    @FindBy(how = How.CSS, using="#iachfield17")
    public WebElement routingNumber_Field;

    @FindBy(how = How.CSS, using="#iachfield18")
    public WebElement accountType_Field;

    @FindBy(how = How.CSS, using="#iachfield18 option")
    public WebElement accountType_List;

    @FindBy(how = How.CSS, using="#sdReg div:nth-of-type(1) div:nth-of-type(1) label")
    public WebElement termsAndConditions_PrivacyCookiePolicy_Radio;

    @FindBy(how = How.CSS, using="#sdReg div:nth-of-type(2) div:nth-of-type(1) label")
    public WebElement pricingAndFees_Radio;

    @FindBy(how = How.CSS, using="#btnSubmit")
    public WebElement submitButton_InAlmostDone;




}
