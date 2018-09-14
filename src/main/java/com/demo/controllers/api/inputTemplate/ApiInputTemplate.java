package com.demo.controllers.api.inputTemplate;

import com.demo.utilities.Helper;

import java.util.HashMap;

public class ApiInputTemplate {

    private static String POST_REQ_01="{\n" +
            "  \"name\": \"{{name}}\",\n" +
            "  \"alpha2_code\": \"{{alpha2_code}}\",\n" +
            "  \"alpha3_code\": \"{{alpha3_code}}\"\n" +
            "}";

    private static String POST_REQ_WITHOUTNAMEFIELD="{\n" +
            "  \"alpha2_code\": \"{{alpha2_code}}\",\n" +
            "  \"alpha3_code\": \"{{alpha3_code}}\"\n" +
            "}";

    private static String POST_REQ_WITHOUTALPHA2CODE="{\n" +
            "  \"name\": \"{{name}}\",\n" +
            "  \"alpha3_code\": \"{{alpha3_code}}\"\n" +
            "}";

    private static String POST_REQ_WITHOUTALPHA3CODE="{\n" +
            "  \"name\": \"{{name}}\",\n" +
            "  \"alpha2_code\": \"{{alpha2_code}}\"\n" +
            "}";

    private static HashMap<String, String> map = null;
    public static void initializer(){
        if(map == null){
            map = new HashMap<String, String>();
            map.put("POST_REQ_01", POST_REQ_01);
            map.put("POST_REQ_WITHOUTNAMEFIELD", POST_REQ_WITHOUTNAMEFIELD);
            map.put("POST_REQ_WITHOUTALPHA2CODE", POST_REQ_WITHOUTALPHA2CODE);
            map.put("POST_REQ_WITHOUTALPHA3CODE", POST_REQ_WITHOUTALPHA3CODE);

        }

    }

    public static  String getTemplate(String key,HashMap<String,String> inputMap){
        return Helper.replaceMacros(map.get(key),inputMap);
    }
}
