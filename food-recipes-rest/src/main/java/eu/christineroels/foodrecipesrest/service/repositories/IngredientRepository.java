package eu.christineroels.foodrecipesrest.service.repositories;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Page<Ingredient> findAllByIngredientName(String ingredientName, PageRequest pageRequest);


}
