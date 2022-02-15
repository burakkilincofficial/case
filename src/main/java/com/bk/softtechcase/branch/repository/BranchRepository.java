package com.bk.softtechcase.branch.repository;

import com.bk.softtechcase.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

    List<Branch> findByNameContaining(String name);

}