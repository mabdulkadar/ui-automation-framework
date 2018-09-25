package com.demo.controllers.friday.dataprovider;

import com.demo.base.GlobalConstants;
import com.demo.utilities.ExcelUtility;
import org.testng.annotations.DataProvider;

public class AddressParserServiceDataProvider {

    private static String testCaseLocation= GlobalConstants.externalTestDataLocationStr+"/friday/";
    private static String germanAddressTestCaseData="GermanAddressTestData.xlsx";

    @DataProvider(name = "AddressParserService")
    public static Object[][] addressParserService() throws Exception{

        return ExcelUtility.getDataAsArrayWithTestCaseId(testCaseLocation+germanAddressTestCaseData,"AddressParserService");

    }
}
