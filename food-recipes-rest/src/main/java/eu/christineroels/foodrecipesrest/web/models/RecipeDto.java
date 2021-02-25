package eu.christineroels.foodrecipesrest.web.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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


    private double totalTime;

    @PositiveOrZero
    private int amountServings;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("lastUpdatedDate")
    private OffsetDateTime lastUpdatedDate;

    public void setTotalTime() {
        this.totalTime = cookingTime+preparationTime;
    }
}
