package com.bk.softtechcase.customer.mapper;

import com.bk.softtechcase.branch.entity.Branch;
import com.bk.softtechcase.customer.entity.Customer;
import com.bk.softtechcase.customer.model.CustomerRequest;
import com.bk.softtechcase.customer.model.response.CreateCustomerResponse;
import com.bk.softtechcase.customer.model.response.CustomerResponse;
import com.bk.softtechcase.customer.model.response.UpdateCustomerResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

/**
 * CustomerMapper.
 * @since 2022-02-14
 * @author burak kilinc
 */
@Component
public class CustomerMapper {

    public CustomerResponse convertCustomer2CustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .branches(customer.getBranches())
                .lastUpdatedDate(customer.getLastUpdatedDate())
                .build();
    }

    public Customer convertCustomerRequest2Customer(CustomerRequest customerRequest, Branch branch) {
        Set<Branch> singleton = null;
        if(branch!=null){
            singleton = Collections.singleton(branch);
        }
        return Customer.builder()
                .fullName(customerRequest.getFullName())
                .branches(singleton)
                .build();
    }

    public CreateCustomerResponse convertCustomer2CreateCustomerResponse(Customer customer) {
        return CreateCustomerResponse.builder()
                .id(customer.getId())
                .createdDate(customer.getRegisterDate())
                .fullName(customer.getFullName())
                .build();
    }

    public UpdateCustomerResponse convertCustomer2UpdateCustomerResponse(Customer customer) {
        return UpdateCustomerResponse.builder()
                .id(customer.getId())
                .updateDate(customer.getLastUpdatedDate())
                .fullName(customer.getFullName())
                .build();
    }
}
