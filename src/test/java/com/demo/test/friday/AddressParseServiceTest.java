package com.demo.test.friday;

import com.demo.base.BaseScript;
import com.demo.controllers.friday.AddressParserServiceTestController;
import com.demo.controllers.friday.dataprovider.AddressParserServiceDataProvider;
import com.demo.testreport.TestCaseId;
import org.testng.annotations.Test;

public class AddressParseServiceTest extends BaseScript {

    AddressParserServiceTestController addressParserServiceTestController = new AddressParserServiceTestController();


    @Test(testName="Address Parser Service",groups = { "Address Parser","Regression" },
            description="Verify the German address parser service.",
            dataProvider = "AddressParserService",dataProviderClass = AddressParserServiceDataProvider.class)
    public void germanAddressParseTest(TestCaseId testCaseId,
                                    String inputData, String expectedResult){

        String actualResult = addressParserServiceTestController.getAddressResultFromApplication(inputData);
        addressParserServiceTestController.compareResult(expectedResult,actualResult);

    }
}
