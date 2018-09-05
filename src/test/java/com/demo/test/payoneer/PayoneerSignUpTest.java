package com.demo.test.payoneer;

import com.demo.base.BaseScript;
import org.testng.annotations.Test;

public class PayoneerSignUpTest extends BaseScript {

    @Test(testName="SignUp as Company",groups = { "SignUp","Regression" },
            description="Sign Up as Not US Resident for Company.")
    public void signUpNotResidentForCompany() throws Exception {

        logmsg("TestCase : Sign Up as Not US Resident for Company.");
        payoneer().commonController().openApplication();
        payoneer().signUpController().notUSResident();
        payoneer().signUpController().gettingStartedAsCompany("TestDemo Company",
                "Incorporation (Inc)");
        payoneer().signUpController().fillCompanyContactInformatio("www.testurl.com",
                "demoTest","demoTestLast","test@test.com");
        payoneer().signUpController().enterDateOfBirth("Jul","14","1981");

    }
}
