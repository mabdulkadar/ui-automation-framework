package com.demo.base;

public class GlobalConstants {

    public static final String strLocation = System.getProperty("user.dir");
    public static final String strEnv = System.getProperty("env");

    public static final String WinChromeDriverPath = strLocation + "/src/main/resources/lib/win/chromedriver.exe";
    public static final String MacChromeDriverPath = strLocation + "/src/main/resources/lib/mac/chromedriver";
    public static final String UnixChromeDriverPath = strLocation + "/src/main/resources/lib/linux/chromedriver";

    public static final String WinGeckoDriverPath = strLocation + "/src/main/resources/lib/win/geckodriver.exe";
    public static final String MacGeckoDriverPath = strLocation + "/src/main/resources/lib/mac/geckodriver";
    public static final String UnixGeckoDriverPath = strLocation + "/src/main/resources/lib/linux/geckodriver";

    private static final String strConfigLocation="/src/main/resources/config/";
    public static final String strConfigFileName=strLocation+strConfigLocation+strEnv+"_CONFIG.properties";

    public static final String externalTestDataLocationStr=strLocation+"/src/test/resources/testdata/";

    public static String screenshotPath = null;


    //Application details
    public static String tempMailURL = null;
    public static String applicationURL = null;
    public static String apiBaseUri = null;


    public static int fiddlerPort=8888;
}
