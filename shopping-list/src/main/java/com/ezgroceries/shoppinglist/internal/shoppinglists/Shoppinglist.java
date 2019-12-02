package com.ezgroceries.shoppinglist.internal.shoppinglists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shoppinglist {
    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;

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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredients(List<String> ingredients) {
        if(this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        }
        this.ingredients.addAll(ingredients);
    }
}
