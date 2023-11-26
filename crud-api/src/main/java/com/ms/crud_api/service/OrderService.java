package com.ms.crud_api.service;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.OrderDetailEntity;
import com.ms.crud_api.model.entity.OrderEntity;
import com.ms.crud_api.model.request.order.OrderRequest;
import com.ms.crud_api.model.request.order_detail.OrderDetailRequest;
import com.ms.crud_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity create(OrderRequest req) throws Exception {
        // casting req to entity
        OrderEntity order = req.toEntity();
        // create variables for store total price and order details
        double totalPrice = 0.0;
        ArrayList<OrderDetailEntity> orderDetail = new ArrayList<>();

        for (OrderDetailRequest detail : req.getDetails()) {
            // calculate total price
            totalPrice += (detail.getPrice() * detail.getQty());

            // adding order detail
            OrderDetailEntity ordDetail = new OrderDetailEntity();
            ordDetail.setQty(detail.getQty());
            ordDetail.setProductName(detail.getProductName());
            ordDetail.setPrice(detail.getPrice());
            ordDetail.setOrder(order);
            orderDetail.add(ordDetail);
        }

        // prepare data
        order.setTotalPrice(totalPrice);
        order.setOrderDetails(orderDetail);

        try {
            // save data
            return this.orderRepository.save(order);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public OrderEntity update(Long id, OrderRequest req) throws Exception {
        // validate id exist in db or not
        OrderEntity foundOrder = this.orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));

        // create variables for store total price and order details
        double totalPrice = 0.0;
        ArrayList<OrderDetailEntity> orderDetail = new ArrayList<>();
        for (OrderDetailRequest detail : req.getDetails()) {
            // calculate total price
            totalPrice += (detail.getPrice() * detail.getQty());

            // adding order detail
            OrderDetailEntity ordDetail = new OrderDetailEntity();
            ordDetail.setQty(detail.getQty());
            ordDetail.setProductName(detail.getProductName());
            ordDetail.setPrice(detail.getPrice());
            ordDetail.setOrder(foundOrder);

            orderDetail.add(ordDetail);
        }

        // prepare data
        foundOrder.setCustomerName(req.getCustomerName());
        foundOrder.setTotalPrice(totalPrice);
        foundOrder.setOrderDetails(orderDetail);

        try {
            return this.orderRepository.save(foundOrder);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public OrderEntity delete(Long id) throws Exception {
        OrderEntity foundOrder = this.orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));

        try {
            this.orderRepository.deleteById(foundOrder.getId());
            return foundOrder;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<OrderEntity> findAll() {
        return this.orderRepository.findAll();
    }

    public OrderEntity findOne(Long id) throws NotFoundException {
        return this.orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));
    }
}
