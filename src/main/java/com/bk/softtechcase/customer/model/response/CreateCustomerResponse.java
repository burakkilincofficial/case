package com.bk.softtechcase.customer.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * CreateCustomerResponse.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponse {
    private Integer id;
    private String fullName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
}
