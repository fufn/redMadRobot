package com.bootcamp.testTask.dto.mapper.impl;

import com.bootcamp.testTask.dto.ItemDto;
import com.bootcamp.testTask.dto.mapper.Mapper;
import com.bootcamp.testTask.entity.Item;
import com.bootcamp.testTask.entity.Users;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemMapper implements Mapper<ItemDto, Item> {
    @Override
    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .description(item.getDescription())
                .minPrice(item.getMinPrice())
                .currentPrice(item.getCurrentPrice())
                .isActive(item.getIsActive())
                .image(item.getImage())
                .initializerUserId(item.getInitializerUser().getId())
                .currentUserBuyingId(item.getCurrentUserBuying() == null ? null : item.getCurrentUserBuying().getId())
                .build();
    }

    @Override
    public Item toEntity(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .minPrice(itemDto.getMinPrice())
                .isActive(itemDto.getIsActive())
                .currentPrice(itemDto.getCurrentPrice())
                .image(itemDto.getImage())
                .initializerUser(Users.builder()
                        .id(itemDto.getInitializerUserId())
                        .build())
                .currentUserBuying(Users.builder()
                        .id(itemDto.getCurrentUserBuyingId())
                        .build())
                .build();
    }

    public List<Item> listToEntity(List<ItemDto> itemDtos){
        return itemDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<ItemDto> listToDto(List<Item> items){
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Page<ItemDto> pageToDto(Page<Item> items){
        return new PageImpl<ItemDto>(items.stream().map(this::toDto).collect(Collectors.toList()));
    }
}
