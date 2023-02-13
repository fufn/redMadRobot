package com.bootcamp.testTask.service;

import com.bootcamp.testTask.entity.Item;
import com.bootcamp.testTask.filter.ItemFilter;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<Item> getItems(Pageable pageable);

    List<Item> getByFilters(ItemFilter itemFilter);

    Item saveItem(Item item);

    Item placeBet(Item item);
}
