package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity<Long> {
    @Column(nullable = false, length = 30, unique = true)
    private String username;

//    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
