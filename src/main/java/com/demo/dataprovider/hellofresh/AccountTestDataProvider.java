package com.demo.dataprovider.hellofresh;

import com.demo.base.GlobalConstants;
import com.demo.utilities.ExcelUtility;
import org.testng.annotations.DataProvider;

public class AccountTestDataProvider {

    private static String testCaseLocation= GlobalConstants.externalTestDataLocationStr+"/hellofresh/";

    private static String accountTestDataFileName="AccountTestData.xlsx";

    @DataProvider(name = "Sign In Test")
    public static Object[][] getSignInTestData() throws Exception{

        return ExcelUtility.getDataAsArray(testCaseLocation+accountTestDataFileName,"SignInTestData");

    }

    @DataProvider(name = "Log In Test")
    public static Object[][] getLogInTestData() throws Exception{

        return ExcelUtility.getDataAsArray(testCaseLocation+accountTestDataFileName,"LogInTestData");

    }

    @DataProvider(name = "Check Out Test")
    public static Object[][] getCheckOutTestData() throws Exception{

        return ExcelUtility.getDataAsArrayWithTestCaseId(testCaseLocation+accountTestDataFileName,"CheckOutTestData");

    }
}
