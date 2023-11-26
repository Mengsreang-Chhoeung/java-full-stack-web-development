package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "addresses")
public class AddressEntity extends BaseEntity<Long> {
    @Column(length = 100, nullable = false)
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserEntity user;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AddressEntity that = (AddressEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
