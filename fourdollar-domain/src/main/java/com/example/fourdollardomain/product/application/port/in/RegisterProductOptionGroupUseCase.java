package com.example.fourdollardomain.product.application.port.in;

import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductOptionGroupCommand;
import org.jetbrains.annotations.NotNull;

public interface RegisterProductOptionGroupUseCase {

    void registerProductOptionGroup(@NotNull RegisterProductOptionGroupCommand command);

}
