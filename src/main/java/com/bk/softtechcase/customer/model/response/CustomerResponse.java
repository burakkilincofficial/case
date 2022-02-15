package com.bk.softtechcase.customer.model.response;

import com.bk.softtechcase.branch.entity.Branch;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * CustomerResponse.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Integer id;
    private Integer branchesCount;
    private String fullName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedDate;
    private Set<Branch> branches;
}
