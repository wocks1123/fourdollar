package com.example.fourdollardomain.cart.application.port.in.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddItemToCartCommand(
        @NotNull Long productId,
        @NotNull Integer quantity,
        @NotNull List<OptionSelection> options
) {
    public record OptionSelection(
            @NotNull Long optionId
    ) {
    }
}
