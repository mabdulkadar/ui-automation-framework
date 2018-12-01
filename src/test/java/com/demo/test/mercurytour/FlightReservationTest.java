package com.demo.test.mercurytour;

import com.demo.base.BaseScript;
import org.testng.annotations.Test;

public class FlightReservationTest  extends BaseScript {

    @Test(testName="Fight Reservation",groups = { "Fight Reservation","Regression" },
            description="Login Mercury  applicaiton")
    public void loginTest() throws Exception {

        logmsg("Login into Application");
        mercuryFlightReservationApp().mercuryLoginController().login("mak.auto","jilanisha");
    }

    @Test(testName="Login",groups = { "Fight Reservation","Regression" },
            description="Search One way flight.")
    public void searchOnewayFlightTest() throws Exception {

        logmsg("Search One way flight.");
        mercuryFlightReservationApp().flightFinderController().selectOneWay();
        mercuryFlightReservationApp().flightFinderController().selectValueFromPassengerDropDown("3");
        mercuryFlightReservationApp().flightFinderController().selectValueFromDepartingFromDropDown("London");
        mercuryFlightReservationApp().flightFinderController().selectValueOnMonth("9");
        mercuryFlightReservationApp().flightFinderController().selectValueOnDay("4");
        mercuryFlightReservationApp().flightFinderController().selectValueFromArrivingInDropDown("Seattle");
        mercuryFlightReservationApp().flightFinderController().selectValueFromReturningMonthDropDown("12");
        mercuryFlightReservationApp().flightFinderController().selectValueReturningDayDropDown("4");
        mercuryFlightReservationApp().flightFinderController().selectBusiessClassRadio();
        mercuryFlightReservationApp().flightFinderController().selectValueFromArilinesDropDown("Blue Skies Airlines");
        mercuryFlightReservationApp().flightFinderController().clickContinueButton();
    }
}
