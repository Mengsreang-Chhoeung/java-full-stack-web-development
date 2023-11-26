package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.ProductEntity;
import com.ms.crud_api.model.request.product.ProductRequest;
import com.ms.crud_api.model.response.product.ProductResponse;
import com.ms.crud_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest req) throws Exception {
        ProductEntity data = this.productService.create(req);

        return ResponseEntity.ok(ProductResponse.fromEntity(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest req) throws Exception {
        ProductEntity data = this.productService.update(id, req);

        return ResponseEntity.ok(ProductResponse.fromEntity(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable Long id) throws Exception {
        ProductEntity data = this.productService.delete(id);

        return ResponseEntity.ok(ProductResponse.fromEntity(data));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> data = this.productService.findAll().stream().map(ProductResponse::fromEntity).toList();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findOne(@PathVariable Long id) throws NotFoundException {
        ProductEntity data = this.productService.findOne(id);

        return ResponseEntity.ok(ProductResponse.fromEntity(data));
    }
}
