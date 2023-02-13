package com.bootcamp.testTask.repository;

import com.bootcamp.testTask.entity.Users;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

}
