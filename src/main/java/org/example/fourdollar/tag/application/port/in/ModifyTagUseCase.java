package org.example.fourdollar.tag.application.port.in;

import org.example.fourdollar.tag.application.port.in.dto.ModifyTagCommand;
import org.jetbrains.annotations.NotNull;

public interface ModifyTagUseCase {

    void modifyTag(@NotNull Long tagId, @NotNull ModifyTagCommand command);

}
