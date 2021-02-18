package eu.christineroels.foodrecipesrest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class MeasureUnit {
    @Id
    private UUID measureUnitId;
    private String shortSymbol;

    public UUID getMeasureUnitId() {
        return measureUnitId;
    }

    public void setMeasureUnitId(UUID measureUnitId) {
        this.measureUnitId = measureUnitId;
    }

    public String getShortSymbol() {
        return shortSymbol;
    }

    public void setShortSymbol(String shortSymbol) {
        this.shortSymbol = shortSymbol;
    }
}
