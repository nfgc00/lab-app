package com.nfgc.lab.entitiy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AttributeValue Entity class, stores the data related to the Value of the Attributes of a Category
 * 
 * @author Fernando
 *
 */
@Entity
public class AttributeValue {
    
    @Id
    @GeneratedValue
    private Integer id;

    private String value;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Attribute attribute;

    @Transient
    private Integer attributeId;

    public AttributeValue() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Attribute getAttribute() {
        return attribute;
    }
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
    public Integer getAttributeId() {
        return attributeId;
    }
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }
}
