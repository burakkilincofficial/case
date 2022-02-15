package com.bk.softtechcase.branch.service;

import com.bk.softtechcase.branch.entity.Branch;
import com.bk.softtechcase.branch.exception.BranchNotFoundException;
import com.bk.softtechcase.branch.model.BranchRequest;
import com.bk.softtechcase.branch.model.response.AllBranchesResponse;
import com.bk.softtechcase.branch.model.response.BranchResponse;
import com.bk.softtechcase.branch.model.response.CreateBranchResponse;
import com.bk.softtechcase.branch.model.response.UpdateBranchResponse;
import com.bk.softtechcase.common.model.OperationType;
import com.bk.softtechcase.common.model.UpdateBranchCustomerPair;
import com.bk.softtechcase.customer.exception.OperationTypeNotValidException;
import com.bk.softtechcase.customer.exception.CustomerExistNoBranch;
import com.bk.softtechcase.customer.exception.CustomerNotFoundException;

/**
 * BranchService.
 * @since 2022-02-14
 * @author burak kilinc
 */
public interface BranchService {
    AllBranchesResponse getAllBranches(String name);

    CreateBranchResponse createBranch(BranchRequest branchRequest);

    Branch findById(Integer id) throws BranchNotFoundException;

    void deleteBranchById(Integer id) throws BranchNotFoundException;

    UpdateBranchResponse updateBranch(BranchRequest branchRequest, Integer id) throws BranchNotFoundException;

    BranchResponse getById(Integer id) throws BranchNotFoundException;

    BranchResponse updateCustomer(UpdateBranchCustomerPair updateBranchCustomerPair, OperationType operationType) throws CustomerNotFoundException, BranchNotFoundException, OperationTypeNotValidException, CustomerExistNoBranch;

}
