package com.ms.crud_api.model.request.account;

import com.ms.crud_api.model.entity.AccountEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class AccountRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "John Doe", maxLength = 50)
    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name cannot be empty!")
    @Size(max = 50, message = "Name cannot be bigger than 50 characters!")
    private String name;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "0", minimum = "0")
    @NotNull(message = "Balance is required!")
    private Double balance;

    public AccountEntity toEntity() {
        AccountEntity account = new AccountEntity();
        account.setName(this.name);
        account.setBalance(this.balance);

        return account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
