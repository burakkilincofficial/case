package com.bk.softtechcase.branch.mapper;

import com.bk.softtechcase.branch.entity.Branch;
import com.bk.softtechcase.branch.model.BranchRequest;
import com.bk.softtechcase.branch.model.response.BranchResponse;
import com.bk.softtechcase.branch.model.response.CreateBranchResponse;
import com.bk.softtechcase.branch.model.response.UpdateBranchResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper class for Branches.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Component
public class BranchMapper {
    public BranchResponse convertBranch2BranchResponse(Branch branch) {
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .customers(branch.getCustomers())
                .lastUpdatedDate(branch.getLastUpdatedDate())
                .build();
    }

    public Branch convertBranchRequest2Branch(BranchRequest branchRequest) {
        return Branch.builder()
                .name(branchRequest.getName())
                .build();
    }

    public CreateBranchResponse convertBranch2CreateBranchResponse(Branch branch) {
        return CreateBranchResponse.builder()
                .id(branch.getId())
                .createdDate(branch.getCreatedDate())
                .name(branch.getName())
                .build();
    }

    public UpdateBranchResponse convertBranch2UpdateBranchResponse(Branch branch) {
        return UpdateBranchResponse.builder()
                .id(branch.getId())
                .updatedDate(branch.getLastUpdatedDate())
                .name(branch.getName())
                .build();
    }
}
