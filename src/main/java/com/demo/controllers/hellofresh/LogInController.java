package com.demo.controllers.hellofresh;


import com.demo.base.GlobalConstants;
import com.demo.pages.hellofresh.AuthenticationPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.demo.utilities.SeleniumUtils.*;

public class LogInController extends TestLibrary {

    AuthenticationPage authenticationPage = null;

    public LogInController(WebDriver driver)  {
        super(driver);
        authenticationPage = PageFactory.initElements(driver, AuthenticationPage.class);

    }

    public void openApplication(){
        launchURL(GlobalConstants.applicationURL);
    }

    public void logout(){

        clickObject(2, authenticationPage.signOut_Link,"Sign Out Link");

    }

    public void signInNewAccount(String newEmailAdddressStr){

        clickObject(2, authenticationPage.signIn_Link,"Sign In Link");
        typeValue(authenticationPage.emailAddress_TextField,"Email Address",newEmailAdddressStr);
        clickObject(1, authenticationPage.create_an_account_Button,"Create An Account Button");

    }

    public void existingAccountLogin(String emailAddressStr,String passwordStr){

        clickObject(2, authenticationPage.signIn_Link,"Sign In Link");
        typeValue(authenticationPage.registered_emailAddress_TextField,"Existing User Email Address",emailAddressStr);
        typeValue(authenticationPage.registered_password_TextField,"Existing User Password",passwordStr);
        clickObject(1, authenticationPage.signIn_Button,"Sign In Button");

    }
}
