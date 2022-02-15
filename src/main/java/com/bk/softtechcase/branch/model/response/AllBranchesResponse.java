package com.bk.softtechcase.branch.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AllBranchesResponse for getAll operations.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllBranchesResponse {
    private Integer branchCount;
    private List<BranchResponse> data;
}
