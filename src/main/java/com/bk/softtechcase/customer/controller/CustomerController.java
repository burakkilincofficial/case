package com.bk.softtechcase.customer.controller;

import com.bk.softtechcase.branch.exception.BranchNotFoundException;
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
import com.bk.softtechcase.customer.service.CustomerService;
import com.bk.softtechcase.customer.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for customers.
 * @since 2022-02-14
 * @author burak kilinc
 */
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("")
    public AllCustomersResponse getAllCustomers(@RequestParam(required = false) String fullName) {
        return customerService.getAllCustomers(fullName);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable("id") Integer id) throws CustomerNotFoundException {
        return customerService.getById(id);
    }

    @PostMapping("")
    public CreateCustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) throws BranchNotFoundException, CustomerAlreadyExistException {
        return customerService.createCustomer(customerRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) throws CustomerNotFoundException {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UpdateCustomerResponse updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerRequest updateCustomerRequest) throws CustomerNotFoundException, BranchNotFoundException {
        return customerService.updateCustomer(updateCustomerRequest, id);
    }

    @PutMapping("")
    public CustomerResponse updateBranch(@RequestBody UpdateBranchCustomerPair updateBranchCustomerPair,
                                         @RequestParam() OperationType operationType) throws BranchNotFoundException, CustomerNotFoundException, OperationTypeNotValidException, CustomerExistNoBranch {
        return customerService.updateBranch(updateBranchCustomerPair, operationType);
    }
}
