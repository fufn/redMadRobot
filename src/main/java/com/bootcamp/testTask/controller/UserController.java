package com.bootcamp.testTask.controller;

import com.bootcamp.testTask.dto.UserDto;
import com.bootcamp.testTask.dto.mapper.impl.UserMapper;
import com.bootcamp.testTask.entity.Users;
import com.bootcamp.testTask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final Logger logger = LogManager.getLogger(getClass());

    @Operation(summary = "Registers users")
    @PostMapping(value = "/users")
    public UserDto registration(@Valid @RequestBody UserDto user) throws Exception {
        logger.info("POST request to register user = {}", user);
        return userMapper.toDto(userService.saveUser(userMapper.toEntity(user)));
    }

    @Operation(summary = "Entering the profile with User's items")
    @GetMapping(value = "/users/profile")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public UserDto profile(@Valid @RequestParam Long id){
        logger.info("GET request to enter profile");
        return userMapper.toDtoWithItems(userService.getUser(id));
    }
}
