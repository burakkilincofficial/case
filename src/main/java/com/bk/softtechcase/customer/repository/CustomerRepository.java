package com.bk.softtechcase.customer.repository;

import com.bk.softtechcase.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFullNameContaining(String fullName);

    boolean existsByFullName(String fullName);

}