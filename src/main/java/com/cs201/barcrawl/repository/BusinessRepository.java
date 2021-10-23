package com.cs201.barcrawl.repository;

import com.cs201.barcrawl.models.Business;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusinessRepository extends CrudRepository<Business, Integer> {
    // Nothing to see here~
}