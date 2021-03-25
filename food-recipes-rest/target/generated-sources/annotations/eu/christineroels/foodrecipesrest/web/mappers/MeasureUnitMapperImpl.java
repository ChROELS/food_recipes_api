package eu.christineroels.foodrecipesrest.web.mappers;

import eu.christineroels.foodrecipesrest.domain.MeasureUnit;
import eu.christineroels.foodrecipesrest.web.models.MeasureUnitDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-25T17:00:51+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9.1 (JetBrains s.r.o.)"
)
@Component
public class MeasureUnitMapperImpl implements MeasureUnitMapper {

    @Override
    public MeasureUnitDto measureUnitToDto(MeasureUnit measureUnit) {
        if ( measureUnit == null ) {
            return null;
        }

        MeasureUnitDto measureUnitDto = new MeasureUnitDto();

        return measureUnitDto;
    }

    @Override
    public MeasureUnit dtoToMeasureUnit(MeasureUnitDto measureUnitDto) {
        if ( measureUnitDto == null ) {
            return null;
        }

        MeasureUnit measureUnit = new MeasureUnit();

        return measureUnit;
    }
}
