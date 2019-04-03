# demo ui qa automation framework

* This repo is used as the core of ui automation framework, which can be used to automate multiple Web applications.

* Page Object Model design pattern has been used in this project.

```

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




## How To Run (Using Maven Command)
```bash
from command line Use following command to run test: 
mvn clean test -Denv=DEMO -DBrowser=firefox -DsuiteXmlFile=src/test/resources/Suites/TestSuite.xml
mvn clean test -Denv=DEMO -DBrowser=chrome -DsuiteXmlFile=src/test/resources/Suites/TestSuite.xml
mvn clean test -Denv=DEMO -DBrowser=headless -DsuiteXmlFile=src/test/resources/Suites/TestSuite.xml

-DsuiteXmlFile=src/test/resources/Suites/TestNGSuite.xml is optional, you have different *.xml file, please use it.

```

## How To Run (Using Java Jar file)
```bash
java -Denv=DEMO -DBrowser=firefox -jar target/demo-ui-qa-1.0-SNAPSHOT-fat-tests.jar src/test/resources/Suites/TestSuite.xml

```

## How To Run (In IDE)
```bash
from IDE: Right click TestNGSuite.xml file under ~/test/resources/Suites folder and pass parameters -Denv=DEMO -DBrowser=firefox
```




## Authors

* **Sharik Latif** - *Initial work* -
# demo-ui-qa
# demo-ui-qa
