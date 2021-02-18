package eu.christineroels.foodrecipesrest.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.christineroels.foodrecipesrest.services.RecipeService;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest
@ComponentScan(basePackages ={"eu.christineroels.foodrecipesrest.web.mappers"})
class RecipeControllerTest {

    @MockBean
    RecipeService recipeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    RecipeDto validRecipe;

    @BeforeEach
    public void setUp(){
        validRecipe = new RecipeDto();
        validRecipe.setRecipeId(UUID.randomUUID());
        validRecipe.setAmountServings(4);
        validRecipe.setCookingTime(143);
        validRecipe.setPreparationTime(30);
    }

    @Test
    public void getRecipe(){

    }

    @Test
    public void updateRecipe(){}
    @Test
    public void createRecipe(){}
    @Test
    public void deleteRecipe(){}

}