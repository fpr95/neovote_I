package com.digiteo.neovoteII.errors;

// Custom exception for handle response in VoterController#getVoterGetDTOs endpoint
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String msg){
        super(msg);
    }
}
