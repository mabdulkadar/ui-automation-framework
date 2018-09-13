package com.demo.utilities;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class JacksonConversion {

    static ObjectMapper mapper = null;

    public JacksonConversion() {

        if(mapper == null) {
            this.mapper = new ObjectMapper();
            this.mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
            this.mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public static HashMap<String, String> convertStringInToMap(String strValue ){


        HashMap<String, String> map =null;

        //Convert String to HashMap
        try {
            map = new HashMap<String, String>();
            map = mapper.readValue(strValue, new TypeReference<HashMap<String, String>>(){});

        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return map;

    }

}
