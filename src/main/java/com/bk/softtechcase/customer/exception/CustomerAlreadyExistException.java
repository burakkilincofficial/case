package com.bk.softtechcase.customer.exception;

/**
 * CustomerAlreadyExistException.
 * @since 2022-02-14
 * @author burak kilinc
 */
public class CustomerAlreadyExistException extends Exception {
    public CustomerAlreadyExistException(String message) {
        super(message);
    }

}
