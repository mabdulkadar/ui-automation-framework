package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TempMailPage {

    @FindBy(how= How.CSS,using="input#mail")
    public WebElement eamilAddressField;

    @FindBy(how= How.XPATH,using="//div/span/a[text()='Verify your Meetup accoun']")
    public WebElement verificationEmailLink;


    /*@FindBy(how= How.CSS,using="table > tbody > tr > td > table:nth-child(2) > tbody > tr > td > table > tbody > tr > td > table")
    public WebElement verifyYourAccountLinkInEmail;*/


    @FindBy(how= How.PARTIAL_LINK_TEXT,using="Verify your accou")
    public WebElement verifyYourAccountLinkInEmail;

    @FindBy(how= How.CSS,using="inbox-header email-view")
    public WebElement emailView;


}
