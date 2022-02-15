package com.bk.softtechcase.customer.exception;

/**
 * CustomerAlreadyExistException.
 * @since 2022-02-14
 * @author burak kilinc
 */
public class CustomerExistNoBranch extends Exception {
    public CustomerExistNoBranch(String message) {
        super(message);
    }

}
