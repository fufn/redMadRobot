package com.bootcamp.testTask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private Double minPrice;
    private Double currentPrice;
    private Boolean isActive;
    private Byte[] image;
    private Long initializerUserId;
    private Long currentUserBuyingId;


}
