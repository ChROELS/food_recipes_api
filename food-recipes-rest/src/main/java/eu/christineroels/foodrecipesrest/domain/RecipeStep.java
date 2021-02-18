package eu.christineroels.foodrecipesrest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class RecipeStep {
    @Id
    private UUID recipetStepId;

    private String stepDescription;

    public UUID getRecipetStepId() {
        return recipetStepId;
    }

    public void setRecipetStepId(UUID recipetStepId) {
        this.recipetStepId = recipetStepId;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}
