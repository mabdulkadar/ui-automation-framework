package com.demo.pages.trivago;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how = How.CLASS_NAME,using="search-icon")
    public WebElement searchIcon;

    @FindBy(how = How.CSS,using=".search-icon.open")
    public WebElement searchCloseIcon;

    @FindBy(how = How.CSS,using=".search-input")
    public WebElement searchInputField;

    @FindBy(how = How.ID,using="confirm")
    public WebElement newsLetterCheckBox;

    @FindBy(how = How.CSS,using=".et-email")
    public WebElement newsLetterEmailTextField;

    @FindBy(how = How.CSS,using=".submit")
    public WebElement newsLetterSubmitButton;

    @FindBy(how = How.CSS,using=".submitted")
    public WebElement newsLetterSubmittedConfirmation;

    public String _contactLink= "contact";

    @FindBy(how = How.CSS,using=".nav-icon")
    public WebElement topLeftMenuIcon;


    public String _destinationCarouselMenuCards = "div[class^='swiper-slide menu-destination-card'] a div[class^='menu-container'] div[class^='destination-menu']";
}
