package com.bootcamp.testTask.repository;

import com.bootcamp.testTask.entity.Item;
import com.bootcamp.testTask.entity.Item_;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
@RequiredArgsConstructor
public class CustomItemRepository {

    private final ItemRepository itemRepository;

    public List<Item> findByFilters(String name, String description, Double minPrice) {
        return itemRepository.findAll(where(nameLike(name)).and(descriptionLike(description)).and(minPriceHigherThan(minPrice)));
    }

    public Specification<Item> nameLike(String name) {
        if (name == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Item_.NAME), "%" + name + "%");
    }

    public Specification<Item> descriptionLike(String description) {
        if (description == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Item_.DESCRIPTION), "%" + description + "%");
    }

    public Specification<Item> minPriceHigherThan(Double minPrice) {
        if (minPrice == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Item_.MIN_PRICE), minPrice);
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }
}
