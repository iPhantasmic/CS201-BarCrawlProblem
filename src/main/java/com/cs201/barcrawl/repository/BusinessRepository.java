package com.cs201.barcrawl.repository;

import com.cs201.barcrawl.models.Business;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessRepository extends CrudRepository<Business, Long> {
    List<Business> findAllByOrderByNameAsc();
    Business findBusinessByBusinessId(String id);
    // Nothing to see here~
}