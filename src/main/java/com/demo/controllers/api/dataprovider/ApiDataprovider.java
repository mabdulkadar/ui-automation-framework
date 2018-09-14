package com.demo.controllers.api.dataprovider;

import com.demo.base.GlobalConstants;
import com.demo.utilities.ExcelUtility;
import org.testng.annotations.DataProvider;

public class ApiDataprovider {

    private static String testCaseLocation= GlobalConstants.externalTestDataLocationStr+"/api/";

    private static String accountTestDataFileName="ApiTestData.xlsx";

    @DataProvider(name = "AllCountriesTestData")
    public static Object[][] getallCountriesTestData() throws Exception{

        return ExcelUtility.getDataAsArrayWithTestCaseId(testCaseLocation+accountTestDataFileName,"AllCountriesTestData");

    }

    @DataProvider(name = "CountryIdTestData")
    public static Object[][] getCountryIdTestData() throws Exception{

        return ExcelUtility.getDataAsArrayWithTestCaseId(testCaseLocation+accountTestDataFileName,"CountryIdTestData");

    }

    @DataProvider(name = "NewCountryTestData")
    public static Object[][] getcreateNewCountryTestData() throws Exception{

        return ExcelUtility.getDataAsArrayWithTestCaseId(testCaseLocation+accountTestDataFileName,"NewCountryTestData");

    }

    @DataProvider(name = "NewCountryTestDataNegative")
    public static Object[][] getcreateNewCountryNegativeTestData() throws Exception{

        return ExcelUtility.getDataAsArrayWithTestCaseId(testCaseLocation+accountTestDataFileName,"NewCountryTestDataNegative");

    }
}
