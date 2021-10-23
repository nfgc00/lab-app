package com.nfgc.lab.repository;

import com.nfgc.lab.entitiy.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Category Repository interface to perform database operations
 * 
 * @author Fernando
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
