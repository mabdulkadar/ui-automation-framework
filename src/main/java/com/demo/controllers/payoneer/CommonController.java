package com.demo.controllers.payoneer;

import com.demo.base.AppConstants;
import com.demo.utilities.TestLibrary;
import org.openqa.selenium.WebDriver;
import static com.demo.utilities.SeleniumUtils.*;

public class CommonController extends TestLibrary {

    public CommonController(WebDriver driver)  {
        super(driver);

    }

    public void openApplication(){
        launchURL(AppConstants.applicationURL);
    }
}
