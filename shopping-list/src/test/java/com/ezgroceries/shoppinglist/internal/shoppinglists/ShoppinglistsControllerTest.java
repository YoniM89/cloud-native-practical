package com.ezgroceries.shoppinglist.internal.shoppinglists;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.internal.cocktail.Cocktail;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppinglistsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnShoppinglists() throws Exception {
        this.mockMvc.perform(
                get("/shopping-lists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Stephanie's birthday")))
                .andExpect(content().string(containsString("My birthday")));
    }

    @Test
    public void shouldReturnShoppinglist() throws Exception {
        this.mockMvc.perform(
                get("/shopping-lists/23b3d85a-3928-41c0-a533-6538a71e17c4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Dummy name")))
                .andExpect(content().string(containsString("Tequila")))
                .andExpect(content().string(containsString("Salt")));
    }

    @Test
    public void shouldCreateShoppinglist() throws Exception {
        this.mockMvc.perform(
                post("/shopping-lists")
                        .content("{\"name\": \"Stephanie's birthday\"}")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Stephanie's birthday")));
    }

    @Test
    public void shouldAddIngredientsToShoppinglist() throws Exception {
        this.mockMvc.perform(
                post("/shopping-lists/23b3d85a-3928-41c0-a533-6538a71e17c4/cocktails")
                        .content((new Gson()).toJson(mockCocktailList()))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Tequila")))
                .andExpect(content().string(containsString("Triple sec")))
                .andExpect(content().string(containsString("Lime juice")))
                .andExpect(content().string(containsString("Salt")));
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
}
