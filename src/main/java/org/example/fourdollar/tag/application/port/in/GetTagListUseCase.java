package org.example.fourdollar.tag.application.port.in;

import org.example.fourdollar.tag.application.port.in.dto.TagDetail;
import org.jetbrains.annotations.NotNull;

public interface GetTagListUseCase {

    @NotNull TagDetail getTag(@NotNull Long tagId);

}
