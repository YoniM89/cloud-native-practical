package com.ezgroceries.shoppinglist.internal.cocktail;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCocktailList() throws Exception {
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
