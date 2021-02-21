package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.service.repositories.RecipeRepository;
import eu.christineroels.foodrecipesrest.web.mappers.RecipeMapper;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper){
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }
    @Override
    public RecipeDto getRecipeById(UUID recipeId) {
        Recipe recipeReturned = recipeRepository.getOne(recipeId);
        return recipeMapper.recipeToRecipeDto(recipeReturned);
    }

    @Override
    public RecipeDto updateRecipe(UUID recipeId, RecipeDto recipeDto) {
        if(recipeRepository.existsById(recipeId)){
            recipeRepository.deleteById(recipeId);
            Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
            recipeRepository.save(recipe);
            return recipeDto;
        }else{
            //To Do
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public void deleteRecipe(UUID recipeId) {
        if(recipeRepository.existsById(recipeId)) {
            recipeRepository.deleteById(recipeId);
        }else{
            //To Do
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public RecipeDto saveNewRecipe(RecipeDto recipeDto) {
       Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
       return recipeMapper.recipeToRecipeDto(recipeRepository.save(recipe));
    }
}
