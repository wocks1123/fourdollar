package com.example.fourdollardomain.category.domain;

import com.example.fourdollardomain.common.exception.FdAssert;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int level;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Category> subCategories = new ArrayList<>();

    @Builder
    public Category(String name, String description, int displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
        this.level = 1;
    }

    public void addSubCategory(Category subCategory) {
        FdAssert.notNull(subCategory, "Sub-category must not be null");
        FdAssert.isTrue(!this.subCategories.contains(subCategory), "Sub-category already exists in this category");
        FdAssert.isTrue(this.level < 3, "Cannot add sub-category beyond level 3");

        subCategory.setParent(this);
        subCategory.level = this.level + 1;
        this.subCategories.add(subCategory);
    }

    public void modify(String name, String description, int displayOrder) {
        this.name = name;
        this.description = description;
        this.level = displayOrder;
    }

    private void setParent(Category parent) {
        this.parent = parent;
        this.parentId = parent.id;
    }

}
