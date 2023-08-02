package com.ms.app.model.embeddable;

import com.ms.app.constant.enums.GenderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class GeneralInfo {
    @Column(length = 20, nullable = false)
    private String firstname;

    @Column(length = 10, nullable = false)
    private String lastname;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(length = 15, nullable = false)
    private String phone;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}
