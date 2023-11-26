package com.ms.crud_api.model.response.address;

import java.io.Serializable;

public class AddressResponse implements Serializable {
    private Long id;
    private String address;

    public AddressResponse(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
