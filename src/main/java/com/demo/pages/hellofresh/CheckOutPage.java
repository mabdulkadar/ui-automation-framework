package com.demo.pages.hellofresh;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckOutPage {

    @FindBy(how= How.LINK_TEXT,using="Women")
    public WebElement womenCategory_Link;

    public String _productName="[class='product-name'][title='XXX']";

    @FindBy(how= How.CSS,using="#quantity_wanted")
    public WebElement quantity_TextField;

    @FindBy(how= How.ID,using="group_1")
    public WebElement size_DropDown;

    @FindBy(how= How.CSS,using="#add_to_cart button")
    public WebElement addToCart_Button;

    @FindBy(how= How.CSS,using="#layer_cart a[title='Proceed to checkout']")
    public WebElement proceedToCheckOut_CartSection_Button;

    @FindBy(how= How.CSS,using="p[class*='cart_navigation'] a[title='Proceed to checkout']")
    public WebElement proceedToCheckOut_SummarySection_Button;

    @FindBy(how= How.NAME,using="processAddress")
    public WebElement proceedToCheckOut_AddressSection_Button;

    @FindBy(how= How.CSS,using="#uniform-cgv")
    public WebElement termsOfService_ShippingSection_RadioButton;

    @FindBy(how= How.NAME,using="processCarrier")
    public WebElement proceedToCheckOut_ShippingSection_Button;

    @FindBy(how= How.CLASS_NAME,using="bankwire")
    public WebElement bankWire_PaymentSection_Button;



    @FindBy(how= How.CSS,using="p[id*='cart_navigation'] button")
    public WebElement iConfirmMyOrder_PaymentSection_Button;

    @FindBy(how= How.CSS,using=".page-heading")
    public WebElement orderConfirmationPage_Header;

    @FindBy(how= How.CSS,using="li[class='step_done step_done_last four'] a")
    public WebElement shippingStepConfirmation;

    @FindBy(how= How.CSS,using="li[id='step_end'][class='step_current last']")
    public WebElement paymentStepConfirmation;

    @FindBy(how= How.CSS,using="#order-confirmation .box .cheque-indent .dark")
    public WebElement orderCompleteText_forBankWire_OrderConfirmationPage;
}
