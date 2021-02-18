package eu.christineroels.foodrecipesrest.web.mappers;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapping a recipe object
 * in the model class to a recipe object in the corresponding domain class
 */
@Component
@Mapper(uses = {DateMapper.class})
public interface RecipeMapper {

    RecipeDto recipeToRecipeDto(Recipe recipe);
    Recipe recipeDtoToRecipe(RecipeDto dto);
}
