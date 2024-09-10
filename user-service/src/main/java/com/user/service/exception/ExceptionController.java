package com.user.service.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserError> handleException(UserException userException) {

        UserError userError = new UserError(userException.getMessage(), userException.getErrorDetails());
        return ResponseEntity.status(userException.getHttpStatus().value()).body(userError);
    }

}
