package eu.christineroels.foodrecipesrest.web.mappers;

import eu.christineroels.foodrecipesrest.domain.MeasureUnit;
import eu.christineroels.foodrecipesrest.web.models.MeasureUnitDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapping a measure unit object
 * in the model class to a measure unit object in the corresponding domain class
 */
@Component
@Mapper(uses = {DateMapper.class})
public interface MeasureUnitMapper {

    MeasureUnitDto measureUnitToDto(MeasureUnit measureUnit);
    MeasureUnit dtoToMeasureUnit(MeasureUnitDto measureUnitDto);
}
