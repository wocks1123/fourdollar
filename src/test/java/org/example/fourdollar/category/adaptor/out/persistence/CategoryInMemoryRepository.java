package org.example.fourdollar.category.adaptor.out.persistence;

import org.example.fourdollar.category.application.port.out.LoadCategoryPort;
import org.example.fourdollar.category.application.port.out.SaveCategoryPort;
import org.example.fourdollar.category.domain.Category;
import org.example.fourdollar.common.TestUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryInMemoryRepository implements SaveCategoryPort, LoadCategoryPort {

    private final Map<Long, Category> categoryStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    @Override
    public @NotNull Category save(@NotNull Category category) {
        Long id = category.getId() == null
                ? idGenerator.incrementAndGet()
                : category.getId();
        TestUtils.setId(category, id);
        categoryStore.put(id, category);
        return category;
    }

    @Override
    public Optional<Category> findBy(@NotNull Long categoryId) {
        return Optional.ofNullable(categoryStore.get(categoryId));
    }

}
