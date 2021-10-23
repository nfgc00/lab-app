package com.nfgc.lab.service;

import java.util.List;

import com.nfgc.lab.entitiy.Attribute;

/**
 *  Attribute Service interface to perform business logic operations.
 * 
 * @author Fernando
 *
 */
public interface AttributeService {
    
    /**
     * Saves an Attribute
     * 
     * @param attribute Object with the data of the Attribute
     * @return {@code Attribute}
     */
    Attribute save(Attribute attribute);

    /**
     * Returns an Attribue identified by the given ID
     * 
     * @param id Attribute identifier
     * @return {@code Attribute}
     */
    Attribute getById(Integer id);
    
    /**
     * Returns a {@code List} of all the Attributes of a Category
     * 
     * @param category Category identifier
     * @return {@code List<Attribute>}
     */
    // List<Attribute> getByCategory(Integer category);
}
