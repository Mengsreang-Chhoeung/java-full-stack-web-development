package com.ms.crud_api.model.response.user;

import com.ms.crud_api.model.entity.UserEntity;
import com.ms.crud_api.model.response.address.AddressResponse;

import java.io.Serializable;

public class UserRegisterResponse implements Serializable {
    private Long id;
    private String username;
    private AddressResponse address;

    public UserRegisterResponse(Long id, String username, AddressResponse address) {
        this.id = id;
        this.username = username;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public static UserRegisterResponse fromEntity(UserEntity entity) {
        AddressResponse addr;
        if (entity.getAddress() == null)
            addr = null;
        else addr = new AddressResponse(entity.getAddress().getId(), entity.getAddress().getAddress());

        return new UserRegisterResponse(
                entity.getId(),
                entity.getUsername(),
                addr
        );
    }
}
