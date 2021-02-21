package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;

import java.util.UUID;

public interface RecipeService {
    RecipeDto getRecipeById(UUID recipeId);
    RecipeDto updateRecipe(UUID recipeId, RecipeDto recipeDto);
    void deleteRecipe(UUID recipeId);
    RecipeDto saveNewRecipe(RecipeDto recipeDto);

}
