package com.demo.test.api;

import com.demo.base.BaseScript;
import com.demo.base.GlobalConstants;
import com.demo.controllers.api.ApiController;
import com.demo.controllers.api.dataprovider.ApiDataprovider;
import com.demo.testreport.TestCaseId;
import org.testng.annotations.Test;

public class ApiGetTest extends BaseScript {

    private ApiController apiController = new ApiController();

    @Test(testName="Get Country",groups = { "Country","Regression" },
            description="Get all Countries and validate it returns DE,US,GB",
            dataProvider = "AllCountriesTestData",dataProviderClass = ApiDataprovider.class)
    public void getAllCountriesTest(TestCaseId testCaseId,String TestCaseDescription,
                                    String expectedStatusCode,String expectedResult){

        logmsg("Make GET All Countries call.");
        apiController.getCall(TestCaseDescription, GlobalConstants.apiBaseUri,
                "country/get/all",expectedStatusCode);

        logmsg("Verify expected Countries are in the GET All Countries Response.");
        apiController.compareGetArrayResponse(expectedResult);

    }

    @Test(testName="Get Country",groups = { "Country","Regression" },
            description="Get individual Country by Get call.",
    dataProvider = "CountryIdTestData",dataProviderClass = ApiDataprovider.class)
    public void getCountryByIdTest(TestCaseId testCasdId,String TestCaseDescription,
                                   String countryId, String expectedStatusCode, String expectedResult){

        logmsg("Make GET country call.");
        apiController.getCall(TestCaseDescription,
                GlobalConstants.apiBaseUri,"/country/get/iso2code/"+countryId,expectedStatusCode);

        logmsg("Verify GET country response data.");
        apiController.compareGetSingleResponse(expectedResult);


    }

    @Test(testName="Get Country",groups = { "Country","Regression" },
            description="Create New Country using Post call.",
            dataProvider = "NewCountryTestData",dataProviderClass = ApiDataprovider.class)
    public void createNewCountryTest(TestCaseId testCasdId,String TestCaseDescription,
                                   String inputData,String expectedStatusCode){

        apiController.createNewCountryPostCall(TestCaseDescription,
                GlobalConstants.apiBaseUri,"/country",inputData,expectedStatusCode);

        logmsg("Compare the Post call Api Response.");
        apiController.compareJsonStrings(apiController.expectedResult,apiController.actualApiResponse);

        logmsg("Make /country/get/iso2code/<alpla_cod_ value> to check data persist in database.");
        apiController.getCall(TestCaseDescription,
                GlobalConstants.apiBaseUri,"/country/get/iso2code/"+apiController.alpha2_code,"200");

        logmsg("Compare the result of Get call with expectedResult prepared in Post call.");
        apiController.compareApiResponseResultObject(apiController.expectedResult,apiController.expectedResult);


    }

    @Test(testName="Get Country",groups = { "Country","Regression" },
            description="Create New Country using Post call.",
            dataProvider = "NewCountryTestDataNegative",dataProviderClass = ApiDataprovider.class)
    public void createNewCountryNegativeTest(TestCaseId testCasdId,String TestCaseDescription,
                                     String inputData,String expectedStatusCode){

        apiController.createNewCountryPostCall(TestCaseDescription,
                GlobalConstants.apiBaseUri,"/country",inputData,expectedStatusCode);

        logmsg("Compare the Post call Api Response as empty.");
        apiController.compareJsonStrings("{}",apiController.actualApiResponse);

        logmsg("Make /country/get/iso2code/<alpla_cod_ value> to check data not persist in database.");
        apiController.getCall(TestCaseDescription,
                GlobalConstants.apiBaseUri,"/country/get/iso2code/"+apiController.alpha2_code,"200");

        logmsg("Compare the result of Get call Response as empty for the country not created.");
        apiController.compareApiResponseResultObject("{}",apiController.expectedResult);


    }
}
