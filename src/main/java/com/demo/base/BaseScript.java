package com.demo.base;

import com.demo.controllers.hellofresh.HelloFreshApplicationController;
import com.demo.controllers.liveintent.LiveIntent_ApplicationController;
import com.demo.controllers.payoneer.Payoneer_ApplicationController;
import com.demo.controllers.trivago.Trivago_ApplicationController;
import com.demo.utilities.Helper;
import com.demo.utilities.SeleniumUtils;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseScript extends SeleniumUtils {

    public static Configuration config = null;

    public static WebDriver driver = null;

    public enum BROWSER
    {
        firefox, chrome,headless
    }

    /**
     * Constructor to Initialize the Framework
     */
    public BaseScript(){

        if(driver == null) {

            WebDriver localDriver = null;

            try {

                /*Load configuration details*/
                if (config == null) {
                    config = new Configuration(GlobalConstants.strConfigFileName);
                    GlobalConstants.applicationURL = config.getConfiguration("applicationURL");

                }

                switch (Helper.getOS()) {
                    case "win":
                        System.setProperty("webdriver.chrome.driver", GlobalConstants.WinChromeDriverPath);
                        System.setProperty("webdriver.gecko.driver", GlobalConstants.WinGeckoDriverPath);
                        break;
                    case "mac":
                        System.setProperty("webdriver.chrome.driver", GlobalConstants.MacChromeDriverPath);
                        System.setProperty("webdriver.gecko.driver", GlobalConstants.MacGeckoDriverPath);
                        break;
                    case "unix":
                        System.setProperty("webdriver.chrome.driver", GlobalConstants.UnixChromeDriverPath);
                        System.setProperty("webdriver.gecko.driver", GlobalConstants.UnixGeckoDriverPath);
                        break;

                    default:
                        System.out.println("Please check Operating System, We are supporting only Windows/Unix/Mac");
                        System.exit(1);
                }

                BROWSER selectedBrowser = null;
                selectedBrowser = BROWSER.valueOf(System.getProperty("Browser"));

                DesiredCapabilities capabilities = null;

                ProfilesIni profile = null;
                FirefoxProfile firefoxprofile = null;

                try {
                    switch (selectedBrowser) {
                        case firefox: {
					/*firefoxprofile = new FirefoxProfile();
					firefoxprofile.setPreference("security.insecure_password.ui.enabled",false);*/
                            capabilities = DesiredCapabilities.firefox();
                            capabilities.setCapability("marionette", true);
                            capabilities.setCapability("acceptInsecureCerts", true);
                            //localDriver = new FirefoxDriver(firefoxprofile);
                            localDriver = new FirefoxDriver();
                            localDriver.manage().window().maximize();
                            break;
                        }

                        case chrome: {

                            Map<String, Object> prefs = new HashMap<String, Object>();
                            DesiredCapabilities caps = DesiredCapabilities.chrome();
                            ChromeOptions options = new ChromeOptions();
                            options.setExperimentalOption("prefs", prefs);
                            options.addArguments("--disable-infobars");
                            options.addArguments("--start-maximized");
                            caps.setCapability(ChromeOptions.CAPABILITY, options);
                            caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                            localDriver = new ChromeDriver(caps);
                            localDriver.manage().window().maximize();
                            break;

                        }

                        case headless: {

                            localDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
                            ((HtmlUnitDriver) localDriver).setJavascriptEnabled(true);
                            break;

                        }

                        default: {
                            localDriver = new FirefoxDriver();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);

                }


            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }


            if (BaseScript.driver == null) {
                BaseScript.driver = localDriver;
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            }
        }
    }



    /**
     * Object - Trivago Application Testing
     */
    Trivago_ApplicationController trivago= null;
    public Trivago_ApplicationController trivago()
    {
        if(trivago ==  null) {
            trivago = new Trivago_ApplicationController(driver);
        }

        return trivago;
    }

    /**
     * Object - Payoneer Application Testing
     */
    Payoneer_ApplicationController payoneer= null;
    public Payoneer_ApplicationController payoneer()
    {
        if(payoneer ==  null) {
            payoneer = new Payoneer_ApplicationController(driver);
        }

        return payoneer;
    }

    /**
     * Object - LiveIntent Application Testing
     */
    LiveIntent_ApplicationController liveIntent= null;
    public LiveIntent_ApplicationController liveIntent()
    {
        if(liveIntent ==  null) {
            liveIntent = new LiveIntent_ApplicationController(driver);
        }

        return liveIntent;
    }

    /**
     * Object - Hellofresh Application Testing
     */
    HelloFreshApplicationController helloFresh= null;
    public HelloFreshApplicationController helloFresh()
    {
        if(helloFresh ==  null) {
            helloFresh = new HelloFreshApplicationController(driver);
        }

        return helloFresh;
    }

    @AfterSuite
    public void TearDownSuite()
    {
        //Close the Driver
        if(driver != null) {
            driver.quit();
        }

    }
}
