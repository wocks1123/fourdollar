package com.example.fourdollardomain.product.application.port.in.dto;

import java.util.List;

public record RegisterProductOptionGroupCommand(
        String name,
        int displayOrder,
        List<RegisterProductOptionCommand> options
) {
}
