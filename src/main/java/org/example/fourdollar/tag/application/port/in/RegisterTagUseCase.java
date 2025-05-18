package org.example.fourdollar.tag.application.port.in;

import org.example.fourdollar.tag.application.port.in.dto.RegisterTagCommand;
import org.jetbrains.annotations.NotNull;

public interface RegisterTagUseCase {

    @NotNull Long registerTag(@NotNull RegisterTagCommand command);

}
