package eu.christineroels.foodrecipesrest.service.repositories;

import eu.christineroels.foodrecipesrest.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Page<Recipe> findAllByRecipeName(String recipeName, PageRequest pageRequest);

    Page<Recipe> findAllByIngredient(String recipeIngredient, PageRequest pageRequest);

}
