package com.demo.controllers.payoneer;

import com.demo.pages.payoneer.CalendarPage;
import com.demo.pages.payoneer.SignUpPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.demo.utilities.SeleniumUtils.*;

public class SignUpController extends TestLibrary {

    SignUpPage signUpPage = null;
    CalendarPage calendarPage =  null;

    public SignUpController(WebDriver driver)  {
        super(driver);
        signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        calendarPage = PageFactory.initElements(driver, CalendarPage.class);
    }


    public void openApplication(){

    }

    public void notUSResident(){

        clickObject(5,signUpPage.notUS_Resident_Continue_Link,
                "if you are not a US Resident, please Continue");

    }

    public void gettingStartedAsCompany(String companyNameStr,String typeOfBusinessLegalEntityStr
                                        ){

        waitPageLoaded();
        clickObject(10,signUpPage.company_RadioButton,"Company RadioButton");
        typeValue(signUpPage.campanyLegalName_TextField,"Company's Legal Name",companyNameStr);
        clickObject(5,signUpPage.typeOfBusinessLegalEntity_Field,"Type of business legal entity");
        selectWebElement(5,signUpPage._typeOfBusinessLegalEntity_Lists,typeOfBusinessLegalEntityStr);


    }

    public void fillCompanyContactInformatio(String companyWebSiteUrlStr, String firstNameContactStr,
                                             String lastNameContactStr, String emailAddressStr){

        typeValue(signUpPage.companyWebSiteURL_TextField,"Company's Legal Name",companyWebSiteUrlStr);
        typeValue(signUpPage.firtNameOfContactPerson_TextField,"First Name contact person",firstNameContactStr);
        typeValue(signUpPage.lastNameOfContactPerson_TextField,"Last Name contact person",lastNameContactStr);
        typeValue(signUpPage.emailAddress_TextField,"Email address",emailAddressStr);
        typeValue(signUpPage.re_enterEmailAddress_TextField,"Re-enter Email address",emailAddressStr);

    }

    public void enterDateOfBirth(String monthStr,String dateStr, String yearStr){
        clickObject(1,calendarPage.dateField,"Contact person's date of birth");
        selectWebElement(1,calendarPage._monthField,monthStr);
        selectWebElement(1,calendarPage._yearField,yearStr);
        selectWebElement(1,calendarPage._dateField,dateStr);


    }
}
