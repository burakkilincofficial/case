package com.bk.softtechcase.branch.service.impl;

import com.bk.softtechcase.branch.entity.Branch;
import com.bk.softtechcase.branch.exception.BranchNotFoundException;
import com.bk.softtechcase.branch.mapper.BranchMapper;
import com.bk.softtechcase.branch.model.BranchRequest;
import com.bk.softtechcase.branch.model.response.AllBranchesResponse;
import com.bk.softtechcase.branch.model.response.BranchResponse;
import com.bk.softtechcase.branch.model.response.CreateBranchResponse;
import com.bk.softtechcase.branch.model.response.UpdateBranchResponse;
import com.bk.softtechcase.branch.repository.BranchRepository;
import com.bk.softtechcase.branch.service.BranchService;
import com.bk.softtechcase.common.model.OperationType;
import com.bk.softtechcase.common.model.UpdateBranchCustomerPair;
import com.bk.softtechcase.customer.entity.Customer;
import com.bk.softtechcase.customer.exception.CustomerExistNoBranch;
import com.bk.softtechcase.customer.exception.CustomerNotFoundException;
import com.bk.softtechcase.customer.exception.OperationTypeNotValidException;
import com.bk.softtechcase.customer.service.CustomerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * BranchServiceImpl.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final CustomerService customerService;

    public BranchServiceImpl(@Lazy CustomerService customerService, BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.customerService = customerService;
    }

    @Override
    public AllBranchesResponse getAllBranches(String name) {
        List<Branch> allBranches;
        List<BranchResponse> branchResponses = new ArrayList<>();
        if (name != null) {
            allBranches = branchRepository.findByNameContaining(name);
        } else {
            allBranches = branchRepository.findAll();
        }
        if (allBranches != null && !allBranches.isEmpty()) {
            allBranches.forEach(branch -> branchResponses.add(branchMapper.convertBranch2BranchResponse(branch)));
        }
        return AllBranchesResponse.builder()
                .data(branchResponses)
                .branchCount(branchResponses.size())
                .build();
    }

    @Override
    public CreateBranchResponse createBranch(BranchRequest branchRequest) {
        Branch branch = branchMapper.convertBranchRequest2Branch(branchRequest);
        branchRepository.save(branch);
        return branchMapper.convertBranch2CreateBranchResponse(branch);
    }

    @Override
    public Branch findById(Integer id) throws BranchNotFoundException {
        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(String.format("branch not found, id: %s", id)));
    }

    @Override
    public void deleteBranchById(Integer id) throws BranchNotFoundException {
        Branch branch = findById(id);
        branchRepository.delete(branch);
    }

    @Override
    public UpdateBranchResponse updateBranch(BranchRequest branchRequest, Integer id) throws BranchNotFoundException {
        Branch branch = findById(id);
        branch.setName(branchRequest.getName());
        branchRepository.save(branch);
        return branchMapper.convertBranch2UpdateBranchResponse(branch);
    }

    @Override
    public BranchResponse getById(Integer id) throws BranchNotFoundException {
        Branch branch = findById(id);
        return branchMapper.convertBranch2BranchResponse(branch);
    }

    @Override
    public BranchResponse updateCustomer(UpdateBranchCustomerPair updateBranchCustomerPair, OperationType operationType) throws CustomerNotFoundException, BranchNotFoundException, OperationTypeNotValidException, CustomerExistNoBranch {
        Customer customer = customerService.findById(updateBranchCustomerPair.getCustomerId());
        Branch branch = findById(updateBranchCustomerPair.getBranchId());
        Set<Customer> customers = branch.getCustomers();
        if (operationType.equals(OperationType.ADD)) {
            customers.add(customer);
        } else if (operationType.equals(OperationType.REMOVE)) {
            if (customers == null || customers.isEmpty()) {
                throw new CustomerExistNoBranch(String.format("customer with id:%s already does not exists any branch ", updateBranchCustomerPair.getCustomerId()));
            }
            customers.remove(customer);
        } else {
            throw new OperationTypeNotValidException("Operation types must be ADD or REMOVE, invalid branch type");
        }
        branch.setCustomers(customers);
        branchRepository.save(branch);
        return BranchResponse.builder()
                .customers(branch.getCustomers())
                .id(branch.getId())
                .name(branch.getName())
                .lastUpdatedDate(branch.getLastUpdatedDate())
                .customersCount(branch.getCustomers().size())
                .build();
    }
}
