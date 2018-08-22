package com.demo.utilities;

import com.demo.base.AppConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;



public class RestAssuredUtils extends TestLibrary {

    private static RequestSpecBuilder builder;
    private static RequestSpecification requestSpec;

    private static Response response = null;


    /**
     * Object Make GET Call
     * @param messageToPrint message to Print in the Report
     * @param headerMap key,value maps of headers
     * @param baseUri base Uri of the Service
     * @param urlPath url Path of the Service
     * @param proxy To monitor the Traffic in Fiddler
     * @return Response Object
     */
    public static Response apiGetCall(String messageToPrint,
                                      HashMap<String,String> headerMap,HashMap<String,String> queryMap,
                                      String baseUri, String urlPath,
                                      String proxy){

        RequestSpecification input = buildRequestSpecification(headerMap,queryMap,
                baseUri, urlPath, null);

        if (input != null) {
            try {
                if(StringUtils.isNotEmpty(proxy)) {

                    response = given()
                            .proxy("localhost",AppConstants.fiddlerPort)
                            .spec(input)
                            .when()
                            .get();
                }else{
                    response = given()
                            .spec(input)
                            .when()
                            .get();
                    logPass("GET CALL Success for Service URL :"+input.get());
                }
            } catch (Exception e) {
                e.getMessage();
                logerror("Exception in GET CALL with exception for Service URL :"+input.get()+"     Exception: " + e.getMessage());
            }

        } else {
            logerror(" RequestSpecification Object is null for API GET CALL");
        }
        return response;

    }

    /**
     * Object Make POST Call
     * @param messageToPrint message to Print in the Report
     * @param headerMap key,value maps of headers
     * @param baseUri base Uri of the Service
     * @param urlPath url Path of the Service
     * @param inputReqStr input request content as json/xml
     * @param proxy To monitor the Traffic in Fiddler
     * @return Response Object
     */
    public static Response apiPostCall(String messageToPrint,
                                       HashMap<String,String> headerMap,HashMap<String,String> queryMap,
                                       String baseUri, String urlPath, String inputReqStr,
                                       String proxy){

        RequestSpecification input = buildRequestSpecification(headerMap,queryMap,
                baseUri, urlPath, inputReqStr);

        if (input != null) {
            try {
                if(StringUtils.isNotEmpty(proxy)) {

                    response = given()
                            .proxy("localhost",AppConstants.fiddlerPort)
                            .spec(input)
                            .when()
                            .post();
                }else{
                    response = given()
                            .spec(input)
                            .when()
                            .post();
                    logPass("POST CALL Success for Service URL :"+input.get());
                }
            } catch (Exception e) {
                e.getMessage();
                logerror("Exception in POST CALL with exception for Service URL :"+input.get()+"     Exception: " + e.getMessage());
            }

        } else {
            logerror(" RequestSpecification Object is null for API POST CALL");
        }
        return response;

    }

    /**
     * Object Make PUT Call
     * @param messageToPrint message to Print in the Report
     * @param headerMap key,value maps of headers
     * @param baseUri base Uri of the Service
     * @param urlPath url Path of the Service
     * @param inputReqStr input request content as json/xml
     * @param proxy To monitor the Traffic in Fiddler
     * @return Response Object
     */
    public static Response apiPutCall(String messageToPrint,
                                      HashMap<String,String> headerMap,HashMap<String,String> queryMap,
                                      String baseUri, String urlPath, String inputReqStr,
                                      String proxy){

        RequestSpecification input = buildRequestSpecification(headerMap,queryMap,
                baseUri, urlPath, inputReqStr);

        if (input != null) {
            try {
                if(StringUtils.isNotEmpty(proxy)) {

                    response = given()
                            .proxy("localhost",AppConstants.fiddlerPort)
                            .spec(input)
                            .when()
                            .put();
                }else{
                    response = given()
                            .spec(input)
                            .when()
                            .put();
                    logPass("PUT CALL Success for Service URL :"+input.get());
                }
            } catch (Exception e) {
                e.getMessage();
                logerror("Exception in PUT CALL with exception for Service URL :"+input.get()+"     Exception: " + e.getMessage());
            }

        } else {
            logerror(" RequestSpecification Object is null for API PUT CALL");
        }
        return response;

    }

