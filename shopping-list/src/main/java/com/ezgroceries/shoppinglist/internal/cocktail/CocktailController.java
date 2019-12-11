package com.ezgroceries.shoppinglist.internal.cocktail;

import com.ezgroceries.shoppinglist.external.cocktail.CocktailDBClient;
import com.ezgroceries.shoppinglist.external.cocktail.CocktailDBResponse.DrinkResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    @Autowired
    private CocktailDBClient cocktailDBClient;

    @GetMapping
    public List<Cocktail> getCocktails(@RequestParam String search) {
        return getCocktailResources(search);
    }

    private List<Cocktail> getCocktailResources(String search) {
        List<Cocktail> cocktails = new ArrayList<>();
        List<DrinkResource> drinkResources = cocktailDBClient.searchCocktails(search).getDrinks();

        if(drinkResources != null) {
            cocktails = drinkResources.stream()
                    .map(x -> {
                        List<String> ingredients = new ArrayList<>();
                        if(x.getStrIngredient1() != null) {
                            ingredients.add(x.getStrIngredient1());
                        }
                        if(x.getStrIngredient2() != null) {
                            ingredients.add(x.getStrIngredient2());
                        }
                        if(x.getStrIngredient3() != null) {
                            ingredients.add(x.getStrIngredient3());
                        }
                        if(x.getStrIngredient4() != null) {
                            ingredients.add(x.getStrIngredient4());
                        }
                        if(x.getStrIngredient5() != null) {
                            ingredients.add(x.getStrIngredient5());
                        }

                        Cocktail cocktail = new Cocktail(UUID.randomUUID(), x.getStrDrink(), x.getStrGlass(),
                                x.getStrInstructions(), x.getStrDrinkThumb(), ingredients);

                        return cocktail;
                    })
                    .collect(Collectors.toList());
        }

        return cocktails;
    }

    private List<Cocktail> getDummyResources() {
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
}
