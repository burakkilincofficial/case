package com.bk.softtechcase.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * TODO Describe this class.
 * @since 2022-02-15
 * @author burak kilinc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBranchCustomerPair {
    private Integer branchId;
    private Integer customerId;
}
