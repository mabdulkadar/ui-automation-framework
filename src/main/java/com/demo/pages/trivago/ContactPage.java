package com.demo.pages.trivago;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContactPage {

    @FindBy(how = How.CSS,using=".contact-msg")
    public WebElement yourMessageField;

    @FindBy(how = How.CSS,using=".contact-input")
    public WebElement fullNameField;

    @FindBy(how = How.CSS,using="#contact-email")
    public WebElement yourEmailField;

    @FindBy(how = How.ID,using="confirm")
    public WebElement confirmCheckBox;

    @FindBy(how = How.CSS,using=".contact-submit")
    public WebElement submitButton;

    @FindBy(how = How.CLASS_NAME,using="alert-error")
    public WebElement submitFailMessage;
}
