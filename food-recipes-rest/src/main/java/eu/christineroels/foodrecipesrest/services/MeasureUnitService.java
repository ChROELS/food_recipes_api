package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.domain.MeasureUnit;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import eu.christineroels.foodrecipesrest.web.models.MeasureUnitDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MeasureUnitService {
    //For this service, unlike in other services,
    // I will transfer the conversion data to object to the controller.
    //It is a different way to proceed.
    List<MeasureUnit> getAll();
    void saveMeasureUnit(MeasureUnit measureUnit) throws Exception;
    MeasureUnit getMeasureUntById(UUID id);
    MeasureUnit updateMeasureUnit(UUID id, MeasureUnit measureUnit);
    void deleteMeasureUnit(UUID uuid);



}
