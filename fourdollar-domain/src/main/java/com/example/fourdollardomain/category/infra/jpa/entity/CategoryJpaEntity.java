package com.example.fourdollardomain.category.infra.jpa.entity;

import com.example.fourdollardomain.category.domain.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryJpaEntity {

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
    private CategoryJpaEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CategoryJpaEntity> subCategories = new ArrayList<>();

    public static CategoryJpaEntity from(Category category) {
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.id = category.getId();
        entity.parentId = category.getParentId();
        entity.name = category.getName();
        entity.description = category.getDescription();
        entity.level = category.getLevel();
        entity.displayOrder = category.getDisplayOrder();
        entity.subCategories = category.getSubCategories().stream()
                .map(CategoryJpaEntity::from)
                .toList();
        return entity;
    }

    public Category toDomain() {
        return new Category(
                id,
                parentId,
                name,
                description,
                level,
                displayOrder,
                subCategories.stream().map(CategoryJpaEntity::toDomain).toList()
        );
    }

    public CategoryJpaEntity(Long id, Long parentId, String name, String description, int level, int displayOrder, List<CategoryJpaEntity> subCategories) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.level = level;
        this.displayOrder = displayOrder;
        this.subCategories = subCategories;
    }

}
