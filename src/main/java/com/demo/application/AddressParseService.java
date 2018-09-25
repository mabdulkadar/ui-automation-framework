package com.demo.application;

import com.demo.utilities.JacksonConversion;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressParseService {

    private static final Pattern PATTERN_1 = Pattern.compile("^\\d+(\\.\\d+)?");
    private static final Pattern  PATTERN_2 = Pattern.compile("([\\d]*) ([a-zäöüßA-Z\\d]*$)");

    /**
     * Objective - Method to parse German Address
     * @param inputAddressStr String
     * @return jsonString
     * @throws JsonProcessingException exception
     */
    public String parseGermanAddressStringToJSONString(String inputAddressStr) throws JsonProcessingException {

        return getAddresses(PATTERN_1,inputAddressStr);
    }


    /**
     * Objective - method parse the address
     * @param pattern regex Pattern Object
     * @param inputAddressStr input address String
     * @return jsonStrin
     * @throws JsonProcessingException exception
     */
    private String getAddresses(Pattern pattern, String inputAddressStr) throws JsonProcessingException {

        JacksonConversion objectConversion = new JacksonConversion();
        Matcher matcher;
        String houseNoStr = null;
        String streetStr = null;
        String resultStr = null;

        AddressDTO addressDTO = null;
        boolean isMatched = false;

        matcher = pattern.matcher(inputAddressStr);

        if (matcher.find()) {
            isMatched = true;
        } else {
            return getAddresses(PATTERN_2, inputAddressStr);
        }

        if (isMatched) {
            addressDTO = new AddressDTO();
            houseNoStr = matcher.group(0).trim();
            streetStr = matcher.replaceAll("").replace(",","").trim();
            addressDTO.setHousenumber(houseNoStr);
            addressDTO.setStreet(streetStr);
            resultStr = objectConversion.convertObjectToString(addressDTO);
        }
        return resultStr;
    }


}
