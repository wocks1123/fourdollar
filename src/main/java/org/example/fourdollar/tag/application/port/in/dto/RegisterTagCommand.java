package org.example.fourdollar.tag.application.port.in.dto;

import org.jetbrains.annotations.NotNull;

public record RegisterTagCommand(
        @NotNull String name,
        @NotNull String slug
) {
}
