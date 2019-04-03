package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {


    @FindBy(how= How.CSS,using="div#ctaLinks div:nth-child(4) > a > span")
    public WebElement signUpLink;

    @FindBy(how= How.CSS,using="a#register-trigger--withEmail")
    public WebElement signUpWithEmailLink;

    @FindBy(how= How.CSS,using="div[class*='login'] > a > div > span")
    public WebElement loginLink;

    @FindBy(how= How.CSS,using="div#exploreCTA span")
    public WebElement joinMeetupLink;

    @FindBy(how= How.CSS,using="p#register-error--name")
    public WebElement nameFieldError;

    @FindBy(how= How.CSS,using="p#register-error--email")
    public WebElement emailFieldError;

    @FindBy(how= How.CSS,using="p#register-error--password")
    public WebElement paswordFieldError;



    //https://www.meetup.com/welcome/


}
