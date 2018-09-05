package com.demo.pages.payoneer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CalendarPage {

    @FindBy(how= How.ID,using = "datepicker")
    public WebElement dateField;


    public String _monthField=".ui-datepicker-month option";

    public String _yearField=".ui-datepicker-year option";

    public String _dateField=".ui-datepicker-calendar tbody tr";
}
