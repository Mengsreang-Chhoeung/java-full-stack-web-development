package com.ms.crud_api.model.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class RechargeAccountBalanceRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "0", minimum = "0")
    @NotNull(message = "Balance is required!")
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
