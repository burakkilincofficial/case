package com.bk.softtechcase.customer.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * UpdateCustomerResponse.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerResponse {
    private Integer id;
    private String fullName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}
