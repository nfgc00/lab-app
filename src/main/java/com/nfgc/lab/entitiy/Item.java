package com.nfgc.lab.entitiy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Item Entity class, stores the data related to the Item of a Category
 * 
 * @author Fernando
 *
 */
@Entity
public class Item {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @Transient
    private Integer categoryId;
    
    @OneToMany(mappedBy = "item")
    private List<AttributeValue> attributesValues;

    
    public Item() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<AttributeValue> getAttributesValues() {
        return attributesValues;
    }
    public void setAttributesValues(List<AttributeValue> attributesValues) {
        this.attributesValues = attributesValues;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
