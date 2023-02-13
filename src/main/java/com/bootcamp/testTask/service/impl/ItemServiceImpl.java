package com.bootcamp.testTask.service.impl;

import com.bootcamp.testTask.entity.Item;
import com.bootcamp.testTask.entity.Users;
import com.bootcamp.testTask.filter.ItemFilter;
import com.bootcamp.testTask.repository.CustomItemRepository;
import com.bootcamp.testTask.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements com.bootcamp.testTask.service.ItemService {

    private final CustomItemRepository itemRepository;
    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public Page<Item> getItems(Pageable pageable){
        logger.debug("Getting Items by Pageable Object {}", pageable);
        return itemRepository.findAll(pageable);
    }

    @Override
    public List<Item> getByFilters(ItemFilter itemFilter){
        logger.debug("Getting Items by Filters {}", itemFilter);
        return itemRepository.findByFilters(itemFilter.getName(), itemFilter.getDescription(), itemFilter.getMinPrice());
    }

    @Override
    public Item saveItem(Item item){
        logger.debug("Saving item {}", item);
        return itemRepository.save(item);
    }

    @Override
    public Item placeBet(Item item){
        logger.debug("Placing a bet {}", item);
        Item curItem = itemRepository.findById(item.getId());
        if (curItem.getCurrentPrice() <= item.getCurrentPrice()){
            logger.debug("Price is higher that current");
            if (curItem.getCurrentUserBuying() == null){
                logger.debug("The first buyer! Starting a timer");
                winner(item.getId());
            } else {
                logger.debug("New buyer!");
                logger.info("Sending previous buyer a notification");
            }
            curItem.setCurrentUserBuying(Users.builder().id(item.getCurrentUserBuying().getId()).build());
            curItem.setCurrentPrice(item.getCurrentPrice());
        }
        return itemRepository.save(curItem);
    }

    @Scheduled(fixedDelay = 300000L)
    public void winner(Long id){
        Item item = itemRepository.findById(id);
        logger.info("Sending notification to owner = {}", item.getCurrentUserBuying());
        logger.info("Sending notification to winner = {}", item.getInitializerUser());
        item.setIsActive(false);
        itemRepository.save(item);
    }
}
