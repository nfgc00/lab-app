package com.nfgc.lab.service;

import java.util.List;

import com.nfgc.lab.entitiy.Item;

/**
 *  Item Service interface to perform business logic operations
 * 
 * @author Fernando
 *
 */
public interface ItemService {
    
    /**
     * Saves an Item with the values of its Category Attributes
     * 
     * @param item Object with the data of the Item
     * @return {@code Item}
     */
    Item save(Item item);

    /**
     * Updates an Item with the values of its Category Attributes
     * 
     * @param id Item identifier
     * @param item Data of the Item to be updated
     * @return {@code Item}
     */
    Item update(Integer id, Item item);

    /**
     * Returns a {@code List} of all the Items available
     * 
     * @return {@code List<Item>}
     */
    List<Item> getAll();

    /**
     * Returns an Item identified by the given ID
     * 
     * @param id Item identifier
     * @return {@code Item}
     */
    Item getById(Integer id);

    /**
     * Returns a {@code List} of all the Items of a Category
     * 
     * @return {@code List<Item>}
     */
    List<Item> getAllByCategory(Integer categoryId);
}
