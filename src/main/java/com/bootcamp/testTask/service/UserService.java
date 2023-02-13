package com.bootcamp.testTask.service;

import com.bootcamp.testTask.entity.Users;

public interface UserService {
    Users saveUser(Users user) throws Exception;
    Users findByEmail(String email);
    Users getUser(Long id);
}
