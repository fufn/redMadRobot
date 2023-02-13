package com.bootcamp.testTask.repository;

import com.bootcamp.testTask.entity.Role;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
