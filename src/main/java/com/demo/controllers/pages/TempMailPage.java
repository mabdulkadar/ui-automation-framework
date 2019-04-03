package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TempMailPage {

    @FindBy(how= How.CSS,using="input#mail")
    public WebElement eamilAddressField;

    @FindBy(how= How.CSS,using="div.table-responsive.mailListTable table#mails tbody tr td:nth-child(2)")
    public WebElement verificationEmailLink;


    @FindBy(how= How.CSS,using="table > tbody > tr > td > table:nth-child(2) > tbody > tr > td > table > tbody > tr > td > table")
    public WebElement verifyYourAccountLinkInEmail;


}
