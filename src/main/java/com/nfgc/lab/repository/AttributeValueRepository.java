package com.nfgc.lab.repository;

import com.nfgc.lab.entitiy.AttributeValue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Attribute Value Repository interface to perform database operations
 * 
 * @author Fernando
 *
 */
@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
    
}
