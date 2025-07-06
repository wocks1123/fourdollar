package com.example.fourdollardomain.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_line_item_id", nullable = false)
    private CartLineItem cartLineItem;

    @Column(name = "option_group_name", nullable = false, length = 50)
    private String optionGroupName;

    @Column(name = "option_value", nullable = false, length = 100)
    private String optionValue;

    @Column(name = "additional_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal additionalPrice;


    public CartItem(CartLineItem cartLineItem, String optionGroupName, String optionValue, BigDecimal additionalPrice) {
        this.cartLineItem = cartLineItem;
        this.optionGroupName = optionGroupName;
        this.optionValue = optionValue;
        this.additionalPrice = additionalPrice;
    }

    public CartItem(Long id, CartLineItem cartLineItem, String optionGroupName, String optionValue) {
        this.id = id;
        this.cartLineItem = cartLineItem;
        this.optionGroupName = optionGroupName;
        this.optionValue = optionValue;
    }

}
