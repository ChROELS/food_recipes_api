package eu.christineroels.foodrecipesrest.web.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    @Null
    private UUID recipeId;

    @NotBlank
    private String recipeName;

    @PositiveOrZero
    private double cookingTime;
    @PositiveOrZero
    private double preparationTime;

    @PositiveOrZero
    private int amountServings;

    private OffsetDateTime createdDate;
    private OffsetDateTime lastUpdatedDate;

}
