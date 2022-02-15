package com.bk.softtechcase.customer.service.impl;

import com.bk.softtechcase.branch.entity.Branch;
import com.bk.softtechcase.branch.exception.BranchNotFoundException;
import com.bk.softtechcase.branch.service.BranchService;
import com.bk.softtechcase.common.model.OperationType;
import com.bk.softtechcase.common.model.UpdateBranchCustomerPair;
import com.bk.softtechcase.customer.entity.Customer;
import com.bk.softtechcase.customer.exception.OperationTypeNotValidException;
import com.bk.softtechcase.customer.exception.CustomerAlreadyExistException;
import com.bk.softtechcase.customer.exception.CustomerExistNoBranch;
import com.bk.softtechcase.customer.exception.CustomerNotFoundException;
import com.bk.softtechcase.customer.mapper.CustomerMapper;
import com.bk.softtechcase.customer.model.CustomerRequest;
import com.bk.softtechcase.customer.model.response.AllCustomersResponse;
import com.bk.softtechcase.customer.model.response.CreateCustomerResponse;
import com.bk.softtechcase.customer.model.response.CustomerResponse;
import com.bk.softtechcase.customer.model.response.UpdateCustomerResponse;
import com.bk.softtechcase.customer.repository.CustomerRepository;
import com.bk.softtechcase.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CustomerServiceImpl.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BranchService branchService;
    private final CustomerMapper customerMapper;

    @Override
    public AllCustomersResponse getAllCustomers(String fullName) {
        List<Customer> allCustomers;
        List<CustomerResponse> customerResponses = new ArrayList<>();
        if (fullName != null) {
            allCustomers = customerRepository.findByFullNameContaining(fullName);
        } else {
            allCustomers = customerRepository.findAll();
        }
        if (allCustomers != null && !allCustomers.isEmpty()) {
            allCustomers.forEach(customer -> customerResponses.add(customerMapper.convertCustomer2CustomerResponse(customer)));
        }
        return AllCustomersResponse.builder()
                .data(customerResponses)
                .customerCount(customerResponses.size())
                .build();
    }

    @Override
    public CreateCustomerResponse createCustomer(CustomerRequest customerRequest) throws BranchNotFoundException, CustomerAlreadyExistException {
        Branch branch = checkBranchId(customerRequest);
        checkByFullName(customerRequest.getFullName());
        Customer customer = customerMapper.convertCustomerRequest2Customer(customerRequest, branch);
        customerRepository.save(customer);
        return customerMapper.convertCustomer2CreateCustomerResponse(customer);
    }

    private Branch checkBranchId(CustomerRequest customerRequest) throws BranchNotFoundException {
        Branch branch = null;
        if (customerRequest.getBranchId() != null) {
            branch = branchService.findById(customerRequest.getBranchId());
        }
        return branch;
    }

    @Override
    public Customer findById(Integer id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(String.format("customer not found, id: %s", id)));
    }

    @Override
    public void checkByFullName(String fullName) throws CustomerAlreadyExistException {
        boolean isExist = customerRepository.existsByFullName(fullName);
        if (isExist) {
            throw new CustomerAlreadyExistException(String.format("Customer %s already exists", fullName));
        }
    }

    @Override
    public void deleteCustomerById(Integer id) throws CustomerNotFoundException {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }

    @Override
    public UpdateCustomerResponse updateCustomer(CustomerRequest customerRequest, Integer id) throws CustomerNotFoundException, BranchNotFoundException {
        Customer customer = findById(id);
        checkBranchId(customerRequest);
        customer.setFullName(customerRequest.getFullName());
        customerRepository.save(customer);
        return customerMapper.convertCustomer2UpdateCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getById(Integer id) throws CustomerNotFoundException {
        Customer customer = findById(id);
        return customerMapper.convertCustomer2CustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateBranch(UpdateBranchCustomerPair updateBranchCustomerPair, OperationType operationType) throws CustomerNotFoundException, BranchNotFoundException, OperationTypeNotValidException, CustomerExistNoBranch {
        Customer customer = findById(updateBranchCustomerPair.getCustomerId());
        Branch branch = branchService.findById(updateBranchCustomerPair.getBranchId());
        Set<Branch> branches = customer.getBranches();
        if (operationType.equals(OperationType.ADD)) {
            branches.add(branch);
        } else if (operationType.equals(OperationType.REMOVE)) {
            if (branches == null || branches.isEmpty()) {
                throw new CustomerExistNoBranch(String.format("customer with id:%s already does not exists any branch ", updateBranchCustomerPair.getCustomerId()));
            }
            branches.remove(branch);
        } else {
            throw new OperationTypeNotValidException("branch types must be ADD or REMOVE, invalid branch type");
        }
        customer.setBranches(branches);
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .branches(customer.getBranches())
                .id(customer.getId())
                .fullName(customer.getFullName())
                .lastUpdatedDate(customer.getLastUpdatedDate())
                .branchesCount(customer.getBranches().size())
                .build();
    }
}
