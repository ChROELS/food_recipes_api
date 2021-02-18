package eu.christineroels.foodrecipesrest.domain;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class RecipeIngredient {
    @Id
    private UUID recipeIngredientId;

    private double ingredientAmount;
    @OneToOne
    private MeasureUnit measureUnit;
    @OneToOne
    private Ingredient ingredient;
    @ManyToOne
    private Recipe recipe;
}
