package com.bk.softtechcase.common.exception.handler;

import com.bk.softtechcase.branch.exception.BranchNotFoundException;
import com.bk.softtechcase.common.exception.ErrorDetails;
import com.bk.softtechcase.customer.exception.OperationTypeNotValidException;
import com.bk.softtechcase.customer.exception.CustomerAlreadyExistException;
import com.bk.softtechcase.customer.exception.CustomerExistNoBranch;
import com.bk.softtechcase.customer.exception.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<?> handleTodoNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<?> handleUserNameAlreadyExistException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerExistNoBranch.class)
    public ResponseEntity<?> handleCustomerExistNoBranch(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OperationTypeNotValidException.class)
    public ResponseEntity<?> handleBranchTypeNotValidException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}