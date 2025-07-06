package com.example.fourdollardomain.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<CartLineItem> cartLineItems;

    public Cart(Long id, List<CartLineItem> cartLineItems) {
        this.id = id;
        this.cartLineItems = cartLineItems;
    }

}
