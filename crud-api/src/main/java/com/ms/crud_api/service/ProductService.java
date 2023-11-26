package com.ms.crud_api.service;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.PostEntity;
import com.ms.crud_api.model.entity.ProductEntity;
import com.ms.crud_api.model.entity.TagEntity;
import com.ms.crud_api.model.request.product.ProductRequest;
import com.ms.crud_api.repository.ProductRepository;
import com.ms.crud_api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
    }

    public ProductEntity create(ProductRequest req) throws Exception {
        // casting from request model to entity
        ProductEntity data = req.toEntity();

        // validate tagIds exist in db
        List<TagEntity> foundTagsList = this.tagRepository.findAllById(req.getTagIds());
        Set<TagEntity> foundTags = Set.copyOf(foundTagsList);

        // set tags data
        data.setTags(foundTags);

        try {
            return this.productRepository.save(data);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ProductEntity update(Long id, ProductRequest req) throws Exception{
        // validate id exist in db or not
        ProductEntity foundProduct = this.productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        foundProduct.setName(req.getName());
        foundProduct.setPrice(req.getPrice());
        foundProduct.setDescription(req.getDescription());

        // validate tagIds exist in db
        List<TagEntity> foundTagsList = this.tagRepository.findAllById(req.getTagIds());
        Set<TagEntity> foundTags = Set.copyOf(foundTagsList);

        // set tags data
        foundProduct.setTags(foundTags);

        try {
            return this.productRepository.save(foundProduct);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ProductEntity delete(Long id) throws Exception {
        ProductEntity found = this.findOne(id);

        try {
            this.productRepository.deleteById(found.getId());
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        return found;
    }

    public List<ProductEntity> findAll() {
        return this.productRepository.findAll();
    }

    public ProductEntity findOne(Long id) throws NotFoundException {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
