package com.ms.crud_api.model.request.product;

import com.ms.crud_api.model.entity.ProductEntity;

import java.io.Serializable;
import java.util.List;

public class ProductRequest implements Serializable {
    private String name;
    private Double price;
    private String description;
    private List<Long> tagIds;

    public ProductEntity toEntity() {
        ProductEntity product = new ProductEntity();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setDescription(this.description);

        return product;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
