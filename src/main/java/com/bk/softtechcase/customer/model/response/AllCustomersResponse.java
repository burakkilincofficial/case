package com.bk.softtechcase.customer.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AllCustomersResponse.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllCustomersResponse {
    private Integer customerCount;
    private List<CustomerResponse> data;
}
