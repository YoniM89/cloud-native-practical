package com.ezgroceries.shoppinglist.internal.shoppinglists;

import java.util.UUID;

public class Shoppinglist {
    private UUID shoppingListId;
    private String name;

    public Shoppinglist(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public Shoppinglist(String name) {
        this(UUID.randomUUID(), name);
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public String getName() {
        return name;
    }
}
