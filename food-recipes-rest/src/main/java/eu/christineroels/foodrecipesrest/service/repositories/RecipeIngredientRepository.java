package eu.christineroels.foodrecipesrest.service.repositories;

import eu.christineroels.foodrecipesrest.domain.RecipeIngredient;
import eu.christineroels.foodrecipesrest.domain.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {



}
