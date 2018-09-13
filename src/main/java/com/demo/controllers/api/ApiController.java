package com.demo.controllers.api;

import com.demo.controllers.api.inputTemplate.ApiInputTemplate;
import com.demo.utilities.Helper;
import com.demo.utilities.TestLibrary;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;


import static com.demo.utilities.RestAssuredUtils.*;

public class ApiController extends TestLibrary {

    private Response responseObj = null;
    public String actualApiResponse = null;
    public String expectedResult = null;
    public String alpha2_code = null;

    /**
     * Make POST Call
     * @param messageToPrint String
     * @param baseUri service uri
     * @param urlPath service url path
     * @param inputDataStr String value as input data from external file
     * @param expectedStatusCode http status code
     */
    public void createNewCountryPostCall(String messageToPrint,String baseUri,String urlPath,String inputDataStr,String expectedStatusCode){

        logmsg("Test Case : "+messageToPrint);

        logmsg("Prepare input request for Post call to create new country.");
        String inputRequestStrJson = prepareInputRequest(inputDataStr);

        //Expected Result should be same as inputRequest for new Country
        logmsg("Store expected Post Api Response in expectedResult Variable for comparison.");
        expectedResult = new String(inputRequestStrJson);

        logmsg("Trigger Post call.");
        responseObj = apiPostCall("POST call for "+baseUri+"/"+urlPath,
                null,null,baseUri,urlPath,inputRequestStrJson,null);

        logmsg("Verify Post Response Http Status Code.");
        verifyHttpStatusCodeInResponse(responseObj,expectedStatusCode);

        logmsg("Store Post Call Api Response content in actualApiResponse variable for comparison.");
        actualApiResponse = getResponseContent(responseObj);

    }

    /**
     * Make GET Call
     * @param messageToPrint String
     * @param baseUri service uri
     * @param urlPath service url path
     * @param expectedStatusCode http status code
     */
    public void getCall(String messageToPrint,String baseUri,String urlPath,String expectedStatusCode){

        logmsg("Test Case : "+messageToPrint);

        responseObj = apiGetCall("GET call for "+baseUri+"/"+urlPath,
                null,null,baseUri,urlPath,null);

        verifyHttpStatusCodeInResponse(responseObj,expectedStatusCode);

    }

    /**
     * Compare Array Records in JSON Object
     * @param expectedResult String expected JSON Object
     */
    public void compareGetArrayResponse(String expectedResult){

        List<JSONObject> actualResults = responseObj.jsonPath().getList("RestResponse.result");

        List<Object> expectedResults = new JSONArray(expectedResult).toList();


        for(Object country : expectedResults){

            if(actualResults.contains((HashMap<String,String>)country)){
                logPass("Country available == >"+((HashMap<String,String>)country).toString());
            }else{
                logFail("Country not available == >"+((HashMap<String,String>)country).toString());
            }

        }



    }


    /**
     * Compare Single JSON Object Response
     * @param expectedResult String expected JSON Object
     */
    public void compareGetSingleResponse(String expectedResult){

        JSONObject expectedJsonObj = null;
        JSONObject actualJsonObj = null;

        try{

            expectedJsonObj = new JSONObject(expectedResult);
            actualJsonObj = new JSONObject(getResponseContent(responseObj));


            //Check Result value
            try {
                JSONAssert.assertEquals(expectedJsonObj.getJSONObject("result"),
                        actualJsonObj.getJSONObject("RestResponse").getJSONObject("result"),
                        true);
                logPass("Result values is matching");
            }catch(Exception je){
                logerror("Result values is not matching. Mismatch : "+je.getMessage());
            }

            //Check Messages value
            try {
                Assert.assertEquals(expectedJsonObj.getString("message"),
                        actualJsonObj.getJSONObject("RestResponse").getJSONArray("messages").getString(0));
                logPass("Message values is matching");
            }catch(Exception je){
                logerror("Result values is not matching. Mismatch : "+je.getMessage());
            }



        }catch(Exception e){
            logerror("Exception comparing result  Exception: "+e.getMessage());
        }


    }

    /**
     * Compare result json object in all countries get call
     * @param expectedResult String expected JSON value
     * @param actualResult String actual JSON value
     */
    public void compareApiResponseResultObject(String expectedResult,String actualResult){

        JSONObject jsonActualResObj = null;

        try{

            jsonActualResObj = new JSONObject(actualResult);
            compareJsonStrings(expectedResult,jsonActualResObj.getJSONObject("result").toString());

        }catch(Exception e){
            e.printStackTrace();
            logerror("Exception in processing actual api response. Exception : "+e.getMessage());
        }
    }

    /**
     * Compare json String values
     * @param expectedResult String expected JSON value
     * @param actualResult String actual JSON value
     */
    public void compareJsonStrings(String expectedResult,String actualResult){


            //Check Result value
            try {
                JSONAssert.assertEquals(expectedResult,
                        actualResult,
                        true);
                logPass("Expected and Actual values are matching.");
            }catch(Exception je){
                logerror("Expected and Actual values are not matching. Exception : "+je.getMessage());
            }



    }


    /**
     * Objective - Prepare the input request passed from input data from external file
     * @param inputData inputdata string from external file it should be like key1:value1;key2:value2
     * @return
     */
    private String prepareInputRequest(String inputData){

        HashMap<String,String> inputMap = null;

        try{

            inputMap = Helper.convertStringToMap(inputData);

            //Expected alpha2_code to be created
            alpha2_code = inputMap.get("alpha2_code");

            return ApiInputTemplate.getTemplate(inputMap.get("inputTemplateName"),inputMap);

        }catch(Exception e){
            e.printStackTrace();
            logerror("Exception in preparing input request based on input data. Exception : "+e.getMessage());
            return null;
        }


    }
}
