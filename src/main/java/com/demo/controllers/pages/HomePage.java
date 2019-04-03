package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how= How.CSS,using="#profileNav > span > span > svg")
    public WebElement profileButton;

    @FindBy(how= How.CSS,using="#nav-account-links > li:nth-child(1) > a")
    public WebElement profileLink;



}
