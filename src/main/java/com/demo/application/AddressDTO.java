package com.demo.application;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"street",  "housenumber"})
public class AddressDTO {

    private String street = null;
    private String housenumber = null;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getStreet() {
        return street;
    }

    public String getHousenumber() {
        return housenumber;
    }
}
