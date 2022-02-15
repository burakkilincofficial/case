package com.bk.softtechcase.customer.service;

import com.bk.softtechcase.branch.exception.BranchNotFoundException;
import com.bk.softtechcase.customer.entity.Customer;
import com.bk.softtechcase.customer.exception.OperationTypeNotValidException;
import com.bk.softtechcase.customer.exception.CustomerAlreadyExistException;
import com.bk.softtechcase.customer.exception.CustomerExistNoBranch;
import com.bk.softtechcase.customer.exception.CustomerNotFoundException;
import com.bk.softtechcase.customer.model.CustomerRequest;
import com.bk.softtechcase.common.model.OperationType;
import com.bk.softtechcase.common.model.UpdateBranchCustomerPair;
import com.bk.softtechcase.customer.model.response.AllCustomersResponse;
import com.bk.softtechcase.customer.model.response.CreateCustomerResponse;
import com.bk.softtechcase.customer.model.response.CustomerResponse;
import com.bk.softtechcase.customer.model.response.UpdateCustomerResponse;

/**
 * CustomerService.
 * @since 2022-02-14
 * @author burak kilinc
 */
public interface CustomerService {
    AllCustomersResponse getAllCustomers(String fullName);

    CreateCustomerResponse createCustomer(CustomerRequest customerRequest) throws BranchNotFoundException, CustomerAlreadyExistException;

    Customer findById(Integer id) throws CustomerNotFoundException;

    void checkByFullName(String fullName) throws CustomerAlreadyExistException;

    void deleteCustomerById(Integer integer) throws CustomerNotFoundException;

    UpdateCustomerResponse updateCustomer(CustomerRequest customerRequest, Integer id) throws CustomerNotFoundException, BranchNotFoundException;

    CustomerResponse getById(Integer id) throws CustomerNotFoundException;

    CustomerResponse updateBranch(UpdateBranchCustomerPair updateBranchCustomerPair, OperationType operationType) throws CustomerNotFoundException, BranchNotFoundException, OperationTypeNotValidException, CustomerExistNoBranch;
}

