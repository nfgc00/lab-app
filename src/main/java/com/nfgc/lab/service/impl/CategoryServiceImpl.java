package com.nfgc.lab.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.nfgc.lab.entitiy.Attribute;
import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.exception.ResourceNotFoundException;
import com.nfgc.lab.repository.AttributeRepository;
import com.nfgc.lab.repository.CategoryRepository;
import com.nfgc.lab.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Category Service implementation class to perform business logic operations.
 * 
 * @author Fernando
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Category save(Category category) {
        categoryRepository.save(category);
        if (category.getAttributes() != null) {
            for (Attribute attribute : category.getAttributes()) {
                attribute.setCategory(category);
                attributeRepository.save(attribute);
            }
        }
        return category;
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
