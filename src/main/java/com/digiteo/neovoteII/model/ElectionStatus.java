package com.digiteo.neovoteII.model;

import lombok.Getter;

public enum ElectionStatus {

    NOT_INIT("NI"), IN_PROGRESS("IP"), FINISHED("F"), SUSPENDED("S");

    @Getter
    private String code;

    private ElectionStatus(String code){
        this.code = code;
    }

}
