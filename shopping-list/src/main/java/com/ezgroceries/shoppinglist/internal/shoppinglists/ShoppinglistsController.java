package com.ezgroceries.shoppinglist.internal.shoppinglists;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppinglistsController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shoppinglist createShoppinglist(@RequestBody Shoppinglist shoppinglist) {
        return shoppinglist;
    }
}
