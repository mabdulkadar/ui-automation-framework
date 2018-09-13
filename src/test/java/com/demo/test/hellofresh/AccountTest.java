package com.demo.test.hellofresh;

import com.demo.base.BaseScript;
import com.demo.dataprovider.hellofresh.AccountTestDataProvider;
import com.demo.testreport.TestCaseId;
import com.demo.utilities.Helper;
import org.testng.annotations.Test;

public class AccountTest  extends BaseScript {


    @Test(testName="Sign In",groups = { "Account","Regression" },
            description="Create New Account.",
            dataProvider = "Sign In Test", dataProviderClass = AccountTestDataProvider.class)
    public void signInTest(String FirstName,String LastName,String Password,String DateOfBirthDay,String DateOfBirthMonth,String DateOfBirthYear){

        String newEmailStr=FirstName+"."+LastName+"@domain"+ Helper.generateUniqueValue()+".com";

        logmsg("Test Case : Create New Account.");
        helloFresh().loginPageController().openApplication();
        helloFresh().loginPageController().signInNewAccount(newEmailStr);
        helloFresh().accountController().createNewAccount(FirstName,LastName,Password,DateOfBirthDay,DateOfBirthMonth,DateOfBirthYear);
        helloFresh().accountController().verifyAccountDetails(FirstName+" "+LastName);
        helloFresh().loginPageController().logout();
    }

    @Test(testName="Login In",groups = { "Account","Regression" },
            description="Login with Existing Account Details.",
            dataProvider = "Log In Test", dataProviderClass = AccountTestDataProvider.class)
    public void logInTest(String EmailAddress,String Password,String Name) {

        logmsg("Test Case : Login with Existing Account Details.");
        helloFresh().loginPageController().existingAccountLogin(EmailAddress,Password);
        helloFresh().accountController().verifyAccountDetails(Name);
        helloFresh().loginPageController().logout();

    }

    @Test(testName="Check Out",groups = { "Check Out","Regression" },
            description="Verify Check out.",
            dataProvider = "Check Out Test", dataProviderClass = AccountTestDataProvider.class)
    public void checkoutTest(TestCaseId testCaseId,String TestCaseDescription,
                             String EmailAddress, String Password, String ProductName,
                             String Quantity,String Size)  {

        logmsg("Test Case : "+TestCaseDescription);
        helloFresh().loginPageController().existingAccountLogin(EmailAddress,Password);
        helloFresh().checkOutController().checkOutWomenCategoryProduct(ProductName,Quantity,Size);
        helloFresh().checkOutController().checkOutBankWirePaymentOption();
        helloFresh().checkOutController().verifyOrderConfirmation();
        helloFresh().loginPageController().logout();

    }


}
