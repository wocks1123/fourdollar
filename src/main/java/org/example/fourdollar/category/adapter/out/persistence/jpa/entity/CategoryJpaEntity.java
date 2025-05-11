package org.example.fourdollar.category.adapter.out.persistence.jpa.entity;

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
