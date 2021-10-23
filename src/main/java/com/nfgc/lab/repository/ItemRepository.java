package com.nfgc.lab.repository;

import java.util.List;

import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.entitiy.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Item Repository interface to perform database operations
 * 
 * @author Fernando
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    List<Item> findByCategory(Category category);
}
