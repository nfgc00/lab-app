package com.nfgc.lab.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.nfgc.lab.entitiy.AttributeValue;
import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.entitiy.Item;
import com.nfgc.lab.exception.ResourceNotFoundException;
import com.nfgc.lab.repository.AttributeValueRepository;
import com.nfgc.lab.repository.ItemRepository;
import com.nfgc.lab.service.AttributeService;
import com.nfgc.lab.service.CategoryService;
import com.nfgc.lab.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Item Service implementation class to perform business logic operations.
 * 
 * @author Fernando
 *
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AttributeService attributeService;

    @Override
    public Item save(Item item) {
        Category category = categoryService.getById(item.getCategoryId());
        item.setCategory(category);
        item = itemRepository.save(item);
        if(item.getAttributesValues() != null) {
            for(AttributeValue av : item.getAttributesValues()) {
                av.setAttribute(attributeService.getById(av.getAttributeId()));
                av.setItem(item);
                attributeValueRepository.save(av);
            }
        }
        return item;
    }
    
    @Override
    public Item update(Integer id, Item item) {
        Item dbItem = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "Id", id));
        for(AttributeValue dbVal : dbItem.getAttributesValues()) {
            for(AttributeValue av : item.getAttributesValues()) {
                if(dbVal.getAttribute().getId().equals(av.getAttributeId())) {
                    dbVal.setValue(av.getValue());
                    attributeValueRepository.save(dbVal);
                }
            }
        }
        setItemProperties(dbItem);
        return dbItem;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = itemRepository.findAll();
        for(Item item : items) {
            setItemProperties(item);
        }
        return items;
    }

    @Override
    public Item getById(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "Id", id));
        setItemProperties(item);
        return item;
    }

    @Override
    public List<Item> getAllByCategory(Integer categoryId) {
        Category category = categoryService.getById(categoryId);
        List<Item> items = itemRepository.findByCategory(category);
        for(Item item : items) {
            setItemProperties(item);
        }
        return items;
    }

    private void setItemProperties(Item item) {
        item.setCategoryId(item.getCategory().getId());
        for(AttributeValue av : item.getAttributesValues()) {
            av.setAttributeId(av.getAttribute().getId());
        }
    }
}
