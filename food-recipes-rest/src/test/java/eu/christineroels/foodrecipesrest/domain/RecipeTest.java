package eu.christineroels.foodrecipesrest.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeTest {
    static Recipe recipe;
    @BeforeAll
    static void setUp(){
        recipe = new Recipe();
    }
    @Test
    void getAmountServings(){
        Recipe recipe = mock(Recipe.class);
        when(recipe.getAmountServings()).thenReturn(4);
        Assertions.assertEquals(4, recipe.getAmountServings());
    }
    @Test
    void getProperties(){
        recipe.setCookingTime(85);
        recipe.setPreparationTime(15);
        recipe.setRecipeName("Chocolate Mousse");
        recipe.setTotalTime();
        Assertions.assertAll("Recipe fields getters",
                ()->Assertions.assertEquals("Chocolate Mousse",recipe.getRecipeName()),
                ()->Assertions.assertEquals(85,recipe.getCookingTime()),
                ()->Assertions.assertEquals(15,recipe.getPreparationTime()),
                ()->Assertions.assertEquals(100,(recipe.getCookingTime()+recipe.getPreparationTime())));
    }

    @DisplayName("Recipe step list")
    @ParameterizedTest(name = "{displayName}" + ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @ValueSource(strings = {"Melt chocolates","Add eggs whites in snow"})
    void addRecipeSteps(String step){
        RecipeStep step1 = new RecipeStep();
        step1.setStepDescription(step);
        recipe.addRecipeStep(step1);
        Assertions.assertNotNull(recipe.getRecipeSteps().get(0));
    }

}