package eu.christineroels.foodrecipesrest.web.mappers;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-25T17:00:51+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9.1 (JetBrains s.r.o.)"
)
@Component
public class IngredientMapperImpl implements IngredientMapper {

    @Override
    public IngredientDto ingredientToDto(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        IngredientDto ingredientDto = new IngredientDto();

        return ingredientDto;
    }

    @Override
    public Ingredient DtoToIngredient(IngredientDto ingredientDto) {
        if ( ingredientDto == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        return ingredient;
    }
}
