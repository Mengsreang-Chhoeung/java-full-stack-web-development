package com.ms.app.model.entity;

import com.ms.app.infrastructure.converter.ContactObjectConverter;
import com.ms.app.model.obj.ContactObject;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_username", columnNames = "username"),
                @UniqueConstraint(name = "unique_phone_number", columnNames = "phoneNumber")
        }
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 15, nullable = false)
    private String phoneNumber;

    @Column(length = 100)
    @Convert(converter = ContactObjectConverter.class)
    private ContactObject contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public ContactObject getContact() {
        return contact;
    }

    public void setContact(ContactObject contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
