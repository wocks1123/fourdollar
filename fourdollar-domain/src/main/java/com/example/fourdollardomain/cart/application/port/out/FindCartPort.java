package com.example.fourdollardomain.cart.application.port.out;

import com.example.fourdollardomain.cart.domain.Cart;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface FindCartPort {

    Optional<Cart> findCartById(@NotNull Long id);

    Optional<Cart> findCartByMemberId(@NotNull Long memberId);

}
