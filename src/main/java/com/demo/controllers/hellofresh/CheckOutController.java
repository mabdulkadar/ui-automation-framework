package com.demo.controllers.hellofresh;

import com.demo.pages.hellofresh.CheckOutPage;
import com.demo.pages.hellofresh.MyAccountPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.demo.utilities.SeleniumUtils.*;

public class CheckOutController extends TestLibrary {

    MyAccountPage myAccountPage = null;
    CheckOutPage checkOutPage = null;

    public CheckOutController(WebDriver driver)  {
        super(driver);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        checkOutPage = PageFactory.initElements(driver, CheckOutPage.class);

    }

    public void checkOutWomenCategoryProduct(String productName,String quantity,String size){

        clickObject(2,myAccountPage.womenCategory_Link,"Women Category");
        clickRegularObject(2,myAccountPage._productName,productName,"Product Link");

        clearValue(checkOutPage.quantity_TextField,"Quantity");
        typeValue(checkOutPage.quantity_TextField,"Quantity",quantity);
        selectByVisibleTextFromDropDown(checkOutPage.size_DropDown,"Size",size);
        clickObject(1,checkOutPage.addToCart_Button,"Add To Cart Button");
        clickObject(1,checkOutPage.proceedToCheckOut_CartSection_Button,"Proceed To CheckOut Button in Cart");
        clickObject(1,checkOutPage.proceedToCheckOut_SummarySection_Button,"Proceed To CheckOut Button in Summary");
        clickObject(1,checkOutPage.proceedToCheckOut_AddressSection_Button,"Proceed To CheckOut Button in Address");
        clickObject(1,checkOutPage.termsOfService_ShippingSection_RadioButton,"Terms of service CheckBox");
        clickObject(1,checkOutPage.proceedToCheckOut_ShippingSection_Button,"Proceed To CheckOut Button in Shipping");

    }

    public void checkOutBankWirePaymentOption(){

        clickObject(1,checkOutPage.bankWire_PaymentSection_Button,"Pay by bank wire");
        clickObject(1,checkOutPage.iConfirmMyOrder_PaymentSection_Button,"I confirm my order");

    }

    public void verifyOrderConfirmation(){

        verifyTextInWebElement(checkOutPage.orderConfirmationPage_Header,"ORDER CONFIRMATION in HEADER",
                "ORDER CONFIRMATION");
        verifyElementDisplayed(checkOutPage.shippingStepConfirmation,"04. Shipping");
        verifyElementDisplayed(checkOutPage.paymentStepConfirmation,"05. Payment");
        verifyTextInWebElement(checkOutPage.orderCompleteText_forBankWire_OrderConfirmationPage,"our order on My Store is complete.",
                "our order on My Store is complete.");

        String currentURLStr = getCurrentUrl();
        verifyStringContainsMatchingValue("Check current url query parameter contains value",
                currentURLStr,"controller=order-confirmation");

    }
}
