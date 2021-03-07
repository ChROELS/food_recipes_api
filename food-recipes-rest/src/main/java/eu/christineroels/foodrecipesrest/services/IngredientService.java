package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IngredientService {
    void saveIngredient(IngredientDto ingredientDto);
    IngredientDto getIngredientById(UUID ingredientId);
    IngredientDto updateIngredient(UUID ingredientId, IngredientDto ingredientDto);
    void deleteIngredient(UUID ingredientId);
    List<IngredientDto> getAllByName(String name);
    List<IngredientDto> getAll();
    boolean containsIngredientId(UUID ingredientId);
}
