package com.demo.controllers.friday;

import com.demo.application.AddressParseService;
import com.demo.utilities.TestLibrary;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.skyscreamer.jsonassert.JSONAssert;

public class AddressParserServiceTestController extends TestLibrary {

    AddressParseService addressParseServiceAppUnderTest = new AddressParseService();

    public  String getAddressResultFromApplication(String inputAddressStr)  {


        try {
            logmsg("Get Json Format Address String from the Application.");
            return addressParseServiceAppUnderTest.parseGermanAddressStringToJSONString(inputAddressStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logerror("Exception from the application. Exception : "+e.getMessage());
            return e.getMessage();
        }

    }

    public void compareResult(String expectedJsonStr,String actualJsonStr){

        try {
            JSONAssert.assertEquals(expectedJsonStr, actualJsonStr, true);
            logPass("Expected and Actual Result matching.");
        }catch(Exception je){
            je.printStackTrace();
            logmsg("Expected Result : "+expectedJsonStr);
            logmsg("Actual Result : "+actualJsonStr);
            logerror("Expected Result Not Matched. Exception : "+je.getMessage());

        }
    }


}
