package com.ms.spring_file_upload.model.request.file;

import java.io.Serializable;

public class UpdateFileNameRequest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
