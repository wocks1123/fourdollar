package com.example.fourdollardomain.category.domain;

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
    }

    public void addSubCategory(Category subCategory) {
        subCategory.setParent(this);
        this.subCategories.add(subCategory);
    }

    public void modify(String name, String description, int level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    private void setParent(Category parent) {
        this.parent = parent;
        if (parent != null && !parent.getSubCategories().contains(this)) {
            parent.getSubCategories().add(this);
        }
        this.parentId = parent != null ? parent.getId() : null;
    }

}
