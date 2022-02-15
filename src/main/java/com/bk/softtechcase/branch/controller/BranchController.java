package com.bk.softtechcase.branch.controller;

import com.bk.softtechcase.branch.exception.BranchNotFoundException;
import com.bk.softtechcase.branch.model.BranchRequest;
import com.bk.softtechcase.branch.model.response.AllBranchesResponse;
import com.bk.softtechcase.branch.model.response.BranchResponse;
import com.bk.softtechcase.branch.model.response.CreateBranchResponse;
import com.bk.softtechcase.branch.model.response.UpdateBranchResponse;
import com.bk.softtechcase.branch.service.BranchService;
import com.bk.softtechcase.common.model.OperationType;
import com.bk.softtechcase.common.model.UpdateBranchCustomerPair;
import com.bk.softtechcase.customer.exception.OperationTypeNotValidException;
import com.bk.softtechcase.customer.exception.CustomerExistNoBranch;
import com.bk.softtechcase.customer.exception.CustomerNotFoundException;
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
 * Controller for Branch business.
 * @since 2022-02-14
 * @author burak kilinc
 */
@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("")
    public AllBranchesResponse getAllBranches(@RequestParam(required = false) String name) {
        return branchService.getAllBranches(name);
    }

    @GetMapping("/{id}")
    public BranchResponse getBranchById(@PathVariable() Integer id) throws BranchNotFoundException {
        return branchService.getById(id);
    }

    @PostMapping("")
    public CreateBranchResponse createBranch(@RequestBody BranchRequest createBranchRequest) {
        return branchService.createBranch(createBranchRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable("id") Integer id) throws BranchNotFoundException {
        branchService.deleteBranchById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UpdateBranchResponse updateBranch(@PathVariable("id") Integer id, @RequestBody BranchRequest updateBranchRequest) throws BranchNotFoundException {
        return branchService.updateBranch(updateBranchRequest, id);
    }


    @PutMapping("")
    public BranchResponse updateCustomer(@RequestBody UpdateBranchCustomerPair updateBranchCustomerPair,
                                         @RequestParam() OperationType operationType) throws BranchNotFoundException, CustomerNotFoundException, OperationTypeNotValidException, CustomerExistNoBranch {
        return branchService.updateCustomer(updateBranchCustomerPair, operationType);
    }
}
