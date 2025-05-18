package org.example.fourdollar.tag.application.port.out;

import org.example.fourdollar.tag.domain.Tag;
import org.jetbrains.annotations.NotNull;

public interface SaveTagPort {
    
    @NotNull Tag save(@NotNull Tag tag);

}
