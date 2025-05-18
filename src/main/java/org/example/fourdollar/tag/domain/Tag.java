package org.example.fourdollar.tag.domain;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Tag {

    private final Long id;
    private String name;

    public Tag(@Nullable Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(@NotNull String name) {
        this.id = null;
        this.name = name;
    }

    public void modify(@NotNull String name) {
        this.name = name;
    }
}
