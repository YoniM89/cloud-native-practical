package com.ezgroceries.shoppinglist.internal.shoppinglists;

import com.ezgroceries.shoppinglist.internal.cocktail.Cocktail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public List<Shoppinglist> getAllShoppinglists() {
        List<Shoppinglist> shoppinglistList = mockShoppinglistList();

        return shoppinglistList;
    }

    @PostMapping(path="{shoppinglistId}/cocktails")
    public List<Cocktail> addCocktailToShoppinglist(@PathVariable(name="shoppinglistId")UUID shoppinglistId,
            @RequestBody ArrayList<Cocktail> cocktailList) {
        Shoppinglist shoppinglist = new Shoppinglist(shoppinglistId, "Dummy name");
        cocktailList.forEach((cocktail) -> shoppinglist.addIngredients(cocktail.getIngredients()));

        return cocktailList;
    }

    @GetMapping(path="{shoppinglistId}")
    public Shoppinglist getShoppinglist(@PathVariable(name="shoppinglistId") UUID shoppinglistId) {
        Shoppinglist shoppinglist = new Shoppinglist(shoppinglistId, "Dummy name");

        List<Cocktail> cocktailList = mockCocktailList();

        cocktailList.forEach((cocktail) -> shoppinglist.addIngredients(cocktail.getIngredients()));

        return shoppinglist;
    }

    private List<Cocktail> mockCocktailList() {
        List<Cocktail> cocktailList = new ArrayList<>();
        cocktailList.add(new Cocktail(
                UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt"))
        );
        cocktailList.add(new Cocktail(
                UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                "Cocktail glass",
                "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt"))
        );
        return cocktailList;
    }

    private List<Shoppinglist> mockShoppinglistList() {
        List<Shoppinglist> shoppinglistList = new ArrayList<>();
        List<Cocktail> cocktailList = mockCocktailList();

        Shoppinglist shoppinglist1 = new Shoppinglist(UUID.randomUUID(), "Stephanie's birthday");
        cocktailList.forEach((cocktail) -> shoppinglist1.addIngredients(cocktail.getIngredients()));
        Shoppinglist shoppinglist2 = new Shoppinglist(UUID.randomUUID(), "My birthday");
        cocktailList.forEach((cocktail) -> shoppinglist2.addIngredients(cocktail.getIngredients()));

        shoppinglistList.add(shoppinglist1);
        shoppinglistList.add(shoppinglist2);

        return shoppinglistList;
    }
}
