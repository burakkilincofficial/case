package com.bk.softtechcase.branch.exception;

/**
 * BranchNotFound exception.
 * @since 2022-02-14
 * @author burak kilinc
 */
public class BranchNotFoundException extends Exception {
    public BranchNotFoundException(String message) {
        super(message);
    }
}
