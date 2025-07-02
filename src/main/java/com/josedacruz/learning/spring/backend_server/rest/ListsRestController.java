package com.josedacruz.learning.spring.backend_server.rest;

import com.josedacruz.learning.spring.backend_server.lists.SessionScopedList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListsRestController {

    private final SessionScopedList wishList;

    public ListsRestController(SessionScopedList list) {
        this.wishList = list;
    }

    @PostMapping("/lists")
    public void add(@RequestBody String item) {
        wishList.addItem(item);
    }

    @GetMapping("/lists")
    public List<String> getLists() {
        return wishList.getItems();
    }

}
