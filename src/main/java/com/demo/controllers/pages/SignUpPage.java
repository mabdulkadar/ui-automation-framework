package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpPage {

    @FindBy(how= How.CSS,using="input#register-field--name")
    public WebElement yourNameTextField;

    @FindBy(how= How.CSS,using="input#register-field--email")
    public WebElement emailAddressTextField;


    @FindBy(how= How.CSS,using="input#register-field--password")
    public WebElement passwordTextField;

    @FindBy(how= How.CSS,using="button[type=\"submit\"]")
    public WebElement confirmButton;


}
