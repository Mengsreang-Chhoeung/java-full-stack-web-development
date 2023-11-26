package com.ms.crud_api.model.response.product;

import com.ms.crud_api.model.entity.ProductEntity;
import com.ms.crud_api.model.entity.TagEntity;
import com.ms.crud_api.model.response.tag.TagResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductResponse implements Serializable {
    private final Long id;
    private final String name;
    private final Double price;
    private final String description;
    private final List<TagResponse> tags;

    public ProductResponse(Long id, String name, Double price, String description, List<TagResponse> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.tags = tags;
    }

    public static ProductResponse fromEntity(ProductEntity entity) {
        List<TagResponse> tags = new ArrayList<>(Collections.emptyList());
        if (entity.getTags() != null)  {
            for (TagEntity tag : entity.getTags()) {
                tags.add(TagResponse.fromEntity(tag));
            }
        }

        return new ProductResponse(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription(), tags);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<TagResponse> getTags() {
        return tags;
    }
}
