package com.bootcamp.testTask.controller;

import com.bootcamp.testTask.dto.ItemDto;
import com.bootcamp.testTask.dto.mapper.impl.ItemMapper;
import com.bootcamp.testTask.dto.mapper.impl.UserMapper;
import com.bootcamp.testTask.entity.Item;
import com.bootcamp.testTask.filter.ItemFilter;
import com.bootcamp.testTask.service.ItemService;
import com.bootcamp.testTask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final Logger logger = LogManager.getLogger(getClass());

    @Operation(summary = "Get Items by Pageable Object")
    @GetMapping(value = "/items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Page<ItemDto> getItems(@Valid @RequestBody Pageable pageable){
        logger.info("GET request with pageable = {}", pageable);
        return itemMapper.pageToDto(itemService.getItems(pageable));
    }

    @Operation(summary = "Get Items by Filters")
    @GetMapping(value = "/items/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<ItemDto> getItemsByFilter(@Valid @RequestBody ItemFilter filter){
        logger.info("GET request with filters = {}", filter);
        return itemMapper.listToDto(itemService.getByFilters(filter));
    }

    @Operation(summary = "Save Item")
    @PostMapping(value = "/items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ItemDto saveItem(@Valid @RequestBody ItemDto itemDto){
        logger.info("POST request with itemDto = {}", itemDto);
        return itemMapper.toDto(itemService.saveItem(itemMapper.toEntity(itemDto)));
    }

    @Operation(summary = "Make a Bet")
    @PutMapping(value = "/items/bet")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ItemDto placeBet(@Valid @RequestBody ItemDto itemDto){
        logger.info("PUT request with itemDto = {}", itemDto);
        return itemMapper.toDto(itemService.placeBet(itemMapper.toEntity(itemDto)));
    }

    @Operation(summary = "Update Item")
    @PutMapping(value = "/items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ItemDto updateItem(@Valid @RequestBody ItemDto itemDto){
        logger.info("PUT request with itemDto = {}", itemDto);
        return itemMapper.toDto(itemService.saveItem(itemMapper.toEntity(itemDto)));
    }

}
