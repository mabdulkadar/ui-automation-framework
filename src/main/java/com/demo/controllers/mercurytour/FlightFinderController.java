package com.demo.controllers.mercurytour;

import com.demo.pages.mercurytour.FlightFinderPage;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.demo.utilities.SeleniumUtils.*;

public class FlightFinderController extends TestLibrary {

    FlightFinderPage flightFinderPage = null;

    /**
     * Objective - Initialize Constructor with Page Objects
     * @param driver Webdriver
     */
    public FlightFinderController(WebDriver driver)  {
        super(driver);
        flightFinderPage = PageFactory.initElements(driver, FlightFinderPage.class);

    }

    public void selectOneWay(){
        clickObject(5,flightFinderPage.oneWayRadioButton,"Select one way radio button.");
    }

    public void selectRoundTrip(){
        clickObject(5,flightFinderPage.oneWayRadioButton,"Select one way radio button.");
    }

    public void selectValueFromPassengerDropDown(String noOfPassegners){
        clickObject(5,flightFinderPage.passengersDropDown,"Click Passenger Dropdown");
        selectByValueFromDropDown(flightFinderPage.passengersDropDown,
                "Select a Value from Passegner Dropdown", noOfPassegners);
    }

    public void selectValueFromDepartingFromDropDown(String departingValue){
        clickObject(5,flightFinderPage.departingFromDropDown,"Click Departing From Dropdown");
        selectByValueFromDropDown(flightFinderPage.departingFromDropDown,
                "Select a Value from Departing From Dropdown", departingValue);
    }

    public void selectValueOnMonth(String onMonthValue){
        clickObject(5,flightFinderPage.onMonthDropDown,"Click On Month Dropdown");
        selectByValueFromDropDown(flightFinderPage.onMonthDropDown,
                "Select a Value from On Month From Dropdown", onMonthValue);
    }

    public void selectValueOnDay(String onDayValue){
        clickObject(5,flightFinderPage.onDayDropDown,"Click On Day Dropdown");
        selectByValueFromDropDown(flightFinderPage.onDayDropDown,
                "Select a Value from On Day From Dropdown", onDayValue);
    }

    public void selectValueFromArrivingInDropDown(String arrivingValue){
        clickObject(5,flightFinderPage.arrivingInDropDown,"Click Arriving In Dropdown");
        selectByValueFromDropDown(flightFinderPage.arrivingInDropDown,
                "Select a Value from Arriving In Dropdown", arrivingValue);
    }

    public void selectValueFromReturningMonthDropDown(String returningMonthValue){
        clickObject(5,flightFinderPage.returningMonthDropDown,"Click Returning Month Dropdown");
        selectByValueFromDropDown(flightFinderPage.returningMonthDropDown,
                "Select a Value from Returning Month From Dropdown", returningMonthValue);
    }

    public void selectValueReturningDayDropDown(String returningDayValue){
        clickObject(5,flightFinderPage.returningDayDropDown,"Click Returning Day Dropdown");
        selectByValueFromDropDown(flightFinderPage.returningDayDropDown,
                "Select a Value from Returning Day From Dropdown", returningDayValue);
    }

    public void selectEconomyClassRadio(){
        clickObject(5,flightFinderPage.economyClassRadioButton,"Select EconomyClass radio button.");
    }

    public void selectBusiessClassRadio(){
        clickObject(5,flightFinderPage.businessClassRadioButton,"Select BusinessClass radio button.");
    }

    public void selectFirstClassRadio(){
        clickObject(5,flightFinderPage.firstClassRadioButton,"Select FirstClass radio button.");
    }

    public void selectValueFromArilinesDropDown(String airlineValues){
        clickObject(5,flightFinderPage.airlineDropDown,"Click Airlines Dropdown");
        selectByVisibleTextFromDropDown(flightFinderPage.airlineDropDown,
                "Select a Value from Airlines Dropdown", airlineValues);
    }

    public void clickContinueButton(){
        clickObject(5,flightFinderPage.continueButton,"Click Continue Button.");
    }

}
