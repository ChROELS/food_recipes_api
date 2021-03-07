package eu.christineroels.foodrecipesrest.web.mappers;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-07T11:56:45+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9.1 (JetBrains s.r.o.)"
)
@Component
public class RecipeMapperImpl implements RecipeMapper {

    @Override
    public RecipeDto recipeToRecipeDto(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeDto recipeDto = new RecipeDto();

        return recipeDto;
    }

    @Override
    public Recipe recipeDtoToRecipe(RecipeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Recipe recipe = new Recipe();

        return recipe;
    }
}
