package com.demo.test;

import com.demo.base.BaseScript;
import com.demo.utilities.Helper;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseScript {

    String tempEmailId = null;
    String name= null;
    String logoImagePatch = null;


    @Test(groups = { "Registration" })
    public void verifyTC1_NewUserRegistration(){

        name="testername"+ Helper.generateUniqueValue();

        tempEmailId = applicationController().tempMailPageController().getTemporaryEmailId();
        applicationController().registrationPageController().fillRegistrationForm(name,tempEmailId,"test@1234");
        applicationController().tempMailPageController().verifyYourAccountConfirmationInEmail();

    }

    @Test(groups = { "Registration" },dependsOnMethods={"verifyTC1_NewUserRegistration"})
    public void verifyTC2_UploadLogoForNewUser(){

        name="testername"+ Helper.generateUniqueValue();
        logoImagePatch = System.getProperty("user.dir")+"/src/test/resources/logoimages/pepsi.png";
        applicationController().welcomePageController().uploadProfileLogo(logoImagePatch);
        applicationController().welcomePageController().provideProfileInformation();

    }

    @Test(groups = { "Registration" },dependsOnMethods={"verifyTC2_UploadLogoForNewUser"})
    public void verifyTC3_NewUserInformationInProfile(){

        applicationController().homePageController().openWebsiteHomePageInNewTab();
        applicationController().homePageController().navigateProfilePage();
        applicationController().homePageController().verifyUserInformation(name);
        applicationController().homePageController().verifyLogoPictureInPorfilePage(name);

    }

    @Test(groups = { "Registration" },dependsOnMethods={"verifyTC3_NewUserInformationInProfile"})
    public void verifyTC4_NewUserRegistrationWithoutMandatoyFields(){

        applicationController().registrationPageController().fillRegistrationFormWithooutMandatoryFields();
        applicationController().registrationPageController().verifyRegistratioPageUrlIsActive("/register/");
        applicationController().registrationPageController().verifyErrorInformationForAllMandatoryFields();

    }
}
