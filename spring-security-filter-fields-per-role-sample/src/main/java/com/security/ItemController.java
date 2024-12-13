package com.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping
    public Item getItem() {
        Item item = new Item();
        item.setId("1");
        item.setName("item");
        item.setOwner("_adesh");
        return item;
    }
}
