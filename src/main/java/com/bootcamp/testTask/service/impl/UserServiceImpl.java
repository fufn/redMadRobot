package com.bootcamp.testTask.service.impl;

import com.bootcamp.testTask.entity.Role;
import com.bootcamp.testTask.entity.Users;
import com.bootcamp.testTask.repository.RoleRepository;
import com.bootcamp.testTask.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.bootcamp.testTask.service.UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final static String USER_ALREADY_EXIST = "There is already an account with the same email.";
    private final Logger logger = LogManager.getLogger(getClass());
    @Override
    public Users saveUser(Users user) throws Exception {
        logger.debug("Trying to save a user. " + user);
        Users existingUser = this.findByEmail(user.getEmail());

        if(existingUser != null){
            logger.debug("Such user already exists. " + user);
            throw new Exception(USER_ALREADY_EXIST);
        }

        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(List.of(role));
        logger.debug("Saving user.");
        return userRepository.save(user);
    }


    @Override
    public Users findByEmail(String email) {
        logger.debug("Finding the user by email = {}", email);
        return userRepository.findByEmail(email);
    }

    public Users getUser(Long id){
        logger.debug("Getting User by id = {}", id);
        return userRepository.findById(id).orElseThrow();
    }

}
