package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import eu.christineroels.foodrecipesrest.service.repositories.RecipeRepository;
import eu.christineroels.foodrecipesrest.web.mappers.RecipeMapper;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@Tag(value = "serviceTest")
@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeMapper recipeMapper;
    @InjectMocks
    RecipeServiceImpl recipeService;

    private UUID id;
    private RecipeDto recipeDto;
    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        recipeDto = mock(RecipeDto.class);
    }

    @Test
    void getRecipeById() {
      when(recipeRepository.getOne(id)).thenReturn(any(Recipe.class));
      RecipeDto recipe = recipeService.getRecipeById(id);
      verify(recipeRepository).getOne(id);
      verify(recipeMapper).recipeToRecipeDto(recipeRepository.getOne(id));

    }

    @Test
    void updateRecipeNotFound() {
        when(recipeRepository.existsById(id)).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class,()->recipeService.updateRecipe(id,recipeDto));
    }
    @Test
    void updateRecipeFound() {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(id);
        recipe.setRecipeName("");
        recipe.setCookingTime(1.0);
        recipe.setPreparationTime(0.5);
        recipe.setAmountServings(3);
        recipe.setTotalTime();
        recipe.setLastUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        recipe.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        when(recipeRepository.existsById(id)).thenReturn(true);
        when(recipeRepository.getOne(id)).thenReturn(recipe);
        when(recipeMapper.recipeDtoToRecipe(recipeDto)).thenReturn(recipe);
        recipeService.updateRecipe(id,recipeDto);
        verify(recipeRepository).save(recipe);
    }

    @Test
    void deleteRecipeNotFound() {
        when(recipeRepository.existsById(id)).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class,()->recipeService.deleteRecipe(id));

    }
    @Test
    void deleteRecipeFound() {
        when(recipeRepository.existsById(id)).thenReturn(true);
        recipeService.deleteRecipe(id);
        verify(recipeRepository).deleteById(id);

    }

    @Test
    void saveNewRecipe() {
        Recipe recipe = new Recipe();
        when(recipeMapper.recipeDtoToRecipe(recipeDto)).thenReturn(recipe);
        recipeService.saveNewRecipe(recipeDto);
        verify(recipeRepository).save(recipe);
    }
}