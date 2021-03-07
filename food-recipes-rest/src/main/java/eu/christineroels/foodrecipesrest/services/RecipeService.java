package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface RecipeService {
    boolean containsRecipe(RecipeDto recipe);
    RecipeDto getRecipeById(UUID recipeId);
    RecipeDto updateRecipe(UUID recipeId, RecipeDto recipeDto);
    void deleteRecipe(UUID recipeId);
    RecipeDto saveNewRecipe(RecipeDto recipeDto);

}
