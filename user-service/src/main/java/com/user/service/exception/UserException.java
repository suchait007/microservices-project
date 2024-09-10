package com.user.service.exception;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@RequiredArgsConstructor
@Service
public class UserException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorMsg;
    private List<String> errorDetails;

    public UserException(String msg, List<String> errors, HttpStatus httpStatus) {
        super(msg);
        this.errorMsg = msg;
        this.errorDetails = errors;
        this.httpStatus = httpStatus;
    }
}
