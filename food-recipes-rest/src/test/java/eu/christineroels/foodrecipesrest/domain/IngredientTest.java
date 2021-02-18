package eu.christineroels.foodrecipesrest.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IngredientTest {

    @Test
    void getIngredientName(){
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getName()).thenReturn("Flour");
        Assertions.assertEquals("Flour",ingredient.getName());
    }

}