package com.digiteo.neovoteII.model;


import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderValues {

    MALE("H"), FEMALE("M"), CUSTOM("C"), NOT_SPECIFIED("NE");

    private String code;

    private GenderValues (String code) { this.code = code; }

    @JsonValue()
    public String getCode(){ return code; }

}