    /**
     * Object Make PATCH Call
     * @param messageToPrint message to Print in the Report
     * @param headerMap key,value maps of headers
     * @param baseUri base Uri of the Service
     * @param urlPath url Path of the Service
     * @param inputReqStr input request content as json/xml
     * @param proxy To monitor the Traffic in Fiddler
     * @return Response Object
     */
    public static Response apiPatchCall(String messageToPrint,
                                        HashMap<String,String> headerMap,HashMap<String,String> queryMap,
                                        String baseUri, String urlPath, String inputReqStr,
                                        String proxy){

        RequestSpecification input = buildRequestSpecification(headerMap,queryMap,
                baseUri, urlPath, inputReqStr);

        if (input != null) {
            try {
                if(StringUtils.isNotEmpty(proxy)) {

                    response = given()
                            .proxy("localhost",AppConstants.fiddlerPort)
                            .spec(input)
                            .when()
                            .patch();
                }else{
                    response = given()
                            .spec(input)
                            .when()
                            .patch();
                    logPass("PATCH CALL Success for Service URL :"+input.get());
                }
            } catch (Exception e) {
                e.getMessage();
                logerror("Exception in PATCH CALL with exception for Service URL :"+input.get()+"     Exception: " + e.getMessage());
            }

        } else {
            logerror(" RequestSpecification Object is null for API PATCH CALL");
        }
        return response;

    }

    /**
     * Object Make DELETE Call
     * @param messageToPrint message to Print in the Report
     * @param headerMap key,value maps of headers
     * @param baseUri base Uri of the Service
     * @param urlPath url Path of the Service
     * @param proxy To monitor the Traffic in Fiddler
     * @return Response Object
     */
    public static Response apiDeleteCall(String messageToPrint,
                                         HashMap<String,String> headerMap,HashMap<String,String> queryMap,
                                         String baseUri, String urlPath,
                                         String proxy){

        RequestSpecification input = buildRequestSpecification(headerMap,queryMap,
                baseUri, urlPath, null);

        if (input != null) {
            try {
                if(StringUtils.isNotEmpty(proxy)) {

                    response = given()
                            .proxy("localhost",AppConstants.fiddlerPort)
                            .spec(input)
                            .when()
                            .delete();
                }else{
                    response = given()
                            .spec(input)
                            .when()
                            .delete();
                    logPass("DELETE CALL Success for Service URL :"+input.get());
                }
            } catch (Exception e) {
                e.getMessage();
                logerror("Exception in DELETE CALL with exception for Service URL :"+input.get()+"     Exception: " + e.getMessage());
            }

        } else {
            logerror(" RequestSpecification Object is null for API DELETE CALL");
        }
        return response;

    }


    /**
     * Objective - Get Response Content from Reponse Object
     * @param res - Reponse Object
     * @return - Reponse Content String
     */
    public String getResponseContent(Response res){

        try {
            return res.getBody().asString();
        }catch(Exception e){
            logerror(" Exception while retrieving Response Content String. Exception : "+e.getMessage());
            return null;
        }
    }


    /**
     * Objective - Get Response Header value from Response
     * @param res - Reponse Object
     * @param headerName - header key to be retrieved from the Response
     * @return - Response Content String
     */
    public String getReponseHeader(Response res,String headerName){

        try {
            return res.getHeader(headerName);
        }catch(Exception e){
            logerror(" Exception while retrieving Response Header for the Header:"+headerName+". Exception : "+e.getMessage());
            return null;
        }
    }


    /**
     * Objective - Create Request Specification Object for Input of any Rest Assured Http Requests
     * @param headerMap key,value maps of headers
     * @param baseUri base Uri of the Service
     * @param urlPath url Path of the Service
     * @param inputReqStr input request content as json/xml
     * @return RequestSpecification
     */
    public static RequestSpecification buildRequestSpecification(HashMap<String,String> headerMap,HashMap<String,String> queryMap,
                                                             String baseUri, String urlPath, String inputReqStr){

        RequestSpecBuilder builder = new RequestSpecBuilder();

        if(!headerMap.isEmpty()){

            for(String headerKey : headerMap.keySet()){
                builder.addHeader(headerKey,headerMap.get(headerKey));
            }

        }

        if(!queryMap.isEmpty()) {

            for (String queryParmKey : queryMap.keySet()) {
                builder.addQueryParam(queryParmKey, queryMap.get(queryParmKey));
            }
        }

        builder.setBaseUri(baseUri);
        builder.setBasePath(urlPath);
        builder.setBody(inputReqStr);

        return builder.build();

    }


    /**
     * Objective - Verify HttpStatusCode in Response Object
     * @param response Response Object
     * @param expectedStatusCode status Code to compare with Actual Status Code
     * @return boolean
     */
    public static Boolean verifyHttpStatusCodeInResponse(Response response,Integer expectedStatusCode){

        if(response != null){
            if(response.getStatusCode() == expectedStatusCode){
                logPass("Status Code is Matching");
                return true;
            }else{
                logFail("Status Code is not Matching. Exepected:"+expectedStatusCode+", ActualStatusCode:"+response.getStatusCode());
                return false;
            }
        }else{
            logFail("Response Object is null");
            return false;
        }

    }


    /**
     * Objective - Get HttpStatusCode in Response Object
     * @param response Response Object
     * @return integer of status code
     */
    public static Integer getHttpStatusCode(Response response){

        if(response != null){

                return response.getStatusCode();

        }else{
            logFail("Response Object is null");
            return null;
        }

    }




}
