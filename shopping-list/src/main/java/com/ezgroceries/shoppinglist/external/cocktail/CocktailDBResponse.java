package com.ezgroceries.shoppinglist.external.cocktail;

import java.util.List;

public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5;

        public String getIdDrink() {
            return idDrink;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public String getStrIngredient4() {
            return strIngredient4;
        }

        public String getStrIngredient5() {
            return strIngredient5;
        }
    }
}
