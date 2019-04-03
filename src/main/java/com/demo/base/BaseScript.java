package com.demo.base;

import com.demo.controllers.ApplicationController;
import com.demo.utilities.Helper;
import com.demo.utilities.TestLibrary;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseScript extends TestLibrary {

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

        final ThreadLocal threadLocal = new ThreadLocal();

        if(driver == null) {

            WebDriver localDriver = null;

            try {

                /*Load configuration details*/
                if ((config == null && StringUtils.isNotEmpty(GlobalConstants.strEnv))) {

                    config = new Configuration(GlobalConstants.strConfigFileName);
                    GlobalConstants.applicationURL = config.getConfiguration("applicationURL");
                    GlobalConstants.tempMailURL = config.getConfiguration("tempMailURL");
                    GlobalConstants.apiBaseUri = config.getConfiguration("apiBaseUri");

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
                DesiredCapabilities capabilities = null;

                if(StringUtils.isNotEmpty(System.getProperty("Browser"))) {
                    selectedBrowser = BROWSER.valueOf(System.getProperty("Browser"));


                    try {
                        switch (selectedBrowser) {
                            case firefox: {
                                capabilities = DesiredCapabilities.firefox();
                                capabilities.setCapability("marionette", true);
                                capabilities.setCapability("acceptInsecureCerts", true);
                                //localDriver = new FirefoxDriver(firefoxprofile);
                                localDriver = new FirefoxDriver();
                                localDriver.manage().window().maximize();
                                threadLocal.set(localDriver);
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
                                //localDriver = new FirefoxDriver();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }


            if (BaseScript.driver == null && (localDriver != null)) {
                BaseScript.driver = localDriver;
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            }

        }
    }





    /**
     * Object - www.xxx.com Application Testing
     */
    ApplicationController applicationController = null;
    public ApplicationController applicationController()
    {
        if(applicationController ==  null) {
            applicationController = new ApplicationController(driver);
        }

        return applicationController;
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
