package com.ms.spring_security_jwt.modules.user.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.spring_security_jwt.constant.TableNameConstant;
import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;
import com.ms.spring_security_jwt.infrastructure.model.entity.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = TableNameConstant.USER)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseSoftDeleteEntity {
    @Column(nullable = false, name = "username", length = 30)
    private String username;

    @Column(nullable = false, name = "email")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, name = "password", length = 60)
    private String password;

    @Column(nullable = false, name = "enabled", columnDefinition = "boolean default true")
    private Boolean enabled;

    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "role", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

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
}
