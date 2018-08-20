package com.demo.test.trivago;

import com.demo.base.BaseScript;
import org.testng.annotations.Test;

public class TrivagoContactPageTest extends BaseScript {

    @Test(testName="Contact Information",groups = { "Contact Page","Regression" },
            description="Fill in the contact form and send it (accessible through the footer)")
    public void sendContactInformation() throws Exception {

        logmsg("Fill in the contact form and send it (accessible through the footer)");
        trivago().homePageController().nagivateToContactPage();
        trivago().homePageController().enterContactInformation("QA Automation Testing contact message",
                "QA Test User","qatest@trivago.com");
    }

    @Test(testName="Contact Information",groups = { "Contact Page","Regression" },
            description="Fill in the contact form and send it without click confirm checkbox.",
            dependsOnMethods = { "sendContactInformation" })
    public void sendContactInformationWithOutConfirmationCheckBox() throws Exception {

        logmsg("Fill in the contact form and send it without click confirm checkbox. Submit should failed.");
        trivago().homePageController().enterContactInformationWithoutConfirmation("QA Automation Testing contact message",
                "QA Test User","qatest@trivago.com");
    }
}
