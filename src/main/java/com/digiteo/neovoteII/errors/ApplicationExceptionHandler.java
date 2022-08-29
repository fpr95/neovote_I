package com.digiteo.neovoteII.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.*;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, MethodArgumentNotValidException.class})
    public final ResponseEntity<ApiException> handleException(Exception ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        if(ex instanceof UserNotFoundException){
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException)ex;

            return handleUserNotFoundException(unfe, headers, status, request);
        } else if(ex instanceof MethodArgumentNotValidException){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;

            return handleMethodArgumentNotValidException(manve, headers, status, request);
        }
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleInternalException(ex, null, headers, status, request);
    }

    protected ResponseEntity<ApiException> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                 HttpHeaders headers,
                                                                                 HttpStatus status,
                                                                                 WebRequest request){
        /**
         Collections#emptyList() and #emptyMap() creates inmutable instances of each so the add/put methods will NOT work
         Search for solution in Spring's DI or modify this methods logic [28/07/22 23:35]
          */
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        //return new ResponseEntity<>(errorMap, status);
        return handleInternalException(ex, new ApiException(errors, errorMap), headers, status, request);
    }

    protected ResponseEntity<ApiException> handleUserNotFoundException(UserNotFoundException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatus status,
                                                                 WebRequest request){
        List<String> errors = Collections.emptyList();
        errors.add(ex.getMessage());
        return handleInternalException(ex, new ApiException(errors), headers, status, request);
    }

    //to custom the body for all exception types
    protected ResponseEntity<ApiException> handleInternalException(Exception ex,ApiException body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request){
        if(status.equals(HttpStatus.INTERNAL_SERVER_ERROR)){
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }

    /** Include a 'noHandlerFoundException' */
}
