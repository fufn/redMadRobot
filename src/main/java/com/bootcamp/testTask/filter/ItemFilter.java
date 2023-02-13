package com.bootcamp.testTask.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemFilter {

    private String name;
    private String description;
    private Double minPrice;

}
