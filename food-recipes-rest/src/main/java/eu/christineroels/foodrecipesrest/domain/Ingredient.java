package eu.christineroels.foodrecipesrest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Ingredient {
    @Id
    private UUID ingredientId;
    private String name;
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(UUID ingredientId) {
        this.ingredientId = ingredientId;
    }


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

}
