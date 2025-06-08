package com.example.fourdollardomain.category.domain;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Category {

    private final Long id;
    private Long parentId;
    private String name;
    private String description;
    private int level;
    private int displayOrder;
    private List<Category> subCategories = new ArrayList<>();

    public Category(@NotNull Long id, @NotNull Long parentId, @NotNull String name, @NotNull String description, int level, int displayOrder, List<Category> subCategories) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.level = level;
        this.displayOrder = displayOrder;
        this.subCategories = subCategories;
    }

    public Category(@NotNull String name, @NotNull String description, int displayOrder) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.level = 1;
        this.displayOrder = displayOrder;
    }

    public void modify(@NotNull String name, @NotNull String description, int displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
    }

    public void addSubCategory(@NotNull Category subCategory) {
        if (this.level >= 3) {
            throw new IllegalArgumentException("하위 카테고리는 최대 3단계까지 가능합니다.");
        }

        subCategory.setParentId(this.id);
        subCategory.setLevel(this.level + 1);
        this.subCategories.add(subCategory);
    }

    public void removeSubCategory(@NotNull Category subCategory) {
        if (!this.subCategories.remove(subCategory)) {
            throw new IllegalArgumentException("하위 카테고리가 존재하지 않습니다.");
        }
    }

    private void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
