package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.OrderEntity;
import com.ms.crud_api.model.request.order.OrderRequest;
import com.ms.crud_api.model.response.order.OrderResponse;
import com.ms.crud_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest req) throws Exception {
        OrderEntity data = this.orderService.create(req);

        return ResponseEntity.ok(OrderResponse.fromEntity(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderRequest req) throws Exception {
        OrderEntity data = this.orderService.update(id, req);

        return ResponseEntity.ok(OrderResponse.fromEntity(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> delete(@PathVariable Long id) throws Exception {
        OrderEntity data = this.orderService.delete(id);

        return ResponseEntity.ok(OrderResponse.fromEntity(data));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> data = this.orderService.findAll().stream().map(OrderResponse::fromEntity).toList();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOne(@PathVariable Long id) throws NotFoundException {
        OrderEntity data = this.orderService.findOne(id);

        return ResponseEntity.ok(OrderResponse.fromEntity(data));
    }
}
