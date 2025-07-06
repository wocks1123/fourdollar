package com.example.fourdollardomain.cart.application.port.in;

import com.example.fourdollardomain.cart.application.port.in.dto.AddItemToCartCommand;
import org.jetbrains.annotations.NotNull;

public interface AddItemToCartUseCase {

    void addItem(@NotNull Long memberId, @NotNull AddItemToCartCommand command);

}
