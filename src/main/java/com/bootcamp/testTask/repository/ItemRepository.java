package com.bootcamp.testTask.repository;

import com.bootcamp.testTask.entity.Item;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

}
