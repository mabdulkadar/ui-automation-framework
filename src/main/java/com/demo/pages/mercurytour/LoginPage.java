package com.demo.pages.mercurytour;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how= How.NAME,using="userName")
    public WebElement userName;

    @FindBy(how= How.NAME,using="password")
    public WebElement passWord;

    @FindBy(how= How.NAME,using="login")
    public WebElement submitButton;
}
