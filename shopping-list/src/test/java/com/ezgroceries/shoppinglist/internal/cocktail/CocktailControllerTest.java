package com.ezgroceries.shoppinglist.internal.cocktail;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.external.cocktail.CocktailDBClient;
import com.ezgroceries.shoppinglist.external.cocktail.CocktailDBResponse;
import com.ezgroceries.shoppinglist.external.cocktail.CocktailDBResponse.DrinkResource;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    @Test
    public void shouldReturnCocktailList() throws Exception {
        DrinkResource cocktail = new DrinkResource();
        cocktail.setIdDrink("123");
        cocktail.setStrDrink("Margerita");
        cocktail.setStrDrinkThumb("http://imageresource.jpg");
        cocktail.setStrGlass("Cocktail glass");
        cocktail.setStrIngredient1("Wodka");

        List<DrinkResource> cocktails = new ArrayList<>();
        cocktails.add(cocktail);

        CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
        cocktailDBResponse.setDrinks(cocktails);

        when(cocktailDBClient.searchCocktails(any(String.class))).thenReturn(cocktailDBResponse);

        this.mockMvc.perform(
                get("/cocktails?search=Margerita"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("cocktailId")))
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("glass")));
    }

    @Test
    public void shouldFailOnSearchParam() throws Exception {
        this.mockMvc.perform(
                get("/cocktails"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
