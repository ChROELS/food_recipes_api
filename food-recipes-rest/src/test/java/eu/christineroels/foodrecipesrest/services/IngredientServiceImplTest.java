package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.service.repositories.IngredientRepository;
import eu.christineroels.foodrecipesrest.web.mappers.IngredientMapper;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@Tag(value = "serviceTest")
@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {

    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    IngredientMapper ingredientMapper;

    @InjectMocks
    IngredientServiceImpl ingredientService;

    Ingredient igToUpdate;
    IngredientDto carrot;
    @BeforeEach
    void setUp(){
        UUID id = UUID.randomUUID();
        carrot = new IngredientDto();
        carrot.setIngredientId(id);
        carrot.setName("carrot");
        carrot.setCreatedDate(OffsetDateTime.now());
        carrot.setLastUpdatedDate(OffsetDateTime.now());
        //
        igToUpdate = new Ingredient();
        igToUpdate.setIngredientId(UUID.randomUUID());
        igToUpdate.setName("Bean");
    }

    @Test
    void saveIngredient(){
        //When
        ingredientService.saveIngredient(carrot);
        //Then
        //invokes these methods successfully
        verify(ingredientMapper).DtoToIngredient(any());
        verify(ingredientRepository).save(any());
    }
    @Test
    void getRecipeById() {
        ingredientService.getIngredientById(any());
        //Then
        //invokes these methods successfully
        verify(ingredientMapper).ingredientToDto(any());
        verify(ingredientRepository).getOne(any());
    }
    @Test
    void updateRecipeFound() {
        given(ingredientRepository.existsById(igToUpdate.getIngredientId())).willReturn(true);
        given((ingredientRepository.getOne(igToUpdate.getIngredientId()))).willReturn(igToUpdate);
        given(ingredientMapper.ingredientToDto(igToUpdate)).willReturn(new IngredientDto());
        IngredientDto dto =  ingredientService.updateIngredient(igToUpdate.getIngredientId(),carrot);
        then(ingredientRepository).should().delete(igToUpdate);
        then(ingredientRepository).should().save(igToUpdate);
        then(ingredientMapper).should().ingredientToDto(igToUpdate);
        Assertions.assertNotNull(dto);
    }
    @Test
    void updateRecipeNotFound() {
        given(ingredientRepository.existsById(igToUpdate.getIngredientId())).willReturn(false);
        IngredientDto dto = ingredientService.updateIngredient(igToUpdate.getIngredientId(),carrot);
        then(ingredientRepository).shouldHaveNoMoreInteractions();
        Assertions.assertNull(dto);
    }
    @Test
    void deleteIngredientFound() {
        given(ingredientRepository.existsById(igToUpdate.getIngredientId())).willReturn(true);
        ingredientService.deleteIngredient(igToUpdate.getIngredientId());
        then(ingredientRepository).should().deleteById(igToUpdate.getIngredientId());
    }
    @Test
    void deleteIngredientNotFound() {
        given(ingredientRepository.existsById(igToUpdate.getIngredientId())).willReturn(false);
        ingredientService.deleteIngredient(igToUpdate.getIngredientId());
        then(ingredientRepository).shouldHaveNoMoreInteractions();
    }
    @Test
    void getAllByName() {
        given(ingredientRepository.findAll(Sort.by("name"))).willReturn(List.of(new Ingredient[]
        {igToUpdate,igToUpdate}));
        List<IngredientDto> result = ingredientService.getAllByName(any());
        then(ingredientRepository).shouldHaveNoMoreInteractions();
        Assertions.assertNotNull(result);
    }
}
