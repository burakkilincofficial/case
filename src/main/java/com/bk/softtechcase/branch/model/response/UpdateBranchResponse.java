package com.bk.softtechcase.branch.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * UpdateBranchResponse.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBranchResponse {
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
}
