package com.digiteo.neovoteII.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiException {

    private List<String> message;
    private Map<String,String> errorMap;
    private final OffsetDateTime timestamp = OffsetDateTime.now();

    public ApiException (List<String> message){
        this.message = message;
    }
}
