package com.demo.controllers.mercurytour;

import org.openqa.selenium.WebDriver;

public class MercuryApplicationController {

    WebDriver driver;

    public MercuryApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    private MercuryLoginController mercuryLoginController = null;
    public MercuryLoginController mercuryLoginController() throws Exception {
        if (mercuryLoginController == null) {
            mercuryLoginController = new MercuryLoginController(driver);
        }
        return mercuryLoginController;
    }

    private FlightFinderController flightFinderController = null;
    public FlightFinderController flightFinderController() throws Exception {
        if (flightFinderController == null) {
            flightFinderController = new FlightFinderController(driver);
        }
        return flightFinderController;
    }
}
