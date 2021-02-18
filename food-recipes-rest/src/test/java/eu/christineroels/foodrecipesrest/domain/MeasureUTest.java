package eu.christineroels.foodrecipesrest.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MeasureUTest {
    @Test
    void getIngredientName(){
        MeasureUnit measureUnit = mock(MeasureUnit.class);
        when(measureUnit.getShortSymbol()).thenReturn("dl");
        Assertions.assertEquals("dl",measureUnit.getShortSymbol());
    }
}