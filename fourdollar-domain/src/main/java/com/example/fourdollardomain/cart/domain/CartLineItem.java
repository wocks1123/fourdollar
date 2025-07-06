package com.example.fourdollardomain.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_line_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "base_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "cartLineItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();


    public CartLineItem(Long id, Cart cart, Long productId, BigDecimal basePrice, int quantity, List<CartItem> cartItems) {
        this.id = id;
        this.cart = cart;
        this.productId = productId;
        this.basePrice = basePrice;
        this.quantity = quantity;
        this.cartItems = cartItems;
    }

    public BigDecimal getUnitPrice() {
        return basePrice.add(
                cartItems.stream()
                        .map(CartItem::getAdditionalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    public BigDecimal getTotalPrice() {
        return getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
