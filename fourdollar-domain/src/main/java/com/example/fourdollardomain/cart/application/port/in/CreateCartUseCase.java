package com.example.fourdollardomain.cart.application.port.in;

import com.example.fourdollardomain.cart.domain.Cart;
import org.jetbrains.annotations.NotNull;

public interface CreateCartUseCase {

    @NotNull Cart create(@NotNull CreateCartCommand cart);
    
}
