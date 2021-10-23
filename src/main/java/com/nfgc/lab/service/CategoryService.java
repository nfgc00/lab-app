package com.nfgc.lab.service;

import java.util.List;

import com.nfgc.lab.entitiy.Category;

/**
 *  Category Service interface to perform business logic operations
 * 
 * @author Fernando
 *
 */
public interface CategoryService {
    
    /**
     * Saves a Category 
     * 
     * @param category Object with the data of the Category
     * @return {@code Category}
     */
    Category save(Category category);

    /**
     * Returns a Category identified by the given ID
     * 
     * @param id Category identifier
     * @return {@code Category}
     */
    Category getById(Integer id);

    /**
     * Returns a {@code List} of all the Categories available
     * 
     * @return {@code List<Category>}
     */
    List<Category> getAll();
}
