package com.demo.pages.hellofresh;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthenticationPage {

    @FindBy(how= How.CLASS_NAME,using="login")
    public WebElement signIn_Link;

    //Already Registered User Objects
    @FindBy(how= How.ID,using="email")
    public WebElement registered_emailAddress_TextField;

    @FindBy(how= How.ID,using="passwd")
    public WebElement registered_password_TextField;

    @FindBy(how= How.ID,using="SubmitLogin")
    public WebElement signIn_Button;



    //Create New Account Objects
    @FindBy(how= How.ID,using="email_create")
    public WebElement emailAddress_TextField;

    @FindBy(how= How.ID,using="SubmitCreate")
    public WebElement create_an_account_Button;



    //Create An Account Objects
    @FindBy(how= How.ID,using="id_gender2")
    public WebElement mrs_RadioButton;

    @FindBy(how= How.ID,using="customer_firstname")
    public WebElement firstName_TextField;

    @FindBy(how= How.ID,using="customer_lastname")
    public WebElement lastName_TextField;

    @FindBy(how= How.ID,using="passwd")
    public WebElement password_TextField;

    @FindBy(how= How.ID,using="days")
    public WebElement dateOfBirth_days_Dropdown;

    @FindBy(how= How.ID,using="months")
    public WebElement dateOfBirth_months_Dropdown;

    @FindBy(how= How.ID,using="years")
    public WebElement dateOfBirth_years_Dropdown;

    @FindBy(how= How.ID,using="company")
    public WebElement company_TextField;

    @FindBy(how= How.ID,using="address1")
    public WebElement address_TextField;

    @FindBy(how= How.ID,using="address2")
    public WebElement address_line_2_TextField;

    @FindBy(how= How.ID,using="city")
    public WebElement city_TextField;

    @FindBy(how= How.ID,using="id_state")
    public WebElement state_DropDown;

    @FindBy(how= How.ID,using="postcode")
    public WebElement zip_postal_code_TextField;

    @FindBy(how= How.ID,using="other")
    public WebElement additional_information_TextField;

    @FindBy(how= How.ID,using="phone")
    public WebElement homePhone_TextField;

    @FindBy(how= How.ID,using="phone_mobile")
    public WebElement mobilePhone_TextField;

    @FindBy(how= How.ID,using="alias")
    public WebElement assign_an_address_alias_for_future_reference_TextField;

    @FindBy(how= How.ID,using="submitAccount")
    public WebElement register_Button;

    @FindBy(how= How.CLASS_NAME,using="page-heading")
    public WebElement myAccount_Heading;

    @FindBy(how= How.CLASS_NAME,using="account")
    public WebElement accountName_Link;

    @FindBy(how= How.CLASS_NAME,using="info-account")
    public WebElement welcomeMessage_Header;

    @FindBy(how= How.CLASS_NAME,using="logout")
    public WebElement signOut_Link;

}
