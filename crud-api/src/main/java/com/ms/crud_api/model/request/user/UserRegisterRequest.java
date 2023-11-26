package com.ms.crud_api.model.request.user;

import com.ms.crud_api.model.entity.CategoryEntity;
import com.ms.crud_api.model.entity.UserEntity;
import com.ms.crud_api.model.request.address.AddressRequest;

import java.io.Serializable;

public class UserRegisterRequest implements Serializable {
    private String username;
    private AddressRequest address;

    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setUsername(this.username);
        user.setAddress(this.address.toEntity(user));

        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }
}
