package com.ms.crud_api.model.request.address;

import com.ms.crud_api.model.entity.AddressEntity;
import com.ms.crud_api.model.entity.UserEntity;

import java.io.Serializable;

public class AddressRequest implements Serializable {
    private String address;

    public AddressEntity toEntity(UserEntity parentEntity) {
        AddressEntity address = new AddressEntity();
        address.setAddress(this.address);
        address.setUser(parentEntity);

        return address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
