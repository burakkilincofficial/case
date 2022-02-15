package com.bk.softtechcase.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerRequest.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String fullName;
    private Integer branchId;
}
