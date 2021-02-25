package eu.christineroels.foodrecipesrest.web.mappers;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapping an ingredient object
 * in the model class to a ingredient object in the corresponding domain class
 */
@Component
@Mapper(uses = {DateMapper.class})
public interface IngredientMapper {

    IngredientDto ingredientToDto(Ingredient ingredient);
    Ingredient DtoToIngredient(IngredientDto ingredientDto);
}
