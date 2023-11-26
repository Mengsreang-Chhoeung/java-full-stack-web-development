package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetailEntity extends BaseEntity<Long> {
    @Column(nullable = false, length = 50)
    private String productName;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false, referencedColumnName = "id")
    private OrderEntity order;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailEntity that = (OrderDetailEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
