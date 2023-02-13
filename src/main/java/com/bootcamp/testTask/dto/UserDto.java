package com.bootcamp.testTask.dto;


import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should be empty")
    private String password;

    private List<ItemDto> itemsToSellDto;
    private List<ItemDto> itemsToBuyDto;

}
