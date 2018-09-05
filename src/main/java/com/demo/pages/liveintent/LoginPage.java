package com.demo.pages.liveintent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how= How.CSS,using= "#email")
    public WebElement emailAddress_TextField;

    @FindBy(how= How.CSS,using= "#password")
    public WebElement password_TextField;

    @FindBy(how= How.CSS,using= ".button--primary")
    public WebElement login_Button;


}
