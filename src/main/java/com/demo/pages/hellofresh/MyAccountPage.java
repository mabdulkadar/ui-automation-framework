package com.demo.pages.hellofresh;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage {

    @FindBy(how= How.LINK_TEXT,using="Women")
    public WebElement womenCategory_Link;

    public String _productName="[class='product-name'][title='XXX']";
}
