package com.demo.pages.mercurytour;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FlightFinderPage {

    @FindBy(how= How.CSS,using="input[type='radio'][name='tripType'][value='roundtrip']")
    public WebElement roundTripRadioButton;

    @FindBy(how= How.CSS,using="input[type='radio'][name='tripType'][value='oneway']")
    public WebElement oneWayRadioButton;

    @FindBy(how= How.NAME,using="passCount")
    public WebElement passengersDropDown;

    @FindBy(how= How.NAME,using="fromPort")
    public WebElement departingFromDropDown;

    @FindBy(how= How.NAME,using="fromMonth")
    public WebElement onMonthDropDown;

    @FindBy(how= How.NAME,using="fromDay")
    public WebElement onDayDropDown;

    @FindBy(how= How.NAME,using="toPort")
    public WebElement arrivingInDropDown;

    @FindBy(how= How.NAME,using="toMonth")
    public WebElement returningMonthDropDown;

    @FindBy(how= How.NAME,using="toDay")
    public WebElement returningDayDropDown;

    @FindBy(how= How.CSS,using="input[name='servClass'][value='Business']")
    public WebElement businessClassRadioButton;

    @FindBy(how= How.CSS,using="input[name='servClass'][value='Coach']")
    public WebElement economyClassRadioButton;

    @FindBy(how= How.CSS,using="input[name='servClass'][value='First']")
    public WebElement firstClassRadioButton;

    @FindBy(how= How.NAME,using="airline")
    public WebElement airlineDropDown;

    @FindBy(how= How.NAME,using="findFlights")
    public WebElement continueButton;
}
