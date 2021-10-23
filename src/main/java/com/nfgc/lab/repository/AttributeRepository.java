package com.nfgc.lab.repository;

import com.nfgc.lab.entitiy.Attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Attribute Repository interface to perform database operations
 * 
 * @author Fernando
 *
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
    
}
