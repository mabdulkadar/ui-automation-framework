# demo ui qa automation framework

* This repo is used as the core of our automation framework, which can be used to automate multiple Web applications.

* In this framework we are applying Page Object Model design pattern where Object Repository is created  for web UI elements.
* For each web page in the application, there should be corresponding page class. This Page class will find the WebElements of that web page.
* There are controller classes which contain page methods which perform operations on corresponding WebElements in model classes.
* Test classes will contain a collection of TestMethods/TestCases that are related to the targeted Scenario/Feature.

```
** Which means, each page in the application has one page class, one controller class.
** Test classes will fetch methods from various controllers.

```


## Getting Started

In order to get a copy of the project, clone the project from the respective git repository at your desired local path.

```bash
git clone https://github.com/mabdulkadar/demo-ui-qa.git

```

### Prerequisites

This project should be running on local environments after fulfilling the following Prerequisites

```
1. Latest version of JDK 8. [TJDK 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Latest version of maven. [maven] (https://maven.apache.org/download.cgi)
3. IDE of your choice. [Intellij] 

Note: maven pom.xml should take care of required version of the following:

1. Selenium WebDriver
2. TestNG
3. Extent Report
4. Maven
```

### Installing

1. After having all Prerequisites then cloning the project, everything should work smoothly.
2. Make sure to include unnecessary files to .gitignore file



## Project Config & Running the tests


|       Main Directories    | Packages          |                                                Description                                                    |
| ------------------------- |:-----------------:| -------------------------------------------------------------------------------------------------------------:|
| src/main/java/com/demo    | base              | contains infrastructure of the framework, starting and ending points of the execution, all reusable functions |
|                           | pages             | acting as Object Repository where all WebElements' locators of the page will be collected                     |
|                           | controllers        | contains page methods which perform operations on corresponding WebElements in model classes                  |
|                           |                   |                           |                                                                                   |
| src/main/resources        | config            | *.properties files contains all the environment details like applicaiton url, database details etc, multiple  |
|                           |                   | qa environments, based -Denv argument framework load the property file.                                       |
|                           |                   |                                                                                                               |
|                           | lib               | contains all the drivers like chromedriver, geckodriver for OS like win64,linux64,mac                         |                                                                |
| src/test/java/com/demo    | test              | collection of TestMethods/TestCases and will fetch methods from various controllers                           |
|                           |                   |                                                                                                               |
| src/test/resources        | Suites            | TestNG xml file will be available in this folder.                                                             |                   |                                                                                                               |
| test-result               | html test results | Extent html Test Report with screenshot will be loaded. For each run, latest folder will be get created.      |


## How To Run (Using Maven Command)
```bash
from command line Use following command to run test: 
mvn clean test -Denv=QA -DBrowser=chrome -DsuiteXmlFile=src/test/resources/Suites/TestNGSuite.xml
mvn clean test -Denv=QA -DBrowser=firefox -DsuiteXmlFile=src/test/resources/Suites/TestNGSuite.xml
mvn clean test -Denv=QA -DBrowser=headless -DsuiteXmlFile=src/test/resources/Suites/TestNGSuite.xml

-DsuiteXmlFile=src/test/resources/Suites/TestNGSuite.xml is optional, you have different *.xml file, please use it.

```

## How To Run (Using Java Jar file)
```bash
java -Denv=QA -DBrowser=chrome -jar target/demo-ui-qa-1.0-SNAPSHOT-fat-tests.jar src/test/resources/Suites/TestNGSuite.xml

```

## How To Run (In IDE)
```bash
from IDE: Right click TestNGSuite.xml file under ~/test/resources/Suites folder and pass parameters -Denv=QA -DBrowser=chrome
```

## Supporting Browsers
* **-DBrowser accepts** - *chrome,firefox,headless* - Initial version supports only Chrome Browser, working on firefox/headless browser.

## How write Automation for an Application

1.Create Controller Class for your Application Under ~/src/main/java/com/demo/controllers/<application_package> like below
```bash
public class Demo_ApplicationController {

    WebDriver driver;

    public Demo_ApplicationController(WebDriver driver) {
        this.driver = driver;
    }

   
}
```
2.Register Controller in BaseScript.java under ~/src/main/com/demo/base
```bash
    Demo_ApplicationController demoApplication= null;
    public Demo_ApplicationController demoApplication()
    {
        if(demoApplication ==  null) {
            demoApplication = new Demo_ApplicationController(driver);
        }

        return demoApplication;
    }
```

3.Create your Object repository for application page under ~/src/main/java/com/demo/pages using Page Object Model, for example for login page as below.

```bash

public class Demo_LoginPage {


    @FindBy(how = How.ID,using="username")
    @CacheLookup
    public WebElement usernameTextField;
    @FindBy(how = How.ID,using="password")
    @CacheLookup
    public WebElement passwordTextField;

    @FindBy(how = How.ID,using="submit")
    @CacheLookup
    public WebElement submitButton;

}

```
4.Create Controller for the page , which contains the automated steps under ~/src/main/java/com/demo/controllers . For example, implement test logic for Login in Controller as below.

```bash

public class Demo_LoginPageController extends SeleniumUtils {

    Demo_LoginPage loginPage =  null;



    public Demo_LoginPageController(WebDriver driver) throws Exception {
        super(driver);
        loginPage = PageFactory.initElements(driver, Demo_LoginPage.class);
    }

    public void login() throws Exception{
        launchURL(AppConstants.applicationURL);
        typeValue(loginPage.usernameTextField, "User Name", "user@ttt.com");
        typeValue(loginPage.passwordTextField, "Password", "123333");
        clickObject(loginPage.submitButton, "Submit Button");
    }
}

```

5.Create Test Case under ~/src/test/java/com/demo/test

```bash

public class LoginTest extends BaseScript {

    @Test()
    public void loginTest() throws Exception {

        demoApplication().demoLoginPageController().login();


    }
}

```

6.Add Test Class in testng .xml file under ~/src/test/resources/Suites, like create file TestNGSuite.xml

```bash

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Suite" parallel="false">

	<listeners>

		<listener
			class-name="com.demo.testreport.ExtentReportITestListener" />
	</listeners>


	<test name="Demo">
		<classes>
			<class name="com.demo.test.LoginTest"></class>
		</classes>
	</test>

</suite> 

```

com.demo.testreport.ExtentReportITestListener in the xml file required for Test Report Generation.

7.Output of the Test Result will be available under ~/test-result folder


## Authors

* **Mohamed Abdulkadar** - *Initial work* -

See also the list of [contributors](https://github.com/GroupM-mPlatform/mplatform-qa-automation/graphs/contributors) who participated in this project.