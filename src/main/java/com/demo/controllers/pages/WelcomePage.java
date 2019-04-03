package com.demo.controllers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WelcomePage {

    @FindBy(how= How.CSS,using="input[name='photo']")
    public WebElement uplLoadAPhotoLink;

    @FindBy(how= How.CSS,using="#register-view--photo > section > div:nth-child(4) > div > button")
    public WebElement useThisPhotonButton;

    @FindBy(how= How.CSS,using="#learnNewSkillButton")
    public WebElement developASkillLink;

    //*[@id="register-view--photo"]/section/div[1]/span
    // #register-view--photo > section > div.chunk.margin--top > span
}
