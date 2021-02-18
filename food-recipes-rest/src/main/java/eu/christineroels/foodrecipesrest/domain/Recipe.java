package eu.christineroels.foodrecipesrest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * An entity class describing basic infos to create a food recipe:
 * id, name of the recipe,
 * time needed to cook the preparation,
 * time needed to make the preparation,
 * total amount of time to make this dish (cooking time + preparation time),
 * amount of servings (per person),
 * creation and update time
 */
@Entity
public class Recipe {
    @Id
    private UUID recipeId;
    private String recipeName;
    @OneToMany
    private final List<RecipeStep> recipeSteps = new ArrayList<>();
    private double cookingTime;
    private double preparationTime;
    private double totalTime;
    private int amountServings;
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }





    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void addRecipeStep(RecipeStep step){
        recipeSteps.add(step);
    }

    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(double cookingTime) {
        this.cookingTime = cookingTime;
    }

    public double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(double preparationTime) {
        this.preparationTime = preparationTime;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime() {
        this.totalTime = preparationTime+cookingTime;
    }



    public void setAmountServings(int amountServings) {
        this.amountServings = amountServings;
    }

    public int getAmountServings() {
        return amountServings;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }
}
