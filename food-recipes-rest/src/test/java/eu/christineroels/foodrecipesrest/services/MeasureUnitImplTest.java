package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.MeasureUnit;
import eu.christineroels.foodrecipesrest.service.repositories.MeasureUnitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@Tag(value = "serviceTest")
@ExtendWith(MockitoExtension.class)
class MeasureUnitImplTest {
    @Mock
    MeasureUnitRepository repository;
    @InjectMocks
    MeasureUnitImpl measureUnitService;

    private final MeasureUnit measureUnit = new MeasureUnit();
    @BeforeEach
    void setUp() {
        measureUnit.setMeasureUnitId(UUID.randomUUID());
        measureUnit.setShortSymbol("ml");
    }

    @Test
    void getAll() {
        List<MeasureUnit> allAvailable = new ArrayList<>();
        allAvailable.add(measureUnit);
        given(repository.findAll()).willReturn(allAvailable);
        //When
        List<MeasureUnit> allReturned = measureUnitService.getAll();
        //then
        Assertions.assertEquals(1,allReturned.size());
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void saveMeasureUnit() throws Exception {
        //when
        measureUnitService.saveMeasureUnit(measureUnit);
        //then
        verify(repository).save(measureUnit);
    }


    @Test
    void getMeasureUntById() {
        //given
        given(repository.findById(any())).willReturn(java.util.Optional.of(measureUnit));
        //when
        MeasureUnit result = measureUnitService.getMeasureUntById(measureUnit.getMeasureUnitId());
        //then
        Assertions.assertEquals(measureUnit,result);
    }
    @Test
    void getMeasureUntByIdNullReturned() {
        //given
        given(repository.findById(any())).willReturn(Optional.ofNullable(any()));
        //when
        MeasureUnit result = measureUnitService.getMeasureUntById(measureUnit.getMeasureUnitId());
        //then
        assertNull(result);
    }

    @Test
    void updateMeasureUnit() {
        //given
        measureUnit.setMeasureUnitId(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"));
        when(repository.save(any())).thenReturn(measureUnit);
        //when
        MeasureUnit result = measureUnitService.updateMeasureUnit(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"),measureUnit);
        //then
        verify(repository).findById(any());
        verify(repository).deleteById(any());
        verify(repository).save(any());
        Assertions.assertEquals(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"),result.getMeasureUnitId());
    }

    @Test
    void deleteMeasureUnit() {
        measureUnitService.deleteMeasureUnit(UUID.randomUUID());
        verify(repository).deleteById(any());
    }
}