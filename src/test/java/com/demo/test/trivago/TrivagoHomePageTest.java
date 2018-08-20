package com.demo.test.trivago;

import com.demo.base.BaseScript;
import org.testng.annotations.Test;

public class TrivagoHomePageTest extends BaseScript {


    @Test(testName="Search Location",groups = { "Home Page","Regression" },
            description="Search for any location on Room5 by using the search bar")
    public void search() throws Exception {

        logmsg("Test Case : Search for any location on Room5 by using the search bar");
        trivago().homePageController().search("Germany");

    }


    @Test(testName="Subscribe Newsletter",groups = { "Home Page","Regression" },
            description="Subscribe to the Newsletter",
            dependsOnMethods = { "search"})
    public void subscribeNewsLetter() throws Exception {

        logmsg("Test Case : Subscribe to the Newsletter");
        trivago().homePageController().subscribeNewsLetter("qatest@ttt.com");

    }

    @Test(testName="Destination Menu",groups = { "Home Page","Regression" },
            description="Navigate to a destination through the menu on the top left",
            dependsOnMethods = { "subscribeNewsLetter"})
    public void nagivateDetinationMenu() throws Exception {

        logmsg("Test Case : Navigate to a destination through the menu on the top left");
        trivago().homePageController().navigateDestination();

    }


    @Test(testName="Subscribe Newsletter",groups = { "Home Page","Regression" },
            description="Subscribe to the Newsletter for the same Coookie, it should not allow to subscribe.",
            dependsOnMethods = { "nagivateDetinationMenu"})
    public void subscribeNewsLetterForSameCookie() throws Exception {

        logmsg("Subscribe to the Newsletter for the same Cookie, it should not allow to subscribe.All the newsletter fields should not be displayed.");
        trivago().homePageController().subscribeNewsLetterForSameCookie("qatest@ttt.com");

    }





}
