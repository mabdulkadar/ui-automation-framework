package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    @FindBy(how= How.CSS,using="div h1 span.memName.fn")
    public WebElement nameHeader;

    @FindBy(how= How.CSS,using="// span#member-profile-photo img")
    public WebElement profileLogoPicture;


}
